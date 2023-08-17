package model;

public class ListaEnlazada implements List {
    Nodo cabeza;
    int size;
    private static boolean flagAddPatient = false;
    private static String buscarPacienteMsg = "";

    public ListaEnlazada() {
        cabeza = null;
        size = 0;
    }

    public String addFirstPlace(Patient n){
        if(verifyEmpty()){
            cabeza = new Nodo(n);
        } else {
            Nodo temp = cabeza;
            Nodo newCabeza = new Nodo(n);
            newCabeza.enlazarSiguiente(temp);
        }
        size++;
        return "Paciente añadido correctamente";
    }
    public String addPatient(Patient n, int input, String newCode){
        flagAddPatient = false;
        String message = "";
        if(verifyEmpty()){
            cabeza = new Nodo(n);
            if(input==2){
                message = "Patient successfully added";
            } else {
                message = "Patient successfully added first, no patients were registered";
            }
        } else {
            Nodo temp = new Nodo(n);
            Nodo tempCabeza = cabeza;
            if(input==2){
                recursiveAddLastPlace(temp, tempCabeza);
                message = "Patient successfully added";
            } else if (input==3){
                recursiveAddAfterPatient(temp, tempCabeza, newCode);
                message = "Patient succesfully added after the patient "+newCode;
            } else {
                recursiveAddBeforePatient(temp, tempCabeza, newCode);
                message = "Patient succesfully added before the patient "+newCode;
            }
        }
        size++;
        if(flagAddPatient==true){
            message = "Could not add the new patient, patient identified with code "+newCode+" was not found";
            size--;
        }
        return message;
    }
    public void recursiveAddLastPlace(Nodo temp, Nodo tempCabeza){
        if (verifySiguiente(tempCabeza)){
            tempCabeza.enlazarSiguiente(temp);
        } else {
            Nodo tempSiguiente = tempCabeza.obtenerSiguiente();
            recursiveAddLastPlace(temp,tempSiguiente);
        }
    }
    public void  recursiveAddAfterPatient(Nodo temp, Nodo tempCabeza, String newCode) {
        if(verifyNodeCode(tempCabeza, newCode)){
            Nodo siguiente = tempCabeza.obtenerSiguiente();
            temp.enlazarSiguiente(siguiente);
            tempCabeza.enlazarSiguiente(temp);
        } else {
            if(tempCabeza.obtenerSiguiente()!=null){
                Nodo siguiente = tempCabeza.obtenerSiguiente();
                recursiveAddAfterPatient(temp, siguiente, newCode);
            } else {
                flagAddPatient = true;
            }
        }

    }
    public void recursiveAddBeforePatient(Nodo temp, Nodo tempCabeza, String newCode) {
        if(verifyNodeCode(tempCabeza, newCode)){
            temp.enlazarSiguiente(tempCabeza);
            cabeza = temp;
        } else if(tempCabeza.obtenerSiguiente()!=null) {
            if((verifyNodeCode(tempCabeza.obtenerSiguiente(), newCode))){
                Nodo siguiente = tempCabeza.obtenerSiguiente();
                temp.enlazarSiguiente(siguiente);
                tempCabeza.enlazarSiguiente(temp);
            } else {
                Nodo siguiente = tempCabeza.obtenerSiguiente();
                recursiveAddAfterPatient(temp, siguiente, newCode);
            }
        } else {
            flagAddPatient = true;
        }
    }

    public String eliminarPaciente(String code) {
        flagAddPatient = false;
        String message = "";
        if(verifyEmpty()){
            flagAddPatient = true;
        } else {
            Nodo tempCabeza = cabeza;
            recursiveEliminarPaciente(tempCabeza, code);
        }
        size--;
        message = "The patient identified with code "+code+ " was deleted succesfully";
        if(flagAddPatient==true){
            message = "Could not delete the patient, patient identified with code "+code+" was not found";
            size++;
        }
        return message;
    }
    public void recursiveEliminarPaciente(Nodo tempCabeza, String code){
        if(verifyNodeCode(tempCabeza, code)){
            Nodo temp = tempCabeza.obtenerSiguiente();
            tempCabeza.enlazarSiguiente(null);
            cabeza = temp;
        } else if(tempCabeza.obtenerSiguiente()!=null) {
            if((verifyNodeCode(tempCabeza.obtenerSiguiente(), code))){
                Nodo siguiente = tempCabeza.obtenerSiguiente().obtenerSiguiente();
                tempCabeza.obtenerSiguiente().enlazarSiguiente(null);
                tempCabeza.enlazarSiguiente(siguiente);
            } else {
                Nodo siguiente = tempCabeza.obtenerSiguiente();
                recursiveEliminarPaciente(siguiente, code);
            }
        } else {
            flagAddPatient = true;
        }
    }
    public String buscarPaciente(String code) {
        buscarPacienteMsg = "";
        if(verifyEmpty()){
            buscarPacienteMsg = "Patient identified with code "+code+ " was not found";
        } else {
            Nodo tempCabeza = cabeza;
            recursiveBuscarPaciente(tempCabeza, code);
        }
        return buscarPacienteMsg;
    }
    public void recursiveBuscarPaciente(Nodo tempCabeza, String code) {
        if(verifyNodeCode(tempCabeza, code)){
            buscarPacienteMsg = tempCabeza.toStringBuscar();
        } else if(tempCabeza.obtenerSiguiente()!=null) {
            Nodo siguiente = tempCabeza.obtenerSiguiente();
            recursiveBuscarPaciente(siguiente, code);
        } else {
            buscarPacienteMsg = "Patient identified with code "+code+ " was not found";
        }
    }
    public String editarPaciente(String[] fieldsSeparated, String[] answerSeparated, String code) {
        Nodo primero = cabeza;

            String msg = "The patient identified with code "+code+" was not found";
            Nodo tempCabeza = cabeza;
            boolean flag = true;
            for(int i = 0; i < size; i++){
                if(verifyNodeCode(tempCabeza, code)){
                    for(int j = 0; j < fieldsSeparated.length; j++){
                        switch (fieldsSeparated[j]){
                            case "NAME": tempCabeza.obtenerPaciente().setName(answerSeparated[j]);break;
                            case "HOSPITAL": tempCabeza.obtenerPaciente().setHospital(answerSeparated[j]);break;
                            case "DIAGNOSIS": tempCabeza.obtenerPaciente().setDiagnosis(answerSeparated[j]);break;
                            case "CODE": tempCabeza.obtenerPaciente().setCode(answerSeparated[j]);break;
                        }
                    }
                    msg = "Patient information was succesfully updates";
                    flag = false;
                } else if(flag){
                    if(tempCabeza.obtenerSiguiente()!=null){
                        Nodo temp = tempCabeza;
                        tempCabeza = temp.obtenerSiguiente();
                    }
                }
            }
        return msg;
    }
    public String prueba() {
        String msg = "";
        Nodo ca = cabeza;
        for(int i = 0; i < size; i++){
            msg += ca.obtenerPaciente().getCode()+"\n";
            ca = ca.obtenerSiguiente();
        }
        return msg;
    }


    public boolean verifyNodeCode(Nodo tempCabeza, String newCode) {
        return tempCabeza.getCode().equalsIgnoreCase(newCode);
    }
    public boolean verifyEmpty(){
        return cabeza==null;
    }
    public boolean verifySiguiente(Nodo n){
        return n.obtenerSiguiente()==null;
    }
}
