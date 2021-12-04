package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.frontend.controller.helper.LoggedInUserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final LoggedInUserBean loggedInUserBean;
    private final UserService userService;

    public RegisterController(LoggedInUserBean loggedInUserBean, UserService userService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
    }

    @GetMapping({ "/register"})
    public String LoginForm(Model model) {
        if (loggedInUserBean.isLoggedIn())
        {
            return "redirect:/browse";
        }

        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping({ "/register"})
    public String LoginSubmit(@ModelAttribute User user, Model model) {
        if (user.getUserName().length() < 4) {
            model.addAttribute("message", "Username needs to be at least 4 characters long.");
            return LoginForm(model);
        }
        if (userService.findByUsername(user.getUserName()) != null) {
            model.addAttribute("message", "Username already exists.");
            return LoginForm(model);
        }
        if (user.getPasswordHash().length() < 8) {
            model.addAttribute("message", "Password needs to be at least 8 characters long.");
            return LoginForm(model);
        }
        user.setCredits(100);
        userService.save(user);
        return "redirect:/login";
    }

}