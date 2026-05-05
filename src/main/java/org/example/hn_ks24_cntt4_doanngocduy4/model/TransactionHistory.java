package org.example.hn_ks24_cntt4_doanngocduy4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_history")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal amount;

    @JoinColumn(name = "wallet_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;
}
