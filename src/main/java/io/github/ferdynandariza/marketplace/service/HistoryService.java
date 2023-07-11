package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.AccountDetailRepository;
import io.github.ferdynandariza.marketplace.dao.HistoryRepository;
import io.github.ferdynandariza.marketplace.dto.DropdownDTO;
import io.github.ferdynandariza.marketplace.dto.history.HistoryRowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    AccountDetailRepository accountDetailRepository;

    private final int rowPerPage = 10;

    public Page<HistoryRowDTO> getTable(String sellerId, String buyerId, Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("id"));
        var table = historyRepository.getTable(sellerId, buyerId, pageable);
        return table;
    }

    public List<DropdownDTO> getSellerDropdown() {
        return accountDetailRepository.getSellerDropdown();
    }

    public List<DropdownDTO> getBuyerDropdown() {
        return accountDetailRepository.getBuyerDropdown();
    }
}
