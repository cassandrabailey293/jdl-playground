package com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.myapp.domain.enumeration.Type;

/**
 * A D.
 */
@Entity
@Table(name = "d")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class D implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Type name;

    @OneToOne(mappedBy = "a")
    @JsonIgnore
    private C d;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getName() {
        return name;
    }

    public D name(Type name) {
        this.name = name;
        return this;
    }

    public void setName(Type name) {
        this.name = name;
    }

    public C getD() {
        return d;
    }

    public D d(C c) {
        this.d = c;
        return this;
    }

    public void setD(C c) {
        this.d = c;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof D)) {
            return false;
        }
        return id != null && id.equals(((D) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "D{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
