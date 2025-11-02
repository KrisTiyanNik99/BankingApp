package app.web.security;

import app.user.model.User;
import app.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;
import java.util.UUID;

@Component
public class HttpInterceptor implements HandlerInterceptor{
    private static final Set<String> UNAUTHENTICATED_ENDPOINTS = Set.of("/", "/login", "/register");

    private final UserService userService;

    @Autowired
    public HttpInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        *   Implement auth logic which to do not allow for user to access page without login and get session first!
        */
        if (UNAUTHENTICATED_ENDPOINTS.contains(request.getServletPath())) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/login");
            return false;
        }

        UUID userId = (UUID) session.getAttribute("user_id");
        if (userId == null) {
            session.invalidate();
            response.sendRedirect("/login");
            return false;
        }

        User user = userService.getById(userId);
        if (!user.isActive()) {
            response.sendRedirect("/register");
            return false;
        }

        return true;
    }
}
