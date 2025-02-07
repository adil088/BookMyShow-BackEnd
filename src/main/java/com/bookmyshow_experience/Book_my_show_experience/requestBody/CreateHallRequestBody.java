package com.bookmyshow_experience.Book_my_show_experience.requestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateHallRequestBody {
    String hallName;
    int seats;
}
