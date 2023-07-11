package io.github.ferdynandariza.marketplace.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRowDTO {

    private Integer id;

    private String product;

    private Integer quantity;

    private String shipment;

    private String seller;

    private Double totalPrice;

    public CartRowDTO(Integer id) {
        this.id = id;
    }
}
