package ob.entregas.SpringBoot.Laptop;

import ob.entregas.SpringBoot.Laptop.entities.Laptop;
import ob.entregas.SpringBoot.Laptop.controllers.LaptopController;
import ob.entregas.SpringBoot.Laptop.repositorys.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class LaptopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LaptopApplication.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
		LaptopController laptopController = context.getBean(LaptopController.class);

		Laptop laptop1 = new Laptop(null,"Apple","10x.2",399.90);
		Laptop laptop2 = new Laptop(null,"Apple","300XY",599.90);
		Laptop laptop3 = new Laptop(null,"HP","HP-J100",279.90);

		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
		laptopRepository.save(laptop3);


		//Laptop recibido de postman
		List<Laptop> listaLaptops = laptopRepository.findAll();

		for (Laptop laptops : listaLaptops) {

			System.out.println(laptops);
		}





	}

}
