package ma.enset.digitalbanking.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount extends BankAccount{
    private double overDraft;
}