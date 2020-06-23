package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "readUser")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("userList", userService.getAllUser());
        return "readUser";
    }

    @GetMapping(value = "updateUser")
    public String updateUserPage(ModelMap model, Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping(value = "updateUser")
    public ModelAndView updateUser(ModelMap model, User user) {
        userService.updateUser(user);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }

    @PostMapping(value = "addUser")
    public ModelAndView addUser(ModelMap model, User user) {
        userService.addUser(user);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }

    @GetMapping(value = "deleteUser")
    public ModelAndView deleteUser(ModelMap model, Long id) {
        userService.deleteUser(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }
}
