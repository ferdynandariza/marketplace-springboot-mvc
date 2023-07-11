package io.github.ferdynandariza.marketplace.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Buyer")
    private String buyerId;

    @ManyToOne
    @JoinColumn(name = "Buyer", insertable = false, updatable = false)
    private AccountDetail buyer;

    @Column(name = "ProductId")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "ShipmentName")
    private String shipmentName;

    @Column(name = "TotalPrice")
    private Double totalPrice;

    @Column(name = "Date")
    private LocalDate date;

    public History(Integer id,
                   String buyerId,
                   Integer productId,
                   Integer quantity,
                   String shipmentName,
                   Double totalPrice,
                   LocalDate date) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
        this.shipmentName = shipmentName;
        this.totalPrice = totalPrice;
        this.date = date;
    }
}
