package za.co.anycompany.mapper;

import org.mapstruct.Mapper;
import za.co.anycompany.dto.OrderDTO;
import za.co.anycompany.persistence.entity.OrderEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(OrderEntity entity);
    List<OrderDTO> toDTOs(List<OrderEntity> entities);
}
