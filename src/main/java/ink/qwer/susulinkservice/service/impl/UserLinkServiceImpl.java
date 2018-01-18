package ink.qwer.susulinkservice.service.impl;

import ink.qwer.susulinkservice.dto.ResponseDTO;
import ink.qwer.susulinkservice.entity.UserEntity;
import ink.qwer.susulinkservice.entity.UserLinkEntity;
import ink.qwer.susulinkservice.mapper.UserLinkMapper;
import ink.qwer.susulinkservice.service.UserLinkService;
import ink.qwer.susulinkservice.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserLinkServiceImpl implements UserLinkService {

    @Autowired
    private UserLinkMapper userLinkMapper;

    @Autowired
    private UserService userService;

    private static class LinkInfoDTO {

        public String title;
        public String icon_url;
        public String keywords;
        public String description;

        private LinkInfoDTO() {
        }

        private LinkInfoDTO(String title, String icon_url, String keywords, String description) {
            this.title = title;
            this.icon_url = icon_url;
            this.keywords = keywords;
            this.description = description;
        }

        @Override
        public String toString() {
            return "LinkInfoDTO{" +
                    "title='" + title + '\'' +
                    ", icon_url='" + icon_url + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", description='" + description + '\'' +
                    '}' + '\n';
        }
    }

    private static final Map<String, LinkInfoDTO> HOT_LINK_INFO_KVS = new HashMap<String, LinkInfoDTO>();

    static {

        LinkInfoDTO bd = new LinkInfoDTO("百度一下，你就知道", "https://www.baidu.com/favicon.ico", "百度搜索", "全球最大的中文搜索引擎、致力于让网民更便捷地获取信息，找到所求。");
        LinkInfoDTO jd = new LinkInfoDTO("京东(JD.COM)-正品低价、品质保障、配送及时、轻松购物！", "https://www.jd.com/favicon.ico", "网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD,DV,相机,数码,配件,手表,存储卡,京东", "京东JD.COM-专业的综合网上购物商城,销售家电、数码通讯、电脑、家居百货、服装服饰、母婴、图书、食品等数万个品牌优质商品.便捷、诚信的服务，为您提供愉悦的网上购物体验!");

        HOT_LINK_INFO_KVS.put("http://baidu.com", bd);
        HOT_LINK_INFO_KVS.put("https://baidu.com", bd);
        HOT_LINK_INFO_KVS.put("http://www.baidu.com", bd);
        HOT_LINK_INFO_KVS.put("https://www.baidu.com", bd);

        HOT_LINK_INFO_KVS.put("http://jd.com", jd);
        HOT_LINK_INFO_KVS.put("https://jd.com", jd);
        HOT_LINK_INFO_KVS.put("http://www.jd.com", jd);
        HOT_LINK_INFO_KVS.put("https://www.jd.com", jd);

    }


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
        return responseDTO
                .putDatum("links", this.userLinkMapper.pageSelect(dto))
                .putDatum("totalCount", this.count(userId, keywords))
                .set("1", "查询成功");
    }

    @Override
    public ResponseDTO deleteForController(int id) {
        ResponseDTO responseDTO = new ResponseDTO();
        int effectRows = this.userLinkMapper.delete(id);
        if (effectRows != 1) {
            return responseDTO.set("-99", "数据库异常，请稍后重试");
        }
        return responseDTO.set("1", "删除成功");
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

    @Override
    public ResponseDTO updateForController(UserLinkEntity userLinkEntity) {
        ResponseDTO responseDTO = new ResponseDTO();
        int id = userLinkEntity.getId();
        String title = userLinkEntity.getTitle();
        String href = userLinkEntity.getHref();
        int userId = userLinkEntity.getUser_id();
        if (id < 1 || userId < 1 || title == null || "".equals(title) || href == null || "".equals(href)) {
            return responseDTO.set("-88", "请求参数异常");
        }
        if (this.userService.select(id) == null) {
            return responseDTO.set("-1", "该用户不存在");
        }
        int count = this.userLinkMapper.countForUpdateCheck(userId, title, id);
        if (count > 0) {
            return responseDTO.set("-1", "该标题已存在");
        }
        userLinkEntity.setUpdate_time(new Date());
        int effectRows = this.userLinkMapper.updateById(userLinkEntity);
        if (effectRows != 1) {
            return responseDTO.set("-99", "数据库异常，请稍后重试");
        }
        return responseDTO.set("1", "修改成功");
    }

    @Override
    public ResponseDTO findOneForController(int id, int userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        UserLinkEntity userLinkEntity = this.userLinkMapper.selectById(id);
        if (userLinkEntity == null) {
            return responseDTO.set("-1", "找不到该信息");
        }
        return responseDTO
                .putDatum("link", userLinkEntity)
                .set("1", "查找成功");
    }

    @Override
    public ResponseDTO parseLinkForController(String link) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (link == null || "".equals(link)) {
            return responseDTO.set("-88", "请求参数异常");
        }

        String protocol = ""; // like http:
        Matcher protocolMatcher = Pattern.compile("^([^:]+:)//").matcher(link);
        if (protocolMatcher.find()) {
            protocol = protocolMatcher.group(1);
        }

        if (!"http:".equalsIgnoreCase(protocol) && !"https:".equalsIgnoreCase(protocol)) {
            return responseDTO.set("-88", "请求参数异常");
        }

        String origin = ""; // like http://www.baidu.com
        Matcher originMatcher = Pattern.compile("(^[^:]+://[^/?]+)").matcher(link);
        if (originMatcher.find()) {
            origin = originMatcher.group(1);
        }

        LinkInfoDTO linkInfoDTO = HOT_LINK_INFO_KVS.get(origin);
        if (linkInfoDTO != null) {
            return responseDTO.putDatum("link_info", linkInfoDTO).set("1", "操作成功");
        }

        Document doc = null;
        try {
            doc = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
                    .get();
            if (doc == null) throw new IOException();
        } catch (IOException e) {
            return responseDTO.set("-1", "抱歉，暂未能读取到默认信息");
        }

        Element head = doc.head();

        linkInfoDTO = new LinkInfoDTO();

        Element title = head.getElementsByTag("title").first();
        linkInfoDTO.title = title == null ? "" : title.text();

        Elements links = head.getElementsByTag("link");
        for (Element element : links) {
            String rel = element.attr("rel");
            if (rel.matches("(?i)shortcut|icon|shortcut\\s{1,}icon|icon\\s{1,}shortcut")) {
                String icon_url = element.attr("href");
                if (icon_url.matches("(?i)data:image/.+")) {
                    continue;
                }
                if (icon_url.matches("(?i)https?://.+")) {
                    // nothing
                } else if (icon_url.matches("//.+")) {
                    icon_url = protocol + icon_url;
                } else if (icon_url.matches("/.+")) {
                    icon_url = origin + icon_url;
                } else {
                    icon_url = origin + '/' + icon_url;
                }
                linkInfoDTO.icon_url = icon_url;
                break;
            }
        }

        Elements metas = head.getElementsByTag("meta");
        for (Element element : metas) {
            String name = element.attr("name");
            if ("keywords".equalsIgnoreCase(name)) {
                linkInfoDTO.keywords = element.attr("content");
            } else if ("description".equalsIgnoreCase(name)) {
                linkInfoDTO.description = element.attr("content");
            }
            String keywords = linkInfoDTO.keywords;
            String description = linkInfoDTO.description;
            if (keywords != null && !"".equals(keywords) && description != null && !"".equals(description)) {
                break;
            }
        }

        HOT_LINK_INFO_KVS.put(origin, linkInfoDTO);

        return responseDTO.putDatum("link_info", linkInfoDTO).set("1", "操作成功");
    }

}
