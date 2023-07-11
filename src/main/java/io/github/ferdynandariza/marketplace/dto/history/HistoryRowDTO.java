package io.github.ferdynandariza.marketplace.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRowDTO {

    private LocalDate date;

    private String seller;

    private String buyer;

    private String product;

    private Integer quantity;

    private String shipment;

    private Double totalPrice;

}
