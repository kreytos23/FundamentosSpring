package com.fundamentosSpring.fundamentos.Entity;

import javax.persistence.*;

/**
 * Aqui se usa Java JPA, que es la API para modelar bases de datos con clases
 * de Java.
 * Primero se usan las anotaciones @Entity y @Table para declarar que la clase será el modelo
 * de una tabla y con table se le da el nombre que tendra la tabla
 *
 * Despues de usan otras anotaciones comop @Column y @Id para defenir parametros usados en la
 * declaracion de tablas como normalmente se haria en un RDBMS
 */


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private Long id;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(Long id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
