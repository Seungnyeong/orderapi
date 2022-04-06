package dev.practice.order.domain.item;

import dev.practice.order.domain.item.optionGroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item);
}