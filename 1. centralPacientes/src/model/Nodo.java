package model;

import java.util.Date;

public class Nodo {

    Patient paciente;
    Nodo siguiente;
    public Nodo(Patient n){
        this.paciente = n;
        this.siguiente = null;
    }

    public Patient obtenerPaciente(){
        return paciente;
    }
    public void enlazarSiguiente(Nodo n){
        siguiente = n;
    }
    public Nodo obtenerSiguiente(){
        return siguiente;
    }
    public String getCode() {
        return this.paciente.getCode();
    }
    public String toStringBuscar() {
        String msg = "Informacion del paciente:\n";
        msg +="\nCode: "+paciente.getCode();
        msg += "\nName: "+paciente.getName();
        msg += "\nHospital: "+paciente.getHospital();
        msg += "\nDiagnosis: "+paciente.getDiagnosis();
        msg += "\nGenre: "+paciente.getGenre();
        msg += "\nBirth date: "+paciente.DateFormat();
        return msg;
    }
}
