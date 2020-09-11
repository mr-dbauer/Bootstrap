package springBootBauerProject.Bootstrap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springBootBauerProject.Bootstrap.models.User;
import springBootBauerProject.Bootstrap.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "userInfo";
    }
}
