package cl.andres.java.cft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.andres.java.cft.modelo.Carrera;
import cl.andres.java.cft.repository.CarreraRepository;

@SpringBootApplication
public class CftSpringApplication implements WebMvcConfigurer{

	@Autowired
	private CarreraRepository carreraRepository;
		
	
	public static void main(String[] args) {
		SpringApplication.run(CftSpringApplication.class, args);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// convierte un objeto String a Carrera
		registry.addConverter(new Converter<String, Carrera>(){
			// el metodo que maneja la conversion
			@Override
			public Carrera convert(String strFieldFormCarreraId) {
				int id = Integer.parseInt(strFieldFormCarreraId);
				Carrera carrera = carreraRepository.findById(id);
				return carrera;
			}
		});
	}
}
