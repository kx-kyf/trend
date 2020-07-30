package cn.how2j.trend;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
public class ThirdPartIndexDataApplication {
    public static void main(String[] args) {
        int port = 8090;
        int eurekaServerPort = 8761;
        if (NetUtil.isUsableLocalPort(eurekaServerPort)){
            System.err.printf("检查到端口%d未启动，判断Eureka服务中心未启动，本服务无法使用，故退出%n",eurekaServerPort);
            System.exit(1);
        }
        //如果启动就带了参数，程序就会用那个参数作为端口，代码可以不用改，只要在启动时带不同的参数即可
        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if (arg.startsWith("port=")) {
                    String strPort = StrUtil.subAfter(arg, "port=", true);
                    if (NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(ThirdPartIndexDataApplication.class).properties("server.port=" + port).run(args);
    }
}
