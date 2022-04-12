package com.epam.esm.dao.entity;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Certificate implements Serializable {
    private long id;
    @Size(max = 100, min = 1)
    private String name;
    @Size(max = 100, min = 1)
    private String description;
    @Min(1)
    private double price;
    @Min(1)
    private int duration;
    private String createDate;
    private String lastUpdateDate;
    private List<String> tagNames;

    public Certificate() {
    }

    public Certificate(long id) {
        this.id = id;
    }

    public Certificate(String name,String description, double price,
                       long id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public Certificate(String name, double price, String description, List<String> tagNames, int duration) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.tagNames = tagNames;
        this.duration = duration;
    }

    @JsonCreator
    public Certificate(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("description") String description,
                       @JsonProperty("price") double price,@JsonProperty("duration") int duration,
                       @JsonProperty("tagNames") List<String> tagNames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.tagNames = tagNames;
        this.duration = duration;
    }

    public Certificate(String name, double price,
                       int duration, String createDate, String lastUpdateDate, String description) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    public Certificate(long id, String name, String description, Double price,
                       int duration, String createDate, String lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    @JsonGetter("id")
    public long getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("price")
    public double getPrice() {
        return price;
    }

    @JsonSetter("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonGetter("duration")
    public int getDuration() {
        return duration;
    }

    @JsonSetter("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonGetter("createDate")
    public String getCreateDate() {
        return createDate;
    }

    @JsonSetter("createDate")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonGetter("lastUpdateDate")
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    @JsonSetter("lastUpdateDate")
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    @JsonGetter("tagNames")
    public List<String> getTagNames() {
        return tagNames;
    }
    @JsonSetter("tagNames")
    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(", id = ").append(id);
        builder.append(", name = ").append(name);
        builder.append(", description = ").append(description);
        builder.append(", price = ").append(price);
        builder.append(", duration = ").append(duration);
        builder.append(", createDate = ").append(createDate);
        builder.append(", lastUpdateDate = ").append(lastUpdateDate);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return getId() == that.getId() && Double.compare(that.getPrice(), getPrice()) == 0 && getDuration() == that.getDuration() && getName().equals(that.getName()) && getDescription().equals(that.getDescription()) && getCreateDate().equals(that.getCreateDate()) && getLastUpdateDate().equals(that.getLastUpdateDate()) && getTagNames().equals(that.getTagNames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getDuration(), getCreateDate(), getLastUpdateDate(), getTagNames());
    }
}
