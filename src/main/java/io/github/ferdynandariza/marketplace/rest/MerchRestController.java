package io.github.ferdynandariza.marketplace.rest;

import io.github.ferdynandariza.marketplace.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merch")
public class MerchRestController {

    @Autowired
    MerchandiseService service;

    @GetMapping("/{id}/info")
    public ResponseEntity<Object> info(@PathVariable(required = true) Integer id) {
        try {
            var info = service.getProductInfo(id);
            return ResponseEntity.status(HttpStatus.OK).body(info);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

}
