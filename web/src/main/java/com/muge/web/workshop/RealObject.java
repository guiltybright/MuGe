package com.muge.web.workshop;

public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSometing");
    }
    @Override
    public void somethingElse(String arg) {
        System.out.println("somethisElse " + arg);
    }
}
