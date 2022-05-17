package cl.andres.java.cft.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/") // las vistas nuevas agregan su mapping a la ruta absoluta. este es localhost:xxxx/alumno/nuevo
	public String index() {
		return "index";
	}
	
	
}
