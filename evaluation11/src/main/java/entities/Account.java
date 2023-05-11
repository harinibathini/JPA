package entities;

import lombok.Data;

import javax.persistence.*;
import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String accountType;
    private int accountNo;
    private Date accountDate;
    @ManyToMany(mappedBy = "accounts",cascade = CascadeType.ALL)
    private List<User> users;
    @ManyToOne
    private Bank bank;

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountNo=" + accountNo +
                ", accountDate=" + accountDate +
//                ", users=" + users +
//                ", bank=" + bank +
                '}';
    }
}
