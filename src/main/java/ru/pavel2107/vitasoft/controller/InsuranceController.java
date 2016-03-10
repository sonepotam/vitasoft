package ru.pavel2107.vitasoft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.pavel2107.vitasoft.model.Category;
import ru.pavel2107.vitasoft.model.Driver;
import ru.pavel2107.vitasoft.model.Insurance;
import ru.pavel2107.vitasoft.model.SEX;
import ru.pavel2107.vitasoft.service.CategoryService;
import ru.pavel2107.vitasoft.service.DriverService;
import ru.pavel2107.vitasoft.service.InsuranceService;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
@RestController
@RequestMapping( value = "/rest/ins")
@Transactional( readOnly = true)
public class InsuranceController {
    @Autowired
    InsuranceService service;

    @Autowired
    DriverService driverService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping( method = RequestMethod.GET)
    public List<Insurance> getAll(){
        return service.getAll();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public Insurance get(@PathVariable("id") Integer id){
        Insurance insurance = service.get( id);
        return insurance;
    }

    @RequestMapping( method = RequestMethod.POST)
    public void updateOrCreate(Insurance insurance, BindingResult result, SessionStatus status){
        status.setComplete();
        service.save( insurance);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
       service.delete( id);
       return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping( value = "/deldrv/{insID}/{driverID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDriver( @PathVariable( "insID") Integer id, @PathVariable( "driverID") Integer driverID){
        service.deleteDriver( id, driverID);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping( value ="/upddrv", method = RequestMethod.POST)
    public Driver updateDriver(
            @RequestParam("id") Integer ID, @RequestParam( "driverid") Integer driverID,
            @RequestParam("surname") String surname, @RequestParam("name") String name,
            @RequestParam("secondname") String secondname, @RequestParam("sex") String sex,
            @RequestParam("bdate") String bdate, @RequestParam("category") String cat
            ){

        LocalDate birthDate = LocalDate.parse( bdate, DateTimeFormatter.ofPattern( "dd.MM.yyyy"));
        Category category = categoryService.get( cat);
        Driver drv = new Driver(driverID, surname, name, secondname, SEX.valueOf(sex), birthDate, category);
        drv = service.updateDriver( ID, drv);
        return drv;
    }

    @RequestMapping( value ="/insdrv", method = RequestMethod.POST)
    public Driver updateDriver(
            @RequestParam("id") Integer ID, @RequestParam( "driverid") Integer driverID){

        Driver drv = driverService.get( driverID);
        drv = service.updateDriver( ID, drv);
        return drv;
    }


}

