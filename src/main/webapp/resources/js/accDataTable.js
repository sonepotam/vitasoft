var refDataTable;


var ajaxUrl = "http://localhost:8080/vitasoft/rest/";
var insuranceID = "1";

var failedNote;

var sDeleteBtn = "Delete";
var sEditBtn   = "Edit";

function updateTable() {
        $.ajax({
            type: "GET",
            url: ajaxUrl + "ins/" + insuranceID,
            success: function (data) {
                console.log( "updateTable.ajax.GET");
                updateTableByData(data);
            }
        });
    return false;
}



function editRow( id){
    $.ajax(
        {
            type: "GET",
            url : ajaxUrl + "driver/" + id,
            success: function( data){
                $("#surname"   ).val( data.surname);
                $("#name"      ).val( data.name);
                $("#secondname").val( data.secondname);
                $("#category"  ).val( data.category.type);
                $("#sex"       ).val( data.sex);
                $("#bdate"     ).val( (new Date( data.birthDate[0], data.birthDate[1] - 1, data.birthDate[2])).format( "dd.mm.yyyy") );
                $("#driverid"  ).val( data.id);
                setage();
                console.log( "editRow started");
            }
        }
    );

}


function deleteRow(id) {
        $.ajax({
            url: ajaxUrl + "ins/deldrv/" + insuranceID + "/" + id,
            type: 'DELETE',
            success: function () {
                updateTable();
                console.log( "deleteRow.ajax");
                successNoty('Deleted');
            }
        });
}




function updateTableByData(data) {
    refDataTable.clear();
    if( data != "") {
        for (var i = 0; i < data.driverList.length; i++) {
            refDataTable.row.add([
                data.driverList[i].id,
                data.driverList[i].surname + " " + data.driverList[i].name + " " + data.driverList[i].secondname,
                (  new Date( data.driverList[i].birthDate[0], data.driverList[i].birthDate[1] -1, data.driverList[i].birthDate[2])).format("dd.mm.yyyy"),
                ( (new Date()).getFullYear() - data.driverList[i].birthDate[0]),
                data.driverList[i].sex == "MALE" ? "М" : "Ж",
                data.driverList[i].category.type,
                sDeleteBtn,
                sEditBtn
            ])
        }
    }
    refDataTable.draw();
}


function saveDriver() {
    var driver   = {}
    var category = {}

    if( !isValidDate( $( "#bdate").val())) {
        mfailNoty( "Дата " +  $( "#bdate").val() + " введена некорректно");
        return;
    }


    driver.name       = $( "#name").val();
    driver.surname    = $( "#surname").val();
    driver.secondname = $("#secondname").val();
    driver.sex        = $("#sex").val();

    category.type = $("#category").val();
    category.name = "Категория " + category.type;
    driver.category = category;

    driver.id         = $("#driverid"  ).val();

    if( driver.id == ""){
        mfailNoty( "Здесь можно только редактировать данные. Новых водителей запрещено заносить в базу.");
        return;
    }


    $.ajax({
        type: "POST",
        url: ajaxUrl + "ins/upddrv",
        data: {
            "id"        : insuranceID,
            "driverid"  : $("#driverid"  ).val(),
            "surname"   : $( "#surname").val(),
            "name"      : $( "#name").val(),
            "secondname": $( "#secondname").val(),
            "sex"       : $( "#sex").val(),
            "bdate"     : $( "#bdate").val(),
            "category"  : $( "#category").val()
        },
        success: function () {

            updateTable();
            successNoty('Данные сохранены');

            $( "#bdate").val( "");
            $( "#name").val("");
            $( "#surname").val("");
            $("#secondname").val("");
            $("#sex").val("");
            $("#category").val("");
            $("#driverid"  ).val("");

            console.log( "save.standalone");
        },
        error : function(e) {
            mfailNoty( "Ошибка при сохранении данных");
            console.log("ERROR: ", e.responseText);
        },
        done : function(e) {
            successNoty('Готово');
        }
    });
}


function setage(){
  var begDate = $( "#bdate").val();
  var curDate = new Date();
  var age = "???";
  if( isValidDate( begDate)){
      begDate = begDate.split( ".");
      age = curDate.getFullYear() - begDate[2];
  } else {
      mfailNoty( "Дата рождения введена некорректно");
  }
  $( "#age").val( age);
}

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        var iban = row[0];
        return '<a class="btn btn-xs btn-primary" onclick="editRow(\'' + iban + '\');">Edit</a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        var iban = row[0];
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(\'' + iban +'\');">Delete</a>';
    }
    return data;
}


function isValidDate(val)
{
    var val_r = val.split(".");
    var curDate = new Date(val_r[2], val_r[1]-1, val_r[0]);
    return curDate != 'Invalid Date';
}

$(document).ready(function() {


    refDataTable = $('#datatable').DataTable(
        {
            stateSave: true,
            "columns": [
                { // id
                    "searchable": false,
                    "orderable":  false,
                    "visible"  :  true
                },
                {  // fio
                    "searchable": true,
                    "orderable": true
                },
                {   // bDate
                    "searchable": true,
                    "orderable": true
                },
                {   // age
                    "searchable": true,
                    "orderable": true
                },
                {   // sex
                    "searchable": true,
                    "orderable": true
                },
                {  // categ
                    "searchable": true,
                    "orderable": true
                },
                {   // deleteBtn
                    "searchable": false,
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                },
                {   // editBtn
                    "searchable": false,
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                }
            ],
            "initComplete": function(settings, json) {
                $(document).ajaxError(function (event, jqXHR, options, jsExc) {
                    failNoty(event, jqXHR, options, jsExc);
                });
            }
        }
    );





    $('#select').select2({
        ajax: {
            url: ajaxUrl + "/driver/fio",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    fio: params.term,
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var drivers = [];
                if( data != "") {
                    for (var i = 0; i < data.length; i++) {
                        drivers[drivers.length] = {
                            id: data[i].id,
                            text: data[i].surname + " " + data[i].name + " " + data[i].secondname + " - " +
                            (new Date(data[i].birthDate[0], data[i].birthDate[1] - 1, data[i].birthDate[2])).format("dd.mm.yyyy")
                        };
                    }
                }else{
                    mfailNoty( "Данных не найдено");
                }


                return {
                    results: drivers,
                    pagination: {
                        more: (params.page * 15) < data.length
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; },
        minimumInputLength: 1
    });

    $('#select').on("select2:select",
        function (e) {
            var driverID = $("#select").val();
            $.ajax({
                type: "POST",
                url: ajaxUrl + "ins/insdrv",
                data: {
                    "id"        : insuranceID,
                    "driverid"  : driverID
                },
                success: function () {
                    updateTable();
                    successNoty('Данные сохранены');
                    console.log( "save.standalone");
                },
                error : function(e) {
                    mfailNoty(e.responseText);
                    console.log("ERROR: ", e.responseText);
                },
                done : function(e) {
                    successNoty('Готово');
                }
            });

        });


    $(function () {
        $('#bdatepicker').datetimepicker({pickTime: false, language: 'ru' });
    });


    //
    // заполняем таблицу с данными
    //
    $.ajax
    ({
        type : "GET",
        url  :  ajaxUrl + "ins/" + insuranceID,
        data : "",
        async: false,
        success: function( data )
        {
            updateTableByData( data);
        }
    });

    //
    // заполняем категории прав
    //
    $.ajax
    ({
        type : "GET",
        url  :  ajaxUrl + "category",
        data : "",
        async: false,
        success: function( data )
        {
           for( var i = 0; i < data.length; i++){
               $('#category').append($("<option />").val(data[i].type).text(data[i].name));
            }
        }
    });


// начало работы с autocomplete
    var startSearch = false;
    var driversFIO  = [];
    var driversIDs  =[];

    $("#autocomplete").autocomplete({
        source: function (request, response) {
            if( !startSearch ) return;
            startSearch = false;
            driversFIO  = [];
            driversIDs  = [];
            $.ajax
            ({
                type : "GET",
                url: ajaxUrl + "/driver/fio",
                data : { fio: $("#autocomplete").val() },
                async: false,
                success: function( data )
                {
                   for( var i = 0; i < data.length; i++){
                       driversIDs[ driversIDs.length] = data[i].id;
                       driversFIO[ driversFIO.length] =
                           data[i].surname + " " + data[i].name + " " + data[i].secondname + " - " +
                           (new Date(data[i].birthDate[0], data[i].birthDate[1] - 1, data[i].birthDate[2])).format("dd.mm.yyyy");

                   }
                   if( data.length == 0){ mfailNoty( "Данных не найдено");}
                }
            });

            response(driversFIO);

        },
        minLength: 0,
        select: function( ev, ui){
            var fio = ui.item.label;
            var driverID = -1;
            for( var i = 0; i < driversFIO.length; i++){
               if( driversFIO[i] == fio){
                   driverID = driversIDs[ i];
                   break;
               }
            }
            if( driverID == - 1){
                mfailNoty( "Не найден " + fio);
                return;
            }
            $.ajax({
                type: "POST",
                url: ajaxUrl + "ins/insdrv",
                data: {
                    "id"        : insuranceID,
                    "driverid"  : driverID
                },
                success: function () {
                    updateTable();
                    successNoty('Данные сохранены');
                    console.log( "save.standalone");
                },
                error : function(e) {
                    mfailNoty(e.responseText);
                    console.log("ERROR: ", e.responseText);
                },
                done : function(e) {
                    successNoty('Готово');
                }
            });

        }
    }).keypress(function(e) {
            if(e.keyCode == 13)
            {
                startSearch = true;
                $(this).autocomplete('search');
            }
        }
    );

// окончание работы с autocomplete



});



<!-- Noty -->
function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: 'Failed: ' + errorInfo,
        type: 'error',
        layout: 'bottomRight'
    });
}


function mfailNoty( message) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + message,
        type: 'error',
        layout: 'bottomRight'
    });
}

