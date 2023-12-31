/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Validacion;

import java.lang.reflect.Field;

/**
 *
 * @author Usuario
 */
public class Utiles {

    /**
     * Valida cédula .
     * @return true si la cadena representa una cédula válida, false en caso
     * contrario.
     */
    public static boolean validadorDeCedula(String cedula) {
        if (cedula.length() != 10) { // Verifica si la longitud de la cédula es distinta de 10
            return false; // Devuelve falso si la longitud no es la esperada para una cédula
        }

        for (int i = 0; i < cedula.length(); i++) {
            if (!Character.isDigit(cedula.charAt(i))) { // Verifica si todos los caracteres son dígitos
                return false; // Devuelve falso si hay algún carácter no numérico
            }
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i)); // Obtiene el valor numérico del carácter
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito; // Suma los dígitos de la cédula
        }

        int modulo = suma % 10;
        int digitoVerificador = (modulo == 0) ? 0 : 10 - modulo;

        return digitoVerificador == Character.getNumericValue(cedula.charAt(9)); // Comprueba si el dígito verificador coincide
    }

    /**
     * Obtiene el objeto Field correspondiente al atributo especificado en la
     * clase proporcionada.
     * @param clazz La clase en la que se buscará el atributo.
     * @param attribute El nombre del atributo que se desea obtener.
     */
    public static Field getField(Class clazz, String attribute) {
        Field field = null; // Inicializa el campo como nulo
        // Itera a través de los campos declarados en la clase y sus superclases
        for (Field f : clazz.getFields()) {
            if (f.getName().equalsIgnoreCase(attribute)) { // Comprueba si el nombre del campo coincide con el atributo 
                field = f; // Si encuentra una coincidencia, asigna el campo y sale del bucle
                break;
            }
        }
        // Itera a través de todos los campos declarados directamente en la clase
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(attribute)) { // Comprueba si el nombre del campo coincide con el atributo 
                field = f; // Si encuentra una coincidencia, asigna el campo y sale del bucle
                break;
            }
        }
        return field; // Retorna el campo encontrado
    }

}
