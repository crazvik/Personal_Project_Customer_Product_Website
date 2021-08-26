package com.hobby.webApp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Document("Activation Keys")
public class ActivationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String key;

    public ActivationKey() {
    }

    public ActivationKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ActivationKey{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationKey that = (ActivationKey) o;
        return Objects.equals(id, that.id) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key);
    }
}
