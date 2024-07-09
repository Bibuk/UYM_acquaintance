package com.acquaintance.acquaintance.Controller;


import com.acquaintance.acquaintance.Service.UserService;
import com.acquaintance.acquaintance.Entity.Wall;
import com.acquaintance.acquaintance.Repository.UserRepo;
import com.acquaintance.acquaintance.Repository.WallRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("api/v1/")
@AllArgsConstructor
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WallRepo TaskRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser(){
        return "Wall";
    }

    @GetMapping("/registration")
    public String regPage(){
        return "registration";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        userService.registerNewUser(username, password, email);
        return "redirect:/api/v1/users";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:api/v1/logout";
    }

    @GetMapping("/tasks")
    public String getTasks(Map<String, Object> model) {
        Iterable<Wall> tasks = TaskRepo.findAll();
        model.put("tasks", tasks);
        return "Wall";
    }

    @PostMapping("/addTask")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String addTask(@RequestParam String text) {
        Wall taskText = new Wall(text);
        TaskRepo.save(taskText);
        return "redirect:api/v1/tasks";
    }

    @PostMapping("/deleteTask")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String deleteTask(@RequestParam Long id) {
        TaskRepo.deleteById(id);
        return "redirect:/test/tasks";
    }
}