package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;

@Controller
public class NotesController {

    private final LoggedInUserBean loggedInUserBean;

    private final UserService userService;
    private final NoteService noteService;

    public NotesController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String LoginForm(Model model) {
        if (loggedInUserBean.isLoggedIn()) {
            model.addAttribute("user", loggedInUserBean.getLoggedInUser());
            model.addAttribute("notes", noteService.findAll().stream()
                    .filter(note -> note.getCreatorUser().equals(loggedInUserBean.getLoggedInUser()))
                    .sorted(Comparator.comparing(Note::getTitle))
                    .toArray());
            return "notes";
        }
        else
            return "redirect:/login";
    }
}