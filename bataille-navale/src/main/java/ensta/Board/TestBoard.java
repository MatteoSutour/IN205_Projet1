package ensta;

class TestBoard{
    
    public static void main( String[] args )
    {
        Board myBoard = new Board("myBoard", 10);
        Destroyer myDestroyer = new Destroyer(Orientation.EAST);
        myBoard.putShip(myDestroyer, 1, 1);
        Carrier myCarrier = new Carrier(Orientation.NORTH);
        myBoard.putShip(myCarrier, 6, 7);
        Submarine mySubmarine = new Submarine(Orientation.SOUTH);
        myBoard.putShip(mySubmarine, 9, 2); // Provoque l'exception "Bateau depasse du plateau".
        Submarine mySubmarine2 = new Submarine(Orientation.WEST);
        myBoard.putShip(mySubmarine2, 4, 8); // Provoque l'exception "Bateaux se chevauchent".
        myBoard.sendHit(0, 1);
        myBoard.sendHit(8, 5);
        myBoard.sendHit(2, 1);
        myBoard.sendHit(6, 4);
        myBoard.sendHit(1, 1);
        myBoard.print();
    }
}