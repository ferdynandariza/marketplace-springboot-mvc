package io.github.ferdynandariza.marketplace.dto.profile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBalanceDTO {

    private String username;

    @NotNull(message = "Dana harus diisi")
    @Min(value = 0, message = "Dana yang ingin ditambahkan harus positif")
    private Double balanceAddititon;

}
