package io.github.ferdynandariza.marketplace.dto.merchandise;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertProductDTO {

    private Integer id;

    @NotBlank(message = "Product name must be filled")
    @Size(max = 50, message = "Product name must be not longer than 50 characters")
    private String name;

    @NotBlank(message = "Category name must be filled")
    @Size(max = 50, message = "Category name must be not longer than 50 characters")
    private String categoryName;

    @Size(max = 200, message = "Description must be not longer than 200 characters")
    private String description;

    @NotBlank(message = "Seller must be filled")
    private String sellerId;

    @NotNull(message = "Product price must be filled")
    @Min(value = 0, message = "Product price must be greater than 0")
    private Double price;

    @NotNull(message = "Discontinue must be filled")
    private Boolean discontinue;
}
