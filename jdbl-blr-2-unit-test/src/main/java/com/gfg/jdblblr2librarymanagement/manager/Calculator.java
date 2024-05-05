package com.gfg.jdblblr2librarymanagement.manager;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {

    public int add(int a , int b){
        return square(a)+square(b);
    }

    public int sub(int a , int b){
        return square(a)-square(b);
    }

    public int divide(int a , int b){
        return div(a,b);
    }

    private int div(int a , int b){
        return a/b;
    }



    private int square(int a) {
        return  a * a;
    }
}
