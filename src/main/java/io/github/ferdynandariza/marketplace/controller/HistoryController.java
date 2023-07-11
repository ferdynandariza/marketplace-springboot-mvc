package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(required = false) String sellerId,
                        @RequestParam(required = false) String buyerId,
                        Model model) {
        var table = service.getTable(sellerId, buyerId, page);
        var totalPages = table.getTotalPages();
        model.addAttribute("sellerDropdown", service.getSellerDropdown());
        model.addAttribute("buyerDropdown", service.getBuyerDropdown());
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("buyerId", buyerId);
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "history/index";
    }

}
