package ob.entregas.SpringBoot.Laptop.repositorys;

import ob.entregas.SpringBoot.Laptop.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {
}
