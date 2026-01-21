/* Hashmap para gestionar usuarios por
*  accesos con username
*  Laura Pedraza y Jorge Zarate
*/
import java.util.HashMap;

public class UserManager {
    public HashMap<String, Usuario> usuarios;

    public UserManager(){
        this.usuarios = new HashMap<>();
    }

    //Registrar nuevo usuario
    public void registrarUsuario(String username){
        if(username == null || username.trim().isEmpty()){
            System.out.println("Error:username no puede ser nulo o vacio");
            return;
        }
        if(!usuarios.containsKey(username)){
            int id = usuarios.size()+1; //id unico
            Usuario nuevoUsuario = new Usuario(id, username);
            usuarios.put(username, nuevoUsuario);
        }else{
            System.out.println("Error: el usuario '"+ username + "' ya existe");
        }
    }
    //obtener usuario por su username
    public Usuario obtenerUsuario(String username){
        return usuarios.get(username);
    }

}
