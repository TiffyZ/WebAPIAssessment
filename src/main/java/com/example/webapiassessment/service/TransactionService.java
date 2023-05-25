package com.example.webapiassessment.service;
import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.domain.Transaction;
import com.example.webapiassessment.repository.TransactionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;

    public TransactionService(TransactionRepository transactionRepository, CustomerService customerService) {
        this.transactionRepository = transactionRepository;
        this.customerService = customerService;
    }

    /**
     * Create a new transaction for a specific customer.
     *
     * @param customerId The ID of the customer.
     * @param date       The date of the transaction.
     * @param amount     The amount of the transaction.
     * @return The created transaction.
     */
    public Transaction createTransaction(Long customerId, LocalDate date, double amount) throws ChangeSetPersister.NotFoundException {
        Customer customer = customerService.getCustomerById(customerId);
        Transaction transaction = new Transaction(customer, date, amount);
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieve transactions for a specific customer by their ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of transactions associated with the specified customer ID.
     */
    public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    /**
     * Retrieve transactions between start and end months for a specific customer.
     *
     * @param customerId The ID of the customer.
     * @param startDate The start month of the date range.
     * @param endDate The end month of the date range.
     * @return The list of transactions between the specified months for the customer.
     */
    public List<Transaction> getTransactionsByCustomerIdAndDateRange(Long customerId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
    }
}
