package dev.camquevedo.bankInc.Models.v1;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;
    public long number;

    public Product(long id, String name, long number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Product(String name, long number) {
        this.name = name;
        this.number = number;
    }

    public Product(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
