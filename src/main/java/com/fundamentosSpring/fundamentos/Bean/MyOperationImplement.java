package com.fundamentosSpring.fundamentos.Bean;

public class MyOperationImplement implements MyOperation{
    @Override
    public int sum(int number) {
        return ++number;
    }
}
