package com.fundamentosSpring.fundamentos.Component;

import org.springframework.stereotype.Component;

/**
 * Aqui se hace la primera implementacion de nuestro bean, que es la implemetacion de los metodos declarados en
 * la interfaz ComponentDependency
 *
 * Se debe declarar la anotacioon @Component para que el Contenedor de Spring pueda hacer el @Autowired
 * de esta clase
 */
@Component
public class ComponentImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi implementacion");
    }
}
