package cn.how2j.trend.web;

import cn.how2j.trend.config.IpConfiguration;
import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.web*
 * @since 1.0
 */
@RestController
@CrossOrigin
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    IpConfiguration configuration;

    @GetMapping("/codes")
    public List<Index> codes() throws Exception {
        System.out.println("current instance's port is "+ configuration.getPort());
        return indexService.get();
    }
}
