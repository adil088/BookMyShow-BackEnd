package com.bookmyshow_experience.Book_my_show_experience.requestBody;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateShowRequestBody {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endTime;
    String movieName;
}
