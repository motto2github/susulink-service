package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.entity.CommonLinkEntity;
import ink.qwer.susulinkservice.mapper.CommonLinkMapper;
import ink.qwer.susulinkservice.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonLinkServiceImpl implements CommonLinkService {

    @Autowired
    private CommonLinkMapper commonLinkMapper;

    @Override
    public int count(String keywords) {
        CommonLinkMapper.DTO dto = new CommonLinkMapper.DTO();
        dto.keywords = keywords;
        return this.commonLinkMapper.count(dto);
    }

    @Override
    public List<CommonLinkEntity> list(String keywords, int pageNumber, int pageSize) {
        CommonLinkMapper.DTO dto = new CommonLinkMapper.DTO();
        dto.keywords = keywords;
        dto.pageNumber = pageNumber;
        dto.pageSize = pageSize;
        return commonLinkMapper.pageSelect(dto);
    }

}
