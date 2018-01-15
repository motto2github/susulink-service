package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.mapper.CommonLinkMapper;
import ink.qwer.susulinkservice.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonLinkServiceImpl implements CommonLinkService {

    @Autowired
    private CommonLinkMapper commonLinkMapper;


    @Override
    public ResponseDTO pageSelectForController(String keywords, int pageNumber, int pageSize) {
        ResponseDTO responseDTO = new ResponseDTO();

        CommonLinkMapper.DTO dto = new CommonLinkMapper.DTO();
        dto.keywords = keywords;
        dto.pageNumber = pageNumber;
        dto.pageSize = pageSize;
        responseDTO.putDatum("links", this.commonLinkMapper.pageSelect(dto));

        dto = new CommonLinkMapper.DTO();
        dto.keywords = keywords;
        responseDTO.putDatum("totalCount", this.commonLinkMapper.count(dto));

        return responseDTO.set("1", "success");
    }

}
