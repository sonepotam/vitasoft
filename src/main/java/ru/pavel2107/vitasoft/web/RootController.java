package ru.pavel2107.vitasoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by lenovo on 12.12.2015.
 */
@Controller
public class RootController {

    @RequestMapping( value = "/", method = RequestMethod.GET)
    public String root()
    {
        return "index";
    }


}
