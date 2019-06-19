package com.yur.cryptrader;

import com.yur.cryptrader.common.currency.CurrencyPair;
import com.yur.cryptrader.platforms.binance.BinancePriceReceiver;
import com.yur.cryptrader.platforms.exmo.ExmoPriceReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptraderApplication implements CommandLineRunner {

	@Autowired
	private ExmoPriceReceiver exmoPriceReceiver;
	@Autowired
	private BinancePriceReceiver binancePriceReceiver;

	public static void main(String[] args) {
		SpringApplication.run(CryptraderApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Successfuly run app!!!");
		System.out.println("Got currency from Exmo for BTC: \n" + exmoPriceReceiver.getPrice(CurrencyPair.BTC_USDT));
		System.out.println("Got currency from Binance for BTC: \n" + binancePriceReceiver.getPrice(CurrencyPair.BTC_USDT));
	}
}
