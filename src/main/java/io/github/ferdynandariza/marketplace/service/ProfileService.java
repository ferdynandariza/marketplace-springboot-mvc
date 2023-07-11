package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.AccountDetailRepository;
import io.github.ferdynandariza.marketplace.dao.AccountRepository;
import io.github.ferdynandariza.marketplace.dao.HistoryRepository;
import io.github.ferdynandariza.marketplace.dto.profile.AddBalanceDTO;
import io.github.ferdynandariza.marketplace.dto.profile.ProfileDTO;
import io.github.ferdynandariza.marketplace.dto.profile.ProfileHistoryRowDTO;
import io.github.ferdynandariza.marketplace.entity.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    AccountDetailRepository accountDetailRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    AccountRepository accountRepository;

    private final int rowPerPage = 10;


    public ProfileDTO getProfile(String username) {
        return accountDetailRepository.getProfile(username);
    }

    public Page<ProfileHistoryRowDTO> getTable(String username, Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("id"));
        var role = accountRepository.findById(username).get().getRole();
        Page<ProfileHistoryRowDTO> table;
        table = role.equals("Buyer") ?
                historyRepository.getTableOfBuyer(username, pageable) :
                historyRepository.getTableOfSeller(username, pageable);
        return table;
    }

    public AccountDetail addBalance(AddBalanceDTO dto) {
        var user = accountDetailRepository.findById(dto.getUsername()).get();
        var currentBalance = user.getBalance();
        user.setBalance(currentBalance + dto.getBalanceAddititon());
        return accountDetailRepository.save(user);
    }

}
