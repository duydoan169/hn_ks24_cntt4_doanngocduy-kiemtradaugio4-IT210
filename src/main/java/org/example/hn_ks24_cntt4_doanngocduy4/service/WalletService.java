package org.example.hn_ks24_cntt4_doanngocduy4.service;

import lombok.RequiredArgsConstructor;
import org.example.hn_ks24_cntt4_doanngocduy4.model.TransactionHistory;
import org.example.hn_ks24_cntt4_doanngocduy4.model.Wallet;
import org.example.hn_ks24_cntt4_doanngocduy4.repository.TransactionHistoryRepository;
import org.example.hn_ks24_cntt4_doanngocduy4.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findById(fromWalletId)
                .orElseThrow(() -> new RuntimeException("Wallet nguồn không tồn tại"));

        Wallet toWallet = walletRepository.findById(toWalletId)
                .orElseThrow(() -> new RuntimeException("Wallet đích không tồn tại"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Số tiền không hợp lệ");
        }

        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Số dư không đủ");
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        walletRepository.save(fromWallet);

        toWallet.setBalance(toWallet.getBalance().add(amount));
        walletRepository.save(toWallet);

        TransactionHistory outHistory = new TransactionHistory();
        outHistory.setAmount(amount.multiply(BigDecimal.valueOf(-1)));
        outHistory.setWallet(fromWallet);
        transactionHistoryRepository.save(outHistory);

        TransactionHistory inHistory = new TransactionHistory();
        inHistory.setAmount(amount);
        inHistory.setWallet(toWallet);
        transactionHistoryRepository.save(inHistory);

    }
}
