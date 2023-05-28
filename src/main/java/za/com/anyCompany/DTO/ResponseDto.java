package za.com.anyCompany.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto implements Serializable {

    private String message;
    private boolean status;
}
