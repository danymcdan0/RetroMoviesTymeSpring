package com.toolongtapes.retromoviesite.model.service;

import com.toolongtapes.retromoviesite.model.entities.Actor;
import com.toolongtapes.retromoviesite.model.entities.Film;
import com.toolongtapes.retromoviesite.model.entities.FilmActor;
import com.toolongtapes.retromoviesite.model.entities.FilmDTO;
import com.toolongtapes.retromoviesite.model.repositories.FilmActorRepository;
import com.toolongtapes.retromoviesite.model.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmActorRepository filmActorRepository;

    public FilmService(FilmRepository filmRepository, FilmActorRepository filmActorRepository) {
        this.filmRepository = filmRepository;
        this.filmActorRepository = filmActorRepository;
    }

    public List<FilmDTO> findFilmAndActor(@RequestParam String sort){
        ArrayList<FilmDTO> films = new ArrayList<>();
        for (Film film: filmRepository.findAll()){
            FilmDTO newEntry = new FilmDTO();
            newEntry.Actors = new ArrayList<>();
            List<FilmActor> actorsByFilmId = filmActorRepository.findFilmActorsByFilm(film);
            for (FilmActor filmActor: actorsByFilmId){
                newEntry.Actors.add(filmActor.getActor());
            }
            newEntry.FilmId = film.getId();
            newEntry.Title = film.getTitle();
            newEntry.Date = film.getReleaseYear();
            newEntry.Rating = film.getRating();
            newEntry.Length = film.getLength();
            newEntry.Description = film.getDescription();
            films.add(newEntry);
        }
        if (sort != null){
            films.sort(Comparator.comparing(FilmDTO::getReleaseDate).reversed());
        } else {films.sort(Comparator.comparing(FilmDTO::getReleaseDate));}
        return films;
    }

    public List<FilmDTO> findFilmAndActorByFilm(@RequestParam String query, @RequestParam String sort) {
        List<FilmDTO> allFilms = findFilmAndActor(sort);
        List<FilmDTO> resultFilms = new ArrayList<>();
        for (FilmDTO filmDTO: allFilms) {
            if (filmDTO.Title.contains(query.toUpperCase())){
                resultFilms.add(filmDTO);
            }
        }
        return resultFilms;
    }

    public Object findFilmAndActorByActor(@RequestParam String query, @RequestParam String sort) {
        List<FilmDTO> allFilms = findFilmAndActor(sort);
        List<FilmDTO> resultFilms = new ArrayList<>();
        for (FilmDTO filmDTO: allFilms) {
            for (Actor actor: filmDTO.Actors) {
                if (actor.getFullName().contains(query.toUpperCase())){
                    resultFilms.add(filmDTO);
                }
            }
        }
        return resultFilms;
    }
}
