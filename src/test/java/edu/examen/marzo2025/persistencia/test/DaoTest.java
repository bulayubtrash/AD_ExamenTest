package edu.examen.marzo2025.persistencia.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.examen.marzo2025.modelo.Examen;
import edu.examen.marzo2025.modelo.Pregunta;
import edu.examen.marzo2025.persistencia.ExamenesDAO;
import edu.examen.marzo2025.persistencia.ExamenesDAOImpl;

public class DaoTest {

	private static ExamenesDAO examenesDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		examenesDAO = new ExamenesDAOImpl();
	}

	@Test
	public void testRecuperarExamenes() {
		List<Examen> resultado = recuperarExamenes();
		assertTrue(resultado.isEmpty());
	}

	@Test
	public void testNumeroPreguntasDelExamen() {
		Examen examen = null;
		try {
			examen = new Examen(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1212"),12,"asdf");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //TODO construye el examen con los datos necesarios
		boolean resultadoAniadirExamen = examenesDAO.aniadirExamen(examen);
		assertTrue (resultadoAniadirExamen);
		List<Examen> listaExamenes = recuperarExamenes();
		assertFalse(listaExamenes.isEmpty());
		Pregunta pregunta = new Pregunta("qwe",12,12); //TODO construye una pregunta
		boolean resultadoAnidirPregunta = examenesDAO.aniadirPregunta(pregunta);
		assertTrue (resultadoAnidirPregunta);
		List<Pregunta> listaPreguntas = examenesDAO.recuperarPreguntas();
		assertNotNull(listaPreguntas);
		assertFalse(listaPreguntas.isEmpty());
		int idExamen = listaExamenes.get(0).getId();
//		int idExamen = 1000; //TODO Comenta esta línea y descomenta la anterior. Cambia el método getId() por el correspondiente.
		int idPregunta = listaPreguntas.get(0).getId();
//		int idPregunta = 1000; //TODO Comenta esta línea y descomenta la anterior. Cambia el método getId() por el correspondiente.
		boolean resultadoAniadirPreguntaExamen = examenesDAO.aniadirPreguntaAExamen(idExamen, idPregunta);
		assertTrue (resultadoAniadirPreguntaExamen);
		int resultado = examenesDAO.numeroPreguntasDelExamen(idExamen);
		assertEquals(1,resultado);
		boolean resultadoElminarPregunta = examenesDAO.eliminarPregunta(idPregunta);
		assertTrue(resultadoElminarPregunta);
		boolean resultadoElminarExamen = examenesDAO.eliminarExamen(idExamen);
		assertTrue(resultadoElminarExamen);
	}

	private List<Examen> recuperarExamenes() {
		List<Examen> resultado = examenesDAO.recuperarExamenes();
		assertNotNull(resultado);
		return resultado;
	}
	
	@Test
	public void testCorrecto() {
		
	}

	

}
