package gc._4.pr2.grupo1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Admin>> mostrarTodosFactura() {
		return new ResponseEntity<List<Admin>>(service.mostrarTodos(),HttpStatus.OK);
	}

	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/Admin")
    public String login(@RequestBody Admin admin) {
    	System.out.println(" entro ");
        /*UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUser(), admin.getContraseña());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);*/
        if (/*authentication.isAuthenticated()*/true) {
        	System.out.println(" entro2 ");
           
        } /*else {
        	System.out.println("no entro ");
            return "/";
        }*/
        return "redirect:/Admin/init/new";
    }
    
    
   
    
    
    
}
