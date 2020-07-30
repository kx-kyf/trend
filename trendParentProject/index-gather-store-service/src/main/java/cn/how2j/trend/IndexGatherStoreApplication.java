package cn.how2j.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend*
 * @Date 2020/5/24
 * @since 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
public class IndexGatherStoreApplication {
    public static void main(String[] args) {
        int port = 0;
        int defaultPort = 8001;
        int redisPort = 6379;
        int eurekaServerPort = 8761;
        port = defaultPort;

        if (NetUtil.isUsableLocalPort(eurekaServerPort)){
            System.err.printf("检查到端口%d未启动，判断Eureka服务中心未启动，本服务无法使用，故退出%n",eurekaServerPort);
            System.exit(1);
        }
        if (NetUtil.isUsableLocalPort(redisPort)){
            System.err.printf("检查到端口%d未启动，判断redis服务中心未启动，本服务无法使用，故退出%n",redisPort);
            System.exit(1);
        }
        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }
        new SpringApplicationBuilder(IndexGatherStoreApplication.class).properties("server.port=" + port).run(args);
    }
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
