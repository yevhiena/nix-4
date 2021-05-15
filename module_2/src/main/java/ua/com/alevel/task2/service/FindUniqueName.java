package ua.com.alevel.task2.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindUniqueName {

    public String findUniqueName(List<String> names) throws IOException {
        Map<String, String> uniqueNames = new LinkedHashMap<>();
        Map<String, String> duplicateNames = new LinkedHashMap<>();

        for (String name : names) {
            if(uniqueNames.containsKey(name)){
                uniqueNames.remove(name);
                duplicateNames.put(name, name);
            }
            else if(!uniqueNames.containsKey(name) && !duplicateNames.containsKey(name)){
                uniqueNames.put(name, name);
            }
        }
        String unique;
        if(uniqueNames.size()>0){
        unique = uniqueNames.keySet().iterator().next();
        return unique;
        }
        else return null;

    }
}
