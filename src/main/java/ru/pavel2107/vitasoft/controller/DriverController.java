package ru.pavel2107.vitasoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.service.DriverService;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */


@RestController
@RequestMapping( value = "/rest/driver")
@Transactional( readOnly = true)
public class DriverController {

    @Autowired
    DriverService service;

    @RequestMapping( method = RequestMethod.GET)
    public List<Driver> getAll(){
        return service.getAll();
    }

    @RequestMapping( value = "/{driver}", method = RequestMethod.GET)
    public Driver get(@PathVariable( "driver") Integer driverID){
        Driver driver = service.get( driverID);
        return driver;
    }


    @RequestMapping( value = "/fio", method = RequestMethod.GET)
    public List<Driver> getByFio(@RequestParam( "fio") String fio){
        List<Driver> drivers = service.getByName( fio);
        return drivers;
    }

    @RequestMapping( value = "/{driver}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete( @PathVariable("driver") Integer driverID){
        // TODO : проверить наличие водителя в полисах. В рамках этого приложения нет смысла
        service.delete( driverID);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrCreate(Driver driver, BindingResult result, SessionStatus status){
        status.setComplete();
        service.save( driver);
    }



}
