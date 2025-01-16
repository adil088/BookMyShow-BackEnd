package com.bookmyshow_experience.Book_my_show_experience.requestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequestBody {
    String name;
    String email;
    String password;
    Long contactNumber;
    String userType;
}
