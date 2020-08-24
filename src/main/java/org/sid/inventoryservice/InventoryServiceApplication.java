package org.sid.inventoryservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Entity
class Product{
	@Id @GeneratedValue
	private Long id;
	private String name;
	private double price;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}

@RepositoryRestResource
interface ProduitRepository extends JpaRepository<Product, Long>{}

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start( ProduitRepository produitRepository,RepositoryRestConfiguration restConfiguration  ) {
		return args->{
			restConfiguration.exposeIdsFor(Product.class);
			produitRepository.save(new Product(null, "ord HP 878", 8700));
			produitRepository.save(new Product(null, "ord Mac Book", 12000));
			produitRepository.save(new Product(null, "Imprmante Epson 32", 700));
			produitRepository.findAll().forEach(System.out::println);
			
		};
	}
	

}
