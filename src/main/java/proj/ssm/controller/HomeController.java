package proj.ssm.controller;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import proj.ssm.entity.DiscussPost;
import proj.ssm.entity.Page;
import proj.ssm.entity.User;
import proj.ssm.service.DiscussPostService;
import proj.ssm.service.LikeService;
import proj.ssm.service.UserService;
import proj.ssm.util.SsmConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements SsmConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

//    @Value("${qiniu.key.access}")
//    private String accessKey;
//    @Value("${qiniu.key.secret}")
//    private String secretKey;
//    @Value("${qiniu.bucket.img}")
//    private String img;
    @Value("${quniu.bucket.url}")
    private String imgBucketUrl;


    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // 方法调用钱,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findDiscussPostRows(0)); //获取所有数据总行数
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount", likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);


//        Auth auth = Auth.create(accessKey, secretKey);
//        String downloadToken = auth.privateDownloadUrl(imgBucketUrl+"/java.png")
        String downloadUrl = imgBucketUrl+"cafe.png";
        model.addAttribute("iconPath",downloadUrl);
        return "/index";
    }

}