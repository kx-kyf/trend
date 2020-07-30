package cn.how2j.trend.web;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class BackTestController {
    @Autowired
    BackTestService backTestService;


    @GetMapping("/simulate/{code}")
    public Map<String,Object> baskTest(@PathVariable("code") String code){
        List<IndexData> indexData = backTestService.listIndexData(code);
        Map<String,Object> result = new HashMap<>();
        result.put("indexDatas", indexData);
        return result;
    }


}
