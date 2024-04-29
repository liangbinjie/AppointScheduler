package poo.barberia;

import java.io.Serializable;

public class Servicio implements Serializable {
    private String nombre;
    
    public Servicio() {
        this.nombre = "";
    }
    
    public Servicio(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
    
}
