package com.example.webapiassessment.repository;

import com.example.webapiassessment.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Retrieve transactions by customer ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of transactions associated with the specified customer ID.
     */
    List<Transaction> findByCustomerId(Long customerId);
    List<Transaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

}
