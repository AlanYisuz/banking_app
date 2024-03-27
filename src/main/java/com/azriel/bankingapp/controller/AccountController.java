package com.azriel.bankingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azriel.bankingapp.dto.AccountDto;
import com.azriel.bankingapp.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;
	
	@PostMapping("/createAccount")
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
		return new ResponseEntity(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
}
