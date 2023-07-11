package io.github.ferdynandariza.marketplace.dto.shipment;

import io.github.ferdynandariza.marketplace.validation.UniqueShipmentName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertShipmentDTO {

    @UniqueShipmentName(message = "Shipment name already used")
    @NotBlank(message = "Shipment name must be filled")
    @Size(max = 50, message = "Shipment name must be not longer than 50 characters")
    private String name;

    @NotNull(message = "Price must be filled")
    private Double price;

    @NotNull(message = "Service must be filled")
    private Boolean service;

}
