package za.co.anycompany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.anycompany.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Double> {

}
