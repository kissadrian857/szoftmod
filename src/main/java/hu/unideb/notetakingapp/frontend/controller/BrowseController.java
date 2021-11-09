package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrowseController {
    private final LoggedInUserBean loggedInUserBean;
    private final UserService userService;
    private final NoteService noteService;

    public BrowseController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping({"/browse"})
    public String browse(Model model) {
        model.addAttribute("all_notes", noteService.findNotesExceptId(loggedInUserBean.getLoggedInUser().getId()));

        return "browse";
    }
}
