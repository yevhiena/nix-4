package ua.com.alevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SumOfNumbersInRandomString {

    public static int sumOfNumbers(String string){
        int sum = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }
        return sum;
    }

}
