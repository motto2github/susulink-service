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
    private ResponseDTO list(int user_id, String keywords, int page_number, int page_size) {
        return this.userLinkService.pageSelectForController(user_id, keywords, page_number, page_size);
    }

    @PostMapping("/user-link/insert")
    private ResponseDTO insert(String title, String href, String summary, String icon_url, int user_id) {
        UserLinkEntity user_link_entity = new UserLinkEntity();
        user_link_entity.setTitle(title);
        user_link_entity.setHref(href);
        user_link_entity.setSummary(summary);
        user_link_entity.setIcon_url(icon_url);
        user_link_entity.setUser_id(user_id);
        return this.userLinkService.insertForController(user_link_entity);
    }

    @PostMapping("/user-link/remove")
    private ResponseDTO remove(int id) {
        return this.userLinkService.deleteForController(id);
    }

    @PostMapping("/user-link/update")
    private ResponseDTO update(int id, String title, String href, String summary, String icon_url, int user_id) {
        UserLinkEntity user_link_entity = new UserLinkEntity();
        user_link_entity.setId(id);
        user_link_entity.setTitle(title);
        user_link_entity.setHref(href);
        user_link_entity.setSummary(summary);
        user_link_entity.setIcon_url(icon_url);
        user_link_entity.setUser_id(user_id);
        return this.userLinkService.updateForController(user_link_entity);
    }

    @PostMapping("/user-link/findone")
    private ResponseDTO findOne(int id, int user_id) {
        return this.userLinkService.findOneForController(id, user_id);
    }

    @PostMapping("/user-link/parse-link")
    private ResponseDTO parseLink(String link) {
        return this.userLinkService.parseLinkForController(link);
    }

}
