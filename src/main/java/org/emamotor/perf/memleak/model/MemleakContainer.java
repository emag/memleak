package org.emamotor.perf.memleak.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
public class MemleakContainer {

    private List<Memleak> memleaks = new ArrayList<>();

    public List<Memleak> getMemleaks() {
        return memleaks;
    }

    public void setMemleaks(List<Memleak> memleaks) {
        this.memleaks = memleaks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemleakContainer that = (MemleakContainer) o;

        if (memleaks != null ? !memleaks.equals(that.memleaks) : that.memleaks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return memleaks != null ? memleaks.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MemleakContainer{" +
                "memleaks=" + memleaks +
                '}';
    }

}
