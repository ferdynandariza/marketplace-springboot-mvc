package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.profile.AddBalanceDTO;
import io.github.ferdynandariza.marketplace.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService service;

    @GetMapping("/{username}")
    public String index(@PathVariable(name = "username") String username,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var profile = service.getProfile(username);
        var table = service.getTable(username, page);
        var totalPages = table.getTotalPages();
        var addBalance = new AddBalanceDTO(username, 0.0);
        model.addAttribute("profile", profile);
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("addBalance", addBalance);
        return "profile/index";
    }

    @GetMapping("/add-balance")
    public String addBalance(Model model) {
        var dto = new AddBalanceDTO();
        model.addAttribute("dto", dto);
        return "profile/balance-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") AddBalanceDTO dto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "profile/balance-form";
        }
        service.addBalance(dto);
        return "redirect:/profile/" + dto.getUsername();
    }


}
