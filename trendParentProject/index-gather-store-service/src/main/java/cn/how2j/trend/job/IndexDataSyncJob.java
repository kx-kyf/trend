package cn.how2j.trend.job;

import cn.how2j.trend.pojo.Index;
import cn.how2j.trend.service.IndexDataService;
import cn.how2j.trend.service.IndexService;
import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.job*
 * @Date 2020/7/24
 * @since 1.0
 */
public class IndexDataSyncJob extends QuartzJobBean {
    @Autowired
    private IndexService indexService;
    @Autowired
    private IndexDataService indexDataService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时启动："+ DateUtil.now());
        List<Index> indexs = indexService.fresh();
        for (Index index : indexs) {
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束" +DateUtil.now());
    }

}
