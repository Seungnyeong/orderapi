package dev.practice.order.interfaces.item;


import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    // register
    @Mappings({@Mapping(source = "request.itemOptionGroupList", target = "itemOptionGroupRequestList")})
    ItemCommand.RegisterItemRequest of(ItemDTO.RegisterItemRequest request);

    @Mappings({@Mapping(source = "itemOptionList", target = "itemOptionRequestList")})
    ItemCommand.RegisterItemOptionGroupRequest of(ItemDTO.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest of(ItemDTO.RegisterItemOptionRequest request);

    ItemDTO.RegisterResponse of(String itemToken);

    // retrieve
    ItemDTO.Main of(ItemInfo.Main main);

    ItemDTO.ItemOptionGroupInfo of(ItemInfo.ItemOptionGroupInfo itemOptionGroup);

    ItemDTO.ItemOptionInfo of(ItemInfo.ItemOptionInfo itemOption);
}
