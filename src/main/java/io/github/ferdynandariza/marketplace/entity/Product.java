package io.github.ferdynandariza.marketplace.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Seller")
    private String sellerId;

    @ManyToOne
    @JoinColumn(name = "Seller", insertable = false, updatable = false)
    private AccountDetail seller;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Discontinue")
    private Boolean discontinue;

    public Product(Integer id,
                   String name,
                   String categoryName,
                   String description,
                   String sellerId,
                   Double price,
                   Boolean discontinue) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.sellerId = sellerId;
        this.price = price;
        this.discontinue = discontinue;
    }
}
