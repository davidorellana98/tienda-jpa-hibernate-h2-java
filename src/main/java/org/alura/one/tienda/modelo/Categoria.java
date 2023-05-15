package org.alura.one.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    private CategoriaId categoriaId;

    public Categoria(String nombre) {
        this.categoriaId = new CategoriaId(nombre, "123");
    }

    public Categoria() { }

    public String getNombre() {
        return categoriaId.getNombre();
    }

    public void setNombre(String nombre) {
        this.categoriaId.setNombre(nombre);
    }
}
