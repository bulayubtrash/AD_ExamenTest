package edu.examen.marzo2025.modelo;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private int curso;

    @Column(length = 10, nullable = false)
    private String grupo;

    @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreguntaDeExamen> preguntas;

    public Examen() {
    }

    public Examen(Date fecha, int curso, String grupo) {
        this.fecha = fecha;
        this.curso = curso;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public List<PreguntaDeExamen> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaDeExamen> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public String toString() {
        return "Examen [id=" + id + ", fecha=" + fecha + ", curso=" + curso + ", grupo=" + grupo + "]";
    }
}
