package ink.qwer.susulinkservice.controller;

import ink.qwer.susulinkservice.entity.CommonLinkStarEntity;
import ink.qwer.susulinkservice.mapper.CommonLinkStarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonLinkStarController {

    @Autowired
    private CommonLinkStarMapper commonLinkStarMapper;

    @GetMapping("/common-link-star/all")
    private List<CommonLinkStarEntity> getAll() {
        return this.commonLinkStarMapper.getAll();
    }

}
