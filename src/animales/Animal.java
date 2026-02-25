/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animales;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Clase abstracta que representa un animal de la granja.
 * Los animales tienen un código identificativo, fecha de nacimiento, sexo y peso.
 * Pueden emitir sonidos, alegrarse, enfadarse y mostrar qué tipo de animal son.
 * 
 * @author Jesús Andrés
 * @version 1.0
 * @since 2026
 */
public abstract class Animal {

    protected String codigo;
    private LocalDate fechaNacimiento;
    private char sexo;
    private double peso;

    /**
     * Constructor de la clase Animal.
     * 
     * Crea un nuevo objeto Animal con un código identificativo, fecha de nacimiento, sexo y peso.
     * Realiza validaciones sobre los parámetros:
     * <ul>
     *   <li>El código debe tener exactamente 5 caracteres que sean dígitos (0-9) o letras minúsculas (a-z)</li>
     *   <li>El sexo debe ser 'M' (hembra) o 'H' (macho)</li>
     *   <li>El peso debe ser un valor positivo mayor que cero</li>
     *   <li>La fecha de nacimiento debe estar en formato ISO-8601 (yyyy-MM-dd) válido</li>
     * </ul>
     * Si algún parámetro no cumple estas condiciones, se lanza una excepción IllegalArgumentException.
     *
     * @param codigo el código identificativo del animal, compuesto por 5 caracteres alfanuméricos en minúscula
     * @param fechaNacimiento la fecha de nacimiento del animal en formato "yyyy-MM-dd"
     * @param sexo el sexo del animal, 'M' para hembra o 'H' para macho
     * @param peso el peso del animal en kilogramos, debe ser mayor que 0
     * @throws IllegalArgumentException si el código no cumple el patrón, el sexo es incorrecto, 
     *         el peso no es positivo o la fecha no tiene un formato válido
     */
    public Animal(String codigo, String fechaNacimiento, char sexo, double peso) {

        LocalDate fecha;

        if (!codigo.matches("[0-9a-z]{5}") || (sexo != 'M' && sexo != 'H') || (peso <= 0)) {
            throw new IllegalArgumentException();
        } else {

            try {
                fecha = LocalDate.parse(fechaNacimiento);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException();
            }
            this.codigo = codigo;
            this.fechaNacimiento = fecha;
            this.sexo = sexo;
            this.peso = peso;
        }
    }

    /**
     * Obtiene el código identificativo del animal.
     * 
     * @return el código del animal (5 caracteres alfanuméricos en minúscula)
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece un nuevo código identificativo para el animal.
     * 
     * @param codigo el nuevo código (debe tener exactamente 5 caracteres alfanuméricos en minúscula)
     * @throws IllegalArgumentException si el código no cumple el patrón establecido
     */
    public void setCodigo(String codigo) {
        if (!codigo.matches("[0-9a-z]{5}")) {
            throw new IllegalArgumentException();
        } else {
            this.codigo = codigo;
        }
    }

    /**
     * Obtiene la fecha de nacimiento del animal.
     * 
     * @return la fecha de nacimiento como objeto LocalDate
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece una nueva fecha de nacimiento para el animal.
     * 
     * @param fechaNacimiento la nueva fecha en formato "yyyy-MM-dd"
     * @throws IllegalArgumentException si la fecha no tiene un formato válido
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        LocalDate fecha;

        try {
            fecha = LocalDate.parse(fechaNacimiento);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException();
        }

        this.fechaNacimiento = fecha;
    }

    /**
     * Obtiene el sexo del animal.
     * 
     * @return 'M' para hembra o 'H' para macho
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del animal.
     * 
     * @param sexo el nuevo sexo ('M' para hembra o 'H' para macho)
     * @throws IllegalArgumentException si el sexo no es 'M' ni 'H'
     */
    public void setSexo(char sexo) {
        if ((sexo != 'M' && sexo != 'H')) {
            throw new IllegalArgumentException();
        } else {
            this.sexo = sexo;
        }
    }

    /**
     * Obtiene el peso del animal en kilogramos.
     * 
     * @return el peso del animal
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece un nuevo peso para el animal.
     * 
     * @param peso el nuevo peso en kilogramos (debe ser mayor que 0)
     * @throws IllegalArgumentException si el peso no es positivo
     */
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.peso = peso;
        }
    }

    /**
     * Genera un código hash para el objeto Animal basado en sus atributos.
     * 
     * @return el valor hash calculado
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        hash = 19 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 19 * hash + this.sexo;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        return hash;
    }

    /**
     * Compara este Animal con otro objeto para determinar si son iguales.
     * Dos animales se consideran iguales si tienen el mismo código, fecha de nacimiento,
     * sexo y peso.
     * 
     * @param obj el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Animal.
     * 
     * @return una cadena con los valores de todos los atributos del animal
     */
    @Override
    public String toString() {
        return "Animal{" + "codigo=" + codigo + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", peso=" + peso + '}';
    }

    /**
     * Método abstracto que debe implementar cada subclase para devolver
     * el sonido característico del animal.
     * 
     * @return una cadena con el sonido que hace el animal
     */
    public abstract String hacerSonido();

    /**
     * Método abstracto que debe implementar cada subclase para devolver
     * una expresión de alegría del animal.
     * 
     * @return una cadena mostrando cómo se alegra el animal
     */
    public abstract String alegrarse();

    /**
     * Método abstracto que debe implementar cada subclase para devolver
     * una expresión de enfado del animal.
     * 
     * @return una cadena mostrando cómo se enfada el animal
     */
    public abstract String enfadarse();

    /**
     * Método abstracto que debe implementar cada subclase para devolver
     * el tipo o raza específica del animal.
     * 
     * @return una cadena indicando qué tipo de animal es
     */
    public abstract String queSoy();

}