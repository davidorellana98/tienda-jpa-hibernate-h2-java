package org.alura.one.tienda.modelo;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

// Esta anotación, me permite injectarla en otra clase para utilizar sus atributos y métodos
@Embeddable
public class DatosPersonales implements Serializable {

    @Serial
    private static final long serialVersionUID = 4198020985304539350L;

    private String nombre;
    private String dni;

    public DatosPersonales() { }

    public DatosPersonales(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
