package com.me.gacl;

/**
 * @author CH-yfy
 * @date 2017/12/25
 */
public class AopServiceImpls implements AopService {
    @Override
    public void doFunc() {
        System.out.println("===The function of [doFunc] in AopServiceImpls===");
    }

    @Override
    public void showFunc() {
        System.out.println("===The function of [showFunc] in AopServiceImpls===");
    }
}
