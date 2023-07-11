package io.github.ferdynandariza.marketplace.dto.shop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartDTO {

    private Integer id;

    private Integer productId;

    private String buyerId;

    @NotNull(message = "Quantity must be filled")
    @Min(value = 0, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotBlank(message = "Shipment must be filled")
    private String shipmentName;
}
