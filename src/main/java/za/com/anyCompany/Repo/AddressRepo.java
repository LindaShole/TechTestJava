package za.com.anyCompany.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.com.anyCompany.model.AddressModel;


@Repository
public interface AddressRepo extends JpaRepository<AddressModel,Long> {

  AddressModel findByCustomerId(long customerId);

}
