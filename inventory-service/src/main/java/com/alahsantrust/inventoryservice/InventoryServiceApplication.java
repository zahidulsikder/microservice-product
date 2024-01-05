package com.alahsantrust.inventoryservice;

import com.alahsantrust.inventoryservice.data.models.entity.Inventory;
import com.alahsantrust.inventoryservice.data.payloads.responses.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
