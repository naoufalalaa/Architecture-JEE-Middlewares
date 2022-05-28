package ma.enset.digitalbanking.services;

import ma.enset.digitalbanking.dtos.*;
import ma.enset.digitalbanking.entities.BankAccount;
import ma.enset.digitalbanking.entities.CurrentAccount;
import ma.enset.digitalbanking.entities.Customer;
import ma.enset.digitalbanking.entities.SavingAccount;
import ma.enset.digitalbanking.exceptions.BalanceNotSufficientException;
import ma.enset.digitalbanking.exceptions.BankAccountNotFoundException;
import ma.enset.digitalbanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double intrestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    List<CustomerDTO> searchCustomers(String keyword);
    List<BankAccountDTO> bankAccountList();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    //get bank account by customer id
    List<BankAccountDTO> getBankAccountByCustomerId(Long customerId);

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer (String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}