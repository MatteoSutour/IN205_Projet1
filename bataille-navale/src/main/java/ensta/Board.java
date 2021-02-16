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

    public void print(){
        System.out.println("Navires :");
        int taille = this.navires.length;
        int marge = 0;
        while (taille >= 1){
            taille /= 10;
            marge++;
        }
        taille = this.navires.length;
        for (int i = 0; i < marge + 1;i++) System.out.print(" ");
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
            System.out.println();
        }

        System.out.println("\nFrappes :");
        for (int i = 0; i < marge + 1;i++) System.out.print(" ");
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
            System.out.println();
        }
    }
}