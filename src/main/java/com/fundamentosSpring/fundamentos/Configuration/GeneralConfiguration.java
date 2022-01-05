package com.fundamentosSpring.fundamentos.Configuration;

import com.fundamentosSpring.fundamentos.Bean.MyBeanWithProperties;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithPropertiesImplement;
import com.fundamentosSpring.fundamentos.Pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;

    @Value("${value.surname}")
    private String surname;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, surname);
    }
}
