package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {
    private final UserService userService;

    private final NoteService noteService;

    public ExampleController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @RequestMapping({"", "/example", "/example.html"})
    public String listUsers(Model model) {
        model.addAttribute("users",userService.findAll());
        model.addAttribute("notes",noteService.findAll());
        return "/example";
    }
}
