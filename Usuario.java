/*
 * Laura Pedraza y Jorge Zarate
 * */
public class Usuario {
    private int id;
    private String username;

    public Usuario(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
}
