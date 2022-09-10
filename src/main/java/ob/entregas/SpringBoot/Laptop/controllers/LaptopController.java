package ob.entregas.SpringBoot.Laptop.controllers;

import ob.entregas.SpringBoot.Laptop.entities.Laptop;
import ob.entregas.SpringBoot.Laptop.repositorys.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.BooleanControl;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {


    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(BooleanControl.class);


    public LaptopController(LaptopRepository laptopRepository) {

        this.laptopRepository = laptopRepository;
    }

    //----> CRUD
        ////CREATE

    @PostMapping("/ApiRest/laptop/create")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {

        if (laptop.getId() != null) {

            log.warn("No se puede crear un producto indicando una ID");
            return ResponseEntity.badRequest().build();

        }

        Laptop result = laptopRepository.save(laptop);

        return ResponseEntity.ok(result);

    }

    ////UPDATE
    @PutMapping("/ApiRest/laptop/update")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        //comprobar que recibimos un id, es decir que el objeto existe
        //de no ser así, sería id nulo por lo tanto estaríamos creando objeto
        if (laptop.getId() == null){
            log.warn("Intentado actualizar un producto que no existe");
            return ResponseEntity.badRequest().build();
        }

        //Comprobamos que realmente existe el laptop
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("El laptop con id " +laptop.getId()+ " no existe en la BDD");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);

        return ResponseEntity.ok(result);

    }



    ////MOSTRAR
    @GetMapping("ApiRest/laptop/findAll")
    public List<Laptop> findAll() {

        return laptopRepository.findAll();

    }

    @GetMapping("ApiRest/laptop/findById/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id) {

        /*
        * Buscamos con la interfaz Jpa CRUD por id
        * guardamos en objeto clase Optional (permite trabajar con nulos)
        * */

        Optional<Laptop> laptopOptional = laptopRepository.findById(id);

        //Comprobamos si el objeto contiene datos o es nulo (no existe o nulo)
        if (laptopOptional.isPresent()){
           return ResponseEntity.ok(laptopOptional.get()); //obtenemos el objeto laptop con ese id
        } else {
            log.warn("No existe un producto con ese id");
           return ResponseEntity.notFound().build(); //no existe producto con ese id
        }

    }


    ////ELIMINAR
    //Misma dinámica, comprobar antes objeto existente

    @DeleteMapping("/ApiRest/laptop/delete/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!laptopRepository.existsById(id)){
            log.warn("Intentado eliminar un laptop que no existe");
            return  ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("ApiRest/laptop/deleteAll")
    public ResponseEntity<Laptop> deleteAll() {

        if (laptopRepository.count() == 0) {

            log.info("Trying to delete a empty DBB");
            return ResponseEntity.badRequest().build();

        }

        laptopRepository.deleteAll();

        return ResponseEntity.noContent().build();

    }




}
