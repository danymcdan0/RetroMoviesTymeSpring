package com.toolongtapes.retromoviesite.controller;

import com.toolongtapes.retromoviesite.model.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmWebController {

    private final FilmService filmService;

    @Autowired
    public FilmWebController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/web/films")
    public String getAllFilms(Model model, @RequestParam(name = "query", required =false)String query,
                              @RequestParam(name = "searchType",required = false) Boolean type,
                              @RequestParam(name="sort", required = false) String sort) {

        model.addAttribute("films", filmService.FilmAndActorFactorySearchHandler(query, type, sort));
        return "films";
    }
}
