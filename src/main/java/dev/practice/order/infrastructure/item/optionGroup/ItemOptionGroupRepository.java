package dev.practice.order.infrastructure.item.optionGroup;

import dev.practice.order.domain.item.optionGroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, Long> {
}
