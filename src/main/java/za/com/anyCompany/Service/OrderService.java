package za.com.anyCompany.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.com.anyCompany.DTO.RequestDto;
import za.com.anyCompany.DTO.ResponseDto;
import za.com.anyCompany.Repo.CustomerRepo;
import za.com.anyCompany.Repo.OrderRepo;
import za.com.anyCompany.Repo.ProductRepo;
import za.com.anyCompany.model.*;

import javax.persistence.*;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    //this is a value that can change, because of that we can add this in the application properties and call it here.
   // @Value("$(VAT)")
    private final double VAT_PERCENTAGE =15.0;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    public ResponseDto placeOrder(RequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto("Default value", true);

        //First need to check if the customer exists or not.
        //If it doesn't exist that create.
        customerService.checkIfCustomerExists(requestDto.getCustomer());

        //Need to check if the quantity of the products being bought are enough:
        requestDto.getProducts().parallelStream().forEach(x -> {
            ProductModel productModel = productRepo.findByProductName(x.getProductName());
            if (productModel == null) {
                responseDto.setMessage("Product Does not exist");
                responseDto.setStatus(false);
            } else {
                //meaning the product exists, now we need to find if we have enough of those products in order to process the Order itself
                if (productModel.getQuantity() - x.getQuantity() < 0) {
                    responseDto.setMessage("Quantity for product" + x.getProductName() + " is insufficient");
                    responseDto.setStatus(false);
                }
            }
        });
            //meaning validation has passed (quantity + product exists)

            //In order to know if the customer is based in the uk or not, we'll have to find look at the payload,
            // if the payload does not have an address object, it means that we are assuming that the customer has been saved before
            // and therefore we can check the db to see if it exist.
            //if the customer doesn't have an address saved then we return a false because the order can't be shipped and VAT can't be applied.

            //first step: Check if the payload has address;

            AddressModel shippingAddress = requestDto.getCustomer().getShippingAddress();
            if (shippingAddress == null) {
                AddressModel addressModelFromDb = addressService.findShippingAddress(requestDto.getCustomer());
                if(addressModelFromDb==null)
                {
                    responseDto.setMessage("No valid address found");
                    responseDto.setStatus(false);
                }
                else{
                 requestDto.setAmount(applyVat(addressModelFromDb,requestDto.getAmount()));
                }
            } else {
                    requestDto.setAmount(applyVat(shippingAddress,requestDto.getAmount()));
                }
            //if not then go to the database and select an existing shipping address.
            //if there's no shipping address in the database as well, then fail the order process.
            //There are two options here: we can either take the price of all the products add together and create a total of the order
            // or it can also be passed in the order model itself.. either way the vat will be totalAmount + totalAmount*vat if it's in the uk.

        if (responseDto.isStatus()) {

            //once validation is done, then you can process the order.

            //you can create a one to many relationship between the order and the products to make it easier.
            //you can also save them individually.

            orderRepo.saveAndFlush(new OrderModel(requestDto.getAmount(),requestDto.getCustomer()));
            responseDto.setMessage("Order has been successfully processed");
            }
        return responseDto;
    }

    private double applyVat(AddressModel addressModel, double amount) {
        if ("UK".equals(addressModel.getCountry())) {
            return amount + (amount * (VAT_PERCENTAGE / 100));
        } else {
            return amount;
        }
    }

}
