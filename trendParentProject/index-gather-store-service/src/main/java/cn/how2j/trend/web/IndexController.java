package cn.how2j.trend.web;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.web*
 * @Date 2020/7/24
 * @since 1.0
 */
@RestController
public class IndexController {
    @Autowired
    IndexService indexService;

    @GetMapping("/freshCodes")
    public List<Index> fresh() throws Exception {
        return indexService.fresh();
    }
    @GetMapping("/removeCodes")
    public String remove() throws Exception {
        indexService.remove();
        return "remove codes successfully";
    }
    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexService.get();
    }



}
