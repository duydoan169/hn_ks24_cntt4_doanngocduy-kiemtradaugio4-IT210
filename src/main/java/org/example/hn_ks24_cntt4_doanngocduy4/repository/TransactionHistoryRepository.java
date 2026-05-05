package org.example.hn_ks24_cntt4_doanngocduy4.repository;

import org.example.hn_ks24_cntt4_doanngocduy4.model.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    Page<TransactionHistory> findTransactionHistoriesByWallet_Id(Long id, Pageable pageable);

    @Query("""
        select th from TransactionHistory th where
        (:min is null or th.amount >= :min)
    """)
    Page<TransactionHistory> search(@Param("min") double min, Pageable pageable);
}
