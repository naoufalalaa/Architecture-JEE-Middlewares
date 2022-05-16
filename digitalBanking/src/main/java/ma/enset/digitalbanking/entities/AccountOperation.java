package ma.enset.digitalbanking.entities;

import ma.enset.digitalbanking.enums.OpType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OpType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;

}