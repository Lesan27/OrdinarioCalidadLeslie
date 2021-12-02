package com.anahuac.calidad.doublesDAO;

public interface AlumnoDAO {
    public boolean agregar_Alumno(Alumno a);
    public boolean borrar_Alumno(Alumno a);
    public boolean actualizar_Correo(Alumno a);
    public Alumno consultar_Alumno(String id);

}
