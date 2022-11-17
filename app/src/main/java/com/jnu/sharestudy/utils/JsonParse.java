package com.jnu.sharestudy.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnu.sharestudy.bean.Goods;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse(){}

    public static JsonParse getInstance(){
        if (instance==null){
            instance=new JsonParse();
        }
        return instance;
    }

    public ArrayList<Goods> getGoods(String json){
        Gson gson =new Gson();
        Type ListType =new TypeToken<ArrayList<Goods>> (){

        }.getType();
        ArrayList<Goods> goods=gson.fromJson(json,ListType);
        return goods;

    }
}
