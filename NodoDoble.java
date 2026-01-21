/*
 * Laura Pedraza y Jorge Zarate
 * */
public class NodoDoble {
    public String descripcion;
    public NodoDoble ant , sig;

    public NodoDoble(String descripcion){
        this.descripcion = descripcion;
        sig = null ;
        ant = null ;
    }
}
