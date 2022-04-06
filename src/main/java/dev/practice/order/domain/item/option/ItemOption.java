package dev.practice.order.domain.item.option;

import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.security.InvalidParameterException;

@Slf4j
@Entity
@NoArgsConstructor
@Getter
@Table(name = "item_options")
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public ItemOption(ItemOptionGroup itemOptionGroup,
                      Integer ordering,
                      String itemOptionName,
                      Long itemOptionPrice
    ) {
        if (itemOptionGroup == null) throw new InvalidParameterException();
        if (ordering == null) throw new InvalidParameterException();
        if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParameterException();
        if (itemOptionPrice == null) throw new InvalidParameterException();

        this.itemOptionGroup = itemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
