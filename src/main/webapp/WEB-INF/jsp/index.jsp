<%@ page import="java.util.List" %>
<%@ page import="org.springframework.data.domain.Page" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<%@ page session="false" %>
<%@ page language="java" session="false"%>




<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="content-type">



<!-- jQuery -->
<!-- <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
-->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>


<script src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>


<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>



<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


<!-- DataTable -->
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">

<!-- Noty -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.3.7/packaged/jquery.noty.packaged.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">

<!-- vitasoft -->
<script src="<c:url value="/resources/js/accDataTable.js" />"></script>



  <!-- Подключить скрипт moment-with-locales.min.js для работы с датами -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment-with-locales.min.js"></script>



  <!-- Подключить скрипт виджета "Bootstrap datetimepicker" -->
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />">
  <script src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js" />"></script>

  <script src="<c:url value="/resources/js/date.format.js" />"></script>

<!-- select2 -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/js/select2.min.js"></script>

 <!-- dataTable -->
 

</head>
<body>


<div class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
				<span class="sr-only">Открыть навигацию</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand navbar-right" href="http://www.github.com/sonepotam">GitHub</a>
		</div>

	</div>
</div>

<h2>Vitasoft TEST Application</h2>
<hr>

<div class="jumbotron">
	<div class="container">
		<div class="shadow">
            <!-- panel -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Редактирование договора страхования</h3>
                </div>
                <div class="panel-body">
                <!-- panel -->

                <div class="view-box">
				<table class="table table-striped table-bordered" id="datatable">
					<thead>
					    <th>id</th>
						<th>ФИО</th>
					    <th>Дата рождения</th>
					    <th>Возраст</th>
					    <th>Пол</th>
					    <th>Категория</th>
					    <th></th>
					    <th></th>
                    </thead>
					<tbody>

					  <tr>
						  <td>1</td>
						  <td>Иванов Иван Иванович</td>
						  <td>12.12.1990</td>
						  <td>28</td>
						  <td>Муж</td>
                          <td>A</td>
						  <td>Delete</td>
						  <td>Edit</td>
					  </tr>

					</tbody>
				</table>
                </div> <!-- panel body -->
            </div>
			</div> <!-- panel -->
		</div>
		<!-- можно попробовать поставить панель сюда -->
        <div class="panel panel-primary">
           <div class="panel-heading">
              <h3 class="panel-title">Добавить водителя в список лиц, допущенных к управлению</h3>
           </div>       
           <div class="panel-body">

               <!--
               <div class="form-primary">
                   <label for="surname" class="col-sm-2 control-label">ФИО</label>
                   <div class="col-sm-10">
                       <select id ="select" class="js-data-example-ajax col-sm-10">
                           <option value="-1" selected="selected">Введите ФИО</option>
                       </select>
                   </div>
               </div>
               -->

               <div class="form-primary">
                   <label for="autocomplete" class="col-sm-2 control-label">ФИО</label>
                   <div class="col-sm-10">
                      <input id ="autocomplete" class="form-control"/>
                   </div>

               </div>

            </div>   
        </div>

	
		</div>
</div>

	<!-- панель для редактирования -->
	<div class="container">
      <form class="form-horizontal" method="post" id="editForm">
        <div class="panel panel-primary">
           <div class="panel-heading">
              <h3 class="panel-title">Редактирование данных о водителе</h3>
              <input id="driverid" type="hidden"/>
           </div>       
           <div class="panel-body">
              
               <div class="form-group">
                   <label for="surname" class="col-sm-2 control-label">Фамилия</label>
                   <div class="col-sm-10">
                      <input id="surname" type="text" class="form-control" placeholder="Фамилия" aria-describedby="basic-addon1">                       
                   </div>
               </div>
               
               <div class="form-group">
                   <label for="name" class="col-sm-2 control-label">Имя</label>
                   <div class="col-sm-10">
                      <input id="name" type="text" class="form-control" placeholder="Имя" aria-describedby="basic-addon2">                       
                   </div>
               </div>
               
               <div class="form-group">
                   <label for="secondname" class="col-sm-2 control-label">Отчество</label>
                   <div class="col-sm-10">
                      <input id="secondname" type="text" class="form-control" placeholder="Отчество" aria-describedby="basic-addon3">
                   </div>
               </div>

               <div class="form-group">
                   <label for="age" class="col-sm-2 control-label">Возраст</label>
                   <div class="col-sm-10">
                       <input id="age" type="text" class="form-control" aria-describedby="basic-addon4" disabled>
                   </div>
               </div>


               <div class="form-group">
                   <label for="category" class="col-sm-2 control-label">Категория прав</label>
                   <div class="col-sm-10">
                      <select id="category" class="form-control"></select>
                   </div>
               </div>
               
               <div class="form-group">
                   <label for="surname" class="col-sm-2 control-label">Пол</label>
                   <div class="col-sm-10">
                      <select id= "sex" class="form-control">
                         <option value="MALE"  >Муж</option>
                         <option value="FEMALE">Жен</option>
                      </select>                   
                   </div>
               </div>

               <div class="input-group date" id="bdatepicker" onchange="setage()">
                   <label for="surname" class="col-sm-2 control-label">Дата рождения</label>
                   <div class="col-sm-10">
                      <input id ="bdate" type="text" class="form-control"  />
                      <span class="input-group-addon">
                         <span class="glyphicon-calendar glyphicon"/>
                       </span>
                   </div>               
               </div>


           </div>
           
           <div class="panel-footer">               
              <div class="form-group">
       	        <div class="col-xs-9">
	 	            <button id="drvsubmit" type="button" class="btn btn-primary" onclick="saveDriver()">Сохранить</button>
		        </div>
              </div>                              
           </div>
        </div>	    
       </form> 
	</div>

	



 


</body>



</html>
