package com.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.myapp.domain.enumeration.Type;

/**
 * A DTO for the {@link com.myapp.domain.D} entity.
 */
public class DDTO implements Serializable {
    
    private Long id;

    private Type name;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getName() {
        return name;
    }

    public void setName(Type name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DDTO)) {
            return false;
        }

        return id != null && id.equals(((DDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
