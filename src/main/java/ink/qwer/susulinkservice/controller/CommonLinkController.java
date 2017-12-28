package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import ink.qwer.susulinkservice.mapper.CommonLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonLinkController {

    @Autowired
    private CommonLinkMapper commonLinkMapper;

    @GetMapping("/common-link/all")
    private List<CommonLinkEntity> getAll() {
        return this.commonLinkMapper.getAll();
    }

}
