package io.github.ferdynandariza.marketplace.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileHistoryRowDTO {

    private LocalDate date;

    private String product;

    private Integer quantity;

    private String shipment;

    private Double totalPrice;

}
