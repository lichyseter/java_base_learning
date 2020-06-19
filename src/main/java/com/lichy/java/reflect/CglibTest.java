package com.lichy.java.reflect;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * 测试cglib动态代理
 *
 * @author lichy
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibEntity.class);
        // 根据proxyfilter中的index返回这两个中的一个
        enhancer.setCallbacks(new Callback[]{new ProxyClass(), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new ProxyFilter());
        CglibEntity myInterface = (CglibEntity) enhancer.create();
        System.out.println(myInterface.getInterfaceValue(1));
    }

    /**
     * 代理类
     */
    static class ProxyClass implements MethodInterceptor {

        //代理类需要代理的方法
        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            System.out.println("调用之前");
            System.out.println("参数" + args[0]);
            System.out.println("函数计算值" + proxy.invokeSuper(obj, args));
            System.out.println("调用之后");
            return "成功";
        }
    }

    /**
     * 过滤器
     */
    static class ProxyFilter implements CallbackFilter {

        @Override
        public int accept(Method arg0) {
            if ("getInterfaceValue".equalsIgnoreCase(arg0.getName())) {
                return 0;
            }
            return 1;
        }

    }

}
