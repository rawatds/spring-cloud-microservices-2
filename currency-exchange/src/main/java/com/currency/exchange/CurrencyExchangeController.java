package com.currency.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private CurrencyExchangeRepo repo;
	
	@Autowired
	Environment env;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to) {
//		List<CurrencyExchange> findByFromAndTo = repo.findAllByFromCAndToC(from, to);
//		if (findByFromAndTo.isEmpty()) {
//			return null;
//		}
//		return findByFromAndTo.get(0);
		
		return repo.findByFromCAndToC(from, to);
		
	}
	
	
	@GetMapping("/test")
	public String sayTest() {
		return "Test at port : " + env.getProperty("server.port"); 
	}
	
}

