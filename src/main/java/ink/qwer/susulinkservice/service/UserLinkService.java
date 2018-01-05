package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.UserLinkEntity;

import java.util.List;

public interface UserLinkService {

    int count(Integer userId, String keywords);

    List<UserLinkEntity> pageSelect(Integer userId, String keywords, Integer pageNumber, Integer pageSize);

}
