package dev.practice.order.domain.item.optionGroup;

import com.google.common.collect.Lists;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.option.ItemOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.List;

@Slf4j
@Entity
@NoArgsConstructor
@Getter
@Table(name = "item_option_groups")
public class ItemOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer ordering;
    private String itemOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = Lists.newArrayList();

    @Builder
    public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
        if (item == null) throw new InvalidParameterException();
        if (ordering == null) throw new InvalidParameterException();
        if (StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParameterException();

        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}
