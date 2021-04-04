package com.stykkapi.stykka.exceptions;

public class StykkaExceptions extends Exception{
    public StykkaExceptions(){

    }

    public StykkaExceptions(String message){
        super(message);
    }

    public StykkaExceptions(String message, Throwable cause){
        super(message, cause);
    }
}
