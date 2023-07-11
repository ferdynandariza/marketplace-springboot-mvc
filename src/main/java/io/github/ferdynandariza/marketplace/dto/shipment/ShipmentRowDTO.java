package io.github.ferdynandariza.marketplace.dto.shipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRowDTO {

    private String name;

    private Double price;

    private String service;

    private Boolean isUsed;
}
