package ensta;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState(AbstractShip ship){
        this.ship = ship;
        this.struck = false;
    }

    public void addStrike(){
        if(!struck){
            ship.addStrike();
            struck = true;
        }
    }

    public boolean isStruck(){
        return struck;
    }

    public String toString(){
        if (struck){
            return ColorUtil.colorize(Character.toString(ship.label), ColorUtil.Color.RED);
        }
        else{
            return Character.toString(ship.label);
        }
    }
    
    public boolean isSunk(){
        return ship.isSunk();
    }

    public AbstractShip getShip(){
        return ship;
    }
}