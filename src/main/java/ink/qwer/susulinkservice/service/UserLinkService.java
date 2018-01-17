package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserLinkEntity;

public interface UserLinkService {

    ResponseDTO insertForController(UserLinkEntity userLink);

    ResponseDTO pageSelectForController(int userId, String keywords, int pageNumber, int pageSize);

    ResponseDTO deleteForController(int id);

    int count(int userId, String keywords);

    boolean existTitleForSomeUser(String title, int userId);

    ResponseDTO updateForController(UserLinkEntity userLink);

    ResponseDTO findOneForController(int id, int userId);

    ResponseDTO parseLinkForController(String link);

}
