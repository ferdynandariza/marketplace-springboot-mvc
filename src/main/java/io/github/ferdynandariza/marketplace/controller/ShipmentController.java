package io.github.ferdynandariza.marketplace.controller;

import io.github.ferdynandariza.marketplace.dto.shipment.InsertShipmentDTO;
import io.github.ferdynandariza.marketplace.dto.shipment.UpdateShipmentDTO;
import io.github.ferdynandariza.marketplace.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    ShipmentService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        var table = service.getTable(page);
        var totalPages = table.getTotalPages();
        model.addAttribute("table", table);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "shipment/index";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) String name, Model model) {
        if (name != null) {
            var totalDependentHistory = service.totalDependentHistory(name);
            var totalDependentCart = service.totalDependentCart(name);
            var isService = service.findById(name).getService();
            if (!isService || (totalDependentHistory + totalDependentCart) != 0) {
                return "redirect:/shipment/index";
            } else {
                var dto = service.findById(name);
                model.addAttribute("action", "update");
                model.addAttribute("dto", dto);
            }
        } else {
            var dto = new InsertShipmentDTO();
            model.addAttribute("action", "insert");
            model.addAttribute("dto", dto);
        }
        return "shipment/form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("dto") InsertShipmentDTO dto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "insert");
            return "shipment/form";
        }
        service.save(dto);
        return "redirect:/shipment/index";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dto") UpdateShipmentDTO dto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "update");
            return "shipment/form";
        }
        service.save(dto);
        return "redirect:/shipment/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String name, Model model) {
        var totalDependentHistory = service.totalDependentHistory(name);
        var totalDependentCart = service.totalDependentCart(name);
        if (totalDependentHistory == 0 && totalDependentCart == 0) {
            service.delete(name);
            return "redirect:/shipment/index";
        }
        model.addAttribute("name", name);
        model.addAttribute("totalDependentHistory", totalDependentHistory);
        model.addAttribute("totalDependentCart", totalDependentCart);
        return "shipment/delete-error";
    }

    @GetMapping("/stop")
    public String stop(@RequestParam(required = true) String name, Model model) {
        service.stop(name);
        return "redirect:/shipment/index";
    }

}
