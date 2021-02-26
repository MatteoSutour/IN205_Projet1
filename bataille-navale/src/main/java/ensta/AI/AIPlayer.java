package ensta;
import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.


    public void putShips() {
        /* La classe AIPlayer redefinit la méthode putShip() de Player en utilisant la méthode putShip() de BattleShipsAI. */
        ai.putShips(ships);
    }

    public Hit sendHit(int[] coords) {
        /* La classe AIPlayer redefinit la méthode sendHit() de Player en utilisant la méthode sendHit() de BattleShipsAI. */
        return ai.sendHit(coords);
    }
}
