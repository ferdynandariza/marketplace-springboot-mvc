package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.shop.AddToCartDTO;
import io.github.ferdynandariza.marketplace.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "") String category,
                        @RequestParam(defaultValue = "") String description,
                        Model model) {
        var table = service.getTable(name, category, description, page);
        var totalPages = table.getTotalPages();
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("description", description);
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "shop/index";
    }


    @GetMapping("/add")
    public String form(@RequestParam(required = true) Integer productId, Model model) {
        var dto = new AddToCartDTO();
        dto.setProductId(productId);
        var shipmentDropdown = service.getShipmentDropdown();
        model.addAttribute("shipmentDropdown", shipmentDropdown);
        model.addAttribute("dto", dto);
        return "shop/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") AddToCartDTO dto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            var shipmentDropdown = service.getShipmentDropdown();
            model.addAttribute("shipmentDropdown", shipmentDropdown);
            return "shop/form";
        }
        service.addToCart(dto);
        return "redirect:/shop/index";
    }

    @GetMapping("/info")
    public String info(@RequestParam(required = true) Integer productId, Model model) {
        var dto = service.getProductInfo(productId);
        model.addAttribute("dto", dto);
        return "shop/info";
    }
}
