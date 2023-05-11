package entities;

import entities.Animal;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int zooId;
    private String zooName;
    private String zooCity;

    @OneToMany(mappedBy = "zoo",cascade = CascadeType.ALL)
    private List<Animal> animals;

    public int getZooId() {
        return zooId;
    }

    public void setZooId(int zooId) {
        this.zooId = zooId;
    }

    public String getZooName() {
        return zooName;
    }

    public void setZooName(String zooName) {
        this.zooName = zooName;
    }

    public String getZooCity() {
        return zooCity;
    }

    public void setZooCity(String zooCity) {
        this.zooCity = zooCity;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public Zoo() {
    }

    public Zoo(int zooId, String zooName, String zooCity, List<Animal> animals) {
        this.zooId = zooId;
        this.zooName = zooName;
        this.zooCity = zooCity;
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "entities.Zoo{" +
                "zooId=" + zooId +
                ", zooName='" + zooName + '\'' +
                ", zooCity='" + zooCity + '\'' +
                ", animals=" + animals +
                '}';
    }
}
