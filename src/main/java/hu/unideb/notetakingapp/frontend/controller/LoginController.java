package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.frontend.controller.helper.LoggedInUserBean;
import hu.unideb.notetakingapp.frontend.controller.helper.SelectedNoteBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoggedInUserBean loggedInUserBean;
    private final SelectedNoteBean selectedNoteBean;

    public LoginController(LoggedInUserBean loggedInUserBean, SelectedNoteBean selectedNoteBean) {
        this.loggedInUserBean = loggedInUserBean;
        this.selectedNoteBean = selectedNoteBean;
    }

    @GetMapping({ "/login"})
    public String LoginForm(Model model) {
        model.addAttribute("loggedInUser", loggedInUserBean.getLoggedInUser());
        model.addAttribute("user", new User());
        model.addAttribute("incorrect_credentials", false);
        return "login";
    }


    @PostMapping({ "/login"})
    public String LoginSubmit(@ModelAttribute User user, Model model) {
        if (loggedInUserBean.login(user.getUserName(), user.getPasswordHash()))
            return "redirect:/browse";
        else {
            model.addAttribute("incorrect_credentials", true);
            return "login";
        }
    }

    @GetMapping({ "/logout"})
    public String Logout(Model model) {
        loggedInUserBean.logout();
        model.addAttribute("logged_out", true);
        return LoginForm(model);
    }

}