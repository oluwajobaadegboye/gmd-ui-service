package edu.cs.mum.gdmstore.uiservice.uiservice.controller;


import edu.cs.mum.gdmstore.uiservice.uiservice.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "common/login";
    }
}
