package dev.camquevedo.bankInc.Models.v1;

import jakarta.persistence.*;

/**
 * Statuses list:
 * (1) Inactive
 * (2) Active
 * (3) Blocked
 * (4) Lost
 * (5) Pending
 * (6) Canceled
 */
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;

    public Status() {}

    public Status(long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}

