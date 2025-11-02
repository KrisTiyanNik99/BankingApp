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
    public ModelAndView login(@Valid LoginRequest loginRequest, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }

        User user = userService.login(loginRequest);
        session.setAttribute("user_id", user.getId());

        return new ModelAndView("redirect:/home");
    }

    // Form handling steps:
    // 1. Return HTML form with empty object
    // 2. Use this empty object in the HTML form to fill the data
    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView registerModelAndView = new ModelAndView();
        registerModelAndView.setViewName("register");
        registerModelAndView.addObject("registerRequest", new RegisterRequest());

        return registerModelAndView;
    }

    // 3. Get the object filled with data via POST request method
    // 4. Validate the received object
    // 5. Catch all the validation errors if any exist - BindingResult
    // 6. Check if there are validation errors - if there are errors return same HTML page, if there are not - redirect to login page
    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterRequest registerRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("register");
        }

        userService.register(registerRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {
        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView homeModelAndView = new ModelAndView();
        homeModelAndView.setViewName("home");
        homeModelAndView.addObject("user", user);

        return homeModelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
