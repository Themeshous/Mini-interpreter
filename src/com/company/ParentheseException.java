package com.company;

public class ParentheseException extends Exception {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\033[0m" ;
    public String getMessage()
    {
        System.out.println(ANSI_RED+"ERREUR : Parenthèse manquante."+ANSI_RESET);
        return null;
    }
}
