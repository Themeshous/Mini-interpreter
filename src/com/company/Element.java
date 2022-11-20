package com.company;

import java.util.HashMap;

public class Element {
    /************************************ LES METHODES ****************************************/
    //---------------------------------- POUR EVALUER TOUS LES ELEMENTS D'UNE EXPRESSION ----------------------//
    public static double evaluer(final String str, HashMap variables) throws VariableNonDeclareeException, CaractereNonAttenduException, ParentheseException, FonctionNonValideException {
        return new Object() {
            int position = -1; //La position dans l'expression.
            int car; // le caractère correspondant à position.
            //----------------------------- FOURNIT LE CARACTERE SUIVANT DANS UN ELEMENT ---------------------------//
            void caractereSuivant() {
                car = (++position < str.length()) ? str.charAt(position) : -1;
            }
            //----------------------------- VERIFIE SI 'car' CORRESOND A UN CARACTERE DONNEE ----------------------//
            boolean Verifier(int car) {
                while (this.car == ' ') caractereSuivant();
                if (this.car == car) {
                    caractereSuivant();
                    return true;
                }
                return false;
            }
            //----------------------------------- ANALYSER UN ELEMENT ---------------------------//
            double analyser() throws CaractereNonAttenduException, VariableNonDeclareeException, ParentheseException, FonctionNonValideException {
                caractereSuivant();
                double x = analyserExpression(); //Analyser l'expression.
                if (position < str.length()) throw new CaractereNonAttenduException(car) ;
                return x;
            }
            //---------------------------------------- ANALYSER UNE EXPRESSION --------------------------------//
            double analyserExpression() throws VariableNonDeclareeException, CaractereNonAttenduException, ParentheseException, FonctionNonValideException {
                double x = analyserTerme();
                for (;;) { // Une boucle infinie jusqu'à avoir un certain x.
                    if      (Verifier('-')) x -= analyserTerme(); // La soustraction.
                    else if (Verifier('+')) x += analyserTerme(); // L'addition.
                    else return x;
                }
            }
            //---------------------------------------- ANALYSER LE TERME ----------------------------------------//
            double analyserTerme() throws VariableNonDeclareeException, CaractereNonAttenduException, ParentheseException, FonctionNonValideException {
                double x = analyserFacteur();
                for (;;) {
                    if      (Verifier('*')) x *= analyserFacteur(); // multiplication
                    else if (Verifier('/')) { //La division
                        try {
                            x /= analyserFacteur();
                        }
                        catch (ArithmeticException e)
                        {
                            System.out.println("ERREUR : Division par zero !");
                        }
                    }
                    else return x ;
                }
            }
            //---------------------------------------- ANALYSER LE FACTEUR ------------------------------------------//
            double analyserFacteur() throws VariableNonDeclareeException, ParentheseException,CaractereNonAttenduException, FonctionNonValideException {
                if (Verifier('+')) return analyserFacteur(); //pour traiter les nombres positis commençant par un +.
                if (Verifier('-')) return -analyserFacteur(); //pour traiter les nombres négatifs.
                double x;
                int pos = this.position; //Position de début.
                if (Verifier('(')) { // Vérification des parenthèses
                    x = analyserExpression();
                    if(!Verifier(')')) throw new ParentheseException() ;
                }  else if ((car >= '0' && car <= '9') || car == '.') { // le cas des nombres
                    while ((car >= '0' && car <= '9') || car == '.') caractereSuivant();
                    x = Double.parseDouble(str.substring(pos, this.position));
                } else if (car >= 'a' && car <= 'z'|| (car >= 'A' && car <= 'Z')) { // Le cas des fonctions et variables
                    while (car >= 'a' && car <= 'z' || (car >= '0' && car <= '9') || (car >= 'A' && car <= 'Z')) caractereSuivant();
                    String fonct = str.substring(pos, this.position);
                    String variable = fonct;
                    if(!(variable.equals("sqrt") || variable.equals("sin") || variable.equals("cos") ||
                            variable.equals("tan")|| variable.equals("log")|| variable.equals("abs")|| variable.equals("exp"))){// Donc c'est une variable
                        if (variables.containsKey(variable)) x = (double) variables.get(variable);
                        else throw new VariableNonDeclareeException(variable);}
                    else { //C'est une fonction
                        x = analyserFacteur();
                        x = switch (fonct) { // On la verifie avec les autres fonctions
                            case "sin" -> Math.sin(Math.toRadians(x));
                            case "cos" -> Math.cos(Math.toRadians(x));
                            case "tan" -> Math.tan(Math.toRadians(x));
                            case "abs" -> Math.abs(x);
                            case "sqrt" -> Math.sqrt(x);
                            case "log" -> Math.log(x);
                            default -> throw new FonctionNonValideException(fonct);
                        };
                    }
                }  else {
                    throw new CaractereNonAttenduException(car);
                }
                if (Verifier('^')) x = Math.pow(x, analyserFacteur()); // Traitement de puissance
                return x;
            }
        }.analyser();
    }
}
