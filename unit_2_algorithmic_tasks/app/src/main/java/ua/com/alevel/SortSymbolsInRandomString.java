package ua.com.alevel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SortSymbolsInRandomString {
    public static void sort(String string) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c : string.toCharArray()) {
            if (Character.isLetter(c)) {
                map.putIfAbsent(c, 0);
                map.put(c, map.get(c) + 1);
            }
        }
        System.out.println("OUTPUT: \n");
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        for (int i = 0; i < map.size(); ++i) {
            if (iterator.hasNext()) {
                Map.Entry<Character, Integer> entry = iterator.next();
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

        }
    }
}
