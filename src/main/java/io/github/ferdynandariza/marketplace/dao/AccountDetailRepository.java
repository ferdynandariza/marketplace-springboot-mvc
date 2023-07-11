package io.github.ferdynandariza.marketplace.dao;

import io.github.ferdynandariza.marketplace.dto.DropdownDTO;
import io.github.ferdynandariza.marketplace.dto.profile.ProfileDTO;
import io.github.ferdynandariza.marketplace.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDetailRepository extends JpaRepository<AccountDetail, String> {


    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.profile.ProfileDTO(
                ad.name,
                a.role,
                ad.address,
                ad.balance
            )
            FROM AccountDetail AS ad
                INNER JOIN ad.account AS a
            WHERE ad.username = :username
            """)
    ProfileDTO getProfile(@Param("username") String username);

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.DropdownDTO(
                ad.username,
                ad.name
            )
            FROM AccountDetail AS ad
                INNER JOIN ad.account AS a
            WHERE a.role = 'Seller'
            """)
    List<DropdownDTO> getSellerDropdown();

    @Query("""
            SELECT new io.github.ferdynandariza.marketplace.dto.DropdownDTO(
                ad.username,
                ad.name
            )
            FROM AccountDetail AS ad
                INNER JOIN ad.account AS a
            WHERE a.role = 'Buyer'
            """)
    List<DropdownDTO> getBuyerDropdown();
}
