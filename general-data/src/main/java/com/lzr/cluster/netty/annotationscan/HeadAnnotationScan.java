package com.lzr.cluster.netty.annotationscan;

import com.lzr.cluster.common.LogicInfoHandler;
import com.lzr.cluster.netty.annotations.Head;
import com.lzr.cluster.netty.interfaces.GameMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzr
 * @date 2019/6/10 0010 22:41
 */
@Log4j2
public class HeadAnnotationScan implements /*ApplicationListener<ContextRefreshedEvent>, */ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /*@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if(event.getApplicationContext().getParent() == null)
            {
                Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Component.class);//获取全部Component
                Set<Map.Entry<String, Object>> entries = beans.entrySet();//遍历Bean
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Object> map = iterator.next();
                    Class<?> aClass = map.getValue().getClass();
                    Head annotation = aClass.getAnnotation(Head.class);
                    if (annotation!=null){
                        Class<?>[] interfaces = aClass.getInterfaces();
                        boolean flag = false;
                        for (Class<?> anInterface : interfaces) {
                            if (anInterface==GameMessage.class){
                                GameMessage gameMessage = (GameMessage) aClass.newInstance();
                                LogicInfoHandler.infoHandle.put(annotation.value(),gameMessage);
                                flag=true;
                                break;
                            }
                        }
                        if (!flag){
                            throw new Error("类["+aClass+"]未实现{GameMessage]");
                        }
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	log.info("扫描上下文数据信息,注入自定义注解");
        if (HeadAnnotationScan.applicationContext==null){
            HeadAnnotationScan.applicationContext=applicationContext;
        }
        String[] beanNamesForType = applicationContext.getBeanNamesForType(GameMessage.class);
        List<String> beanNames = Arrays.asList(beanNamesForType);
        for (String beanName : beanNames) {
            GameMessage gameMessage = applicationContext.getBean(beanName,GameMessage.class);
            Head annotation = gameMessage.getClass().getAnnotation(Head.class);
            if (annotation!=null){
                LogicInfoHandler.infoHandle.put(annotation.value(),gameMessage);
            }
        }
    }
}
