package ui;

/*
(5) eliminar un paciente
(6) consultar sus datos
(7) editar su información médica, para lo cual el usuario debe
suministrar el respectivo código del paciente.
 */

import model.PatientCentral;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase principal del proyecto
 * @author angievig
 * @version 1.0
 * @since August 2023
 */
public class MainPatientCentral {

    /**
     * con es la asociación con la clse controladora del modelo
	 */
    private PatientCentral central;
    /**
     * sc es el objeto que permite leer informaciOn digitada por el usuario
     */
    private Scanner lector;

    /**
     * Constructor de la clase, no recibe prametros.
     * El mEtodo inicializa el objeto lector
     */
    public MainPatientCentral() {
        //iniciando objetos
        lector = new Scanner(System.in);
        central = new PatientCentral();
    }


    public static void main (String[] a){
        // creando un objeto de la clase principal
        MainPatientCentral central = new MainPatientCentral();
        // variable para leer la entrada
        int option= 0;

        //ciclo para ejecutar el menu mientras que el usuario no
        //elija la opciOn para salir (0)
        do {
            option =central.showMenuAndGetOption();
            central.answerOption(option);
        }while (option !=0);


    }


    /**
     * Metodo que se encarga de llamar a los mEtodos que resuelven cada uno de los
     * requerimientos de la aplicaciOn
     * @param userOption, int es el nUmero ingresado por el usuario (no ha sido validado)
     */
    public void answerOption(int userOption) {
        switch(userOption) {
            case 0:
                System.out.println("Cerrando la aplicacion");
                break;
            case 1:
                menuAgregarPaciente();
                break;
            case 2:
                eliminarPaciente();
                break;
            case 3:
                buscarPaciente();
                break;
            case 4:
                editarPaciente();
                break;
            case 5:
                prueba();
                break;
        }
    }
    /**
     * Metodo que muestra el menu de la aplicaciOn, a este metod hace falta implementar la validaciOn
     * del valor ingresado por el usuario
     * @return input, int es la opciOn elegida por el usuario
     */
    public int showMenuAndGetOption() {
        System.out.println("\n\nMenu de la aplicación, digite una opcion\n"+
                "(1) agregar paciente\n" +
                "(2) eliminar paciente\n" +
                "(3) consultar datos de paciente\n"+
                "(4) Editar informacion medica de paciente\n"+
                "(0) Para salir"

        );
        return lector.nextInt();
    }
    private void menuAgregarPaciente(){
        int option = showMenuAgregarPacienteAndGetOption();
        if(option>0&&option<5){agregarPaciente(option);}
    }
    private int showMenuAgregarPacienteAndGetOption() {
        System.out.println("\n\nMenu para agregar pacientes, digite una opcion\n"+
                "(1) agregar paciente de primero\n" +
                "(2) agregar paciente de ultimo\n" +
                "(3) agregar paciente despues de otro paciente\n"+
                "(4) agregar paciente antes de otro paciente\n"+
                "(0) Para devolverse"
        );
        return lector.nextInt();
    }
    private void agregarPaciente(int option) {

        lector.nextLine();

        System.out.println("Este es el menu para agregar un nuevo paciente");
        System.out.println("Digite el codigo del paciente");
        String code = lector.nextLine();
        System.out.println("Digite el nombre del paciente");
        String name = lector.nextLine();
        System.out.println("Digite el nombre del hospital");
        String hospital = lector.nextLine();
        System.out.println("Digite el diagnostico");
        String diagnosis = lector.nextLine();
        boolean flag = true;
        int genre = 0;
        while(flag){
            System.out.println("1. Hombre 2. Mujer");
            genre = lector.nextInt();
            if(genre==1||genre==2){
                flag = false;
            } else {
                System.out.println("Digite un numero valido");
            }
        }
        System.out.println("Digite el dia de nacimiento del paciente");
        int day = lector.nextInt();
        System.out.println("Digite el mes de nacimiento del paciente");
        int month = lector.nextInt();
        System.out.println("Digite el año de nacimiento del paciente");
        int year = lector.nextInt();
        String newCode = "";
        if(option==3||option==4) {
            lector.nextLine();
            System.out.println("Ingresar el codigo del paciente ya ingresado en el hospital " + hospital);
            newCode = lector.nextLine();
        }
        System.out.println(central.agregarPaciente(code, name, hospital, diagnosis, genre, day, month, year, option, newCode));

    }
    private void eliminarPaciente() {

        lector.nextLine();
    	System.out.println("Digite el código del paciente que desea eliminar");
    	String code = lector.nextLine();
        System.out.println(central.eliminarPaciente(code));

	}
	private void buscarPaciente() {
        lector.nextLine();

		System.out.println("Digite el código del paciente que desea buscar");
    	String code = lector.nextLine();
        System.out.println(central.buscarPaciente(code));
	}
    public void editarPaciente() {
        lector.nextLine();

        System.out.println("Digite el codigo del paciente");
        String code = lector.nextLine();
        String info = (central.buscarPaciente(code));
        System.out.println(info);
        if(!info.contains("was not found")){

            System.out.println("\nType the fields you want to edit (except for date of birth and genre), separated by a '-', in such a way: ");
            System.out.println("Name-Hospital-Code");
            String fields = lector.nextLine();
            System.out.println("\nIn the same order as the fields were written, separated by a '-', type the updated information, in such a way: ");
            System.out.println("Juan David-San Jose-007");
            String answer = lector.nextLine();
            System.out.println(central.editarPaciente(fields, answer, code));
        }

    }
    public void prueba(){
        System.out.println(central.prueba());
        System.out.println("SE ARREGLO EL ERROR");
    }






}
