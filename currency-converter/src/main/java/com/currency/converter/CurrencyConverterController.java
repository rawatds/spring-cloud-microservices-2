package com.currency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.converter.facade.CurrencyExchangeProxy;

@RestController
public class CurrencyConverterController {

	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate template;

	
	@Autowired
	private CurrencyExchangeProxy ceProxy;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello world running at port " + env.getProperty("server.port") + ", or " + env.getProperty("local.server.port") + "<br/>" +
					"JAVA_HOME: " + env.getProperty("JAVA_HOME");
	}
	
	@GetMapping("/test")
	public String test() {
		return ceProxy.sayTest();
	}
	
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/{amount}")
	public ConvertedPrice getCurrencyConvert(@PathVariable String from, @PathVariable String to, @PathVariable Float amount) {
		
	
		//RestTemplate template = new RestTemplate();
		//CurrencyExchange currEx = template.getForObject("http://localhost:8000/currency-exchange/from/" + from + "/to/" + to,
		//		CurrencyExchange.class);
		
		CurrencyExchange currEx = template.getForObject("http://currency-exchange-service/currency-exchange/from/" + from + "/to/" + to,
				CurrencyExchange.class);
		
		
		if (currEx == null) {
			return null;
		}
		return new ConvertedPrice(currEx.getId(), from, to, currEx.getConversionRate(), amount, amount*currEx.getConversionRate() );
	}
	
	
	
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/{amount}")
	public ConvertedPrice getCurrencyConvertViaFeign(@PathVariable String from, @PathVariable String to, @PathVariable Float amount) {
		
		ConvertedPrice convertedPrice=  ceProxy.getCurrencyExchangeRate(from, to);
		return new ConvertedPrice(convertedPrice.getId(), from, to, convertedPrice.getConversionRate(), amount, amount*convertedPrice.getConversionRate() );
	
	}
}
