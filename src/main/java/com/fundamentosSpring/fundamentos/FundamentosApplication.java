package com.fundamentosSpring.fundamentos;

import com.fundamentosSpring.fundamentos.Bean.MyBean;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithDependency;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithProperties;
import com.fundamentosSpring.fundamentos.Bean.MyOperation;
import com.fundamentosSpring.fundamentos.Component.ComponentDependency;
import com.fundamentosSpring.fundamentos.Pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    //Aqui se declara la dependencia, lo que se hace es mandar a llamar la interfaz de la cual se harán
    //Varias implementaciones
    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyOperation myOperation;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;

    /*
        Aqui se hace una inyeccion de dependencias por medio del constructor, se puede hacer de otra forma,
        poniendo directamente el @Autowired arriba de la declaracion de la dependencia, sin necesidad de un
        constructor
        Para seleccionar que implementacion usar cuando tienes mas de una, se usa la anotacion @Qualifier,
        Poniendo en el nombre de la clase de la implementacion que se quiere usar con la primera letra del nombre
        en minuscula
     */
    @Autowired
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyOperation myOperation, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myOperation = myOperation;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
    }

    public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//Este metodo nos ayuda a imprimir texto en la terminal a la hora de correr la aplicacion
    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBean.print();
        System.out.println(myOperation.sum(5));
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
    }
}
