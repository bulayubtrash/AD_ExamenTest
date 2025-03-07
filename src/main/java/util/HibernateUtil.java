package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import edu.examen.marzo2025.modelo.Examen;
import edu.examen.marzo2025.modelo.Pregunta;
//import edu.examen.marzo2025.modelo.PreguntaDeExamen;
import edu.examen.marzo2025.modelo.PreguntaDeExamen;

public class HibernateUtil {
	private static final SessionFactory sessionFactory=buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration= new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(Examen.class);
			configuration.addAnnotatedClass(Pregunta.class);
			configuration.addAnnotatedClass(PreguntaDeExamen.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);

			
		} catch (Throwable ex) {
			System.err.println("Error a la inicializacion de sessionFactory"+ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}
