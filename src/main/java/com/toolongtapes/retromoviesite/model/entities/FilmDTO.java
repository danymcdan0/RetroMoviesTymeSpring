package com.toolongtapes.retromoviesite.model.entities;

import jakarta.persistence.Entity;

import java.util.List;

public class FilmDTO {
//    public Integer getFilmId() {
//        return filmId;
//    }
//
//    public void setFilmId(Integer filmId) {
//        this.filmId = filmId;
//    }

    public Integer FilmId;
    public String Title;
    public Integer Date;
    public String Rating;
    public Integer Length;
    public String Description;
    public List<Actor> Actors;

    public Integer getReleaseDate() {
        return Date;
    }
}
