package gc._4.pr2.grupo1.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gc._4.pr2.grupo1.entity.Productos;
import gc._4.pr2.grupo1.service.IProductosService;
import gc._4.pr2.grupo1.service.IUploadFileService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private IProductosService service;


	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping()
	public String index() {
		return "main/Index";
	}
	@GetMapping("productos")
	public String prod() {
		
		return "main/Productos";
	}
	
	@GetMapping("listproductos")
	@ResponseBody
	public List<Productos> mostrarTodosProductos() {
		return service.mostrarTodos();
	}
	@GetMapping(value = "uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
}
