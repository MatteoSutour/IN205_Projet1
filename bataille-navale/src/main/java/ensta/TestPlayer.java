package ensta;

import java.util.*;

class TestPlayer{
    
    public static void main( String[] args )
    {
        Board myBoard = new Board("myBoard", 10);
        Board myOpponentBoard = new Board("myOpponentBoard", 10);
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new Battleship());
        ships.add(new Carrier());
        Player myPlayer = new Player(myBoard, myOpponentBoard, ships);
        myPlayer.putShips();
    }
}