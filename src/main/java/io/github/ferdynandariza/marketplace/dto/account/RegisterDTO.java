package io.github.ferdynandariza.marketplace.dto.account;

import io.github.ferdynandariza.marketplace.validation.ComparePassword;
import io.github.ferdynandariza.marketplace.validation.UniqueUsername;
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
@ComparePassword(message = "Password confirmation did not match")
public class RegisterDTO {

    @UniqueUsername(message = "Username already used, please use other username")
    @NotBlank(message = "Username  must be filled")
    @Size(max = 50, message = "Username must be not longer than 50 characters")
    private String username;

    @NotBlank(message = "Password must be filled")
    @Size(max = 50, message = "Password must be not longer than 50 charactersr")
    private String password;

    @NotBlank(message = "Password must be filled")
    @Size(max = 50, message = "Password must be not longer than 50 characters")
    private String passwordConfirmation;

    @NotBlank(message = "Role must be filled")
    private String role;

    @NotBlank(message = "Name  must be filled")
    @Size(max = 50, message = "Name must be not longer than 50 characters")
    private String name;

    @Size(max = 200, message = "Address must be not longer than 200 characters")
    private String address;

}
