package com.example.skypay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyPayApplication.class, args);
		AccountService account = new AccountServiceImpl();


		account.deposit(1000);
		account.deposit(2000);
		account.withdraw(500);

		account.printStatement();

	}

}
