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
        Hit hit;
        hit = myBoard.sendHit(0, 1);
        if((hit != Hit.MISS) && (hit != Hit.STRUCK)) System.out.println(hit.toString() + " coulé.");
        else System.out.println(hit.toString());
        hit = myBoard.sendHit(8, 5);
        if((hit != Hit.MISS) && (hit != Hit.STRUCK)) System.out.println(hit.toString() + " coulé.");
        else System.out.println(hit.toString());
        hit = myBoard.sendHit(2, 1);
        if((hit != Hit.MISS) && (hit != Hit.STRUCK)) System.out.println(hit.toString() + " coulé.");
        else System.out.println(hit.toString());
        hit = myBoard.sendHit(6, 4);
        if((hit != Hit.MISS) && (hit != Hit.STRUCK)) System.out.println(hit.toString() + " coulé.");
        else System.out.println(hit.toString());
        hit = myBoard.sendHit(1, 1);
        if((hit != Hit.MISS) && (hit != Hit.STRUCK)) System.out.println(hit.toString() + " coulé.");
        else System.out.println(hit.toString());
        myBoard.print();
    }
}