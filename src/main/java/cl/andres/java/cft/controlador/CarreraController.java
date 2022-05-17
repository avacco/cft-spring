package cl.andres.java.cft.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.andres.java.cft.modelo.Carrera;
import cl.andres.java.cft.repository.CarreraRepository;

@Controller
@RequestMapping("/carrera")
public class CarreraController {

	@Autowired
	CarreraRepository carreraRepository;
	
	@GetMapping("/nuevo")
	public String carreraNueva(Carrera carrera) {
		return "carrera/form";
	}
	
	@GetMapping("/editar/{carreraId}")
	public String carreraEditar(@PathVariable int carreraId, Model modelo) {
		Carrera carrera = carreraRepository.findById(carreraId);
		modelo.addAttribute("carrera", carrera);
		return "carrera/form";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam(name = "id", required = true) int id) {
		carreraRepository.delete(id);
		return "redirect:/carrera/listado";
	}
	
	@PostMapping("/procesar")
	public String carreraProcesar(@Valid Carrera carrera, BindingResult informeValidacion) {
		if(informeValidacion.hasErrors()) {
			return "carrera/form";
		}
		
		if (carrera.getId() == 0) {
			carreraRepository.create(carrera);
		}else {
			carreraRepository.edit(carrera);
		}
		return "redirect:/carrera/listado";
	}
	
	@GetMapping("/listado")
	public String listarCarrera(Model modelo){
		List<Carrera> carreras = carreraRepository.findAll();
		modelo.addAttribute("carreras",carreras);
		return "carrera/listado";
	}
}
