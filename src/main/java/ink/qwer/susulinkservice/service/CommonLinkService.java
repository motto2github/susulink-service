package ink.qwer.susulinkservice.service;

import ink.qwer.susulinkservice.dto.ResponseDTO;

public interface CommonLinkService {

    ResponseDTO pageSelectForController(String keywords, int pageNumber, int pageSize);

}
