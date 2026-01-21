/*
 * Laura Pedraza y Jorge Zarate
 * */
import java.util.List;
import java.util.ArrayList;
public class NodoArbol {
   public Object data ; //puede ser publicacion o comentario
   public List<NodoArbol> hijos ; //comentarios anidados

   public NodoArbol(Object data){
       this.data = data;
       this.hijos = new ArrayList<>();
   }
}
