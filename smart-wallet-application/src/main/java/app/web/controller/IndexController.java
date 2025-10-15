package app.web.controller;

import app.user.model.Country;
import app.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return null;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("registerRequest", new RegisterRequest());
        modelAndView.addObject("countries", Country.values());
        modelAndView.setViewName("register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView getRegisterPage(@Valid RegisterRequest registerRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("register");
        }

        // mtyhjmklllllllllkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk

        return new ModelAndView("redirect:/login");
    }
}
