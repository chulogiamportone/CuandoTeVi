package gc._4.pr2.grupo1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import gc._4.pr2.grupo1.entity.Admin;
import gc._4.pr2.grupo1.service.IAdminService;

@Controller
@RequestMapping("/")
public class AdminController {
	@Autowired
	private IAdminService service;

	@GetMapping("/Admin")
	public String admin() {
		return "prod/login";
	}

	@GetMapping("/Adminget")
	@ResponseBody
	public List<Admin> mostrarTodosFactura() {
		return service.mostrarTodos();
	}
/*
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/Admin")
    public String login(@RequestBody Admin admin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUser(), admin.getContraseña());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated()) {
            return "Login exitoso";
        } else {
            return "Login fallido";
        }
    }*/
}
