package com.romellfudi.fudi_annotation.sample.child_pk;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class Hijo {
    private String nombre;
    private int edad;
    public Hijo(String nom, int eda){
        this.setNombre(nom);
        this.setEdad(eda);
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}