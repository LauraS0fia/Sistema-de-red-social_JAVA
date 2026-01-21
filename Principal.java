public class Principal {
    public static void main(String[] args) {
        SocialNetworkManager red = new SocialNetworkManager();

        System.out.println("=== CREANDO USUARIOS ===");
        red.crearUsuario("maria");
        red.crearUsuario("pedro");
        red.crearUsuario("juan");
        red.crearUsuario("maria"); // INVALIDO: duplicado
        red.crearUsuario(""); // INVALIDO: vacio
        red.crearUsuario(null); // INVALIDO: nulo

        System.out.println("\n=== CREANDO PUBLICACIONES ===");
        int pub1 = red.crearPublicacion("maria", "Mi primera publicacion!");
        int pub2 = red.crearPublicacion("pedro", "Hola a todos");
        int pub3 = red.crearPublicacion("juan", "Buenos dias");
        int pub4 = red.crearPublicacion("usuarioFalso", "Esto no existe"); // INVALIDO: usuario no existe
        int pub5 = red.crearPublicacion("maria", ""); // INVALIDO: contenido vacio
        int pub6 = red.crearPublicacion("pedro", null); // INVALIDO: contenido nulo

        System.out.println("\n=== COMENTANDO PUBLICACIONES ===");
        red.comentarPublicacion("pedro", pub1, pub1, "Buen post Maria!");
        red.comentarPublicacion("juan", pub1, pub1, "Me gusta este tema");
        red.comentarPublicacion("maria", pub2, pub2, "Gracias por compartir");
        red.comentarPublicacion("pedro", 999, 999, "Comentario invalido"); // INVALIDO: publicacion no existe
        red.comentarPublicacion("usuarioFalso", pub1, pub1, "Hola"); // INVALIDO: usuario no existe
        red.comentarPublicacion("maria", pub1, 999, "Comentario"); // INVALIDO: nodo padre no existe
        red.comentarPublicacion("juan", pub2, pub2, ""); // INVALIDO: contenido vacio

        System.out.println("\n=== DANDO LIKES ===");
        red.darLike(pub1);
        red.darLike(pub1);
        red.darLike(pub1);
        red.darLike(pub2);
        red.darLike(pub3);
        red.darLike(pub3);
        red.darLike(999); // INVALIDO: publicacion no existe

        System.out.println("\n=== TOP PUBLICACIONES ===");
        System.out.println("Top 3: " + red.getTopNPublicaciones(3));
        System.out.println("Top 5: " + red.getTopNPublicaciones(5));
        System.out.println("Top 0: " + red.getTopNPublicaciones(0)); // INVALIDO: n=0

        System.out.println("\n=== HISTORIAL DE USUARIOS ===");
        System.out.println("Historial maria (ultimas 3): " + red.getHistorialDeUsuario("maria", 3));
        System.out.println("Historial pedro (ultimas 5): " + red.getHistorialDeUsuario("pedro", 5));
        System.out.println("Historial juan (ultimas 2): " + red.getHistorialDeUsuario("juan", 2));
        System.out.println("Historial invalido (usuario no existe): " + red.getHistorialDeUsuario("noExisto", 2));
        System.out.println("Historial con n=0: " + red.getHistorialDeUsuario("maria", 0)); // INVALIDO: n=0
        System.out.println("Historial con n negativo: " + red.getHistorialDeUsuario("pedro", -1)); // INVALIDO: n<0
    }
}