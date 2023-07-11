package io.github.ferdynandariza.marketplace.dao;

import io.github.ferdynandariza.marketplace.dto.DropdownDTO;
import io.github.ferdynandariza.marketplace.dto.shipment.ShipmentRowDTO;
import io.github.ferdynandariza.marketplace.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.shipment.ShipmentRowDTO(
                s.name,
                s.price,
                CASE
                    WHEN s.service = true THEN 'Yes'
                    WHEN s.service = false THEN 'No'
                END,
                false
            )
            FROM Shipment AS s
            """)
    Page<ShipmentRowDTO> getTable(Pageable pageable);

    @Query("""
            SELECT new  io.github.ferdynandariza.marketplace.dto.DropdownDTO(
                s.name, s.name
            )
            FROM Shipment AS s
            WHERE s.service = true 
            """)
    List<DropdownDTO> getDropdown();

    @Query("""
            SELECT COUNT(s.name)
            FROM Shipment AS s
            WHERE s.name = :name
            """)
    Integer countShipmentName(String name);

}
