package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.mapper.UserLinkMapper;
import ink.qwer.susulinkservice.service.UserLinkService;
import ink.qwer.susulinkservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserLinkServiceImpl implements UserLinkService {

    @Autowired
    private UserLinkMapper userLinkMapper;

    @Autowired
    private UserService userService;


    @Override
    public ResponseDTO insertForController(UserLinkEntity userLinkEntity) {
        ResponseDTO responseDTO = new ResponseDTO();
        String title = userLinkEntity.getTitle();
        String href = userLinkEntity.getHref();
        int userId = userLinkEntity.getUser_id();
        if (title == null || "".equals(title) || href == null || "".equals(href)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        UserEntity userEntity = this.userService.select(userId);
        if (userEntity == null) {
            return responseDTO.set("-88", "请求参数异常，找不到该用户");
        }
        if (this.existTitleForSomeUser(title, userId)) {
            return responseDTO.set("-1", "该标题已存在");
        }
        Date now = new Date();
        userLinkEntity.setCreate_time(now);
        userLinkEntity.setUpdate_time(now);
        int effectRows = this.userLinkMapper.insert(userLinkEntity);
        if (effectRows < 1) {
            return responseDTO.set("-99", "数据库异常，请稍后重试");
        }
        return responseDTO.set("1", "添加成功");
    }

    @Override
    public ResponseDTO pageSelectForController(int userId, String keywords, int pageNumber, int pageSize) {
        ResponseDTO responseDTO = new ResponseDTO();
        UserLinkMapper.DTO dto = new UserLinkMapper.DTO();
        dto.userId = userId;
        dto.keywords = keywords;
        dto.pageNumber = pageNumber;
        dto.pageSize = pageSize;
        List<UserLinkEntity> userLinkEntities = this.userLinkMapper.pageSelect(dto);
        responseDTO.putDatum("links", userLinkEntities);
        responseDTO.putDatum("totalCount", this.count(userId, keywords));
        return responseDTO.set("1", "success");
    }


    @Override
    public int count(int userId, String keywords) {
        UserLinkMapper.DTO dto = new UserLinkMapper.DTO();
        dto.userId = userId;
        dto.keywords = keywords;
        return this.userLinkMapper.count(dto);
    }


    @Override
    public boolean existTitleForSomeUser(String title, int userId) {
        return this.userLinkMapper.selectByTitleAndUserId(title, userId) != null;
    }

}