package org.sas.model;

import java.util.Objects;

public class Sensor {
    private int id;
    private String name;
    private SensorType type;
    private User user;

    public int getId() {
        return id;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sensor{id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type.getName() +
                ", user=" + user.getLogin() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return id == sensor.id && Objects.equals(name, sensor.name) && Objects.equals(type, sensor.type)
                && Objects.equals(user, sensor.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type.getName(), user.getLogin());
    }
}
