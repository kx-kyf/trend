package cn.how2j.trend.web;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class IndexDataController {
    @Autowired
    IndexDataService indexDataService;

    @GetMapping("/freshIndexData/{code}")
    public String fresh(@PathVariable("code") String code){
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }

    @GetMapping("/getIndexData/{code}")
    public List<IndexData> get(@PathVariable("code") String code){
        return indexDataService.get(code);
    }

    @GetMapping("/removeIndexData/(code)")
    public String remove(@PathVariable("code") String code){
        indexDataService.remove(code);
        return "remove index data successfully";
    }
}
