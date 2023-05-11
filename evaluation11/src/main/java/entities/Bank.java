package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String bankName;
    private int bankIFSC;
    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private List<User> users;
    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private List<Account> accounts;

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", bankIFSC=" + bankIFSC +
//                ", users=" + users +
//                ", accounts=" + accounts +
                '}';
    }
}
