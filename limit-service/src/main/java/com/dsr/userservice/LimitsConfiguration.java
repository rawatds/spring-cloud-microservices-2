package com.dsr.userservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ConfigurationProperties("limit-service")
@Component
public class LimitsConfiguration {

	private int minimum;
	private int maximum;
}
