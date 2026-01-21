/*
* Lista doblemente enlazada
* Laura Pedraza y Jorge Zarate
*/

import java.util.List;
import java.util.ArrayList;
public class ActivityHistory {
    public NodoDoble cabeza;
    public NodoDoble cola;

    public ActivityHistory(){
        cabeza = null;
        cola=null;
    }

    public void registrarActividad (String desc){
        if(desc == null || desc.trim().isEmpty()){ //descripción nula o vacia
            System.out.println("Error: Descripción vacia");
            return;
        }
        NodoDoble nuevo = new NodoDoble(desc);
        if(cabeza == null){
            cabeza = nuevo;
            cola = nuevo;
        }else{ //agregar al final
            cola.sig = nuevo;
            nuevo.ant = cola;
            cola = nuevo;
        }
    }
//retornar las ultimas n actividades recorriendo hacia atras
    public List<String> obtenerUltimasActividades(int n){
        List<String> actividades = new ArrayList<>();
        if(n<=0){
            System.out.println("Ingresar un número mayor a 0");
            return actividades;
        }
        NodoDoble actual = cola;
        int count = 0;

        while(actual != null && count < n){
            actividades.add(actual.descripcion);
            actual = actual.ant;
            count++;
        }
        return actividades;
    }

}
