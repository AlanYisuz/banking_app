package com.azriel.bankingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.azriel.bankingapp.dto.AccountDto;
import com.azriel.bankingapp.entity.Account;
import com.azriel.bankingapp.mapper.AccountMapper;
import com.azriel.bankingapp.repository.AccountRepository;
import com.azriel.bankingapp.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto); 
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist!!"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, Double amount) {
		Account account =accountRepository.
				findById(id).
				orElseThrow(() -> new RuntimeException("Account does not exist"));
		double newAmount = account.getBalance() + amount;
		account.setBalance(newAmount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, Double amount) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exits"));
		if(account.getBalance()>= amount) {
			double newBalance = account.getBalance() - amount;
			account.setBalance(newBalance);
			Account savedAccount = accountRepository.save(account);
			return AccountMapper.mapToAccountDto(savedAccount);
		}else {
			throw new RuntimeException("the amount is insuffient");
		}
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> listAccounts = accountRepository.findAll();
		return listAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccountById(Long id) {
		if(accountRepository.findById(id).isEmpty()) {
			throw new RuntimeException("Account does not exist");
		}else {
			accountRepository.deleteById(id);
		}
		
	}
	
}
