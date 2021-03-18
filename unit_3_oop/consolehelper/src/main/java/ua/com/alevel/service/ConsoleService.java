package ua.com.alevel.service;

import java.math.BigDecimal;


public interface ConsoleService {

    BigDecimal getMessageFromConsole();

    void writeMessageToConsole(String message);
}
