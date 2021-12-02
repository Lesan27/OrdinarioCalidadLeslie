package com.anahuac.calidad.dbunit;

import com.anahuac.calidad.doublesDAO.Alumno;
import com.anahuac.calidad.doublesDAO.AlumnoDAO;

import java.sql.*;

public class AlumnoDAOMySQL implements AlumnoDAO{

    public Connection getConnectionMySQL() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:33060/pruebas_db", "root", "secret"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    @Override
    public boolean agregar_Alumno(Alumno a){
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(
                    "Insertar Informacion alumnos_tbl(correo, id, nombre, edad) values (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, a.getId());
            preparedStatement.setString(2, a.getNombre());
            preparedStatement.setString(3, a.getCorreo());
            preparedStatement.setString(4, a.getEdad());

            if(preparedStatement.executeUpdate() >= 1){
                result = true;
            }

            connection.close();

        } catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public boolean borrar_Alumno(Alumno a){
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        boolean result = false;

        try {
            preparedStatement = connection.prepareStatement("Borrar de alumno_tbl WHERE id = ?");

            preparedStatement.setString(1, a.getId());

            if(preparedStatement.executeUpdate() >= 1){
                result = true;
            }

            connection.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public boolean actualizar_Correo(Alumno a) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        boolean result = false;

        try {
            preparedStatement = connection.prepareStatement("Actualizar alumno:tbl SET email = ? WHERE id = ?");
            preparedStatement.setString(1, a.getCorreo());
            preparedStatement.setString(2, a.getId());
            if(preparedStatement.executeUpdate() >= 1){
                result = true;
            }

            connection.close();
        } catch(SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public Alumno consultar_Alumno(String id){
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;

        Alumno retrieved = null;

        try{
            preparedStatement = connection.prepareStatement("Seleccionar * FROM alumno_tbl WHERE id = ?");
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();

            rs.next();

            String retrieveId = rs.getString(1);
            String retrieveName = rs.getString(2);
            String retrieveEmail = rs.getString(3);
            String retrieveAge = rs.getString(4);

            retrieved = new Alumno(retrieveName, retrieveId, retrieveAge, retrieveEmail);

            rs.close();
            preparedStatement.close();
            connection.close();
        } catch(SQLException e){
            System.out.println(e);
        }
        return retrieved;
    }

}
