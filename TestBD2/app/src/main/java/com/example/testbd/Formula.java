package com.example.testbd;

public class Formula {
       public String id,name,i,Oborot,KPD,KOEF,In,Om,Old,KVT,key;


    public Formula() {
    }
    public Formula(String key) {
        this.key=key;
    }
    public Formula(String id, String name, String KVT, String Oborot,String i, String KPD, String KOEF, String In, String Om, String Old) {
        this.id = id;
        this.name = name;
        this.KVT = KVT;
        this.Oborot = Oborot;
        this.i = i;
        this.KPD = KPD;
        this.KOEF = KOEF;
        this.In = In;
        this.Om = Om;
        this.Old = Old;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public  String toString(){
        return  name;
    }
}

