package gc._4.pr2.grupo1.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gc._4.pr2.grupo1.entity.Productos;
import gc._4.pr2.grupo1.repository.ProductosRepository;
import gc._4.pr2.grupo1.service.IProductosService;


@Service
public class ProductosServiceImp implements IProductosService {

	@Autowired
	private ProductosRepository repo;

	@Override
	public List<Productos>mostrarTodos() {
		
		return repo.findAll();
		
	}

	@Override
	public Productos mostrarPorId(int id) {
	
		return repo.findById(id).orElse(null);
	}

	@Override
	public Productos guardar(Productos p) {
		
		return repo.save(p);
	}

	@Override
	public void eliminarPorId(int id) {
		repo.deleteById(id);
		
	}

	
	
}
