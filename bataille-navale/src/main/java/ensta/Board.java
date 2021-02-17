package ensta;

public class Board{
    private String nom;
    private char navires[][];
    private boolean frappes[][];

    public Board(String nom, int taille){
        this.nom = nom;
        this.navires = new char[taille][taille];
        this.frappes = new boolean[taille][taille];
    }

    public Board(String nom){
        this(nom, 10);
    }
    
    private String nString(int n, String s){
        String result = s;
        for (int i = 1; i < n; i++) result += s;
        return result;
    }

    public void print(){
        int taille = this.navires.length;
        int marge = 0;
        while (taille >= 1){
            taille /= 10;
            marge++;
        }
        taille = this.navires.length;
        System.out.println("Navires :" + nString(2*taille+marge-4, " ") + "Frappes :");
        System.out.print(nString(marge+1, " "));
        for (char c = 'A'; c <= 'A' + taille - 1; c++) System.out.print(c + " ");
        System.out.print(nString(4+marge+1, " "));
        for (char c = 'A'; c <= 'A' + taille - 1; c++) System.out.print(c + " ");

        System.out.println();

        for (int i = 1; i <= taille; i++){
            System.out.print(i + " ");
            int reste = 0;
            int temp = i;
            while (temp >= 1){
            temp /= 10;
            reste++;
            }
            reste = marge - reste;
            for (int j = 0; j < reste; j++){
                System.out.print(" ");
            }
            for (int j = 1; j <= taille; j++){
                System.out.print(". ");
            }
            System.out.print(nString(4, " "));
            System.out.print(i + " ");
            for (int j = 0; j < reste; j++){
                System.out.print(" ");
            }
            for (int j = 1; j <= taille; j++){
                System.out.print(". ");
            }
            System.out.println();
        }
    }
}