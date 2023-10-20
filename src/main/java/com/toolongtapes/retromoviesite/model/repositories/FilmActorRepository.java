package com.toolongtapes.retromoviesite.model.repositories;

import com.toolongtapes.retromoviesite.model.entities.Actor;
import com.toolongtapes.retromoviesite.model.entities.Film;
import com.toolongtapes.retromoviesite.model.entities.FilmActor;
import com.toolongtapes.retromoviesite.model.entities.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmActorRepository  extends JpaRepository<FilmActor, FilmActorId> {
    List<FilmActor> findFilmActorsByFilm(Film film);
}
