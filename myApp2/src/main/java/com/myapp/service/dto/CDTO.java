package com.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.myapp.domain.C} entity.
 */
public class CDTO implements Serializable {
    
    private Long id;

    @Min(value = 0)
    @Max(value = 41)
    private Integer count;


    private Long aId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long dId) {
        this.aId = dId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CDTO)) {
            return false;
        }

        return id != null && id.equals(((CDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CDTO{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", aId=" + getAId() +
            "}";
    }
}
