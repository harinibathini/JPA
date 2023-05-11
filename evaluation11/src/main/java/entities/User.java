package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private int phone;
    private long aadhaar;
    private long pan;
    private int balance;
    private String firmName;
    private int gstId;
    private String status;

    @ManyToOne
    private Bank bank;
    @ManyToMany
    private List<Account> accounts;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", aadhaar=" + aadhaar +
                ", pan=" + pan +
                ", balance=" + balance +
                ", firmName='" + firmName + '\'' +
                ", gstId=" + gstId +
                ", status='" + status + '\'' +
//                ", bank=" + bank +
//                ", accounts=" + accounts +
                '}';
    }
}
