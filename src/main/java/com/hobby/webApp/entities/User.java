package com.hobby.webApp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Document("Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private boolean isAdmin;
    private String[] roles;
    private ArrayList<ActivationKey> keys;
    private ArrayList<Product> products;

    public User() {
    }

    public User(String firstName, String lastName, int age, String email, String password,
                boolean isAdmin, String[] roles, ArrayList<ActivationKey> keys, ArrayList<Product> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.roles = roles;
        this.keys = keys;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public ArrayList<ActivationKey> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<ActivationKey> keys) {
        this.keys = keys;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", keys=" + keys +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && isAdmin == user.isAdmin && Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) && Objects.equals(password, user.password) &&
                Objects.equals(keys, user.keys) && Arrays.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, age, email, password, isAdmin, keys);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }
}
