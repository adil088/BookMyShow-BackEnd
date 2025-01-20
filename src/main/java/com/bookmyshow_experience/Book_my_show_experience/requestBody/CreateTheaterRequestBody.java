package com.bookmyshow_experience.Book_my_show_experience.requestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheaterRequestBody {

    Long helpLineNumber;
    String address;

}
