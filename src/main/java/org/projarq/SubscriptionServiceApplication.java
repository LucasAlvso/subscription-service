package org.projarq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class SubscriptionServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}
}
