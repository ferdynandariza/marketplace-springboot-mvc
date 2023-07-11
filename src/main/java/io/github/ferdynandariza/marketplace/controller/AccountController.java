package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.account.RegisterDTO;
import io.github.ferdynandariza.marketplace.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping("/login")
    public String login() {
        return "account/login-form";
    }

    @GetMapping("/register/{role}")
    public String registerForm(@PathVariable(name = "role") String role, Model model) {
        var dto = new RegisterDTO();
        dto.setRole(role);
        model.addAttribute("dto", dto);
        model.addAttribute("role", role);
        return "account/register-form";
    }

    @PostMapping("/submit")
    public String submitRegister(@Valid @ModelAttribute("dto") RegisterDTO dto,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("role", dto.getRole());
            return "account/register-form";
        }
        service.registerAccount(dto);
        return "account/success";
    }

    @RequestMapping(value = "/access-denied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied() {
        return "account/access-denied";
    }

//    @GetMapping("/success")
//    public String success(){
//        return "account/success";
//    }


}
