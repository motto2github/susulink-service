package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.service.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/susulink-service/user-link")
public class UserLinkController {

    @Autowired
    private UserLinkService userLinkService;

    @PostMapping("/list")
    private ResponseDTO list(@RequestBody RequestBodyDTO dto) {
        return this.userLinkService.pageSelectForController(dto.user_id, dto.keywords, dto.page_number, dto.page_size);
    }

    @PostMapping("/insert")
    private ResponseDTO insert(@RequestBody RequestBodyDTO dto) {
        UserLinkEntity user_link_entity = new UserLinkEntity();
        user_link_entity.setTitle(dto.title);
        user_link_entity.setHref(dto.href);
        user_link_entity.setSummary(dto.summary);
        user_link_entity.setIcon_url(dto.icon_url);
        user_link_entity.setUser_id(dto.user_id);
        return this.userLinkService.insertForController(user_link_entity);
    }

    @PostMapping("/remove")
    private ResponseDTO remove(@RequestBody RequestBodyDTO dto) {
        return this.userLinkService.deleteForController(dto.id);
    }

    @PostMapping("/update")
    private ResponseDTO update(@RequestBody RequestBodyDTO dto) {
        UserLinkEntity user_link_entity = new UserLinkEntity();
        user_link_entity.setId(dto.id);
        user_link_entity.setTitle(dto.title);
        user_link_entity.setHref(dto.href);
        user_link_entity.setSummary(dto.summary);
        user_link_entity.setIcon_url(dto.icon_url);
        user_link_entity.setUser_id(dto.user_id);
        return this.userLinkService.updateForController(user_link_entity);
    }

    @PostMapping("/findone")
    private ResponseDTO findOne(@RequestBody RequestBodyDTO dto) {
        return this.userLinkService.findOneForController(dto.id, dto.user_id);
    }

    @PostMapping("/parse-link")
    private ResponseDTO parseLink(@RequestBody RequestBodyDTO dto) {
        return this.userLinkService.parseLinkForController(dto.link);
    }

    private static class RequestBodyDTO {

        public int user_id;
        public String keywords;
        public int page_number;
        public int page_size;
        public String title;
        public String href;
        public String summary;
        public String icon_url;
        public int id;
        public String link;

    }

}
