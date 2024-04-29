package poo.barberia;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 *
 * @author Armando Garcia
 */
public class Cita implements Serializable {
        private LocalDateTime horaCita;
        private LocalDateTime diaCita;
        private Cliente cliente;
        private EstadoCita estado;

    public Cita(Cliente cliente, LocalDateTime horaCita, LocalDateTime diaCita, EstadoCita estado) {
        this.horaCita = horaCita;
        this.diaCita = diaCita;
        this.cliente = cliente;
        this.estado = EstadoCita.NO_CONFIRMADA;
    }

    public LocalDateTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalDateTime horaCita) {
        this.horaCita = horaCita;
    }

    public LocalDateTime getDiaCita() {
        return diaCita;
    }

    public void setDiaCita(LocalDateTime diaCita) {
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
