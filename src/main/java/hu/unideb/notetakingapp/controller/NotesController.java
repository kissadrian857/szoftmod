package hu.unideb.notetakingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotesController {

    @GetMapping("/notes")
    public String notes(Model model) {
        return "notes";
    }

}