package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;

import java.util.List;

public interface CommonLinkService {

    int count(String keywords);

    List<CommonLinkEntity> list(String keywords, int pageNumber, int pageSize);

}
