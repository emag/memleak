package org.emamotor.perf.memleak.model;

import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
public class Memleak implements Serializable {

    private int id;

    public Memleak(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memleak memleak = (Memleak) o;

        if (id != memleak.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Memleak{" +
                "id=" + id +
                '}';
    }

}
