package poo.barberia;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author Armando Garcia
 */
public class Cita implements Serializable {
        private String horaCita;
        //private LocalDateTime diaCita;
        private Cliente cliente;
        private EstadoCita estado;
        private String diaCita;
        private Servicio servicio;

    public Cita(Cliente cliente, String horaCita, String diaCita, EstadoCita estado) {
        this.horaCita = horaCita;
        this.diaCita = diaCita;
        this.cliente = cliente;
        this.estado = EstadoCita.NO_CONFIRMADA;
        this.servicio = null;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getDiaCita() {
        return diaCita;
    }

    public void setDiaCita(String diaCita) {
        this.diaCita = diaCita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public EstadoCita getEstadoCita() {
        return estado;
    }
    
    
    public void confirmarCita() throws Exception {
        if (cliente == null) {
            throw new Exception("Cliente no encontrado");
        }
        this.estado = EstadoCita.CONFIRMADA;
    }
}
