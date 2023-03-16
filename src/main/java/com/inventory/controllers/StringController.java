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
   /* public String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
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
    }*/

   /* public String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
        return camelCase.chars() // Step 1: Get a stream of characters from the input string
                .mapToObj(ch -> Character.isUpperCase((char) ch) ? "_" + Character.toLowerCase((char) ch) : Character.toString((char) ch)) // Step 2: For each character, map it to either itself or a string containing the lowercase version of the character preceded by an underscore, depending on whether it is uppercase or not.
                .collect(Collectors.joining()); Step 3: Join the resulting strings together into a single string.
    }*/

        public String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
            // Regular Expression
            String regex = "([a-z])([A-Z]+)";
            // Replacement string
            String replacement = "$1_$2";
           // Replace the given regex
            // with replacement string
            // and convert it to lower case.
            camelCase = camelCase
                    .replaceAll(
                            regex, replacement)
                    .toLowerCase();

            // return string
            return camelCase;
        }
}


