package com.dsr.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {

	@Autowired
	LimitsConfiguration limitConfig;
	
	@GetMapping("/limits")
	public LimitsConfiguration getLimits() {
		return new LimitsConfiguration(limitConfig.getMinimum(), limitConfig.getMaximum());
	}
}
