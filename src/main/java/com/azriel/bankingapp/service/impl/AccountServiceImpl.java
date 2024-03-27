package com.azriel.bankingapp.service.impl;

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
	
}
