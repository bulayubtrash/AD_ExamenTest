package edu.examen.marzo2025.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int num_opciones;

    @Column(nullable = false)
    private int respuesta_correcta;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreguntaDeExamen> examenes;

    public Pregunta() {
    }

    public Pregunta(String nombre, int num_opciones, int respuesta_correcta) {
        this.nombre = nombre;
        this.num_opciones = num_opciones;
        this.respuesta_correcta = respuesta_correcta;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_opciones() {
        return num_opciones;
    }

    public void setNum_opciones(int num_opciones) {
        this.num_opciones = num_opciones;
    }

    public int getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public void setRespuesta_correcta(int respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public List<PreguntaDeExamen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<PreguntaDeExamen> examenes) {
        this.examenes = examenes;
    }

    @Override
    public String toString() {
        return "Pregunta [id=" + id + ", nombre=" + nombre + ", num_opciones=" + num_opciones + ", respuesta_correcta=" + respuesta_correcta + "]";
    }
}
