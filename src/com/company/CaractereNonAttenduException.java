package com.company;

public class CaractereNonAttenduException extends Exception{
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\033[0m" ;
    private int cause;
    public CaractereNonAttenduException(int cause){
        this.cause=cause;
    }
    public String getMessage()
    {
        System.out.println(ANSI_RED+"ERREUR : Caractère non attendu ("+(char)cause+")."+ANSI_RESET);
        return null;
    }
}
