/*
 * Laura Pedraza y Jorge Zarate
 * */
public class NodoAVL {
    public int popularidad;
    public int publicacionId;
    public int altura;
    public NodoAVL izq , der;

    public NodoAVL(int publicacionId, int popularidad) {
        this.publicacionId = publicacionId;
        this.popularidad = popularidad;
        this.altura = 1; // Un nodo nuevo siempre tiene altura 1
        this.izq = null;
        this.der = null;
    }
}
