package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.controller.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.service.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLinkController {

    @Autowired
    private UserLinkService userLinkService;

    @PostMapping("/user-link/list")
    private ResponseDTO list(int curUserId, String keywords, int pageNumber, int pageSize) {
        ResponseDTO responseDTO = new ResponseDTO("1", "success");
        List<UserLinkEntity> userLinkEntities = this.userLinkService.pageSelect(curUserId, keywords, pageNumber, pageSize);
        responseDTO.putDatum("links", userLinkEntities);
        responseDTO.putDatum("totalCount", this.userLinkService.count(curUserId, keywords));
        return responseDTO;
    }

}
