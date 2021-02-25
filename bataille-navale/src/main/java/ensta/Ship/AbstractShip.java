package ensta;

class AbstractShip {
    protected char label;
    protected String nom;
    protected int taille;
    protected Orientation orientation;
    protected int strikeCount;
    
    public AbstractShip(String nom, char label, int taille, Orientation orientation){
        this.nom = nom;
        this.label = label;
        this.taille = taille;
        this.orientation = orientation;
        this.strikeCount = 0;
    }

    public char getLabel(){
        return label;
    }

    public String getNom(){
        return nom;
    }

    public int getTaille(){
        return taille;
    }

    public Orientation getOrientation(){
        return orientation;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    public void addStrike(){
        strikeCount++;
    }

    public boolean isSunk(){
        if(strikeCount >= taille) return true;
        else return false;
    }
}