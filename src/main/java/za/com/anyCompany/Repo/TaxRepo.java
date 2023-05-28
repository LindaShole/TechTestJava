package za.com.anyCompany.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.com.anyCompany.model.TaxModel;
@Repository
public interface TaxRepo extends JpaRepository<TaxModel,Long> {
}
