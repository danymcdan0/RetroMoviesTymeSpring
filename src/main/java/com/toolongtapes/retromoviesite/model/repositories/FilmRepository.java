package com.toolongtapes.retromoviesite.model.repositories;

import com.toolongtapes.retromoviesite.model.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
