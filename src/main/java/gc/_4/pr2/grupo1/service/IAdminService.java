package gc._4.pr2.grupo1.service;

import java.util.List;

import gc._4.pr2.grupo1.entity.Admin;

public interface IAdminService {
	
	public List<Admin>mostrarTodos();
	public Admin mostrarPorId(int id);
	public Admin guardar(Admin Admin);
	public void eliminarPorId(int id);
	


}
