package gc._4.pr2.grupo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gc._4.pr2.grupo1.entity.Factura;
import gc._4.pr2.grupo1.service.FacturaService;



@RestController
public class FacturaController {
	@Autowired
	private FacturaService service;
	
	@GetMapping("/factura")
	public List<Factura> mostrarTodosFactura(){
		
		return service.mostrarTodos();
	}
	
	@GetMapping("/factura/{id}")
	Factura mostrarFacturaId(@PathVariable("id") Long id){
		
		return service.mostrarPorId(id);
	}
	
	@PostMapping("/factura")
	Factura crearNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		
		return service.guardar(facturaDesdeElServicio);
	}
	
	@PutMapping("/factura")
	Factura actualizarNuevoFactura(@RequestBody Factura facturaDesdeElServicio){
		
		return service.guardar(facturaDesdeElServicio);
	}
	
	@DeleteMapping("/factura/{id}")
	void borrarFactura(@PathVariable("id") Long id){
		service.eliminarPorId(id);
	}
}
