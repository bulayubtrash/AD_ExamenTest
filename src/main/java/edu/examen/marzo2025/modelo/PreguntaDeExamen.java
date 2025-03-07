package edu.examen.marzo2025.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntadeexamen")
public class PreguntaDeExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_pregunta", nullable = false)
    private Pregunta pregunta;

    @Column(nullable = false)
    private int posicion;

    public PreguntaDeExamen() {
    }

    public PreguntaDeExamen(Examen examen, Pregunta pregunta, int posicion) {
        this.examen = examen;
        this.pregunta = pregunta;
        this.posicion = posicion;
    }

    public int getId() {
        return id;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "PreguntaDeExamen [id=" + id + ", examen=" + examen.getId() + ", pregunta=" + pregunta.getId() + ", posicion=" + posicion + "]";
    }
}
