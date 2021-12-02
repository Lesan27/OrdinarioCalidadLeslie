package com.anahuac.calidad.dbunit;

import com.anahuac.calidad.doublesDAO.Alumno;
import static org.junit.Assert.*;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DAOAlumnoTest extends DBTestCase {

    public DAOAlumnoTest(){
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:33060/pruebas_db");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "raiz");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "s");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
    }

    @Before
    public void setUp() throws Exception{
        IDatabaseConnection connection = getConnection();
        try {
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e){
            fail("Error: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    @After
    public void tearDown() throws Exception{
    	
    }

    @Test
    public void testAdd() {
        Alumno alumno = new Alumno("les27@gmail", "01","leslie", "20" );
        AlumnoDAOMySQL daoMySQL = new AlumnoDAOMySQL();

        daoMySQL.agregar_Alumno(alumno);

        try {
            IDataSet databaseDataSet = getConnection().createDataSet(); 
            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");


            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception e) {
            fail("Error al crear: " + e.getMessage());
        }
    }

    @Test
    public void testRead() {
        AlumnoDAOMySQL daoMySQL = new AlumnoDAOMySQL();

        daoMySQL.consultar_Alumno("01");

        try {
            IDataSet databaseDataSet = getConnection().createDataSet();
            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/read_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception e){
            fail("Error al leer: " + e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        Alumno alumno = new Alumno("les_@gmail", "02","les", "20");
        AlumnoDAOMySQL daoMySQL = new AlumnoDAOMySQL();

        daoMySQL.actualizar_Correo(alumno);

        try {
            IDataSet databaseDataSet = getConnection().createDataSet();
            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception e){
            fail("Error in UPDATE test: " + e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        Alumno alumno = new Alumno("Leslie", "01", "20", "les27@gmail.com");
        AlumnoDAOMySQL daoMySQL = new AlumnoDAOMySQL();

        daoMySQL.borrar_Alumno(alumno);

        try {
            IDataSet databaseDataSet = getConnection().createDataSet();
            ITable actualTable = databaseDataSet.getTable("alumnos_tbl");

            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
            ITable expectedTable = expectedDataSet.getTable("alumnos_tbl");

            Assertion.assertEquals(expectedTable, actualTable);
        } catch (Exception e){
            fail("Error in DELETE test: " + e.getMessage());
        }
    }
}