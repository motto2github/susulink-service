package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonLinkController {

    @Autowired
    private CommonLinkService commonLinkService;

    @PostMapping("/common-link/list")
    private ResponseDTO list(String keywords, int pageNumber, int pageSize) {
        return this.commonLinkService.pageSelectForController(keywords, pageNumber, pageSize);
    }

}
