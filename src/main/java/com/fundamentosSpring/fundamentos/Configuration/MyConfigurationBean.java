package com.fundamentosSpring.fundamentos.Configuration;

import com.fundamentosSpring.fundamentos.Bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBeanImplementTwo();
    }

    @Bean
    public MyOperation beanSum(){
        return new MyOperationImplement();
    }


    @Bean
    public MyBeanWithDependency beanWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
