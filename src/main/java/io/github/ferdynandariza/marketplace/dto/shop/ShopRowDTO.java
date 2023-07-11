package io.github.ferdynandariza.marketplace.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopRowDTO {

    private Integer id;

    private String name;

    private Double price;

}
