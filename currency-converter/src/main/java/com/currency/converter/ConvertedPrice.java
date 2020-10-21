package com.currency.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedPrice {
	Integer id;
	String fromC;
	String toC;
	Float conversionRate;
	Float sourceAmout;
	Float targetAmount;
}
