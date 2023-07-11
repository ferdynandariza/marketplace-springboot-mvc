package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.AccountDetailRepository;
import io.github.ferdynandariza.marketplace.dao.CartRepository;
import io.github.ferdynandariza.marketplace.dao.HistoryRepository;
import io.github.ferdynandariza.marketplace.dto.cart.CartRowDTO;
import io.github.ferdynandariza.marketplace.entity.AccountDetail;
import io.github.ferdynandariza.marketplace.entity.Cart;
import io.github.ferdynandariza.marketplace.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    AccountDetailRepository accountDetailRepository;

    private final int rowPerPage = 10;

    public Page<CartRowDTO> getTable(String buyerId, Integer page) {
        var pageable = PageRequest.of(page - 1, rowPerPage, Sort.by("id"));
        var table = cartRepository.getTable(buyerId, pageable);

        for (var dto : table) {
            var entity = cartRepository.findById(dto.getId()).get();
            var totalPrice = entity.getShipment().getPrice() +
                    (entity.getQuantity().doubleValue() * entity.getProduct().getPrice());

            dto.setProduct(entity.getProduct().getName());
            dto.setQuantity(entity.getQuantity());
            dto.setShipment(entity.getShipmentName());
            dto.setSeller(entity.getProduct().getSeller().getName());
            dto.setTotalPrice(totalPrice);
        }
        return table;
    }

    public void remove(Integer id) {
        cartRepository.deleteById(id);
    }

    public Cart findById(Integer id) {
        return cartRepository.findById(id).get();
    }

    @Transactional
    public Boolean purchaseAll(String buyerId) {
        var cartIds = cartRepository.getAllIdByBuyer(buyerId);
        List<History> histories = new ArrayList<>();
        Double grandTotal = 0.0;
        for (var id : cartIds) {
            var cart = cartRepository.findById(id).get();

            var history = new History(
                    null,
                    cart.getBuyerId(),
                    cart.getProductId(),
                    cart.getQuantity(),
                    cart.getShipmentName(),
                    getTotalPrice(cart),
                    LocalDate.now()
            );

            histories.add(history);
            grandTotal += getTotalPrice(cart);
        }
        if (getBuyerBalance(buyerId) >= grandTotal) {
            for (var id : cartIds) {
                var cart = cartRepository.findById(id).get();
                addSellerBalance(cart);
                subtractBuyerBalance(buyerId, grandTotal);
                cartRepository.deleteById(id);
            }
            historyRepository.saveAll(histories);
            return true;
        }
        return false;
    }

    private Double getTotalPrice(Cart cart) {
        var totalPrice =
                cart.getShipment().getPrice() +
                        (cart.getQuantity().doubleValue() * cart.getProduct().getPrice());
        return totalPrice;
    }

    private Double getBuyerBalance(String buyerId) {
        return accountDetailRepository.findById(buyerId).get().getBalance();
    }

    private AccountDetail addSellerBalance(Cart cart) {
        var product = cart.getProduct();
        var quantity = cart.getQuantity();
        var seller = product.getSeller();
        var gain = quantity.doubleValue() * product.getPrice();
        var currentBalance = seller.getBalance();
        seller.setBalance(currentBalance + gain);
        return accountDetailRepository.save(seller);
    }

    private AccountDetail subtractBuyerBalance(String buyerId, Double grandTotal) {
        var buyer = accountDetailRepository.findById(buyerId).get();
        var currentBalance = buyer.getBalance();
        buyer.setBalance(currentBalance - grandTotal);
        return accountDetailRepository.save(buyer);
    }
}
