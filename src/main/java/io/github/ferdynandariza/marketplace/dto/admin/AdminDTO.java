package io.github.ferdynandariza.marketplace.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    @NotBlank(message = "Username  must be filled")
    @Size(max = 50, message = "Username must be not longer than 50 characters")
    private String username;

    @NotBlank(message = "Password must be filled")
    @Size(max = 50, message = "Password must be not longer than 50 charactersr")
    private String password;

    @NotBlank(message = "Password must be filled")
    @Size(max = 50, message = "Password must be not longer than 50 characters")
    private String passwordConfirmation;

    private String role;
}
