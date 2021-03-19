package ua.com.alevel.impl;




public class ReverseStringUtil {

    public static String reverse(String src) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] sarr = src.split("\\b");
        for (String s : sarr) {
            if(Character.isLetter(s.charAt(0))){
                for(int i = s.length() - 1; i>=0; i--){
                    stringBuilder.append(s.charAt(i));
                }
            }
            else stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }


    public static String reverse(String src, String dest){
        StringBuilder stringBuilder = new StringBuilder(src);

        int indexOfSubString = stringBuilder.indexOf(dest);
        stringBuilder.replace(indexOfSubString, indexOfSubString + dest.length(), reverse(dest));

        return stringBuilder.toString();
    }


    public static String reverse(String src, int firstIndex, int lastIndex) {
        StringBuilder stringBuilder = new StringBuilder(src);
        char[] array = new char [lastIndex - firstIndex + 1];
        stringBuilder.getChars(firstIndex, lastIndex + 1, array, 0);
        stringBuilder.replace(firstIndex, lastIndex + 1 , reverse(String.copyValueOf(array)));

        return stringBuilder.toString();
    }
}
