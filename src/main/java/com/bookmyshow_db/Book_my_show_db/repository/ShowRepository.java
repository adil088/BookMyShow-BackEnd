package com.bookmyshow_db.Book_my_show_db.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow_db.Book_my_show_db.models.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {

}
