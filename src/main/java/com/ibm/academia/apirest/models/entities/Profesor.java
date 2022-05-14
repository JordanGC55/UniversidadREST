package com.ibm.academia.apirest.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "profesores", schema = "universidad")
//@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona{

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profesor_carrera", schema = "universidad",
    //@JoinTable(name = "profesor_carrera",
                joinColumns = @JoinColumn(name = "profesor_id"),
                inverseJoinColumns = @JoinColumn(name = "carrera_id"))
    private Set<Carrera> carreras;
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "profesores"})


    public Profesor(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo=sueldo;
    }

    @Override
    public String toString() {
        return super.toString() + "Profesor{" +
                "sueldo=" + sueldo +
                '}';
    }

    private static final long serialVersionUID = 6212454084535480124L;
}
