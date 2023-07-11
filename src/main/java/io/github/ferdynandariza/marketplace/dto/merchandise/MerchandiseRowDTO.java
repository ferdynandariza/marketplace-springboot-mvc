package io.github.ferdynandariza.marketplace.dto.merchandise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseRowDTO {

    private Integer id;

    private String name;

    private String category;

    private String discontinue;

    private Boolean isUsed;

}
