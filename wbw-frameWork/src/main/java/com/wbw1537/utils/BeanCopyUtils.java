package com.wbw1537.utils;

import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.HotArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
public class BeanCopyUtils {

    private BeanCopyUtils(){
    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source, result);
            return result;
        } catch (Exception e) {
            log.error("Error occurred in BeanCopyUtils! {}", e.getMessage());
        }
        //返回bean拷贝对象
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
