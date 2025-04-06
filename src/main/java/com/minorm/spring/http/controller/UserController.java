package com.minorm.spring.http.controller;

import com.minorm.spring.database.entity.Role;
import com.minorm.spring.dto.UserCreateEditDto;
import com.minorm.spring.dto.UserFilter;
import com.minorm.spring.service.CompanyService;
import com.minorm.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter filter) {
        // Для работы нужен QueryDsl ПОШЕЛ ОН НА ХУЙ
        model.addAttribute("users", userService.findAll(filter));
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return userService.findById(id)
                        .map(user -> {
                            model.addAttribute("user", user);
                            model.addAttribute("roles", Role.values());
                            model.addAttribute("companies", companyService.findAll());
                            return "user/user";
                        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute UserCreateEditDto user, RedirectAttributes redirectAttributes) {
//        if (true) {
//            redirectAttributes.addAttribute("username", user.getUsername());
//            redirectAttributes.addAttribute("firstname", user.getFirstname());
//            redirectAttributes.addFlashAttribute("user", user);
//            return "redirect:/users/registration";
//        }
        return "redirect:/users/" + userService.create(user).getId();
    }


//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute UserCreateEditDto user){
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }


}
