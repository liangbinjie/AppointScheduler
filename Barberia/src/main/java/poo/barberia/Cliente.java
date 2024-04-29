/*
 * Autora: Tamara Nicole Rodríguez Luna - 2021077818.
 */
package poo.barberia;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

public class Cliente implements Serializable {
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private List<Cita> citas;
    
    Cliente(String email, String nombre, String apellido, String telefono) throws Exception{
        boolean emailValido = validEmail(email);
        boolean telefonoValido = validPhone(telefono);
        if(emailValido && telefonoValido){
            this.email = email;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
        } else if (!emailValido) {
            throw new Exception("Email inválido.");
        } else {
            throw new Exception("Número de teléfono inválido.");
        }
    }
    
    public void modificarCliente(String email, String nombre, String apellido, String telefono) throws Exception{
        boolean emailValido = validEmail(email);
        boolean telefonoValido = validPhone(telefono);
        if(emailValido && telefonoValido){
            this.email = email;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
        } else if (!emailValido) {
            throw new Exception("Email inválido.");
        } else {
            throw new Exception("Número de teléfono inválido.");
        }
    }
    
    public boolean validEmail(String email){
        String regexEmail = "[^@]+@+[^@]+\\.[a-z]{2,}";
        return Pattern.matches(regexEmail, email);
    }
    
    public boolean validPhone(String telefono){
        String regexPhone = "[0-9()+\\-]+";
        return Pattern.matches(regexPhone, telefono);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}