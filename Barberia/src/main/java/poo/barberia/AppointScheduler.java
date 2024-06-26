/*
 * Autora: Tamara Nicole Rodríguez Luna - 2021077818.
 */
package poo.barberia;

import com.toedter.calendar.JDateChooser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class AppointScheduler implements Serializable {
    private String contrasenaCorreo = "hfvcynqXnF3A5n7M";
    private String usuarioCorreo = "MS_pM6Ot7@trial-351ndgwq8zrgzqx8.mlsender.net";
    private Map<String,Horario> horario;
    private Map<String,Cliente> clientes;
    private ArrayList<Cliente> colaEspera;
    private ArrayList<Servicio> servicios;
    private Map<String,Cita> citas;
    private static AppointScheduler instance = null;
    
    public static AppointScheduler getInstance() {
        if (instance == null) {
            try{
                System.out.println("Cargando de disco...");
                instance = cargarDatos();
            }
            catch (Exception e) {
                System.out.println("Creando instancia nueva...");
                instance = new AppointScheduler();
            }
        }
        return instance;
    }
    
    public AppointScheduler(){
        String dias[] = {"Lunes","Martes","Miercoles", "Jueves","Viernes","Sabado","Domingo"};
        this.clientes = new HashMap<>();
        this.colaEspera = new ArrayList<>();
        this.citas = new HashMap<>();
        this.horario = new HashMap<>();
        this.servicios = new ArrayList<Servicio>();
        for (String dia: dias) {
            horario.put(dia, new Horario(dia));
        }
    }
    
    //Métodos de Clientes
    public void crearCliente(String email, String nombre, String apellido, String telefono) throws Exception{
        if(!clientes.containsKey(email)){
            Cliente c = new Cliente(email, nombre, apellido, telefono);
            clientes.put(email, c);
        } else {
            throw new Exception("El cliente especificado ya se encuentra dentro del sistema.");
        }
    }
    
    public void modificarCliente(String email, String nombre, String apellido, String telefono) throws Exception{
        Cliente c = consultarCliente(email);
        c.modificarCliente(email, nombre, apellido, telefono);
        //Actualizar Map de clientes
        clientes.remove(email);
        clientes.put(email, c);
    }
    
    public void eliminarCliente(String email) throws Exception{
        Cita cita = citas.get(email);
        if(existeCliente(email) && !existeClienteColaEspera(email) && cita == null){
            clientes.remove(email);
        } else if (!existeCliente(email)) {
          throw new Exception("Cliente no existe");  
        } else {
            if(existeClienteColaEspera(email)){
                throw new Exception("El cliente e encuentra en la lista de espera.");
            } else {
                throw new Exception("El usuario tiene alguna cita programada.");
            }
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
    
    public HashMap<String, Cliente> obtenerListaClientes() {
        return (HashMap<String, Cliente>) clientes;
    }
    
    //Metodos de Cita
    public void crearCita(String email, int horaCita, String diaCita, int index) throws Exception {
        // https://www.w3schools.com/java/java_date.asp
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(diaCita, formato);

        if (!estaCerrado(date.getDayOfWeek().toString(), horaCita)) {
            if (!clientes.containsKey(email))
                throw new Exception("El cliente no existe");
            Cliente cliente = clientes.get(email);
            Cita cita = new Cita(cliente, horaCita, diaCita, obtenerServicio(index+1));
            consultarCliente(email).setCita(cita);
            citas.put(email, cita);
            System.out.println(servicios.get(index).getNombre());
        } else {
            throw new Exception("La barberia se encuentra cerrado");
        }
    }
    
    public void modificarCita(String email, Cliente clienteActualizado, int horaCita, 
            String diaCita) 
            throws Exception{
        Cita cita = citas.get(email);
        if (cita != null) {
            cita.setCliente(clienteActualizado);
            cita.setDiaCita(diaCita);
            cita.setHoraCita(horaCita);
            System.err.println("Cita modificada exitosamente");
            consultarCliente(email).setCita(cita);
            }
        else {
            throw new Exception("Este usuario no tiene ninguna cita asignada");
            }
    }
    
    public void eliminarCita (String email) throws Exception {
        Cita cita = citas.get(email);
        if (cita != null) {
            citas.remove(email);
            consultarCliente(email).setCita(null);
            System.out.println("La cita de " + email + " ha sido eliminada");
        }
        else {
            throw new Exception("Este cliente no tiene cita para eliminarse");
        }
    }
    
    public Cita consultarCita(String email) throws Exception {
        Cita cita = citas.get(email);
        if (cita != null) {
            return cita;
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
    
    public HashMap<String, Cita> obtenerListaCitas() {
        return (HashMap<String, Cita>) citas;
    }
    
    public ArrayList<Cita> obtenerListaCitasNoConfirmadas() {
        LocalDate siguienteDia = LocalDate.now().plusDays(1);
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String diaFormat = siguienteDia.format(formato);  
        System.out.println(diaFormat);
        ArrayList<Cita> lista = new ArrayList<>();
        citas.forEach((correo, cita) -> {
            System.out.println(cita.getDiaCita());
            System.out.println(cita.getEstado().toString());
            if ((cita.getEstadoCita() == EstadoCita.NO_CONFIRMADA) && (cita.getDiaCita().equals(diaFormat))) {
                lista.add(cita);
            };
        });
        if (lista.isEmpty()) {
            System.out.println("vacio");
        }
        for (Cita c: lista) {
            System.out.println(c.getCliente().getEmail());
        }
        return lista;
    }
    
    // su funcion es eliminar todas las citas que ya pasaron su fecha
//    public void eliminarCitas() {
//        LocalDate dia = LocalDate.now();
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/YYYY");
//        String diaFormat = dia.format(formato);
//        
//    }
    
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
    
    public Servicio consultarServicio(int id) throws Exception {
        Servicio servicio = new Servicio();
        try {
            servicio = obtenerServicio(id);
        } catch (Exception ex) {
            throw new Exception ("No se encontro el servicio");
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
    
    public void eliminarServicio(int id) throws Exception {
        boolean enUso = false;
        try {
            Servicio s = obtenerServicio(id);
            for (Map.Entry<String, Cita> entry: citas.entrySet()) {
                if (entry.getValue().getServicio() == s) {
                    enUso = true;
                }
            }
            if (enUso) {
                throw new Exception ("Servicio en cita programada, no se puede eliminar");
            } else {
                servicios.remove(id-1);
            }
            
        } catch (Exception ex) {
            throw new Exception ("No se encontro el servicio");
        }
    }
    
    public ArrayList<Servicio> obtenerListaServicios() {
        return servicios;
    }
    
    public ArrayList<String> obtenerListaServiciosString() {
        ArrayList<String> lista = new ArrayList<>();
        for (Servicio s: servicios) {
            lista.add(s.getNombre());
        }
        return lista;
    }
    
    
// *************** SECCION ADMINISTRACION ****************
    // https://mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
    public void sendEmail(ArrayList<Cita> listaCitasNoConfirmadas) {
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
            for (Cita c: listaCitasNoConfirmadas) {
                if (c.getEstadoCita() == EstadoCita.NO_CONFIRMADA) {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(c.getCliente().getEmail()));
                    String msgStyled = "  <p>Saludos cordiales " + c.getCliente().getNombre() +",</p>\n" +
                            "  <p>Tienes agendada una cita para el día de mañana en <strong>Barberia POO</strong> a las <strong>" + c.getHoraCita() +"</strong></p>\n" +
                            "  <p>Por favor, confirma tu asistencia llamando al <strong>2572-2761</strong>. Si no confirmas, podrías perder tu espacio.</p>\n" +
                            "  <p>Atentamente,</p>\n" +
                            "  <p><strong>Barberia POO</strong></p>\n" +
                            "  <p><strong>Teléfono:</strong> 2572-2761</p>";
                    MimeBodyPart mimeBodyPart = new MimeBodyPart();
                    mimeBodyPart.setContent(msgStyled, "text/html; charset=utf-8");

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(mimeBodyPart);
                    message.setSubject("Confirmacion de cita");
                    message.setContent(multipart);

                    Transport.send(message);
                }
            }
            
            

            JOptionPane.showMessageDialog(null, "Correo enviado");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static AppointScheduler cargarDatos() throws FileNotFoundException, IOException, ClassNotFoundException {
        AppointScheduler control = new AppointScheduler();
        try {
            FileInputStream file = new FileInputStream("control.bin");
            ObjectInputStream stream = new ObjectInputStream(file);
            control = (AppointScheduler)stream.readObject();
            stream.close();
            file.close();
            return control;
        } catch (Exception e) {
            System.out.println("No se encontro archivo de carga");
        }
        return control;  
    }
    
    public static void guardarDatos(AppointScheduler data) throws FileNotFoundException, IOException {
        try {
            FileOutputStream file = new FileOutputStream("control.bin");
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(data);
            stream.close();
            file.close();
        } catch (Exception ex) {
            System.out.println("Hubo un problema al guardar los datos: " + ex);
        }
    }
    
    public Map<String, Horario> getHorario() {
        return horario;
    }
    
    public void establecerHorario(String dia,int horaApertura, int horaCierre, boolean cerrado) throws Exception {
        if (horaApertura < 0 || horaApertura >= 24 || horaCierre < 0 || horaCierre >= 24) {
            System.out.println("La hora de cierre o apertura del dia " + dia + " no es valida");
            throw new Exception ("La hora de cierre o apertura del dia " + dia + " no es valida");
            
        } else {
            horario.replace(dia, new Horario(dia, horaApertura, horaCierre, cerrado));
        }
        
    }
    
    public boolean estaCerrado(String diaIngles, int hora) {
        switch (diaIngles) {
            case "MONDAY":
                if (horario.get("Lunes").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Lunes").getHoraApertura() && hora < horario.get("Lunes").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "TUESDAY":
                if (horario.get("Martes").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Martes").getHoraApertura() && hora < horario.get("Martes").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "WEDNESDAY":
                if (horario.get("Miercoles").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Miercoles").getHoraApertura() && hora < horario.get("Miercoles").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "THURSDAY":
                if (horario.get("Jueves").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Jueves").getHoraApertura() && hora < horario.get("Jueves").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "FRIDAY":
                if (horario.get("Viernes").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Viernes").getHoraApertura() && hora < horario.get("Viernes").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "SATURDAY":
                if (horario.get("Sabado").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Sabado").getHoraApertura() && hora < horario.get("Sabado").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
            case "SUNDAY":
                if (horario.get("Domingo").isCerrado()) {
                    return true;
                }
                if (hora >= horario.get("Domingo").getHoraApertura() && hora < horario.get("Domingo").getHoraCierre()) {
                    break;
                } else {
                    return true;
                }
        }
        return false;
    }
}
