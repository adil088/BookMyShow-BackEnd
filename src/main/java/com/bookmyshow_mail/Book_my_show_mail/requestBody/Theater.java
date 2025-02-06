package com.bookmyshow_mail.Book_my_show_mail.requestBody;

import java.util.UUID;

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
public class Theater {

    UUID TheaterId;
    String theaterName;
    AppUser owner;
    String address;
    Long theaterHelpline;

}
