package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.controller.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import ink.qwer.susulinkservice.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonLinkController {

    @Autowired
    private CommonLinkService commonLinkService;

    @PostMapping("/common-link/list")
    private ResponseDTO list(String keywords, int pageNumber, int pageSize) {
        ResponseDTO responseDTO = new ResponseDTO("1", "success");
        List<CommonLinkEntity> commonLinks = this.commonLinkService.list(keywords, pageNumber, pageSize);
        responseDTO.putDatum("links", commonLinks);
        responseDTO.putDatum("totalCount", this.commonLinkService.count(keywords));
        return responseDTO;
    }

}
