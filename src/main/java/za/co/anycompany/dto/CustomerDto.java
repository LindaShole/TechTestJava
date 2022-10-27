package za.co.anycompany.dto;

import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CustomerDto {

	@JsonProperty("customerId")
	private int customerId;

	@JsonProperty("customerName")
	private String name;

	@JsonProperty("country")
	private String country;

	@JsonProperty("dateOfBirth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	@JsonProperty("orders")
	private List<OrderDto> orders;

}

