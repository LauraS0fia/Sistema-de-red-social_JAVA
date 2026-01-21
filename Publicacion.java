/*
 * Laura Pedraza y Jorge Zarate
 * */
import java.text.SimpleDateFormat;
import java.util.Date;
public class Publicacion {
    private int id;
    private int autorid;
    private String contenido;
    private long timestamp; //marca de tiempo de la creacion

    public Publicacion (int id, int autorid, String contenido){
        this.id = id;
        this.autorid = autorid;
        this.contenido = contenido;
        this.timestamp = System.currentTimeMillis(); //genera el timestamp automaticamente
    }
    public int getId(){
        return id;
    }
    public int getAutorid(){
        return autorid;
    }
    public String getContenido(){
        return contenido;
    }
    public long getTimestamp(){
        return timestamp;
    }
    public String getFechaLegible(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date(this.getTimestamp()));
    }
}
