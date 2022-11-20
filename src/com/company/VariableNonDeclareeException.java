package com.company;

public class VariableNonDeclareeException extends Exception {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\033[0m" ;
    private String cause;
    public VariableNonDeclareeException(String cause){
        this.cause=cause;
    }
    public String getMessage()
    {
        System.out.println(ANSI_RED+"ERREUR : Variable non déclarée ("+cause+")."+ANSI_RESET);
        return null;
    }
}
