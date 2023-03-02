package com.inventory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/string")
public class StringController {

    /**
     *  Should convert camelCase to underscore lowercase convention
     *  localhost:8080/api/string/to-underscore?input=ostapGonchar
     *
     * @param camelCase e.g. ostapGoncharKoza
     * @return ostap_gonchar_koza
     */
    @GetMapping("/to-underscore")
    public String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
        //TODO implement
        return "";
    }
}
