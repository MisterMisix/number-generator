package com.example.gaishnik.controller;

import com.example.gaishnik.logic.AutoNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/number")
public class ControllerGaishnik {
    AutoNumber number = new AutoNumber();
    @GetMapping("/random")
    public String random(){
        return number.getRandom();
    }
    @GetMapping("/next")
    public String next(){
        return number.getNext();
    }


}
