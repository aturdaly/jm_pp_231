package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "readUser", method = RequestMethod.GET)
    public String usersForm(ModelMap model) {
        model.addAttribute("userList", userService.getAllUser());
        return "readUser";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
    public String updateUserForm(ModelMap model, HttpServletRequest req) {
        model.addAttribute("user", userService.getUserById(Long.parseLong(req.getParameter("id"))));
        return "updateUser";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(ModelMap model, HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        String role = req.getParameter("role");

        userService.updateUser(new User(id, name, password, age, role));
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ModelAndView addUser(ModelMap model, HttpServletRequest req) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        String role = req.getParameter("role");

        userService.addUser(new User(name, password, age, role));
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(ModelMap model, HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));

        userService.deleteUser(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/readUser", model);
    }
}
