package com.fundamentosSpring.fundamentos.Configuration;

import com.fundamentosSpring.fundamentos.Bean.MyBeanWithProperties;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithPropertiesImplement;
import com.fundamentosSpring.fundamentos.Pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Este es otro archivo de configuracion como si hiciese desde el .properties
 * Aqui se usan los values generados en el properties, asi como se habilita el uso
 * de propiedades desde otra clase, con este caso se usa el UserPojo, que usara los values
 * con el prefix User, anotadas en el archivo properties
 */

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

    /**
     * Este es el equivalente a declarar nuestra propiedades de nuestro DataSource desde el
     * archivo .properties
     */
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testDB");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
