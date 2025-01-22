package com.bookmyshow_db.Book_my_show_db.models;

import java.util.UUID;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    Double startTime;
    Double endTime;
    @ManyToOne
    Hall hall;
    int ticketsSold;
}
