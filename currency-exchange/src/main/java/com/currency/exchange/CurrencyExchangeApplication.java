package com.currency.exchange;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CurrencyExchangeRepo repo) {
		return args -> {
			repo.save(new CurrencyExchange(1, "USD", "INR", (float)(Math.random() * 100.0)));
			repo.save(new CurrencyExchange(2, "GBP", "INR", 96.0f));
			repo.save(new CurrencyExchange(3, "EUR", "INR", 72.0f));
			
			repo.findAll().forEach(ce -> System.out.println(ce));
		};

	}
}


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class CurrencyExchange {

	@Id
	@GeneratedValue
	Integer id;
	String fromC;
	String toC;
	Float conversionRate;
}

@Repository
interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Integer> {
	//List<CurrencyExchange> findAllByFromCAndToC(@Param("from") String from, @Param("to") String to);
	CurrencyExchange findByFromCAndToC(String from, String to);
}


