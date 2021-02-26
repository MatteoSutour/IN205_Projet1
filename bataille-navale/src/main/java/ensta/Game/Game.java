package ensta;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            // TODO use a scanner to read player name
            sin = new Scanner(System.in);
            String name = sin.nextLine();

            // TODO init boards
            Board b1, b2;
            b1 = new Board(name);
            b2 = new Board("AI");

            // TODO init this.player1 & this.player2
            List<AbstractShip> ships1 = new ArrayList<AbstractShip>();
            ships1.add(new Destroyer());
            ships1.add(new Submarine());
            ships1.add(new Submarine());
            ships1.add(new Battleship());
            ships1.add(new Carrier());
            this.player1 = new Player(b1, b2, ships1);
            List<AbstractShip> ships2 = new ArrayList<AbstractShip>();
            ships2.add(new Destroyer());
            ships2.add(new Submarine());
            ships2.add(new Submarine());
            ships2.add(new Battleship());
            ships2.add(new Carrier());
            this.player2 = new AIPlayer(b2, b1, ships2);

            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done = false;
        boolean strike = false;
        do {
            if (!done && !strike) {
                do {
                    // TODO player1 send a hit
                    hit = player1.sendHit(coords);

                    strike = hit != Hit.MISS; // TODO set this hit on his board (b1)
                    b1.setHit(strike, coords[0], coords[1]);
                    b1.print();
                    System.out.println(makeHitMessage(false /* incoming hit */, coords, hit));
                    done = updateScore();
                } while(strike && !done);
            }

            if (!done && !strike) {
                do {
                    // TODO player2 send a hit.
                    hit = player2.sendHit(coords);

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
        /*try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private boolean loadSave() {
        /*if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRUCK:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
