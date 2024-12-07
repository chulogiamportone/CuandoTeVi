package gc._4.pr2.grupo1.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gc._4.pr2.grupo1.entity.Admin;
import gc._4.pr2.grupo1.repository.AdminRepository;
import gc._4.pr2.grupo1.service.IAdminService;

@Service
public class AdminServiceImp implements IAdminService {

	@Autowired
	private AdminRepository repo;

	@Override
	public List<Admin> mostrarTodos() {
		return repo.findAll();
	}

	@Override
	public Admin mostrarPorId(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Admin guardar(Admin a) {
		return repo.save(a);
	}

	@Override
	public void eliminarPorId(int id) {
		repo.deleteById(id);
	}

}
