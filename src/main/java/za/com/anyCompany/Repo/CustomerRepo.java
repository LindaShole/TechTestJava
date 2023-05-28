package za.com.anyCompany.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.com.anyCompany.model.CustomerModel;
@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {
   CustomerModel findById(int id);
}
