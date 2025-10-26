package app.web.controller;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.EditUserRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final String USER_SESSION_ATTRIBUTE = "user_id";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getUsersPage() {
        List<User> users = userService.getAll();

        ModelAndView usersPage = new ModelAndView();
        usersPage.setViewName("users");
        usersPage.addObject("users", users);

        return usersPage;
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(HttpSession session) {
        UUID userId = (UUID) session.getAttribute(USER_SESSION_ATTRIBUTE);
        User user = userService.getById(userId);

        ModelAndView profilePage = new ModelAndView();
        profilePage.setViewName("profile-menu");
        profilePage.addObject("user", user);
        profilePage.addObject("editUser", new EditUserRequest());

        return profilePage;
    }

    @PostMapping("/profile")
    public ModelAndView editUser(@Valid EditUserRequest editedUser, BindingResult bindingResult, HttpSession session) {
        UUID userId = (UUID) session.getAttribute(USER_SESSION_ATTRIBUTE);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("profile-menu");
        }

        userService.editUserProfile(userId, editedUser);

        return new ModelAndView("redirect:/home");
    }
}