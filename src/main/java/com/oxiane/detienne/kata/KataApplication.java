package com.oxiane.detienne.kata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oxiane.detienne.kata.repository.AccountDao;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(KataApplication.class);

	@Autowired
	private AccountDao accountDao;

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// to check datas in db on startup (for tests only)
		logger.info("KataApplication...");
		logger.debug("\nvalid account\n{}", accountDao.findById(10011100099l));
		logger.debug("\nclose account\n{}", accountDao.findById(20022200066l));
		logger.debug("\ninvalid account\n{}", accountDao.findById(40044400011l));

	}

}
