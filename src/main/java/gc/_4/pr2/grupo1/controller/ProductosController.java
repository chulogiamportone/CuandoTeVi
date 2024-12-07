package gc._4.pr2.grupo1.controller;

import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gc._4.pr2.grupo1.entity.Productos;
import gc._4.pr2.grupo1.service.IProductosService;
import gc._4.pr2.grupo1.service.IUploadFileService;

@Controller
@RequestMapping("/Admin/init/")
public class ProductosController {
	@Autowired
	private IProductosService service;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("cargarproductos")
	public String listProductos(Model model) {
		try {
			model.addAttribute("listProductos", service.mostrarTodos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "prod/listProductos";
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

	@GetMapping("new")
	public String newProducto(Model model) {
		model.addAttribute("producto", new Productos());
		model.addAttribute("listProductos", service.mostrarTodos());
		return "prod/prod";
	}

	@PostMapping("save")
	public String saveProducto(@Validated @ModelAttribute("producto") Productos producto, BindingResult result, Model model,
			@RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "prod/prod";
		} else {
			if (!image.isEmpty()) {
				if (producto.getId() > 0 && producto.getImagen() != null && producto.getImagen().length() > 0) {
					uploadFileService.delete(producto.getImagen());
				}
				String uniqueFileName = uploadFileService.copy(image);
				producto.setImagen(uniqueFileName);
			}
			service.guardar(producto);
			status.setComplete();
		}
		return "redirect:/Admin/init/cargarproductos";
	}

	@RequestMapping("update/{id}")
	public String goUpdate(@PathVariable(value = "id") int id, Model model) {
		Productos producto = service.mostrarPorId(id);
		model.addAttribute("producto", producto);
		return "prod/prod";
	}
	
	@RequestMapping("detail/{id}")
	public String goDetail(@PathVariable(value = "id") int id, Model model) {
		Productos producto = service.mostrarPorId(id);
		model.addAttribute("producto", producto);
		return "prod/prodDetail";
	}

	@RequestMapping("delete/{id}")
	public String eliminar(@PathVariable(value = "id") int id, Model model) {
		try {
			service.eliminarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Admin/init/cargarproductos";
	}
}
