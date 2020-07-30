package cn.how2j.trend.service;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.util.SpringContextUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.service*
 * @Date 2020/5/24
 * @since 1.0
 */
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    private HashMap<String, List<IndexData>> indexDatas = new HashMap<>();
    @Autowired
    RestTemplate restTemplate;

    public List<IndexData> fetch_indexes_from_third_part(String code){
        List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json",List.class);
        return map2IndexData(temp);
    }

    private List<IndexData> map2IndexData(List<Map> temp) {
        List<IndexData> indexDatas = new ArrayList<>();
        for (Map map : temp) {
            String date = map.get("date").toString();
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData(date,closePoint);
            indexDatas.add(indexData);
        }
        return indexDatas;
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> fresh(String code){
        List<IndexData> indexDatasfromdata = fetch_indexes_from_third_part(code);
        indexDatas.put(code, indexDatasfromdata);
        System.out.println("code:"+code);
        System.out.println("indexeDatas:"+indexDatas.get(code).size());
        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }

    @CachePut(key = "'indexData-code-'+ #p0")
    public List<IndexData> store(String code) {
        return indexDatas.get(code);
    }

    @CacheEvict(key = "'indexData-code-'+ #p0")
    public void remove(String code) {}

    @Cacheable(key = "'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }

    public List<IndexData> third_part_not_connected(String code){
        System.out.println("third_part_not_connected()");
        IndexData index= new IndexData("n/a",0);
        return CollectionUtil.toList(index);
    }

}
