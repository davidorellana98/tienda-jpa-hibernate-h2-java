package org.alura.one.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@Entity
@Table(name = "productos")
@NamedQuery(name = "Producto.consultaPrecio", query = "SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre")
@Inheritance(strategy = InheritanceType.JOINED) // Hace la relación entre entidades separadas, y es utilizada en la clase base
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Producto() { }

    public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", fechaRegistro=" + fechaRegistro +
                ", categoria=" + categoria +
                '}';
    }
}
