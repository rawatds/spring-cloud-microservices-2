package com.currency.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {

	Integer id;
	String fromC;
	String toC;
	Float conversionRate;
}
