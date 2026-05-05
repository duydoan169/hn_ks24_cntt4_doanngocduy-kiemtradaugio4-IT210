package org.example.hn_ks24_cntt4_doanngocduy4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal balance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    List<TransactionHistory> histories;
}
