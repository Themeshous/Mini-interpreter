package com.company;

public class Commande {
    /*********************************** ATTRIBUTS ***************************************/
    protected int position = -1; //représente la position dans la ligne de commande.
    protected int car; //représente le code ASCII du caractère correspondant à position.
    protected String str ;//représente la ligne de commande.


    /*********************************** METHODES **************************************/
    //------------------ LE CONSTRUCTEUR -------------------/
    public Commande(String ligne){ this.str = ligne ;}

    private void caractereSuivant() {
        this.car = (++this.position < str.length()) ? str.charAt(this.position) : -1 ;
    }
    //------------------ METHODE POUR EXTRAIRE LE NOM DE LA COMMANDE --------------//
    public String extraireCommande() throws CommandeNonValideException { // Cette méthode lève l'exception CommandeNonValide
        caractereSuivant(); //Pour se mettre au début de la ligne de commande.
        while (this.car >= 'a' && this.car <= 'z' ) caractereSuivant(); //Récupérer la commande lettre par lettre.
        String cmd  =  str.substring(0, this.position); //Récupérer la commande
        if(!cmd.equals("print") && !cmd.equals("let")) throw new CommandeNonValideException(cmd) ;
        return cmd ; //retourne la commande "let" ou "print"
    }
    //------------------ METHODE POUR EXTRAIRE L'EXPRESSION QUI SUIT LA COMMANDE --------------//
    public String extraireExpression() throws CommandeNonValideException, EspaceManquantException, CommandeNonValideException {//Cette méthode lève l'exception EspaceManquant
        this.position = -1 ;
        extraireCommande() ; //On extrait la commande.
        if(this.car != ' '){ // Si la commande n'est pas suivie par un espace :
            throw new EspaceManquantException(str); // On lève l'exeption indiquant qu'un espace manque.
        }
        else return str.substring(this.position, str.length()); //Retourne l'expression.
    }
}
