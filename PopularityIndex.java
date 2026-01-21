/* Arbol AVL
*  Laura Pedraza y Jorge Zarate
*  */
import java.util.ArrayList;
import java.util.List;

public class PopularityIndex {
    public NodoAVL raiz;

    public PopularityIndex(){
        this.raiz = null;
    }
    private int altura(NodoAVL n){
        if(n == null){
            return 0;
        }
        return n.altura;
    }

    private void actualizarAltura(NodoAVL n) {
        if (n != null) {
            n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
        }
    }

    private int getFactorEquilibrio(NodoAVL n) {
        if (n == null) {
            return 0;
        }
        return altura(n.izq) - altura(n.der);
    }
    private NodoAVL rotacionDerecha(NodoAVL actual) {
        NodoAVL aux = actual.izq;
        NodoAVL T2 = aux.der;

        // Realizar la rotación
        aux.der = actual;
        actual.izq = T2;

        // Actualizar alturas, crucial actualizar primero altura de actual
        // ahora es hijo de aux
        actualizarAltura(actual);
        actualizarAltura(aux);

        // Devolver la nueva raíz del subárbol
        return aux;
    }

    private NodoAVL rotacionIzquierda(NodoAVL actual) {
        NodoAVL aux = actual.der;
        NodoAVL T2 = aux.izq;

        // Realizar la rotación
        aux.izq = actual;
        actual.der = T2;

        // Actualizar alturas, crucial actualizar primero altura de actual
        // ahora es hijo de aux
        actualizarAltura(actual);
        actualizarAltura(aux);

        // Devolver la nueva raíz del subárbol
        return aux;
    }

    // actualiza la popularidad de una publicacion en el arbol
    public void actualizarPopularidad(int publicacionId, int nuevaPopularidad){
            raiz = actualizarRecursivo(raiz, publicacionId, nuevaPopularidad);
    }
    private NodoAVL actualizarRecursivo(NodoAVL nodo, int publicacionId, int nuevaPopularidad ){
            //eliminar el nodo existente
        nodo = eliminarRecursivo(nodo,publicacionId);
        //insertar con la nueva popularidad
        nodo = insertarRecursivo(nodo, publicacionId, nuevaPopularidad);
        return nodo;
    }
    private NodoAVL insertarRecursivo(NodoAVL nodo, int publicacionId, int popularidad){
        if(nodo == null){
            return new NodoAVL(publicacionId, popularidad);
        }
        //comparar por popularidad mayor a la derecha
        if(popularidad < nodo.popularidad){
            nodo.izq = insertarRecursivo(nodo.izq, publicacionId, popularidad);
        }else if (popularidad > nodo.popularidad ){
            nodo.der = insertarRecursivo(nodo.der, publicacionId, popularidad);
        }else{
            //si tienen la misma popularidad comparar por id
            if(publicacionId < nodo.publicacionId){
                nodo.izq = insertarRecursivo(nodo.izq, publicacionId,popularidad);
            }else if(publicacionId > nodo.publicacionId){
                nodo.der = insertarRecursivo(nodo.der,publicacionId,popularidad);
            }else{
                //ya existe , no insertar duplicado
                return nodo;
            }
        }
    actualizarAltura(nodo);
        int factorEquilibrio = getFactorEquilibrio(nodo);
        //caso izquierda-izquierda
        if(factorEquilibrio>1 && popularidad < nodo.izq.popularidad){
            return rotacionDerecha(nodo);
        }
        // Caso derecha-derecha
        if (factorEquilibrio < -1 && popularidad > nodo.der.popularidad) {
            return rotacionIzquierda(nodo);
        }

        // Caso izquierda-derecha
        if (factorEquilibrio > 1 && popularidad > nodo.izq.popularidad) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }

        // Caso derecha-izquierda
        if (factorEquilibrio < -1 && popularidad < nodo.der.popularidad) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }
    private NodoAVL eliminarRecursivo(NodoAVL nodo, int publicacionId) {
        if (nodo == null) {
            return nodo;
        }

        // Buscar el nodo por publicacionId
        boolean encontrado = (nodo.publicacionId == publicacionId);

        if (!encontrado) {
            // Buscar en ambos subárboles
            nodo.izq = eliminarRecursivo(nodo.izq, publicacionId);
            nodo.der = eliminarRecursivo(nodo.der, publicacionId);
        } else {
            // Nodo encontrado, proceder a eliminarlo

            // Caso 1: Nodo con un hijo o sin hijos
            if (nodo.izq == null) {
                return nodo.der;
            } else if (nodo.der == null) {
                return nodo.izq;
            }

            // Caso 2: Nodo con dos hijos
            // Obtener el sucesor inorden (menor del subárbol derecho)
            NodoAVL sucesor = obtenerMinimo(nodo.der);

            // Copiar los datos del sucesor al nodo actual
            nodo.publicacionId = sucesor.publicacionId;
            nodo.popularidad = sucesor.popularidad;

            // Eliminar el sucesor
            nodo.der = eliminarRecursivo(nodo.der, sucesor.publicacionId);
        }

        // Actualizar altura y rebalancear
        actualizarAltura(nodo);
        int factorEquilibrio = getFactorEquilibrio(nodo);

        // Caso izquierda-izquierda
        if (factorEquilibrio > 1 && getFactorEquilibrio(nodo.izq) >= 0) {
            return rotacionDerecha(nodo);
        }

        // Caso izquierda-derecha
        if (factorEquilibrio > 1 && getFactorEquilibrio(nodo.izq) < 0) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }

        // Caso derecha-derecha
        if (factorEquilibrio < -1 && getFactorEquilibrio(nodo.der) <= 0) {
            return rotacionIzquierda(nodo);
        }

        // Caso derecha-izquierda
        if (factorEquilibrio < -1 && getFactorEquilibrio(nodo.der) > 0) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }
    private NodoAVL obtenerMinimo (NodoAVL nodo){
        NodoAVL actual = nodo;
        while (actual.izq != null){
            actual = actual.izq;
        }
        return actual;
    }
    //obtener las top N publicaciones mas populares (recorrido in order inverso)
    public List<Integer> obtenerTopN(int n){
        List<Integer> topPublicaciones = new ArrayList<>();
        if(n <= 0 || raiz == null){
            return topPublicaciones;
        }
        obtenerTopNRecursivo(raiz, n, topPublicaciones);
        return topPublicaciones;
    }
    private void obtenerTopNRecursivo(NodoAVL nodo, int n, List<Integer> lista){
        if(nodo == null || lista.size() >= n){
            return;
        }

        //primer recorrido al arbol derecho (mayor popularidad)
        obtenerTopNRecursivo(nodo.der, n, lista);

        // agregar el nodo actual si aun no tenemos n elementos
        if(lista.size() < n){
            lista.add(nodo.publicacionId);
        }
        //recorrer sub arbol izquierdo
        obtenerTopNRecursivo(nodo.izq, n, lista);
    }
}
