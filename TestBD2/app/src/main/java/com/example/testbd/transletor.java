package com.example.testbd;

public class transletor {
   static String key,i1;

    public transletor() {

    }

    public transletor(String key,String i1) {
        this.key = key;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getI1() {
        return i1;
    }

    public void setI1(String i1) {
        this.i1 = i1;
    }
    @Override
    public  String toString(){
        return "Имя: " + i1;
    }
}
