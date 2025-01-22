package com.bookmyshow_db.Book_my_show_db.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookmyshow_db.Book_my_show_db.models.Hall;

public interface HallRepository extends JpaRepository<Hall, UUID> {

}
