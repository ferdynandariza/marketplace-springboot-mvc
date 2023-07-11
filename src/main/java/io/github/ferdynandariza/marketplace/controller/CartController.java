package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @GetMapping("/{username}")
    public String index(@PathVariable(name = "username") String username,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var table = service.getTable(username, page);
        var totalPages = table.getTotalPages();
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "cart/index";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(required = true) Integer id) {
        var buyerId = service.findById(id).getBuyerId();
        service.remove(id);
        return "redirect:/cart/" + buyerId;
    }

    @GetMapping("/purchase-all")
    public String purchaseAll(@RequestParam(required = true) String buyerId, Model model) {
        if (service.purchaseAll(buyerId)) {
            return "redirect:/cart/" + buyerId;
        } else {
            return "cart/failed";
        }
    }

}
