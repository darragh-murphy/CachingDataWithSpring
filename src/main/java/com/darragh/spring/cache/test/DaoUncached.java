package com.darragh.spring.cache.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@Qualifier("nocache")
public class DaoUncached implements DaoInterface {

    @Override
    public BigInteger getData(int index) {
        return MathUtil.fibonacci(index);
    }


}
