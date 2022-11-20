package com.company;
import java.util.HashMap;
public class Let extends Commande{
    /********************************* LES ATTRIBUTES *********************************/
    String expr ; //Représente tout ce qui suit la commande let ( variable + expression ).
    /********************************* LES METHODES **********************************/
    //---------------------------- LE CONSTRUCTEUR -------------------------/
    public Let(String ligne , String expression) {
        super(ligne); //Le constructeur de la super classe (commande).
        this.expr = expression.substring(1, expression.length()) ; // substring(1,expression.length()) pour enlever l'espace avant l'expression et récupérer ce qui le suit.
    }
    //---------------------------- POUR RECUPERER LE CARACTERE SUIVANT DANS STR ---------------------//
    private void caractereSuivant(String str) {
        if (++this.position < str.length()) {  //la position suivante ne dépasse pas l'expression.
            this.car = str.charAt(this.position);
        } else { //sinon
            this.car = -1;
        }
    }
    //---------------------------- POUR EXECUTER LA COMMANDE LET ----------------------------------//
    public void executer(HashMap table) throws AffectationNonValideException, CaractereNonAttenduException, VariableNonValideException {
        int pos = 0 ;// représente la dernière position visitée dans l'expression.
        this.position = -1 ;
        caractereSuivant(expr);
        if (car >= 'a' && car <= 'z' || (car >= 'A' && car <= 'Z')) { // verifier si la variable commence par un alphabet.
            while (car >= 'a' && car <= 'z' || (car >= '0' && car <= '9') || (car >= 'A' && car <= 'Z')) caractereSuivant(expr);
            String variable = expr.substring(0, this.position); // récuperer le nom de la variabe
            if (variable.equals("sqrt") || variable.equals("sin") || variable.equals("cos") ||
                    variable.equals("tan")|| variable.equals("log")|| variable.equals("abs")|| variable.equals("exp")){
                throw new VariableNonValideException(variable);
            }
            pos = this.position ;
            while( car == ' ' || car == '=' ) caractereSuivant(expr); //Inclure les cas : 'x=8', 'x= 8', 'x =8', 'x = 8'
            if (expr.substring(pos, this.position).equals(" =") || expr.substring(pos, this.position).equals("= ") || expr.substring(pos, this.position).equals("=")
                    || expr.substring(pos, this.position).equals(" = ") ) // verifier c'est une affectation valide.
            {
                try {
                    String expressionValeur = expr.substring(this.position, expr.length()); // récuperer le reste de l'éxpression.
                    Double valeur = Element.evaluer(expressionValeur, table);  //avoir la valeur de l'éxpression.
                    table.put(variable, valeur); // ajouter le couple (variable, valeur) à la table des symboles.
                    System.out.println("Ok.");
                }
                catch (VariableNonDeclareeException | CaractereNonAttenduException | ParentheseException |FonctionNonValideException e)
                {
                    e.getMessage() ;
                }
            }
            else throw new AffectationNonValideException(expr.substring(pos, this.position));
        }
        else throw new CaractereNonAttenduException(car); //La variable commence par un caractère non alphabétique.
    }
}
