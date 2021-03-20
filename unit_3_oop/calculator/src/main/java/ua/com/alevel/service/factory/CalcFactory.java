package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.CalcSrvice;


import java.util.Map;
import java.util.Set;

public class CalcFactory {

    private static CalcFactory instance;
    private Reflections reflections;
    private Set<Class<? extends CalcSrvice>> calcSrvices;

    private CalcFactory(){
        reflections = new Reflections("ua.com.alevel");
        calcSrvices = reflections.getSubTypesOf(CalcSrvice.class);
    }

    public static CalcFactory getInstance(){
        if(instance == null){
            instance = new CalcFactory();
        }
        return instance;
    }

    public CalcSrvice getCalcSrvice(){
        for (Class<? extends CalcSrvice> calcSrvice : calcSrvices) {
            if(!calcSrvice.isAnnotationPresent(Deprecated.class)){
                try {
                    return calcSrvice.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Exception");
                }
            }
        }
        throw new RuntimeException("Exception");
    }
}
