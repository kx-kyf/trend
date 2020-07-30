package cn.how2j.trend.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 描述 :用于SpringBoot机制问题，从已经存在的方法调用redis的方法(自己调用自己的时候看下面的解释)，不会触发redis相关操作，所以用这种方法重新获取一次
 *Spring AOP无法拦截内部的方法调用，所以无法执行一系列拦截代理操作。换言之，实际调用的是this.remove,而不是proxy.remove，Spring的注解处理器就无法生效，但是通过代理方法获得类的方式时就可以正确的执行注解处理器的操作。
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.util*
 * @Date 2020/5/24
 * @since 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private SpringContextUtil() {
        System.out.println("SpringContextUtil()");
    }
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext:"+applicationContext);
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static<T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }


}
