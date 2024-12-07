package gc._4.pr2.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gc._4.pr2.grupo1.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

}
