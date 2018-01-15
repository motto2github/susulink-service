package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.service.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLinkController {

    @Autowired
    private UserLinkService userLinkService;

    @PostMapping("/user-link/list")
    private ResponseDTO list(int curUserId, String keywords, int pageNumber, int pageSize) {
        return this.userLinkService.pageSelectForController(curUserId, keywords, pageNumber, pageSize);
    }

    @PostMapping("/user-link/insert")
    private ResponseDTO insert(String title, String href, String summary, String iconUrl, int curUserId) {
        UserLinkEntity userLink = new UserLinkEntity();
        userLink.setTitle(title);
        userLink.setHref(href);
        userLink.setSummary(summary);
        userLink.setIcon_url(iconUrl);
        userLink.setUser_id(curUserId);
        return this.userLinkService.insertForController(userLink);
    }

}
