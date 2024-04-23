/*
 * Autora: Tamara Nicole Rodríguez Luna - 2021077818.
 */
package poo.barberia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointScheduler {
    private Map<String,Cliente> clientes;
    private ArrayList<Cliente> colaEspera;
    
    AppointScheduler(){
        this.clientes = new HashMap<String,Cliente>();
        this.colaEspera = new ArrayList<Cliente>();
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
}
