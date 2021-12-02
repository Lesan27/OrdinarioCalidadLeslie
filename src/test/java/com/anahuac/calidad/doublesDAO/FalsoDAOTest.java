package com.anahuac.calidad.doublesDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FalsoDAOTest {

    private FalsoDAO dao;
    public HashMap<String, Alumno> alumnos;

    Alumno alutest;
    
    @Before
    public void setUp() throws Exception {
        dao = Mockito.mock(FalsoDAO.class);
        alumnos = new HashMap<String, Alumno>();
        alutest = new Alumno("les27@gmail", "01","leslie", "20");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void agregar_AlumnoTest() {

        System.out.println("\n----- Crear Alumno Test -----");
        int antes_Cantidad = alumnos.size();
        System.out.println("Tamaño antes = " + antes_Cantidad);

        when(dao.agregar_Alumno(any(Alumno.class))).thenAnswer(
            new Answer<Boolean>(){
                public Boolean answer(InvocationOnMock invocation) throws Throwable {
                    Alumno arg = (Alumno) invocation.getArguments()[0];
                    alumnos.put("01", arg);
                    System.out.println("Tamaño despues = " + alumnos.size());
                    return true;
                }
            }
        );

        dao.agregar_Alumno(alutest);
        int despues_Cantidad = alumnos.size();

        assertThat(antes_Cantidad + 1, is(despues_Cantidad));
    }


    @Test
    public void consultar_AlumnoTest() {

        System.out.println("\n----- Consultar Alumno Test ------");
        alumnos.put("01", alutest);
        int antes_Cantidad = alumnos.size();
        System.out.println("Tamaño antes = " + antes_Cantidad);

        when(dao.consultar_Alumno(any(String.class))).thenAnswer(
            new Answer<Alumno>(){
                public Alumno answer(InvocationOnMock invocation) throws Throwable {
                    String arg = (String) invocation.getArguments()[0];
                    System.out.println("Consultar Alumno = " + alumnos.get(arg).getNombre());
                    return alumnos.get(arg);
                }
        });

        dao.consultar_Alumno("01");
        int despues_Cantidad = alumnos.size();
        System.out.println("Tamaño despues = " + despues_Cantidad);

        assertThat(antes_Cantidad, is(despues_Cantidad));
    }

    @Test
	public void actualizar_CorreoTest() 
    {
		
		alumnos.put(alutest.getId(), alutest);
		String correoAntes = alutest.getCorreo();
		System.out.println("Correo antes: " + correoAntes);
		doAnswer(new Answer() 
		{
		    public Object answer(InvocationOnMock invocation) {
		    Alumno arg = (Alumno) invocation.getArguments()[0];
			alumnos.get(arg.getId()).setCorreo("les@gmail.com");
		    return null;
		      }})
		  .when(dao).actualizar_Correo(any(Alumno.class));
		
		dao.actualizar_Correo(alutest);
		String correoDesp = alumnos.get(alutest.getId()).getCorreo();
		System.out.println("Correo despues: " + correoDesp);
		assertThat(correoAntes,is(not(correoDesp)));
	}

    @Test
    public void borrar_AlumnoTest() {

        alumnos.put("01", alutest);

        System.out.println("\n----- Borrar Alumno Test -----");
        int antes_Cantidad = alumnos.size();
        System.out.println("Tamaño antes = " + antes_Cantidad);

        when(dao.borrar_Alumno(any(Alumno.class))).thenAnswer(
                new Answer<Boolean>() {
                    public Boolean answer(InvocationOnMock invocation) throws Throwable {
                        Alumno arg = (Alumno) invocation.getArguments()[0];
                        alumnos.remove(arg.getId(), arg);

                        return true;
                    }
                }
        );dao.borrar_Alumno(alutest);

        System.out.println("Tamaño despues = " + alumnos.size());
        int despues_Cantidad = alumnos.size();
        assertThat(antes_Cantidad - 1, is(despues_Cantidad));
    }
}