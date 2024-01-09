package com.alahsantrust.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iPhone_13_yellow");
//			inventory.setQuantity(500);
//			inventoryRepository.save(inventory);
//			System.out.println(inventory);
//
//			Inventory inventory1 = new Inventory();
//			inventory.setSkuCode("iPhone_13_black");
//			inventory.setQuantity(0);
//
//
//			inventoryRepository.save(inventory1);
//			System.out.println(inventory1);
//		};
//	}



}
