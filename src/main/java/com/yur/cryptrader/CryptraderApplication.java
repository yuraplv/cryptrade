package com.yur.cryptrader;

import com.yur.cryptrader.common.currency.Currency;
import com.yur.cryptrader.platforms.exmo.ExmoPriceReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptraderApplication implements CommandLineRunner {

	@Autowired
	ExmoPriceReceiver exmoPriceReceiver;

	public static void main(String[] args) {
		SpringApplication.run(CryptraderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Successfuly run app!!!");
		System.out.println("Got currency for BTC: \n" + exmoPriceReceiver.getPrice(Currency.BTC));
	}
}
