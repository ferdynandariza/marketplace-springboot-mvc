package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.admin.AdminDTO;
import io.github.ferdynandariza.marketplace.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AccountService service;

    @GetMapping("/index")
    public String registerForm(Model model) {
        var dto = new AdminDTO();
        model.addAttribute("dto", dto);
        return "admin/index";
    }

    @PostMapping("/submit")
    public String submitRegister(@Valid @ModelAttribute("dto") AdminDTO dto,
                                 BindingResult bindingResult,
                                 Model model) {

        dto.setRole("Admin");
        if (bindingResult.hasErrors()) {
            return "admin/index";
        }
        service.registerAdmin(dto);
        return "admin/success";
    }
}
