package com.company;
import java.util.HashMap;
public class Print extends Commande{
    /********************************* LES ATTRIBUTS **********************************/
    String expr;
    /********************************* LES METHODES ***********************************/
    //----------------------- LE CONSTRUCTEUR ---------------------------------/
    public Print(String ligne, String expression) {
        super(ligne);
        this.expr = expression ;
    }
    //---------------------- EXECUTER LA COMMANDE PRINT -----------------------/
    public void executer(HashMap table)
    {
        try {
            System.out.println("La valeur est: " + Element.evaluer(expr, table)); //appele a la methode evaluer
        }
        catch (VariableNonDeclareeException | CaractereNonAttenduException | ParentheseException | FonctionNonValideException e)
        {
            e.getMessage() ;
        }

    }
}
