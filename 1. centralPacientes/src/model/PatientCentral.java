package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase controladora del caso de estudio del texto
 * @author angievig
 * @version 1.0
 * @since August 2023
 */
public class PatientCentral {
	
	private ListaEnlazada listaEnlazada;
	


	public PatientCentral() {
		listaEnlazada = new ListaEnlazada();
	}



	public String eliminarPaciente(String code) {
		return listaEnlazada.eliminarPaciente(code);
	}

	public String buscarPaciente(String code) {
		return listaEnlazada.buscarPaciente(code);
	}
	public String editarPaciente(String fields, String answer, String code) {
		String[] fieldsSepareted = fields.split("-");
		String[] answerSeparated = answer.split("-");
		for(int i = 0; i < fieldsSepareted.length; i++){
			String temp = fieldsSepareted[i];
			fieldsSepareted[i] = temp.toUpperCase();
		}
		return listaEnlazada.editarPaciente(fieldsSepareted, answerSeparated, code);

	}
	public String agregarPaciente(String code, String name, String hospital, String diagnosis, int genreInt, int day, int month, int year, int option, String newCode) {
		Genre genre = getGenre(genreInt);
		Date date = getDate(year, month, day);
		String out = "";
		Patient patient = new Patient(code, name, hospital, diagnosis, genre, date);
		if(option==1){
			out = listaEnlazada.addFirstPlace(patient);
		} else{
			out = listaEnlazada.addPatient(patient, option, newCode);
		}
		return out;
	}

	public Genre getGenre(int genre) {
		Genre gen = Genre.HOMBRE;
		if(genre==2){
			gen = Genre.MUJER;
		}
		return gen;
	}
	public Date getDate(int year, int month, int day) {

		Date date = new Date(year-1900, Calendar.DECEMBER, day);
		switch (month){
			case 1: date = new Date(year-1900, Calendar.JANUARY, day); break;
			case 2: date = new Date(year-1900, Calendar.FEBRUARY, day); break;
			case 3: date = new Date(year-1900, Calendar.MARCH, day); break;
			case 4: date = new Date(year-1900, Calendar.APRIL, day); break;
			case 5: date = new Date(year-1900, Calendar.MAY, day); break;
			case 6: date = new Date(year-1900, Calendar.JUNE, day); break;
			case 7: date = new Date(year-1900, Calendar.JULY, day); break;
			case 8: date = new Date(year-1900, Calendar.AUGUST, day); break;
			case 9: date = new Date(year-1900, Calendar.SEPTEMBER, day); break;
			case 11: date = new Date(year-1900, Calendar.NOVEMBER, day); break;
			case 10: date = new Date(year-1900, Calendar.OCTOBER, day); break;

		}
		return date;
	}
	public String prueba() {
		return listaEnlazada.prueba();
	}
	
}
