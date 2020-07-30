package cn.how2j.trend.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:因为做集群时候不同服务的接口是不同的，使用这个类获得当前服务的ip
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.config*
 * @since 1.0
 */
@Configuration
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
    public int getPort() {
        return this.serverPort;
    }


}
