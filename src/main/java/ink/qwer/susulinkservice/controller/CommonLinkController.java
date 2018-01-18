package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/susulink-service/common-link")
public class CommonLinkController {

    @Autowired
    private CommonLinkService commonLinkService;

    @PostMapping("/list")
    private ResponseDTO list(@RequestBody RequestBodyDTO dto) {
        return this.commonLinkService.pageSelectForController(dto.keywords, dto.page_number, dto.page_size);
    }

    private static class RequestBodyDTO {

        public String keywords;
        public int page_number;
        public int page_size;

    }

}

