package io.github.ferdynandariza.marketplace.dao;

import io.github.ferdynandariza.marketplace.dto.cart.CartRowDTO;
import io.github.ferdynandariza.marketplace.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.cart.CartRowDTO(
                c.id
            )
            FROM Cart AS c
            WHERE c.buyerId = :buyerId
            """)
    Page<CartRowDTO> getTable(@Param("buyerId") String buyerId, Pageable pageable);

    @Query("""
            SELECT COUNT(c.id)
            FROM Cart AS c
            WHERE c.productId = :productId
            """)
    Integer countProductDependent(@Param("productId") Integer productId);

    @Query("""
            SELECT COUNT(c.id)
            FROM Cart AS c
            WHERE c.shipmentName = :shipmentName
            """)
    Integer countShipmentDependent(@Param("shipmentName") String shipmentName);

    @Query("""
            SELECT c.id
            FROM Cart AS c
            WHERE c.buyerId = :buyerId
            """)
    List<Integer> getAllIdByBuyer(@Param("buyerId") String buyerId);
}
