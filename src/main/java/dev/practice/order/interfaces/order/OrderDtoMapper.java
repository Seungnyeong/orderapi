package dev.practice.order.interfaces.order;


import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    @Mappings({
            @Mapping(source = "orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    OrderDTO.Main of(OrderInfo.Main mainResult);

    OrderDTO.DeliveryInfo of(OrderInfo.DeliveryInfo deliveryResult);

    OrderDTO.OrderItem of(OrderInfo.OrderItem orderItemResult);

    OrderCommand.RegisterOrder of(OrderDTO.RegisterOrderRequest request);

    OrderCommand.RegisterOrderItem of(OrderDTO.RegisterOrderItem request);

    OrderCommand.RegisterOrderItemOptionGroup of(OrderDTO.RegisterOrderItemOptionGroupRequest request);

    OrderCommand.RegisterOrderItemOption of(OrderDTO.RegisterOrderItemOptionRequest request);

    OrderDTO.RegisterResponse of(String orderToken);

    OrderCommand.PaymentRequest of(OrderDTO.PaymentRequest request);
}

