package io.github.ferdynandariza.marketplace.dao;

import io.github.ferdynandariza.marketplace.dto.history.HistoryRowDTO;
import io.github.ferdynandariza.marketplace.dto.profile.ProfileHistoryRowDTO;
import io.github.ferdynandariza.marketplace.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.history.HistoryRowDTO(
                h.date,
                s.name,
                b.name,
                p.name,
                h.quantity,
                h.shipmentName,
                h.totalPrice
            )
            FROM History AS h
                INNER JOIN h.product AS p
                INNER JOIN p.seller AS s
                INNER JOIN h.buyer AS b
            WHERE (h.buyerId = :buyerId OR :buyerId = '' OR :buyerId IS NULL)
                AND (p.sellerId = :sellerId OR :sellerId = '' OR :sellerId IS NULL)
            """)
    Page<HistoryRowDTO> getTable(@Param("sellerId") String sellerId,
                                 @Param("buyerId") String buyerId,
                                 Pageable pageable);

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.profile.ProfileHistoryRowDTO(
                h.date,
                p.name,
                h.quantity,
                h.shipmentName,
                h.totalPrice
            )
            FROM History AS h
                INNER JOIN h.product AS p
            WHERE h.buyerId = :username
            """)
    Page<ProfileHistoryRowDTO> getTableOfBuyer(@Param("username") String buyer, Pageable pageable);

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.profile.ProfileHistoryRowDTO(
                h.date,
                p.name,
                h.quantity,
                h.shipmentName,
                h.totalPrice
            )
            FROM History AS h
                INNER JOIN h.product AS p
            WHERE p.sellerId = :username
            """)
    Page<ProfileHistoryRowDTO> getTableOfSeller(@Param("username") String seller, Pageable pageable);

    @Query("""
            SELECT COUNT(h.id)
            FROM History AS h
            WHERE h.productId = :productId
            """)
    Integer countProductDependent(@Param("productId") Integer productId);

    @Query("""
            SELECT COUNT(h.id)
            FROM History AS h
            WHERE h.shipmentName = :shipmentName
            """)
    Integer countShipmentDependent(@Param("shipmentName") String shipmentName);

}
