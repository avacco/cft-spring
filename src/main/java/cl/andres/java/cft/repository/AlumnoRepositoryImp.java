package cl.andres.java.cft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.andres.java.cft.modelo.Alumno;
import cl.andres.java.cft.modelo.Carrera;

@Repository
public class AlumnoRepositoryImp implements AlumnoRepository {

	// Spring autoinstancia esta clase con Autowired. Utiliza los datos en application.properties para conectar a la BD.
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	CarreraRepository carreraRepository;
	
	// Crea un objeto alumno con los resultados del query entrante.
	private Alumno makeObject(ResultSet rs, int numFila) throws SQLException{
		int id 						= rs.getInt("id");
		String nombre 				= rs.getString("nombre");
		LocalDate fechaNacimiento 	= rs.getObject("fecha_nacimiento",LocalDate.class);
		int idCarrera 				= rs.getInt("carrera_id");
		Carrera carrera 			= carreraRepository.findById(idCarrera);
		return new Alumno(id,nombre,fechaNacimiento,carrera);
	}
	
	// Los query entrantes en cuestion
	@Override
	public List<Alumno> findAll() {
		return jdbcTemplate.query("SELECT * FROM alumnos", this::makeObject);
	}

	@Override
	public Alumno findById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM alumnos WHERE id = ?", this::makeObject, id);
	}

	@Override
	public void create(Alumno alumno) {
		String sql = "INSERT INTO alumnos(nombre, fecha_nacimiento, carrera_id) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql,alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getCarrera().getId());
	}

	@Override
	public void edit(Alumno alumno) {
		String sql = "UPDATE alumnos SET nombre = ?, fecha_nacimiento = ?, carrera_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, alumno.getNombre(), alumno.getFechaNacimiento(), alumno.getId(), alumno.getCarrera().getId());
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM alumnos WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

}
