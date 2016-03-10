package ru.pavel2107.vitasoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.pavel2107.vitasoft.model.Category;
import ru.pavel2107.vitasoft.service.CategoryService;

import java.util.List;

/**
 * Created by admin on 08.03.2016.
 */
@RestController
@RequestMapping( value = "/rest/category")
@Transactional( readOnly = true)
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping( method = RequestMethod.GET)
    public List<Category> getAll(){
        return service.getAll();
    }



}
