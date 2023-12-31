/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Estefania Torres
 */
public class Pasajero {
    //atributos
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    
    //getters and setters 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    //metodo para comparar pasajeros y el orden ascendente y descendente
    public Boolean compare(Pasajero otroPasajero, String field, Integer tipo) {
        
        switch (field.toLowerCase()) {
            case "apellido":
                //Si tipo es 0, es orden ascendente si es 1, es orden descendente
                // Retorna verdadero si este pasajero es menor (ascendente) o mayor (descendente);
                if (tipo == 0) {
                    return this.getApellido().toLowerCase().compareTo(otroPasajero.getApellido().toLowerCase()) < 0;
                } else if (tipo == 1) {
                    return this.getApellido().toLowerCase().compareTo(otroPasajero.getApellido().toLowerCase()) > 0;
                }
                break;
            case "nombre":
                if (tipo == 0) {
                    return this.getNombre().toLowerCase().compareTo(otroPasajero.getNombre().toLowerCase()) < 0;
                } else if (tipo == 1) {
                    return this.getNombre().toLowerCase().compareTo(otroPasajero.getNombre().toLowerCase()) > 0;
                }
                break;
            case "dni":
                if (tipo == 0) {
                    return this.getDni().compareTo(otroPasajero.getDni()) < 0;
                } else if (tipo == 1) {
                    return this.getDni().compareTo(otroPasajero.getDni()) > 0;
                }
                break;
            case "telefono":
                if (tipo == 0) {
                    return this.getTelefono().compareTo(otroPasajero.getTelefono()) < 0;
                } else if (tipo == 1) {
                    return this.getTelefono().compareTo(otroPasajero.getTelefono()) > 0;
                }
                break;
            case "id":
                if (tipo == 0) {
                    return this.getId().intValue() < otroPasajero.getId().intValue();
                } else if (tipo == 1) {
                    return this.getId().intValue() > otroPasajero.getId().intValue();
                }
                break;
            default:
                break;
        }
        return false; // Valor predeterminado si la comparación no se realiza correctamente
    }
    //metodo String
    public String toString() {
        return "Nombre: " + nombre + ", apellido: " + apellido + ", id: " + id + "dni" + dni;
    }
}
