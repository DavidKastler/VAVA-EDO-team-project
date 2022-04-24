package vava.edo.Handlers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHandler {

    public static List<Object> searchInList(List<? extends Object> searchedList, String searchedFieldName, String searchedValue) {

        if (searchedValue.equals("")) searchedValue = ".*";

        List<Object> foundValues = new ArrayList<Object>();
        Pattern pattern = null;

        try {
             pattern = Pattern.compile(searchedValue);
        } catch (Exception e) {
            return foundValues;
        }

        Matcher matcher;

        for (Object listEntry : searchedList) {

            try {
                Field searchedField = listEntry.getClass().getDeclaredField(searchedFieldName);
                searchedField.setAccessible(true);
                Object value = searchedField.get(listEntry);
                matcher = pattern.matcher(value.toString());

                if (matcher.find()) {
                    foundValues.add(listEntry);
                }

            } catch (Exception e) {
                System.out.println("error");
            }

        }

        return foundValues;

    }
}
