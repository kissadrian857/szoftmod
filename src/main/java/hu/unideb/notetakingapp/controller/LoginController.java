package hu.unideb.notetakingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String LoginForm(Model model) {
        model.addAttribute("username", new String());
        model.addAttribute("password", new String());
        return "login";
    }

    @PostMapping("/login")
    public String LoginSubmit(@ModelAttribute String username, @ModelAttribute String password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "notes";
    }

}