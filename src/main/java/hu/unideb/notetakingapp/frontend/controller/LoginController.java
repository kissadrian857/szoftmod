package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoggedInUserBean loggedInUserBean;

    private final UserService userService;

    public LoginController(LoggedInUserBean loggedInUserBean, UserService userService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
    }

    @GetMapping({"", "/login"})
    public String LoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping({"", "/login"})
    public String LoginSubmit(@ModelAttribute User user, Model model) {
        User actUser = userService.findByUsername(user.getUserName());
        loggedInUserBean.setLoggedInUser(actUser);

        if (loggedInUserBean.isLoggedIn())
            return "redirect:/notes";
        else
            return "login";
    }

}