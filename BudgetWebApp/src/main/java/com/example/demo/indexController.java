package com.example.demo;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index() {

        return "index";
    }
}
