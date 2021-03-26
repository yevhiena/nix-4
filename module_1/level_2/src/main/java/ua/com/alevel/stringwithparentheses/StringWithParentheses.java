package ua.com.alevel.stringwithparentheses;


import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringWithParentheses {
    public boolean isValidString(String string){
        Stack<Character> openBracketsStack = new Stack<>();
        StringBuilder bracketsString = new StringBuilder();

        HashMap<Character,Character> sample = new HashMap<>();
        sample.put(')', '(');
        sample.put('}', '{');
        sample.put(']', '[');

        Pattern pattern = Pattern.compile("[\\[\\](){}]");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            bracketsString.append(matcher.group());
        }
        if(string.length() == 0) return true;
        else if(bracketsString.length() == 0) return false;
        for (Character c: bracketsString.toString().toCharArray()){
            if(sample.containsValue(c)){
                openBracketsStack.push(c);
            }
            else if(!openBracketsStack.isEmpty() && openBracketsStack.peek() == sample.get(c) ){
                openBracketsStack.pop();
            }
            else return false;
        }

        return true;
    }
}