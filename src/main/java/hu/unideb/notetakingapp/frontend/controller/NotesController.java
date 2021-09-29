package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
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
            model.addAttribute("new_note", new Note());
            model.addAttribute("user", loggedInUserBean.getLoggedInUser());
            model.addAttribute("notes", noteService.findAll().stream()
                    .filter(note -> note.getCreatorUser().getUserName().equals(loggedInUserBean.getLoggedInUser().getUserName()))
                    .sorted(Comparator.comparing(Note::getTitle))
                    .toArray());
            return "notes";
        } else
            return "redirect:/login";
    }

    @PostMapping("/noteadd")
    public String addNote(@ModelAttribute Note note, Model model) {
        note.setCreationDate(LocalDate.now());
        note.setCreatorUser(loggedInUserBean.getLoggedInUser());
        noteService.save(note);
        return "redirect:/notes";
    }
}