package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Interpreteur {

    /****************************************** ATTRIBUTS ************************************/
    HashMap<String, Double> tableDesSymboles = new HashMap<String,Double>() ; //représente la table des symboles qui est structurée en HashMap.
    private String ligne ; //représente la ligne de commande

    /****************************************** METHODES *************************************/
    //------------- LE CONSTRUCTEUR ---------------//
    public Interpreteur()  {
        Scanner sc= new Scanner(System.in);
        System.out.print("\n\nEntrez vos commandes. Tapez end pour terminer votre programme.\n");
        System.out.print("Une commande doit être de la forme\n");
        System.out.print("let <variable> = <expression>\n");
        System.out.print("ou\n");
        System.out.print("print <expression>\n\n");
        System.out.print("> ");
        this.ligne = sc.nextLine() ; //Lire une commande à partir deu clavier
        while(!(this.ligne).equals("end")) //boucle afin d'interpréter les commandes, "end" pour quitter l'invité de commande.
        {
            interpreter();
            System.out.print("> ");
            this.ligne = sc.nextLine();
        }
    }
    //--------------- Pour interpréter les commandes -----------------//
    public void interpreter() {
        Commande commande = new Commande(this.ligne);
        try {
            String cmd = commande.extraireCommande(); //Pour extraire la commande à exécuter.
            String expr = commande.extraireExpression() ; //extraire l'expression associée à la commande.
            if (cmd.equals("print")) // c'est la commande print
            {
                Print print = new Print(this.ligne , expr) ;
                print.executer(tableDesSymboles); //exécuter la commande "print".
            }
            else if (cmd.equals("let"))
            {
                Let let = new Let(this.ligne , expr) ;
                let.executer(tableDesSymboles);//exécuter la commande "let".
            }
        }
        catch (CommandeNonValideException | EspaceManquantException | AffectationNonValideException | CaractereNonAttenduException | VariableNonValideException e)
        {
            e.getMessage() ;
        }
    }
}
