package za.com.anyCompany.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.com.anyCompany.model.AddressModel;
import za.com.anyCompany.model.ProductModel;


@Repository
public interface ProductRepo extends JpaRepository<ProductModel,Long> {

    ProductModel findByProductName(String productName);
}
