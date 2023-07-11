package io.github.ferdynandariza.marketplace.rest;

import io.github.ferdynandariza.marketplace.MapperHelper;
import io.github.ferdynandariza.marketplace.dto.profile.AddBalanceDTO;
import io.github.ferdynandariza.marketplace.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/profile")
public class ProfileRestController {

    @Autowired
    ProfileService service;

    @PostMapping("/add-balance")
    public ResponseEntity<Object> addBalance(@Valid @RequestBody AddBalanceDTO dto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                var updatedEntity = service.addBalance(dto);
                return ResponseEntity.status(201).body(updatedEntity);
            }
            return ResponseEntity.status(422).body(MapperHelper.getErrors(bindingResult.getAllErrors()));

        } catch (Exception exception) {
            return ResponseEntity.status(500).body(exception.getMessage());
        }
    }


}
