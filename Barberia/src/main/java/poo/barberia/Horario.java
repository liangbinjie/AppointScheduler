package poo.barberia;

import java.io.Serializable;

public class Horario implements Serializable {
    private String dia;
    private int horaApertura;
    private int horaCierre;
    private boolean cerrado;
    
    public Horario(String dia, int horaApertura, int horaCierre, boolean cerrado) {
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.cerrado = cerrado;
    }
    
    public Horario(String dia) {
        this.dia = dia;
        this.horaApertura = 0;
        this.horaCierre = 0;
        this.cerrado = true;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(int horaCierre) {
        this.horaCierre = horaCierre;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }
    
    
}
