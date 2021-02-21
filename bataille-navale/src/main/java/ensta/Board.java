package ensta;

public class Board implements IBoard{
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
                if (this.hasShip(i, j)) System.out.print(this.navires[i-1][j-1] + " ");
                else System.out.print(". ");
            }
            System.out.print(nString(4, " "));
            System.out.print(i + " ");
            for (int j = 0; j < reste; j++){
                System.out.print(" ");
            }
            for (int j = 1; j <= taille; j++){
                if (this.getHit(i, j)) System.out.print("x ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    public int getSize(){
        return this.navires.length;
    }

    public void putShip(AbstractShip ship, int x, int y){
        try{
            if ((ship.orientation == Orientation.NORTH && (x - ship.taille < 0))
            || (ship.orientation == Orientation.SOUTH && (x + ship.taille - 1 > this.getSize()))
            || (ship.orientation == Orientation.EAST && (y + ship.taille - 1 > this.getSize()))
            || (ship.orientation == Orientation.WEST && (y - ship.taille < 0))){
                throw new Exception("[Exception] Bateau depasse du plateau.");
            }
            if (ship.orientation == Orientation.NORTH){
                for (int i = x; i > x - ship.taille; i--){
                    if ((this.navires[i-1][y-1] == 'D')
                    || (this.navires[i-1][y-1] == 'S')
                    || (this.navires[i-1][y-1] == 'B')
                    || (this.navires[i-1][y-1] == 'C')) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.SOUTH){
                for (int i = x; i < x + ship.taille; i++){
                    if ((this.navires[i-1][y-1] == 'D')
                    || (this.navires[i-1][y-1] == 'S')
                    || (this.navires[i-1][y-1] == 'B')
                    || (this.navires[i-1][y-1] == 'C')) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.EAST){
                for (int j = y; j < y + ship.taille; j++){
                    if ((this.navires[x-1][j-1] == 'D')
                    || (this.navires[x-1][j-1] == 'S')
                    || (this.navires[x-1][j-1] == 'B')
                    || (this.navires[x-1][j-1] == 'C')) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.WEST){
                for (int j = y; j > y - ship.taille; j--){
                    if ((this.navires[x-1][j-1] == 'D')
                    || (this.navires[x-1][j-1] == 'S')
                    || (this.navires[x-1][j-1] == 'B')
                    || (this.navires[x-1][j-1] == 'C')) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        if (ship.orientation == Orientation.NORTH){
            for (int i = x; i > x - ship.taille; i--){
                this.navires[i-1][y-1] = ship.label;
            }
        }
        if (ship.orientation == Orientation.SOUTH){
            for (int i = x; i < x + ship.taille; i++){
                this.navires[i-1][y-1] = ship.label;
            }
        }
        if (ship.orientation == Orientation.EAST){
            for (int j = y; j < y + ship.taille; j++){
                this.navires[x-1][j-1] = ship.label;
            }
        }
        if (ship.orientation == Orientation.WEST){
            for (int j = y; j > y - ship.taille; j--){
                this.navires[x-1][j-1] = ship.label;
            }
        }
    }

    public boolean hasShip(int x, int y){
        if ((this.navires[x-1][y-1] == 'D')
        || (this.navires[x-1][y-1] == 'S')
        || (this.navires[x-1][y-1] == 'B')
        || (this.navires[x-1][y-1] == 'C')) return true;
        else return false;
    }

    public void setHit(boolean hit, int x, int y){
        this.frappes[x-1][y-1] = hit;
    }

    public Boolean getHit(int x, int y){
        return this.frappes[x-1][y-1];
    }
}