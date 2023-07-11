package io.github.ferdynandariza.marketplace.service;

import io.github.ferdynandariza.marketplace.dao.AccountDetailRepository;
import io.github.ferdynandariza.marketplace.dao.AccountRepository;
import io.github.ferdynandariza.marketplace.dto.account.RegisterDTO;
import io.github.ferdynandariza.marketplace.dto.admin.AdminDTO;
import io.github.ferdynandariza.marketplace.entity.Account;
import io.github.ferdynandariza.marketplace.entity.AccountDetail;
import io.github.ferdynandariza.marketplace.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountDetailRepository accountDetailRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Boolean isUsernameValid(String username) {
        var existingAccount = accountRepository.countUsername(username);
        return !(existingAccount > 0);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = accountRepository.findById(username).get();
        return new ApplicationUserDetails(entity);
    }

    @Transactional
    public void registerAccount(RegisterDTO dto) {
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        var entity = new Account(
                dto.getUsername(),
                hashedPassword,
                dto.getRole()
        );
        var detail = new AccountDetail(
                dto.getUsername(),
                dto.getName(),
                dto.getAddress(),
                0.0
        );
        accountRepository.save(entity);
        accountDetailRepository.save(detail);
    }

    public String getRoleByUsername(String username) {
        var entity = accountRepository.findById(username).get();
        return entity.getRole();
    }

    public void registerAdmin(AdminDTO dto) {
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        var entity = new Account(
                dto.getUsername(),
                hashedPassword,
                dto.getRole()
        );
        accountRepository.save(entity);
    }
}
