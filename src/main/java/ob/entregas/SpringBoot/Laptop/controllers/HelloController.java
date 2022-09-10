package ob.entregas.SpringBoot.Laptop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//EJERCICIO 1
@RestController
public class HelloController {

    @GetMapping("/HelloController/{name}")
    public String saludo(@PathVariable String name){

        return "Hola " +name+ " estás ejecutando desde spring";

    }

}
