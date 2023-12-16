package dev.camquevedo.bankInc.Models.v1;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public long status;
    public long number;
    public long balance;
    public String first_name;
    public String last_name;
    public Timestamp expiration_date;
}
