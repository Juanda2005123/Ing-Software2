package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un paciente, ejemplo simplificado del caso de estudio del texto
 * @author angievig
 * @version 1.0
 * @since August 2023
 */
public class Patient {
	private String code;
	private String name;
	private String hospital;
	private String diagnosis;
	private Genre genre;
	private Date fechaNacimiento;

	public Patient(String code, String name, String hospital, String diagnosis, Genre genre, Date fechaNacimiento){
		this.code = code;
		this.name = name;
		this.hospital = hospital;
		this.diagnosis = diagnosis;
		this.genre = genre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String DateFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(this.fechaNacimiento);
	}
}
