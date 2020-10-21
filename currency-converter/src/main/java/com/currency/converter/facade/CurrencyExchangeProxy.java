package com.currency.converter.facade;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currency.converter.ConvertedPrice;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000/")
@FeignClient(name = "currency-exchange-service")	// For ribbon load balancer
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ConvertedPrice getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to) ;
	
	
	@GetMapping("/test")
	public String sayTest() ;
}
