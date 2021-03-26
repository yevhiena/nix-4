package ua.com.alevel.digitsarray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DigitsArray {

    public int getUniqueSymbols(String stringOfDigits){
        String s = stringOfDigits.replaceAll(" ", "");
        char[] chars = s.toCharArray();
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : chars) {
            uniqueChars.add(c);
        }
        int numberOfUniqueSymbols = uniqueChars.size();
        return numberOfUniqueSymbols;
    }
}
