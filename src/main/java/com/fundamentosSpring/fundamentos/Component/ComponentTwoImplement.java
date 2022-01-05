package com.fundamentosSpring.fundamentos.Component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Hello desde mi componente dos");
    }
}
