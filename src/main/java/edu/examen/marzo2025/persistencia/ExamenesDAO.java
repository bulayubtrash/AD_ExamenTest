package edu.examen.marzo2025.persistencia;

import java.util.List;

import edu.examen.marzo2025.modelo.Examen;
import edu.examen.marzo2025.modelo.Pregunta;

public interface ExamenesDAO {
	
	/**
	 * Devuelve la lista de exámenes que hay
	 * @return La lista con los exámenes que hay, si hay algún error, devuelve null.
	 */
	List<Examen> recuperarExamenes();
	
	/**
	 * Devuelve la lista de preguntas que hay
	 * @return La lista con las preguntas que hay, si hay algún error, devuelve null.
	 */
	List<Pregunta> recuperarPreguntas();
	
	/**
	 * Devuelve el número de preguntas que hay en un examen concreto
	 * @param idExamen
	 * @return
	 */
	int numeroPreguntasDelExamen(int idExamen);
	
	/**
	 * Almacena (persiste) una pregunta
	 * @param pregunta La pregunta no pertenece a ningun examen, es una pregunta suelta
	 * @return True si se guarda la pregunta, false si no se guarda.
	 */
	boolean aniadirPregunta(Pregunta pregunta);
	
	/**
	 * Almacena (persiste) un examen
	 * @param examen El examen no tiene porqué contener ninguna pregunta.
	 * @return True si se guarda el examen, false si no se guarda.
	 */
	boolean aniadirExamen(Examen examen);
	
	/**
	 * Añade una pregunta a un examen. La posición que ocupa la pregunta es la última del examen.
	 * @param idExamen
	 * @param idPregunta
	 * @return True si la operación se ha realizado correctamente y false en caso contrario
	 */
	boolean aniadirPreguntaAExamen(int idExamen, int idPregunta);
	
	/**
	 * Elimina una pregunta de un examen.
	 * @param idExamen
	 * @param idPregunta
	 * @return True si la operación se ha realizado correctamente y false en caso contrario
	 */
	boolean quitarPreguntaAExamen(int idExamen, int idPregunta);
	
	/**
	 * Elimina una pregunta
	 * @param idPregunta
	 * @return True si la operación se ha realizado correctamente, false si no existía la pregunta, y null si hay algún error. 
	 */
	Boolean eliminarPregunta(int idPregunta);
	
	/**
	 * Elimina un examen
	 * @param idExamen
	 * @return True si la operación se ha realizado correctamente, false si no existía la pregunta, y null si hay algún error. 
	 */
	Boolean eliminarExamen(int idExamen);
}
