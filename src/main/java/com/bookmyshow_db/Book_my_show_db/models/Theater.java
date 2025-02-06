package com.bookmyshow_db.Book_my_show_db.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID theaterId;
    String theaterName;
    @ManyToOne
    AppUser owner;
    String address;
    @Column(unique = true)
    Long theaterHelpline;

}
