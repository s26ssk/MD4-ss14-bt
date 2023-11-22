package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User loggedInUser = userService.findByEmail(user.getEmail());
        if (loggedInUser != null && loggedInUser.getPassword().equals(user.getPassword())) {
            httpSession.setAttribute("email", loggedInUser.getEmail());
            return "/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email hoặc mật khẩu không hợp lệ");
            return "/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("email");
        return "redirect:/login";
    }
}


