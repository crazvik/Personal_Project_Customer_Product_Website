package com.hobby.webApp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Document("Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private boolean isActivated;
    private int users;

    public Product() {
    }

    public Product(String name, boolean isActivated, int users) {
        this.name = name;
        this.isActivated = isActivated;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isActivated=" + isActivated +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isActivated == product.isActivated && users == product.users && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActivated, users);
    }
}
