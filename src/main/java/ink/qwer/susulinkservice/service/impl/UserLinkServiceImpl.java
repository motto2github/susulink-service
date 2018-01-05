package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.mapper.UserLinkMapper;
import ink.qwer.susulinkservice.service.UserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLinkServiceImpl implements UserLinkService {

    @Autowired
    private UserLinkMapper userLinkMapper;

    @Override
    public int count(Integer userId, String keywords) {
        UserLinkMapper.DTO dto = new UserLinkMapper.DTO();
        dto.userId = userId;
        dto.keywords = keywords;
        return this.userLinkMapper.count(dto);
    }

    @Override
    public List<UserLinkEntity> pageSelect(Integer userId, String keywords, Integer pageNumber, Integer pageSize) {
        UserLinkMapper.DTO dto = new UserLinkMapper.DTO();
        dto.userId = userId;
        dto.keywords = keywords;
        dto.pageNumber = pageNumber;
        dto.pageSize = pageSize;
        return this.userLinkMapper.pageSelect(dto);
    }

}
