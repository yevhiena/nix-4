package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalcSrvice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BestCulcServiceEver implements CalcSrvice{


    @Override
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        try {
            return a.divide(b, 2, RoundingMode.CEILING);
        }
        catch (ArithmeticException e) {
            e.printStackTrace();
            throw new ArithmeticException("e = " + e.getMessage());
        }
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
