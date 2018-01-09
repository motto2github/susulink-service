package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.UserLinkEntity;

import java.util.List;

public interface UserLinkService {

    int count(int userId, String keywords);

    List<UserLinkEntity> pageSelect(int userId, String keywords, int pageNumber, int pageSize);

}
