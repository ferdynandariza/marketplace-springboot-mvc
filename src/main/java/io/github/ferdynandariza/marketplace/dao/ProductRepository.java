package io.github.ferdynandariza.marketplace.dao;

import io.github.ferdynandariza.marketplace.dto.merchandise.MerchandiseRowDTO;
import io.github.ferdynandariza.marketplace.dto.shop.ShopRowDTO;
import io.github.ferdynandariza.marketplace.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.merchandise.MerchandiseRowDTO(
                p.id,
                p.name,
                p.categoryName,
                CASE 
                    WHEN p.discontinue = true THEN 'Yes'
                    WHEN p.discontinue = false THEN 'No'
                END,
                false
            )
            FROM Product AS p
            WHERE p.sellerId = :username
            """)
    Page<MerchandiseRowDTO> getTable(@Param("username") String username, Pageable pageable);

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.shop.ShopRowDTO(
                p.id,
                p.name,
                p.price
            )
            FROM Product AS p
            WHERE p.name LIKE %:name%
                AND p.categoryName LIKE %:category%
                AND p.description LIKE %:description%
                AND p.discontinue = false 
            """)
    Page<ShopRowDTO> getShopTable(@Param("name") String name,
                                  @Param("category") String category,
                                  @Param("description") String description,
                                  Pageable pageable);

}
