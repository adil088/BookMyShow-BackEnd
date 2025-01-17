package com.bookmyshow_db.Book_my_show_db.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(unique = true, nullable = false)
    Long contactNumber;
    String userType;

}
