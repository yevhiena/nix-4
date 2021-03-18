package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.ConsoleService;


import java.util.Map;
import java.util.Set;

public class ConsoleFactory{

    private static ConsoleFactory instance;
    private Reflections reflections;
    private Set<Class<? extends ConsoleService>> consoleSrvices;
    //private Map<Class<? extends CalcSrvice>>

    private ConsoleFactory(){
        reflections = new Reflections("ua.com.alevel");
        consoleSrvices = reflections.getSubTypesOf(ConsoleService.class);
    }

    public static ConsoleFactory getInstance(){
        if(instance == null){
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public ConsoleService getConsoleService(){
        for (Class<? extends ConsoleService> consoleService : consoleSrvices) {
            if(!consoleService.isAnnotationPresent(Deprecated.class)){
                try {
                    return consoleService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Idiot");
                }
            }
        }
        throw new RuntimeException("Idiot");
    }
}
