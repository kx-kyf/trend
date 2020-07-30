package cn.how2j.trend.service;

import cn.how2j.trend.pojo.Index;
import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.service*
 * @Date 2020/7/24
 * @since 1.0
 */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    private List<Index> indexes;


    /**
     * @Author 乔木
     * @Description 如果从缓存中取不到值，就返回一个无效代码指数回去
     * @Date 8:28 2020/7/26
     * @Param []
     * @Return java.util.List<cn.how2j.trend.pojo.Index>
     **/
    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        Index index = new Index("无效代码指数","000000");
        return CollUtil.toList(index);
    }

}
