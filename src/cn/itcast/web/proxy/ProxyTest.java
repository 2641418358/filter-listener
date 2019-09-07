package cn.itcast.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实对象
        Lenovo lenovo = new Lenovo();

        /*
            三个参数：
                1.类加载器：真实对象.getClass().getClassLoader()
                2.接口数组：真实对象.getClass().getInterfaces()
                3.处理器：new InvocationHandler()
         */
        //2.动态代理增强lenovo对象
        SeleComputer proxy_lenovo = (SeleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /*
                代理逻辑编写得方法：代理对象调用得所有方法都会触发该方法执行
                    参数：
                        1.proxy：代理对象
                        2.method：代理对象调用得方法，被封装为得对象
                        3.args：代理对象调用得方法，传递得实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //System.out.println("该方法被执行了...");

                //判断是否是sale（）方法
                if(method.getName().contains("sale")){
                    //1.增强参数
                    double money = (double) args[0];

                    money = money * 0.85;
                    System.out.println("专车接你。。。");
                    //使用真实对象调用该方法
                    String obj = (String) method.invoke(lenovo, money);
                    System.out.println("免费送货。。。");
                    //增强返回值

                    return obj + "以及鼠标点";
                }else {
                    Object obj = method.invoke(lenovo, args);

                    return obj;
                }

            }
        });

        //3.调用方法
        /*String computer = proxy_lenovo.sale(8000);
        System.out.println(computer);*/

        proxy_lenovo.show();

    }
}
