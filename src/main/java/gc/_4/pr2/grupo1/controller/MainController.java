package gc._4.pr2.grupo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping()
	public String index() {
		return "main/Index";
	}
	@GetMapping("productos")
	public String prod() {
		return "main/Productos";
	}
	
}
