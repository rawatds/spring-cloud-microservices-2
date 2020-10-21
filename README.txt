Main Project
============

These are various microservices services and Eureka naming server (Registry) and the order of startup is as:

1. EurekaServer : It is Eureka Naming Server (aka Eureka Registry). It starts at port 8761.
2. Start three instances of "currency-exchange" service at port 8000, 8001, 8002 by setting "-Dserver.port=800x" at VM args in Eclipse/IntelliJ
3. Start the "currency-converter" service. It internally calls one of the three instance of "currency-exchange" service using Ribbon load balancer. It runs on port 8100.

To test:

a. To list all services registered to Eureka server
	http://localhost:8761/

b. Individual "currency-exchange" service can be tested using:
	http://localhost:800x/currency-exchange/from/USD/to/INR

c. To test the Ribbon load balance using Feign client, try:
	http://localhost:8100/currency-converter-feign/from/USD/to/INR/1000
	
	(it will give you different "conversionRate" depending on which "currency-exchange" service its pointing to)

	Other url to test Ribbon load balancer
	http://localhost:8100/test

d. To test the non-load balanced and non-feign version try
	http://localhost:8100/currency-converter/from/USD/to/INR/1000	
	
	(it will give you same result always, as its getting from "currency-exchange" server port 8080)
	

Optional services:
====================
1. Cloud Config server. It is used to read common and service specific configuration from a git repository. It runs on 8888.
2. The "limit-service" is a sample microservices which takes input from config-server. It runs on 8080.

To test:

a. To see the config values:
	http://localhost:8888/limit-service/dev			(dev can also be replaced by default/qa)
	
b. The "limit-service" can be run on different profiles by typing profile name in "run config" window.



Thanks
Dharmender Rawat

	