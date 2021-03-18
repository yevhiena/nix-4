package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalcSrvice;

import java.math.BigDecimal;
import java.math.BigInteger;


@Deprecated
public class DefaultCalcService implements CalcSrvice  {


    @Override
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }


    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

}
