package com.fundamentosSpring.fundamentos;

import com.fundamentosSpring.fundamentos.Bean.MyBean;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithDependency;
import com.fundamentosSpring.fundamentos.Bean.MyBeanWithProperties;
import com.fundamentosSpring.fundamentos.Bean.MyOperation;
import com.fundamentosSpring.fundamentos.Component.ComponentDependency;
import com.fundamentosSpring.fundamentos.Entity.User;
import com.fundamentosSpring.fundamentos.Pojo.UserPojo;
import com.fundamentosSpring.fundamentos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    private UserRepository userRepository;

    /*
        Aqui se hace una inyeccion de dependencias por medio del constructor, se puede hacer de otra forma,
        poniendo directamente el @Autowired arriba de la declaracion de la dependencia, sin necesidad de un
        constructor
        Para seleccionar que implementacion usar cuando tienes mas de una, se usa la anotacion @Qualifier,
        Poniendo en el nombre de la clase de la implementacion que se quiere usar con la primera letra del nombre
        en minuscula
     */
    @Autowired
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyOperation myOperation, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myOperation = myOperation;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//Este metodo nos ayuda a imprimir texto en la terminal a la hora de correr la aplicacion
    @Override
    public void run(String... args) throws Exception {
        saveUserInDataBase();
        getInformationJpqlFromUser();
    }

    private void getInformationJpqlFromUser() {
        System.out.println("Usuario con el metodo findByUserEmail " +
                userRepository.findByUserEmail("marco@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro ningun usuario"))
        );

        userRepository.findAndSort("user", Sort.by("id").descending())
                .forEach(user -> System.out.println("Usuario con metodo Sort " + user));
    }

    private void saveUserInDataBase(){
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
        User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
        User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
        User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
        User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
        User user7 = new User("user2", "enrique@domain.com", LocalDate.of(2021, 11, 12));
        User user8 = new User("user3", "luis@domain.com", LocalDate.of(2021, 2, 27));
        User user9 = new User("user43", "paola@domain.com", LocalDate.of(2021, 4, 10));

        List<User> users = Arrays.asList(user1, user2,user3, user4, user5,user4, user5, user6, user7, user8, user8, user9);

        //Con este metodo se guardan todos los usuarios en la base de datos con un metodo de JpaRepository
        users.forEach(userRepository::save);
    }

    public void metodosAnteriores(){
        componentDependency.saludar();
        myBean.print();
        System.out.println(myOperation.sum(5));
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        /*
            Aqui se usa la inyeccion de dependencias de nuestro POJO usando los valores que se establecieron
            en nuestro properties, solo se hace la inyeccion de dependencias de siempre y se manda
            a llamar los atreibutos declarados donde lo necesitemos
         */
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword() + " " + userPojo.getAge());
    }
}
