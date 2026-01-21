/* Arbol n-ario
   Laura Pedraza y Jorge Zarate
* */

public class PostTree {
    public NodoArbol contenidoRaiz;

    //iniciar raiz con publicacion original
    public PostTree(Publicacion p) {
        this.contenidoRaiz = new NodoArbol(p);
    }

    //agregar comentario como un nodo hijo del nodo padre (publicacion o comentario)
    public void agregarComentario(int idPadre, Comentario c) {
        if(c == null){
            System.out.println("Error: El comentario no puede ser nulo");
            return;
        }

        NodoArbol nodoPadre = buscarNodo(idPadre);
        if (nodoPadre == null) {
            System.out.println("Error: No se encontró el nodo padre con ID " + idPadre);
            return;
        }

        NodoArbol nuevoComentario = new NodoArbol(c);
        nodoPadre.hijos.add(nuevoComentario);
    }

    //Busca un nodo por su id publicacion o comentario
    public NodoArbol buscarNodo(int id) {
        return buscarNodoRecursivo(contenidoRaiz, id);
    }

    private NodoArbol buscarNodoRecursivo(NodoArbol nodo, int id) {
        if (nodo == null) {
            return null;
        }
        //ver si el actual tiene el id que se esta buscando
        int idActual = -1;
        if (nodo.data instanceof Publicacion) {
            idActual = ((Publicacion) nodo.data).getId();
        } else if (nodo.data instanceof Comentario) {
            idActual = ((Comentario) nodo.data).getId();
        }
        if (idActual == id) {
            return nodo;
        }
        //buscar en los hijos
        for (NodoArbol hijo : nodo.hijos) {
            NodoArbol resultado = buscarNodoRecursivo(hijo, id);
            if (resultado != null) {
                return resultado;
            }
        }
        return null;
    }
}
