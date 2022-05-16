package ma.enset.digitalbanking.dtos;

import lombok.Data;
import ma.enset.digitalbanking.enums.OpType;

import java.util.Date;

@Data

public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private double amount;
    private OpType type;
    private String description;

}