package dev.practice.order.domain.item;

import com.google.common.collect.Lists;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "items")
@Getter
public class Item extends AbstractEntity {

    private static final String PREFIX_ITEM = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken;
    private Long partnerId;
    private String itemName;
    private Long itemPrice;

    // Item : ItemOptionGroup = 1:N

    // mappedBy 참조관계의 주인이 되는 쪽을 선언하는 것
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;
    }

    public void changePrepare() {
        this.status = Status.PREPARE;
    }

    public void changeOnSales() {
        this.status = Status.ON_SALES;
    }

    public void changeEndOfSales() {
        this.status = Status.END_OF_SALES;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {
        if (partnerId == null) throw new InvalidParameterException();
        if (StringUtils.isEmpty(itemName)) throw new InvalidParameterException();
        if (itemPrice == null) throw new InvalidParameterException();

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.partnerId = partnerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }
}
