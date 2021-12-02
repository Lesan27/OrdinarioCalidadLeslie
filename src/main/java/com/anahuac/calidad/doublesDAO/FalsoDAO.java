package com.anahuac.calidad.doublesDAO;

public class FalsoDAO implements AlumnoDAO {

    @Override
    public boolean agregar_Alumno (Alumno a) {
        return false;
    }

    @Override
    public boolean borrar_Alumno(Alumno a) {
        return false;
    }

    @Override
    public boolean actualizar_Correo(Alumno a){
        return false;
    }

    @Override
    public Alumno consultar_Alumno (String id) {
        return null;
    }
}
