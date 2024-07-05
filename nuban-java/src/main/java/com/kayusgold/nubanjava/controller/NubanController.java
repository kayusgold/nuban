package com.kayusgold.nubanjava.controller;

import com.kayusgold.nubanjava.models.Account;
import com.kayusgold.nubanjava.models.Bank;
import com.kayusgold.nubanjava.services.NubanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NubanController {
    private final NubanService nubanService;

    public NubanController(NubanService nubanService) {
        this.nubanService = nubanService;
    }

    @PostMapping("/banks")
    public ResponseEntity<Object> getBanks(@RequestBody(required = true) Account account) {
        try {
            List<Bank> banks = this.nubanService.getAccountNumberBanks(account.getAccountNumber());

            return ResponseEntity.ok(banks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{accountNumber}/banks")
    public ResponseEntity<Object> getAccountBanks(@PathVariable String accountNumber) {
        if(!isValidAccountNumber(accountNumber)) {
            return ResponseEntity.badRequest().body("Invalid account number format");
        }

        try {
            List<Bank> banks = this.nubanService.getAccountNumberBanks(accountNumber);

            return ResponseEntity.ok(banks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches("\\d{10}");  // Regex for 10 digits
    }
}
