package edu.examen.marzo2025.persistencia;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.examen.marzo2025.modelo.Examen;
import edu.examen.marzo2025.modelo.Pregunta;
import edu.examen.marzo2025.modelo.PreguntaDeExamen;
import util.HibernateUtil;

public class ExamenesDAOImpl implements ExamenesDAO {
    private static final Logger Logger = LogManager.getLogger(ExamenesDAOImpl.class);

    @Override
    public List<Examen> recuperarExamenes() {
        List<Examen> lista = List.of(); 
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            lista = se.createQuery("FROM Examen", Examen.class).list();
            Logger.info("Exámenes recuperados con éxito. Total: " + lista.size());
        } catch (Exception e) {
            Logger.error("Error al recuperar exámenes", e);
        }
        return lista;
    }


    @Override
    public List<Pregunta> recuperarPreguntas() {
        List<Pregunta> lista = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            lista = se.createQuery("FROM Pregunta", Pregunta.class).list();
            Logger.info("Preguntas recuperadas con éxito");
        } catch (Exception e) {
            Logger.error("Error al recuperar preguntas", e);
        }
        return lista;
    }

    @Override
    public int numeroPreguntasDelExamen(int idExamen) {
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            Long count = se.createQuery("SELECT COUNT(p) FROM PreguntaDeExamen p WHERE p.examen.id = :idP", Long.class)
                           .setParameter("idP", idExamen)
                           .uniqueResult();
            Logger.info("Número de preguntas recuperado con éxito");
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            Logger.error("Error al recuperar número de preguntas", e);
        }
        return 0;
    }

    @Override
    public boolean aniadirPregunta(Pregunta pregunta) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();
            se.persist(pregunta);
            tr.commit();
            Logger.info("Pregunta añadida con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al añadir pregunta", e);
            return false;
        }
    }

    @Override
    public boolean aniadirExamen(Examen examen) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();
            se.persist(examen);
            tr.commit();
            Logger.info("Examen añadido con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al añadir examen", e);
            return false;
        }
    }

    @Override
    public boolean aniadirPreguntaAExamen(int idExamen, int idPregunta) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();

            Pregunta p = se.get(Pregunta.class, idPregunta);
            Examen e = se.get(Examen.class, idExamen);

            if (p == null || e == null) {
                Logger.warn("No se encontró la pregunta o el examen");
                return false;
            }

            PreguntaDeExamen pde = new PreguntaDeExamen(e, p, 0); 
            se.persist(pde);

            tr.commit();
            Logger.info("Pregunta añadida al examen con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al añadir pregunta al examen", e);
            return false;
        }
    }

    @Override
    public boolean quitarPreguntaAExamen(int idExamen, int idPregunta) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();

            PreguntaDeExamen pde = se.createQuery(
                "FROM PreguntaDeExamen p WHERE p.examen.id = :idExamen AND p.pregunta.id = :idPregunta",
                PreguntaDeExamen.class)
                .setParameter("idExamen", idExamen)
                .setParameter("idPregunta", idPregunta)
                .uniqueResult();

            if (pde == null) {
                Logger.warn("No se encontró la relación Pregunta-Examen");
                return false;
            }

            se.remove(pde);
            tr.commit();
            Logger.info("Pregunta eliminada del examen con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al quitar pregunta del examen", e);
            return false;
        }
    }

    @Override
    public Boolean eliminarPregunta(int idPregunta) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();

            Pregunta p1 = se.get(Pregunta.class, idPregunta);
            if (p1 == null) {
                Logger.warn("No existe la pregunta con el ID especificado");
                return false;
            }

            se.remove(p1);
            tr.commit();
            Logger.info("Pregunta eliminada con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al eliminar la pregunta", e);
            return false;
        }
    }

    @Override
    public Boolean eliminarExamen(int idExamen) {
        Transaction tr = null;
        try (Session se = HibernateUtil.getSessionFactory().openSession()) {
            tr = se.beginTransaction();
            Examen e1 = se.get(Examen.class, idExamen);
            if (e1 == null) {
                Logger.warn("No existe el examen con el ID especificado");
                return false;
            }

            se.remove(e1);
            tr.commit();
            Logger.info("Examen eliminado con éxito");
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            Logger.error("Error al eliminar el examen", e);
            return false;
        }
    }
}
