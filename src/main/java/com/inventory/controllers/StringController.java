package com.inventory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/string")
public class StringController {

    /**
     * Should convert camelCase to underscore lowercase convention
     * localhost:8080/api/string/to-underscore?input=ostapGonchar
     *
     * @param camelCase e.g. ostapGoncharKoza
     * @return ostap_gonchar_koza
     */
    @GetMapping("/to-underscore")
    public String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
        String result = "";
        char c = camelCase.charAt(0);
        result = result + Character.toLowerCase(c);
        for (int i = 1; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = result + '_' + Character.toLowerCase(ch);
            } else {
                result = result + ch;
            }
        }
        return result;
    }
}
