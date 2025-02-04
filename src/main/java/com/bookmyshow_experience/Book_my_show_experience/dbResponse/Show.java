package com.bookmyshow_experience.Book_my_show_experience.dbResponse;

import java.util.UUID;

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
public class Show implements Comparable<Show> {

    UUID id;
    Long startTime;
    Long endTime;
    String movieName;
    Hall hall;
    int ticketsSold;

    public int compareTo(Show obj) {
        return (int) (this.startTime - obj.endTime);
    }
}
