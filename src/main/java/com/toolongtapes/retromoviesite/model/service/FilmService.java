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

    public List<FilmDTO> FilmAndActorFactorySearchHandler(@RequestParam String query, @RequestParam Boolean type, @RequestParam String sort) {
        List<FilmDTO> films;
        if (query == null){
            films = findFilmAndActor(sort);
        }
        else if(type){
            films = findFilmAndActorByFilm(query, sort);
        }
        else {
            films = findFilmAndActorByActor(query, sort);
        }
        return films;
    }

    private List<FilmDTO> findFilmAndActor(@RequestParam String sort){
        ArrayList<FilmDTO> films = new ArrayList<>();
        for (Film film: filmRepository.findAll()){
            FilmDTO newEntry = new FilmDTO();
            List<Actor> actors = new ArrayList<>();
            List<FilmActor> actorsByFilmId = filmActorRepository.findFilmActorsByFilm(film);
            for (FilmActor filmActor: actorsByFilmId){
                actors.add(filmActor.getActor());
            }
            newEntry.setActors(actors);
            newEntry.setFilmId(film.getId());
            newEntry.setTitle(film.getTitle());
            newEntry.setDate(film.getReleaseYear());
            newEntry.setRating(film.getRating());
            newEntry.setLength(film.getLength());
            newEntry.setDescription(film.getDescription());
            films.add(newEntry);
        }
        if (sort != null){
            films.sort(Comparator.comparing(FilmDTO::getDate).reversed());
        } else {films.sort(Comparator.comparing(FilmDTO::getDate));}
        return films;
    }

    private List<FilmDTO> findFilmAndActorByFilm(@RequestParam String query, @RequestParam String sort) {
        List<FilmDTO> allFilms = findFilmAndActor(sort);
        List<FilmDTO> resultFilms = new ArrayList<>();
        for (FilmDTO filmDTO: allFilms) {
            if (filmDTO.getTitle().contains(query.toUpperCase())){
                resultFilms.add(filmDTO);
            }
        }
        return resultFilms;
    }

    private List<FilmDTO> findFilmAndActorByActor(@RequestParam String query, @RequestParam String sort) {
        List<FilmDTO> allFilms = findFilmAndActor(sort);
        List<FilmDTO> resultFilms = new ArrayList<>();
        for (FilmDTO filmDTO: allFilms) {
            for (Actor actor: filmDTO.getActors()) {
                if (actor.getFullName().contains(query.toUpperCase())){
                    resultFilms.add(filmDTO);
                }
            }
        }
        return resultFilms;
    }
}
