package ua.com.alevel.util;

import ua.com.alevel.annotation.CsvCell;
import ua.com.alevel.csvtable.CsvTable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvMapper {

    public static <T> List<T> initialize(String csvTableResourcesPath, Class<T> requiredClass) throws IllegalAccessException {

        CsvTable tableData = getCsvData(csvTableResourcesPath);
        List<T> objects = new ArrayList<>();
        for (int i = 1; i < tableData.size() ; i++) {
            T object;
            try {
                Constructor<T> constructor = requiredClass.getConstructor();
                object = constructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }

            Field[] fields = object.getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(CsvCell.class)) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    try {
                        if (type == int.class || type == Integer.class) {
                            field.set(object, Integer.parseInt(tableData.getCell(i, field.getAnnotation(CsvCell.class).value())));
                        } else if (type == double.class || type == Double.class) {
                            field.set(object, Double.parseDouble(tableData.getCell(i, field.getAnnotation(CsvCell.class).value())));
                        }else if (type == String.class) {
                            field.set(object, tableData.getCell(i, field.getAnnotation(CsvCell.class).value()));
                        } else if (type == Boolean.class || type == boolean.class) {
                            field.set(object, Boolean.parseBoolean(tableData.getCell(i, field.getAnnotation(CsvCell.class).value())));
                        } else if (type.isEnum()) {
                            field.set(object, Enum.valueOf((Class<Enum>) field.getType(), tableData.getCell(i, field.getAnnotation(CsvCell.class).value())));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException();
                    }
                }
            }
            objects.add(object);
        }
        return objects;
    }

    private static CsvTable getCsvData(String path) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            return CsvParser.readFromCsv(inputStream);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
