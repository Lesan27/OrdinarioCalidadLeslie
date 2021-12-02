package com.anahuac.calidad.doublesDAO;

public class Alumno {

    private String nombre, id, correo;
    private String edad;

    public Alumno() {}

    public Alumno(String correo, String id, String nombre, String edad) {
        
    	this.edad   = edad;
        this.id     = id;
        this.correo  = correo;
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

}
