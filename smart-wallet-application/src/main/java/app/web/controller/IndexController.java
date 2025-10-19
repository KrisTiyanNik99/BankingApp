package app.web.controller;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView loginModelAndView = new ModelAndView();

        loginModelAndView.setViewName("login");
        loginModelAndView.addObject("loginRequest", new LoginRequest());

        return loginModelAndView;
    }

    @PostMapping("/login")
    public ModelAndView getLoginPage(@Valid LoginRequest loginRequest, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }

        User user = userService.login(loginRequest);
        System.out.println(user.getId().toString());
        session.setAttribute("user_id", user.getId());

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView registerModelAndView = new ModelAndView();

        registerModelAndView.setViewName("register");
        registerModelAndView.addObject("registerRequest", new RegisterRequest());

        return registerModelAndView;
    }

    @PostMapping("/register")
    public ModelAndView getRegisterPage(@Valid RegisterRequest registerRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("register");
        }

        userService.register(registerRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {
        ModelAndView homeModelAndView = new ModelAndView();

        homeModelAndView.setViewName("home");

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);
        System.out.println(user.getUsername());
        homeModelAndView.addObject("user", user);

        return homeModelAndView;
    }
}
