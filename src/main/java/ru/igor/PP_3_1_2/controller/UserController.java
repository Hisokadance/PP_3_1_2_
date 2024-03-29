package ru.igor.PP_3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.igor.PP_3_1_2.model.User;
import ru.igor.PP_3_1_2.service.UserServices;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> userList = userServices.getAllUser();
        model.addAttribute("user", userList);
        return "users/all-users";
    }

    @GetMapping("/addUser")
    public String addNewUser(Model model, User newUser) {
        model.addAttribute("user", newUser);
        return "users/add-user";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") User saveUser) {
        userServices.addUser(saveUser);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("id") Integer id, Model model) {
        User user = userServices.getUser(id);
        model.addAttribute("userUpdate", user);
        return "users/update-user";
    }

    @PostMapping("/update")
    public String updateNewUser(@ModelAttribute("userUpdate") User updateUser) {
        userServices.updateUser(updateUser);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("id") Integer id) {
        userServices.deleteUser(id);
        return "redirect:/";
    }
}
