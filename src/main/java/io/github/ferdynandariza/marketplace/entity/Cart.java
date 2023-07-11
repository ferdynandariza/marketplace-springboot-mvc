package io.github.ferdynandariza.marketplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "ProductId")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "ProductId", insertable = false, updatable = false)
    private Product product;

    @Column(name = "BuyerId")
    private String buyerId;

    @ManyToOne
    @JoinColumn(name = "BuyerId", insertable = false, updatable = false)
    private AccountDetail buyer;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "ShipmentName")
    private String shipmentName;

    @ManyToOne
    @JoinColumn(name = "ShipmentName", insertable = false, updatable = false)
    private Shipment shipment;


    public Cart(Integer id, Integer productId, String buyerId, Integer quantity, String shipmentName) {
        this.id = id;
        this.productId = productId;
        this.buyerId = buyerId;
        this.quantity = quantity;
        this.shipmentName = shipmentName;
    }
}
