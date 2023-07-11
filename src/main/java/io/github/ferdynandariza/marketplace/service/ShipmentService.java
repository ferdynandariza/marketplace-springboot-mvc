package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.CartRepository;
import io.github.ferdynandariza.marketplace.dao.HistoryRepository;
import io.github.ferdynandariza.marketplace.dao.ShipmentRepository;
import io.github.ferdynandariza.marketplace.dto.shipment.InsertShipmentDTO;
import io.github.ferdynandariza.marketplace.dto.shipment.ShipmentRowDTO;
import io.github.ferdynandariza.marketplace.dto.shipment.UpdateShipmentDTO;
import io.github.ferdynandariza.marketplace.entity.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    HistoryRepository historyRepository;

    private final int rowPerPage = 10;

    public Page<ShipmentRowDTO> getTable(Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("name"));
        var table = shipmentRepository.getTable(pageable);
        table.forEach((shipment) -> {
            shipment.setIsUsed(
                    (totalDependentCart(shipment.getName()) + totalDependentHistory(shipment.getName())
                    ) > 0);
        });
        return table;
    }


    public Shipment save(InsertShipmentDTO dto) {
        var entity = new Shipment(
                dto.getName(),
                dto.getPrice(),
                dto.getService()
        );
        var result = shipmentRepository.save(entity);
        return result;
    }

    public Shipment save(UpdateShipmentDTO dto) {
        var entity = new Shipment(
                dto.getName(),
                dto.getPrice(),
                dto.getService()
        );
        var result = shipmentRepository.save(entity);
        return result;
    }

    public UpdateShipmentDTO findById(String name) {
        var entity = shipmentRepository.findById(name).get();
        var dto = new UpdateShipmentDTO(
                entity.getName(),
                entity.getPrice(),
                entity.getService()
        );
        return dto;
    }

    public void delete(String name) {
        shipmentRepository.deleteById(name);
    }

    public Shipment stop(String name) {
        var entity = shipmentRepository.findById(name).get();
        entity.setService(false);
        return shipmentRepository.save(entity);
    }

    public Integer totalDependentHistory(String name) {
        return historyRepository.countShipmentDependent(name);
    }

    public Integer totalDependentCart(String name) {
        return cartRepository.countShipmentDependent(name);
    }

    public Boolean isShipmentNameValid(String name) {
        var countEntity = shipmentRepository.countShipmentName(name);
        return countEntity <= 0;
    }
}
