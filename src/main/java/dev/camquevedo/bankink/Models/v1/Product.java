package dev.camquevedo.bankink.Models.v1;

public class Product {

    public final long id;
    public final String name;
    public final long number;

    public Product(long id, String name, long number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
