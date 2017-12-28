package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.mapper.UserLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLinkController {

    @Autowired
    private UserLinkMapper userLinkMapper;

    @GetMapping("/user-link/all")
    private List<UserLinkEntity> getAll() {
        return this.userLinkMapper.getAll();
    }

}
