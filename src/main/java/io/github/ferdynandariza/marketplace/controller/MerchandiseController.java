package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.merchandise.UpsertProductDTO;
import io.github.ferdynandariza.marketplace.service.MerchandiseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    MerchandiseService service;

    @GetMapping("/{username}")
    public String index(@PathVariable(name = "username") String username,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var table = service.getTable(username, page);
        var totalPages = table.getTotalPages();
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "merchandise/index";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, Model model) {
        var totalDependentHistory = service.totalDependentHistory(id);
        var totalDependentCart = service.totalDependentCart(id);
        if (totalDependentHistory == 0 && totalDependentCart == 0) {
            var dto = new UpsertProductDTO();
            if (id != null) {
                dto = service.findById(id);
            }
            model.addAttribute("dto", dto);
            return "merchandise/form";
        } else {
            var seller = service.findById(id).getSellerId();
            return "redirect:/merchandise/" + seller;
        }
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") UpsertProductDTO dto,
                       BindingResult bindingResult,
                       Model model) {
        var seller = dto.getSellerId();
        if (bindingResult.hasErrors()) {
            return "merchandise/form";
        }
        service.save(dto);
        return "redirect:/merchandise/" + seller;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Integer id, Model model) {
        var totalDependentHistory = service.totalDependentHistory(id);
        var totalDependentCart = service.totalDependentCart(id);
        var seller = service.findById(id).getSellerId();
        var name = service.findById(id).getName();
        if (totalDependentHistory == 0 && totalDependentCart == 0) {
            service.delete(id);
            return "redirect:/merchandise/" + seller;
        }
        model.addAttribute("name", name);
        model.addAttribute("totalDependentHistory", totalDependentHistory);
        model.addAttribute("totalDependentCart", totalDependentCart);
        return "merchandise/delete-error";
    }

    @GetMapping("/info")
    public String info(@RequestParam(required = true) Integer id, Model model) {
        var dto = service.getProductInfo(id);
        model.addAttribute("dto", dto);
        return "merchandise/info";
    }

    @GetMapping("/stop")
    public String stop(@RequestParam(required = true) Integer id, Model model) {
        var seller = service.findById(id).getSellerId();
        service.discontinue(id);
        return "redirect:/merchandise/" + seller;
    }
}
