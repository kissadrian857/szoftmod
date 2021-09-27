package hu.unideb.notetakingapp.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotesController {
    private final LoggedInUserBean loggedInUserBean;

    public NotesController(LoggedInUserBean loggedInUserBean) {
        this.loggedInUserBean = loggedInUserBean;
    }

    @GetMapping({"", "/notes"})
    public String LoginForm(Model model) {
        model.addAttribute("user", loggedInUserBean.getLoggedInUser());
        return "notes";
    }
}