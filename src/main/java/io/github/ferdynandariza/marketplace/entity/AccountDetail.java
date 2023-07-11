package io.github.ferdynandariza.marketplace.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AccountDetail")
public class AccountDetail {

    @Id
    @Column(name = "Username")
    private String username;

    @OneToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Balance")
    private Double balance;


    public AccountDetail(String username,
                         String name,
                         String address,
                         Double balance) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }


}
