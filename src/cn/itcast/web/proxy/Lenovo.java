package cn.itcast.web.proxy;

/**
 * 真实类
 */
public class Lenovo implements SeleComputer {

    @Override
    public String sale(double money) {
        System.out.println("花了" + money + "元买了一台联想电脑");
        return "联想电脑";
    }

    public void show() {
        System.out.println("展示电脑...");
    }
}
