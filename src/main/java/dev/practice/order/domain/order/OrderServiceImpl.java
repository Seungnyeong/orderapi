package dev.practice.order.domain.order;

import dev.practice.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderStore orderStore;
    private final ItemReader itemReader;
    private final OrderItemSeriesFactory itemSeriesFactory;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        Order order = orderStore.store(requestOrder.toEntity());
        itemSeriesFactory.store(order, requestOrder);
//

        return order.getOrderToken();
    }
}
