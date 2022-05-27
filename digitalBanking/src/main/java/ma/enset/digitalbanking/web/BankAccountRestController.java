package ma.enset.digitalbanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.digitalbanking.dtos.AccountHistoryDTO;
import ma.enset.digitalbanking.dtos.AccountOperationDTO;
import ma.enset.digitalbanking.dtos.BankAccountDTO;
import ma.enset.digitalbanking.exceptions.BalanceNotSufficientException;
import ma.enset.digitalbanking.exceptions.BankAccountNotFoundException;
import ma.enset.digitalbanking.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> getBankAccount() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/{accountId}/operations")
    public  void debiter(
            @PathVariable String accountId,@RequestBody double amount) throws BalanceNotSufficientException, BankAccountNotFoundException {
        bankAccountService.debit(accountId,amount,"debiter");
    }

}