package ro.sda.hypermarket.core.entity;

import ro.sda.hypermarket.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients", schema = "hypermarket")
public class Client extends BaseEntity {

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) &&
                Objects.equals(getName(), client.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
