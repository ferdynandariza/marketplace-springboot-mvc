package io.github.ferdynandariza.marketplace.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private String name;

    private String role;

    private String address;

    private Double balance;

}
