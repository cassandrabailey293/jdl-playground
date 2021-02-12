package com.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A C.
 */
@Entity
@Table(name = "c")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class C implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    @Max(value = 41)
    @Column(name = "count")
    private Integer count;

    @OneToOne
    @JoinColumn(unique = true)
    private D a;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public C count(Integer count) {
        this.count = count;
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public D getA() {
        return a;
    }

    public C a(D d) {
        this.a = d;
        return this;
    }

    public void setA(D d) {
        this.a = d;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof C)) {
            return false;
        }
        return id != null && id.equals(((C) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "C{" +
            "id=" + getId() +
            ", count=" + getCount() +
            "}";
    }
}
