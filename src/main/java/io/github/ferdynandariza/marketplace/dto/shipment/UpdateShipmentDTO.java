package io.github.ferdynandariza.marketplace.dto.shipment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShipmentDTO {

    private String name;

    @NotNull(message = "Price must be filled")
    private Double price;

    @NotNull(message = "Service must be filled")
    private Boolean service;

}
