package za.co.investec.test.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Orders")
public class Order {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    // For currency values BigDecimal should generally be used
    // due to potential rounding issues with double
    private BigDecimal amount;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal vat;
}


