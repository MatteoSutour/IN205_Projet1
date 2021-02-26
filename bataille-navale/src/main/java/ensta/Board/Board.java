package ensta;

public class Board implements IBoard{
    private String nom;
    private ShipState navires[][];
    private Boolean frappes[][];

    public Board(String nom, int taille){
        this.nom = nom;
        this.navires = new ShipState[taille][taille];
        this.frappes = new Boolean[taille][taille];
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
                if (this.hasShip(j-1, i-1)) System.out.print(this.navires[i-1][j-1].toString() + " ");
                else System.out.print(". ");
            }
            System.out.print(nString(4, " "));
            System.out.print(i + " ");
            for (int j = 0; j < reste; j++){
                System.out.print(" ");
            }
            for (int j = 1; j <= taille; j++){
                if (this.getHit(j-1, i-1) == null) System.out.print(". ");
                else if (this.getHit(j-1, i-1) == false) System.out.print("X ");
                else if (this.getHit(j-1, i-1) == true) System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
            }
            System.out.println();
        }
    }

    public int getSize(){
        return this.navires.length;
    }

    public void putShip(AbstractShip ship, int x, int y){
        try{
            if ((ship.orientation == Orientation.NORTH && (y - ship.taille + 1 < 0))
            || (ship.orientation == Orientation.SOUTH && (y + ship.taille >= this.getSize()))
            || (ship.orientation == Orientation.EAST && (x + ship.taille >= this.getSize()))
            || (ship.orientation == Orientation.WEST && (x - ship.taille + 1 < 0))){
                throw new Exception("[Exception] Bateau depasse du plateau.");
            }
            if (ship.orientation == Orientation.NORTH){
                for (int i = y; i > y - ship.taille; i--){
                    if (this.hasShip(x, i)) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.SOUTH){
                for (int i = y; i < y + ship.taille; i++){
                    if (this.hasShip(x, i)) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.EAST){
                for (int j = x; j < x + ship.taille; j++){
                    if (this.hasShip(j, y)) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
            if (ship.orientation == Orientation.WEST){
                for (int j = x; j > x - ship.taille; j--){
                    if (this.hasShip(j, y)) throw new Exception("[Exception] Bateaux se chevauchent.");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        if (ship.orientation == Orientation.NORTH){
            for (int i = y; i > y - ship.taille; i--){
                this.navires[i][x] = new ShipState(ship);
            }
        }
        if (ship.orientation == Orientation.SOUTH){
            for (int i = y; i < y + ship.taille; i++){
                this.navires[i][x] = new ShipState(ship);
            }
        }
        if (ship.orientation == Orientation.EAST){
            for (int j = x; j < x + ship.taille; j++){
                this.navires[y][j] = new ShipState(ship);
            }
        }
        if (ship.orientation == Orientation.WEST){
            for (int j = x; j > x - ship.taille; j--){
                this.navires[y][j] = new ShipState(ship);
            }
        }
    }

    public boolean hasShip(int x, int y){
        if (this.navires[y][x] != null) return true;
        else return false;
    }

    public void setHit(Boolean hit, int x, int y){
        this.frappes[y][x] = hit;
    }

    public Boolean getHit(int x, int y){
        return this.frappes[y][x];
    }

    public Hit sendHit(int x, int y){
        if (this.hasShip(x, y)){
            navires[y][x].addStrike();
            if (navires[y][x].isSunk()){
                return Hit.fromInt(navires[y][x].getShip().taille);
            }
            else{
                return Hit.STRUCK;
            }
        }
        else {
            return Hit.MISS;
        }
    }
}