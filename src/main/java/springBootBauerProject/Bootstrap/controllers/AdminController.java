package springBootBauerProject.Bootstrap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springBootBauerProject.Bootstrap.models.Role;
import springBootBauerProject.Bootstrap.models.User;
import springBootBauerProject.Bootstrap.service.RoleService;
import springBootBauerProject.Bootstrap.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public String printUserList(Model model) {
        model.addAttribute("adminInfo", userService.getAuthenticatedUser());
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin/allUsers";
    }

    @PostMapping("/update")
    public String editUser(User user, String[] role) {
        String[] tole = role;
        Set<Role> roles = new HashSet<>();
        try {
            if(role.length == 2) {
                roles.add(roleService.getRole("USER"));
                roles.add(roleService.getRole("ADMIN"));
            } else if(role.length == 1) {
                if(role[0].equals("ADMIN")) {
                    roles.add(roleService.getRole("ADMIN"));
                } else {
                    roles.add(roleService.getRole("USER"));
                }
            } else {
                return "redirect:/admin";
            }
        } catch (NullPointerException e) {
            Set<Role> rolesOfUser = userService.getUsersById(user.getId()).getRoles();
            roles.addAll(rolesOfUser);
        }
        user.setRoles(roles);
        if (user.getPassword().equals("")) {
            String password = userService.getUsersById(user.getId()).getPassword();
            user.setPassword(password);
            userService.updateUserWithoutPassword(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String add(User user, String[] role) {
        String[] roless=role;
        Set<Role> roles = new HashSet<>();
        if(role.length == 2) {
            roles.add(roleService.getRole("USER"));
            roles.add(roleService.getRole("ADMIN"));
        } else if(role.length == 1) {
            if(role[0].equals("1")) {
                roles.add(roleService.getRole("ADMIN"));
            } else {
                roles.add(roleService.getRole("USER"));
            }
        } else {
            return "redirect:/admin";
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

}
