package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable {
    private long id;
    @Size(max = 100, min = 1)
    private String name;

    public Tag() {}

    public Tag(long id) {
        this.id = id;
    }

    public Tag(String name) {
        this.name = name;
    }

    @JsonCreator
    public Tag(@JsonProperty("id") long id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Tag tag = (Tag) o;
        return getId()==tag.getId() && getName().equals(tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" id = ").append(id);
        builder.append(" name = ").append(name);
        return builder.toString();
    }
}
