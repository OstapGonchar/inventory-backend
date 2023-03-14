package com.inventory;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("Ostap");
        list.add("Taras");
        list.add("Sergii");
        System.out.println(list);

        var linkedList = new LinkedList<String>();
        linkedList.add("Ostap");
        linkedList.add("Taras");
        linkedList.add("Sergii");
        System.out.println(linkedList);

        System.out.println("Now with push instead");
        linkedList.clear();
        linkedList.push("Ostap");
        linkedList.push("Taras");
        linkedList.push("Sergii");
        System.out.println(linkedList);

        System.out.println("Transformed list 1");
        System.out.println(transformToLastCharacterUpperCaseVersionOne(list));

        System.out.println("Transformed list 2");
        System.out.println(transformToLastCharacterUpperCaseVersionTwo(list));

        System.out.println("Transformed list 3");
        System.out.println(transformToLastCharacterUpperCaseVersionThree(list));

        System.out.println(transformToUnderscore("ostapGonchar"));
    }

    private static List<String> transformToLastCharacterUpperCaseVersionOne(List<String> elements) {
        var resultedList = new ArrayList<String>();
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            String firstCharacters = element.substring(0, element.length() - 1);
            String lastCharacter = element.substring(element.length() - 1).toUpperCase();
            resultedList.add(firstCharacters.concat(lastCharacter));
        }
        return resultedList;
    }

    private static List<String> transformToLastCharacterUpperCaseVersionTwo(List<String> elements) {
        var resultList = new ArrayList<String>();
        for (String element : elements) {
            String firstCharacters = element.substring(0, element.length() - 1);
            String lastCharacter = element.substring(element.length() - 1).toUpperCase();
            resultList.add(firstCharacters.concat(lastCharacter));
        }
        return resultList;
    }

    private static List<String> transformToLastCharacterUpperCaseVersionThree(List<String> elements) {
        return elements.stream()
                .filter(element -> element.length() == 5)
                .map(element -> {
                    String firstCharacters = element.substring(0, element.length() - 1);
                    String lastCharacter = element.substring(element.length() - 1).toUpperCase();
                    return firstCharacters.concat(lastCharacter);
                })
                .map(koza -> koza + 1)
                .collect(Collectors.toList());
    }


    //TODO: Change to stream API
    public static String transformToUnderscore(@RequestParam(name = "input") String camelCase) {
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
