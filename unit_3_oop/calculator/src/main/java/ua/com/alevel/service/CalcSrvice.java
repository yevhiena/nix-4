package ua.com.alevel.service;

import java.math.BigDecimal;

public interface CalcSrvice {


    BigDecimal sum(BigDecimal a, BigDecimal b);

    BigDecimal divide(BigDecimal a, BigDecimal b);

    BigDecimal subtract(BigDecimal a, BigDecimal b);

    BigDecimal multiply(BigDecimal a, BigDecimal b);

}
