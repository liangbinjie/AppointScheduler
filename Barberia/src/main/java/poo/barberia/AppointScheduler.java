/*
 * Autora: Tamara Nicole Rodríguez Luna - 2021077818.
 */
package poo.barberia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class AppointScheduler {
    private String contrasenaCorreo = "hfvcynqXnF3A5n7M";
    private String usuarioCorreo = "MS_pM6Ot7@trial-351ndgwq8zrgzqx8.mlsender.net";
    private Map<String,Horario> horario;
    private Map<String,Cliente> clientes;
    private ArrayList<Cliente> colaEspera;
    private ArrayList<Servicio> servicios;
    private Map<String,Cita> citas;
    
    public AppointScheduler(){
        this.clientes = new HashMap<>();
        this.colaEspera = new ArrayList<>();
        this.citas = new HashMap<>();
    }
    
    //Métodos de Clientes
    public void crearCliente(String email, String nombre, String apellido, String telefono) throws Exception{
        Cliente c = new Cliente(email, nombre, apellido, telefono);
        clientes.put(email, c);
    }
    
    public void modificarCliente(String email, String nombre, String apellido, String telefono) throws Exception{
        Cliente c = consultarCliente(email);
        c.modificarCliente(email, nombre, apellido, telefono);
        //Actualizar Map de clientes
        clientes.remove(email);
        clientes.put(email, c);
    }
    
    public void eliminarCliente(String email) throws Exception{
        if(existeCliente(email) && existeClienteColaEspera(email) && true){
            // Falta validar que no tenga citas asignadas en el futuro
            clientes.remove(email);
        } else {
            throw new Exception("Cliente no eliminado.");
        }
    }
    
    public boolean existeCliente(String email){
        return clientes.containsKey(email);
    }
    
    public Cliente consultarCliente(String email) throws Exception{
        Cliente c = clientes.get(email);
        if(c!=null){
            return c;
        } else {
            throw new Exception("Cliente no encontrado.");
        }
    }
    
    //Metodos de Cita
    public void crearCita(String email, LocalDateTime horaCita, 
            LocalDateTime diaCita) throws Exception {
        if (!clientes.containsKey(email))
            throw new Exception("El email de la persona no existe");
        Cliente cliente = clientes.get(email);
        citas.put(email, new Cita(cliente, horaCita, diaCita,
                EstadoCita.CONFIRMADA));
    }
    
    public void modificarCita(String email, Cliente clienteActualizado, LocalDateTime horaCita, 
            LocalDateTime diaCita) 
            throws Exception{
        Cita cita = citas.get(email);
        if (cita != null) {
            cita.setCliente(clienteActualizado);
            cita.setDiaCita(diaCita);
            cita.setHoraCita(horaCita);
            System.err.println("Cita modificada exitosamente");
            }
        else {
            throw new Exception("Este usuario no tiene ninguna cita asignada");
            }
    }
    
    public void eliminarCita (String email) throws Exception {
        Cita cita = citas.get(email);
        if (cita != null) {
            citas.remove(cita);
            System.out.println("La cita de " + email + "ha sido confirmada");
        }
        else {
            throw new Exception("Este cliente no tiene cita para eliminarse");
        }
    }
    
    public void consultarCita(String email) throws Exception {
        Cita cita = citas.get(email);
        if (cita != null) {
            System.out.println("email: " + cita);
            System.out.println("Cliente: " + cita.getCliente());
            System.out.println("Fecha: " + cita.getDiaCita());
            System.out.println("Hora: " + cita.getEstadoCita());
            System.out.println("Estado: " + cita.getEstadoCita());
        } else {
            throw new Exception("No se encontró una cita con ID " + email + ".");
        }
    }    
    public void confirmarCita (String email) throws Exception {
        Cita cita = citas.get(email);
        if (cita != null && cita.getEstadoCita() == EstadoCita.NO_CONFIRMADA) {
            cita.confirmarCita();
            System.out.println("La cita de " + email + "ha sido confirmada");
        }
        else {
            throw new Exception("No se puede confirmar la cita con ID " +
                    email + ". La cita no existe o ya está confirmada.");
        }
    }
    
    //Métodos de Cola de Espera
    public ArrayList<Cliente> mostrarColaEspera(){
        return colaEspera;
    }
    
    public void encolarCliente(Cliente cliente) throws Exception{
        colaEspera.add(cliente);
    }
    
    public Cliente desencolarCliente(String email) throws Exception{
        Cliente c = consultarCliente(email);
        colaEspera.remove(c);
        return c;
    }
    
    public boolean existeClienteColaEspera(String email){
        for(int i=0; i < colaEspera.size(); i++){
            if (colaEspera.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    
    
    
// ************ SECCION DE SERVICIOS ********************
    
    private void mostrarMensajeError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR!", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarMensajeExitoso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private Servicio obtenerServicio(int id) {
        return servicios.get(id-1);
    }
    
    public void crearServicio(String nombre) {
        servicios.add(new Servicio(nombre));
        mostrarMensajeExitoso("Servicio creado");
    }
    
    public Servicio consultarServicio(int id) {
        Servicio servicio = new Servicio();
        try {
            servicio = obtenerServicio(id);
        } catch (Exception ex) {
            mostrarMensajeError("No se encontro el servicio");
        }
        return servicio;
    }
    
    public void modificarServicio(int id, String nombre) {
        try {
            obtenerServicio(id).setNombre(nombre);
            mostrarMensajeExitoso("Servicio modificado");
        } catch (Exception ex) {
            mostrarMensajeError("No se pudo modificar el servicio");
        }
    }
    
    public void eliminarServicio(int id) {
        try {
            servicios.remove(id-1);
            mostrarMensajeExitoso("Servicio eliminado");
        } catch (Exception ex) {
            mostrarMensajeError("No se pudo eliminar el servicio");
        }
    }
    
    
// *************** SECCION HORARIO ****************
    public void establecerHorario() {
        
    }
    
    public void sendEmail(ArrayList listaCitasNoConfirmadas) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.mailersend.net");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuarioCorreo, contrasenaCorreo);
                }
            });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuarioCorreo));
            
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));  *** PONER LOS RECIPIENTES ***


            String msgStyled = "  <p>Saludos cordiales [CLIENTE],</p>\n" +
                            "  <p>Tienes agendada una cita para el día de mañana en <strong>[NOMBRE_NEGOCIO]</strong> para el servicio de <strong>[SERVICIO]</strong>:</p>\n" +
                            "  <ul>\n" +
                            "    <li><strong>Día:</strong> [DIA]</li>\n" +
                            "    <li><strong>Hora:</strong> [HORA]</li>\n" +
                            "  </ul>\n" +
                            "  <p>Por favor, confirma tu asistencia llamando al <strong>[TELEFONO_NEGOCIO]</strong>. Si no confirmas, podrías perder tu espacio.</p>\n" +
                            "  <p>Atentamente,</p>\n" +
                            "  <p><strong>[NOMBRE_NEGOCIO]</strong></p>\n" +
                            "  <p><strong>Teléfono:</strong> [TELEFONO]</p>";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msgStyled, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setSubject("Confirmacion de cita");
            message.setContent(multipart);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Correo enviado");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
