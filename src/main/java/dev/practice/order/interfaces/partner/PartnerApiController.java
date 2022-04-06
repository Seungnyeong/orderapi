package dev.practice.order.interfaces.partner;

import dev.practice.order.application.partner.PartnerFacade;
import dev.practice.order.common.response.CommonResponse;
import dev.practice.order.domain.partner.PartnerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;
    private final PartnerDtoMapper partnerDtoMapper;

    @PostMapping
    public CommonResponse registerPartner(@RequestBody @Valid PartnerDTO.RegisterRequest request) {

        // dto request -> command
        var command = partnerDtoMapper.of(request);
        var partnerInfo = partnerFacade.registerPartner(command);
        var response = new PartnerDTO.RegisterResponse(partnerInfo);
        return CommonResponse.success(response);
        // 1. 외부에서 전달된 파라미터 (dto) -> Command, Criteria convert 로직
        // 2. facade 호출 .. PartnerInfo
        // 3. PartnerInfo -> CommonResponse convert And return
    }
}
