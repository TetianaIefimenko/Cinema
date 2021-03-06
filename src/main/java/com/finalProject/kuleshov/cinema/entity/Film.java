package com.finalProject.kuleshov.cinema.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Film implements Serializable {

    private static final long serialVersionUID = 7064528245536775789L;

    private int id;
    private String name;
    private String directedBy;
    private String description;
    private int duration;
    private Date date;
    private String img;


    public Film() {
    }

    public Film(String name, String directedBy, String description, int duration) {
        this.name = name;
        this.directedBy = directedBy;
        this.description = description;
        this.duration = duration;
    }

    public Film(String name, String directedBy, String description) {
        this.name = name;
        this.directedBy = directedBy;
        this.description = description;
    }

    public Film(String name, String directedBy, String description, int duration, String img) {
        this.name = name;
        this.directedBy = directedBy;
        this.description = description;
        this.duration = duration;
        this.img = img;
    }

    public Film(int id, String name, String directedBy, String description, int duration, String img) {
        this.id = id;
        this.name = name;
        this.directedBy = directedBy;
        this.description = description;
        this.duration = duration;
        this.img = img;
    }

    public Film(int id, String name, String directedBy, String description, int duration) {
        this.id = id;
        this.name = name;
        this.directedBy = directedBy;
        this.description = description;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", directedBy='" + directedBy + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", date=" + date +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return duration == film.duration && Objects.equals(name, film.name) && Objects.equals(directedBy, film.directedBy) && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, directedBy, description, duration);
    }
}
