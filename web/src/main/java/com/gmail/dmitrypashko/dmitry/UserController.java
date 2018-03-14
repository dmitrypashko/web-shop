package com.gmail.dmitrypashko.dmitry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;
import com.gmail.dmitrypashko.dmitry.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/admin/users/page/{pagination}"}, method = RequestMethod.GET)
    public String getAllUser(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        req.setAttribute("paginations", userService.getPaginationList());
        req.setAttribute("users", userService.getAllUsers(pagination - 1));
        return "admin_users";
    }

    @RequestMapping(value = {"/admin/changeRole"}, method = RequestMethod.POST)
    public String changeRole(HttpServletRequest req) throws IOException {
        String id = req.getParameter("id");
        String role = req.getParameter("role");
        userService.changeRoleUser(Long.parseLong(id), role);
        return "redirect:/admin/users/page/1";
    }

    @RequestMapping(value = {"/admin/changeStatusUser"}, method = RequestMethod.POST)
    public String changeStatusUser(HttpServletRequest req) throws IOException {
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        userService.changeStatusUser(Long.parseLong(id), status);
        return "redirect:/admin/users/page/1";
    }


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String loginInGet() throws IOException {
        return "login";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) throws IOException {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUser(user);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGet(HttpServletRequest req, Model model) throws IOException {
        req.setCharacterEncoding("UTF-8");
        model.addAttribute("user", new User());
        return "registration";
    }


}
