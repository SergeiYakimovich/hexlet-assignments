package exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private Map<Object, String> inspectedBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if(bean.getClass().isAnnotationPresent(Inspect.class)) {
            String str = bean.getClass().getAnnotation(Inspect.class).level();
            inspectedBeans.put(bean,str);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if(inspectedBeans.containsKey(bean)) {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getName().equals("sum") || method.getName().equals("mult")) {
                            String message = "Was called method: " + method.getName() + "() with arguments: "
                                    + Arrays.toString(args);
                            if(inspectedBeans.get(bean).equalsIgnoreCase("info")) {
                                LOGGER.info(message);
                            } else {
                                LOGGER.debug(message);
                            }
                            return  method.invoke(bean, args);
                        } else {
                            throw new UnsupportedOperationException(
                                    "Unsupported method: " + method.getName());
                        }
                    }
            );
        }
        return bean;
    }

}
// END
