/* interactuar con la red social
   Laura Pedraza y Jorge Zarate
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocialNetworkManager {
    public UserManager userManager;
    public HashMap<Integer, PostTree> posts;
    public PopularityIndex popularityIndex;
    public HashMap<Integer, ActivityHistory> userActivity;

    //para que los id de las publicaciones y los comentarios sean diferentes
    private int contadorPublicaciones = 0;
    private int contadorComentarios = 0;

    public SocialNetworkManager() {
        this.userManager = new UserManager();
        this.posts = new HashMap<>();
        this.popularityIndex = new PopularityIndex();
        this.userActivity = new HashMap<>();
    }

    // Crea un usuario y su historial de actividad
    public void crearUsuario(String username) {
        if(username == null || username.trim().isEmpty()){
            System.out.println("Error: Username no puede ser nulo o vacío");
            return;
        }
        if(userManager.obtenerUsuario(username) != null){
            System.out.println("Error: El usuario '" + username + "' ya existe");
            return;
        }

        userManager.registrarUsuario(username);
        Usuario usuario = userManager.obtenerUsuario(username);
        if (usuario != null) {
            userActivity.put(usuario.getId(), new ActivityHistory());
            userActivity.get(usuario.getId()).registrarActividad("\n Usuario " + usuario.getUsername() + " ID: "+usuario.getId() +" creado");
        }
    }

    // Crea una publicación, la añade al HashMap y la inicializa en popularityIndex
    public int crearPublicacion(String user, String cont) {
        Usuario usuario = userManager.obtenerUsuario(user);
        if (usuario == null) {
            System.out.println("Error : usuario no encontrado");
            return -1;
        }
        if(cont == null || cont.trim().isEmpty()){
            System.out.println("Error: contenido vacio");
            return -1;
        }
        contadorPublicaciones++;
        int pubId = contadorPublicaciones;
        Publicacion nuevaPublicacion = new Publicacion(pubId, usuario.getId(), cont);
        PostTree arbol = new PostTree(nuevaPublicacion);
        posts.put(pubId, arbol);

        // Inicializar popularidad en 0
        popularityIndex.actualizarPopularidad(pubId, 0);

        String mensaje = "\n Publicación " + nuevaPublicacion.getId() +
                "\n - " + nuevaPublicacion.getContenido() + " - " +
                "\nAutor: " + nuevaPublicacion.getAutorid() +
                "\n el " + nuevaPublicacion.getFechaLegible() +
                "\n =============================================";

        userActivity.get(usuario.getId()).registrarActividad(mensaje);

        return pubId;
    }

    // Busca la publicación, crea el comentario y lo añade al PostTree
    public void comentarPublicacion(String user, int pubId, int padId, String cont) {
        Usuario usuario = userManager.obtenerUsuario(user);
        if(usuario == null){
            System.out.println("Error: usuario no encontrado");
            return;
        }
        if (!posts.containsKey(pubId)) {
            System.out.println("Error: publicación no encontrada");
            return;
        }
        if(cont == null || cont.trim().isEmpty()){
            System.out.println("Error: contenido vacio");
            return;
        }

        PostTree arbol = posts.get(pubId);
        NodoArbol nodoPadre = arbol.buscarNodo(padId);
        if(nodoPadre == null){
            System.out.println("Error nodo padre no encontrado");
            return;
        }
        contadorComentarios++;
        int comId = contadorComentarios; // ID único para comentario
        Comentario nuevoComentario = new Comentario(comId, usuario.getId(), cont);

        arbol.agregarComentario(padId, nuevoComentario);

        String mensaje = "\n Comentario " + nuevoComentario.getId() +
                "\n - " + nuevoComentario.getContenido() + " - " +
                "\n en publicación " + pubId +
                "\n por " + nuevoComentario.getAutorid() +
                "\n el " + nuevoComentario.getFechaLegible()+
                "\n =============================================";
        userActivity.get(usuario.getId()).registrarActividad(mensaje);
    }

    // Incrementa la popularidad y actualiza en popularityIndex
    public void darLike(int publicacionId) {
        if (!posts.containsKey(publicacionId)) {
            System.out.println("Error: Publicación no encontrada");
            return;
        }
        // Buscar la publicación en el índice para obtener su popularidad
        NodoAVL nodo = buscarEnAVL(popularityIndex.raiz, publicacionId);
        int popularidadActual = 0;
        if (nodo != null) {
            popularidadActual = nodo.popularidad;
        }
        // Incrementar y actualizar
        popularityIndex.actualizarPopularidad(publicacionId, popularidadActual + 1);
    }

    // auxiliar para buscar en el AVL
    private NodoAVL buscarEnAVL(NodoAVL nodo, int publicacionId) {
        if (nodo == null) {
            return null;
        }
        if (nodo.publicacionId == publicacionId) {
            return nodo;
        }
        NodoAVL izq = buscarEnAVL(nodo.izq, publicacionId);
        if (izq != null) {
            return izq;
        }
        return buscarEnAVL(nodo.der, publicacionId);
    }

    // Consulta el popularityIndex para obtener las top n publicaciones
    public List<Integer> getTopNPublicaciones(int n) {
        return popularityIndex.obtenerTopN(n);
    }

    // Obtiene el historial de un usuario a través de userActivity
    public List<String> getHistorialDeUsuario(String user, int n) {
        Usuario usuario = userManager.obtenerUsuario(user);
        if (usuario == null || !userActivity.containsKey(usuario.getId())) {
            System.out.println("Error: Usuario no encontrado o sin historial");
            return new ArrayList<>();
        }
        return userActivity.get(usuario.getId()).obtenerUltimasActividades(n);
    }

}
