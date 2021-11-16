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
        if (userService.save(user) == null)
        {
            return "redirect:/register";
        }
        else {
            return "redirect:/login";
        }
    }

}