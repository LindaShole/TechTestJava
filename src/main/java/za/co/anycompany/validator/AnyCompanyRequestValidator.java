package za.co.anycompany.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.dto.OrderDto;

@Component
public class AnyCompanyRequestValidator  implements Validator {
	private static final String FULL_NAME_REGEX = "^[\\p{L} .'-]+$";
	private static final String COUNTRY_NAME_REGEX = "^[a-zA-Z ]*$";

	private static final String CUSTOMER_NAME_FIELD = "name";
	private static final String CUSTOMER_NAME_ERROR_MESSAGE = "Please enter valid name of customer.";
	private static final String CUSTOMER_COUNTRY_FIELD = "country";
	private static final String CUSTOMER_COUNRTY_ERROR_MESSAGE = "Please enter valid country name.";
	private static final String CUSTOMER_ID_FIELD = "customerId";
	private static final String CUSTOMER_ID_ERROR_MESSAGE = "Please provide the valid customer for order. Customer id can not be 0 for order creation.";
	private static final String ORDER_AMOUNT_FIELD = "amount";
	private static final String ORDER_AMOUNT_ERROR_MESSAGE = "Please provide the valid order amount. Amount can not be 0.";

	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerDto.class.equals(clazz) || OrderDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (CustomerDto.class.equals(target.getClass())) {
			CustomerDto customerDto = (CustomerDto) target;
			if (!fieldValidator(customerDto.getName(), FULL_NAME_REGEX)) {
				errors.rejectValue(CUSTOMER_NAME_FIELD, CUSTOMER_NAME_ERROR_MESSAGE);
			}

			if (!fieldValidator(customerDto.getCountry(), COUNTRY_NAME_REGEX)) {
				errors.rejectValue(CUSTOMER_COUNTRY_FIELD, CUSTOMER_COUNRTY_ERROR_MESSAGE);
			}
		} else if (OrderDto.class.equals(target.getClass())) {
			OrderDto orderDto = (OrderDto) target;
			if (orderDto.getCustomerId() <= 0) {
				errors.rejectValue(CUSTOMER_ID_FIELD, CUSTOMER_ID_ERROR_MESSAGE);
			}

			if (orderDto.getAmount() <= 0) {
				errors.rejectValue(ORDER_AMOUNT_FIELD, ORDER_AMOUNT_ERROR_MESSAGE);
			}
		}

	}

	private static boolean fieldValidator(String value, String regex) {
		return !value.isEmpty() && Pattern.compile(regex).matcher(value).matches();
	}
}
