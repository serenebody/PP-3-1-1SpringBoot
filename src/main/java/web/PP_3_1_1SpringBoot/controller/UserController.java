package web.PP_3_1_1SpringBoot.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.PP_3_1_1SpringBoot.model.User;
import web.PP_3_1_1SpringBoot.service.UserService;

import java.util.List;

@Controller
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/table")
    public String showListUser(Model model) {
        List<User> userList = userService.getListUsers();
        model.addAttribute("users",userList);
        return "tableUsersAndEditUsers";
    }
    @GetMapping("/add")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUserInTable";
    }
    @PostMapping("/add")
    public String addUser(@RequestParam String name,@RequestParam String lastName, @RequestParam String gender, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setGender(gender);
        userService.addUser(user);
        model.addAttribute("message","User added successfully");
        return "redirect:/table";
    }
    @PostMapping("/delete")
    public String deleteUser (@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/table";
    }
    @GetMapping("/update")
    public String updateUser(Model model, @RequestParam int id){
        model.addAttribute("user", userService.showUser(id));
        return "edit";
    }
    @PostMapping("/update")
    public String editUser (@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/table";
    }
}
