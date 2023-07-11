package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.CartRepository;
import io.github.ferdynandariza.marketplace.dao.ProductRepository;
import io.github.ferdynandariza.marketplace.dao.ShipmentRepository;
import io.github.ferdynandariza.marketplace.dto.DropdownDTO;
import io.github.ferdynandariza.marketplace.dto.shop.AddToCartDTO;
import io.github.ferdynandariza.marketplace.dto.shop.ProductShopInfoDTO;
import io.github.ferdynandariza.marketplace.dto.shop.ShopRowDTO;
import io.github.ferdynandariza.marketplace.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ShipmentRepository shipmentRepository;

    private final int rowPerPage = 10;

    public Page<ShopRowDTO> getTable(String name, String category, String description, Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("name"));
        var table = productRepository.getShopTable(name, category, description, pageable);
        return table;
    }

    public Cart addToCart(AddToCartDTO dto) {
        var entity = new Cart(
                dto.getId(),
                dto.getProductId(),
                dto.getBuyerId(),
                dto.getQuantity(),
                dto.getShipmentName()
        );
        return cartRepository.save(entity);
    }

    public List<DropdownDTO> getShipmentDropdown() {
        return shipmentRepository.getDropdown();
    }

    public ProductShopInfoDTO getProductInfo(Integer id) {
        var product = productRepository.findById(id).get();
        var seller = product.getSeller();
        var dto = new ProductShopInfoDTO(
                product.getId(),
                product.getName(),
                product.getCategoryName(),
                product.getDescription(),
                product.getPrice(),
                seller.getName()
        );
        return dto;
    }

}
