package entities;

import javax.persistence.*;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animalId;
    private String animalName;
    private int animalAge;
    private String animalType;

    //private Date joinDate;

    @ManyToOne
    private Zoo zoo;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public Animal() {
    }

    public Animal(int animalId, String animalName, int animalAge, String animalType, Zoo zoo) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalType = animalType;
        this.zoo = zoo;
    }

    @Override
    public String toString() {
        return "entities.Animal{" +
                "animalId=" + animalId +
                ", animalName='" + animalName + '\'' +
                ", animalAge=" + animalAge +
                ", animalType='" + animalType + '\'' +
//               ", zoo=" + zoo +
                '}';
    }
}
