package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.CartRepository;
import io.github.ferdynandariza.marketplace.dao.HistoryRepository;
import io.github.ferdynandariza.marketplace.dao.ProductRepository;
import io.github.ferdynandariza.marketplace.dto.merchandise.MerchandiseRowDTO;
import io.github.ferdynandariza.marketplace.dto.merchandise.ProductMerchantInfoDTO;
import io.github.ferdynandariza.marketplace.dto.merchandise.UpsertProductDTO;
import io.github.ferdynandariza.marketplace.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    CartRepository cartRepository;

    private final int rowPerPage = 10;

    public Page<MerchandiseRowDTO> getTable(String username, Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("id"));
        var table = productRepository.getTable(username, pageable);
        table.forEach((product) -> {
            product.setIsUsed(
                    (totalDependentCart(product.getId()) + totalDependentHistory(product.getId())
                    ) > 0);
        });
        return table;
    }

    public Product save(UpsertProductDTO dto) {
        var entity = new Product(
                dto.getId(),
                dto.getName(),
                dto.getCategoryName(),
                dto.getDescription(),
                dto.getSellerId(),
                dto.getPrice(),
                dto.getDiscontinue()
        );
        var result = productRepository.save(entity);
        return result;
    }

    public UpsertProductDTO findById(Integer id) {
        var entity = productRepository.findById(id).get();
        var dto = new UpsertProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getCategoryName(),
                entity.getDescription(),
                entity.getSellerId(),
                entity.getPrice(),
                entity.getDiscontinue()
        );
        return dto;
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public Product discontinue(Integer id) {
        var entity = productRepository.findById(id).get();
        entity.setDiscontinue(true);
        return productRepository.save(entity);
    }

    public ProductMerchantInfoDTO getProductInfo(Integer id) {
        var product = productRepository.findById(id).get();
        var dto = new ProductMerchantInfoDTO(
                product.getName(),
                product.getCategoryName(),
                product.getDescription(),
                product.getPrice(),
                product.getDiscontinue() ? "Yes" : "No"
        );
        return dto;
    }

    public Integer totalDependentHistory(Integer id) {
        return historyRepository.countProductDependent(id);
    }

    public Integer totalDependentCart(Integer id) {
        return cartRepository.countProductDependent(id);
    }

}
