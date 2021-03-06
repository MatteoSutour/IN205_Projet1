package ensta;
import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getNom(), s.getTaille());

            boolean exception;
            InputHelper.ShipInput res;
            do{
                exception = false;
                System.out.println(msg);
                res = InputHelper.readShipInput();
            
                // TODO set ship orientation
                // TODO put ship at given position

                try{
                    if((res.x < 0 || res.x >= this.board.getSize()) || (res.y < 0 || res.y >= this.board.getSize())){
                        exception = true;
                        throw new Exception("[Exception] Bateau en dehors du plateau.");
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            } while(exception == true);

            if(res.orientation.equals("n")){
                s.orientation = Orientation.NORTH;
            }
            if(res.orientation.equals("s")){
                s.orientation = Orientation.SOUTH;
            }
            if(res.orientation.equals("e")){
                s.orientation = Orientation.EAST;
            }
            if(res.orientation.equals("w")){
                s.orientation = Orientation.WEST;
            };
            board.putShip(s, res.x, res.y);

            // TODO when ship placement successful
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;
        int i = 0;

        do {
            InputHelper.CoordInput hitInput;
            // TODO call sendHit on this.opponentBoard

            boolean exception;
            do{
                exception = false;
                System.out.println("où frapper?");
                hitInput = InputHelper.readCoordInput();
                try{
                    if((hitInput.x < 0 || hitInput.x >= opponentBoard.getSize()) || (hitInput.y < 0 || hitInput.y >= opponentBoard.getSize())){
                        exception = true;
                        throw new Exception("[Exception] Bateau en dehors du plateau.");
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            } while(exception == true);

            hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;
            board.setHit(hit != Hit.MISS, coords[0], coords[1]);

            ++i;
            done = i == 1;

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
