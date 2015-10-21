package com.darragh.spring.cache.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@Qualifier("caching-enabled")
public class DaoWithCache implements DaoInterface {

    @Override
    @Cacheable("simple-cache")
    public BigInteger getData(int index) {
        return MathUtil.fibonacci(index);
    }

}
