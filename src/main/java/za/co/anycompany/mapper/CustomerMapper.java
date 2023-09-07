package za.co.anycompany.mapper;

import org.mapstruct.Mapper;
import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.OrderDTO;
import za.co.anycompany.persistence.entity.CustomerEntity;
import za.co.anycompany.persistence.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity entity);
    List<CustomerDTO> toDTOs(List<CustomerEntity> entities);
}
