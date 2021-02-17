package ensta;

public class Battleship extends AbstractShip {

    public Battleship(Orientation orientation){
        super("BattleShip", 'B', 4, orientation);
    }

    public Battleship(){
        this(Orientation.EAST);
    }
}