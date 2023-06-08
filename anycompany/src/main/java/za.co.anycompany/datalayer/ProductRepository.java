package za.co.anycompany.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Product;

@Repository
@RepositoryRestResource(exported = false)
public interface ProductRepository extends CrudRepository<Product,Long> {
}
