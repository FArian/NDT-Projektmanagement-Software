package rzeznika.ndtClient.controller;

import java.util.ArrayList;
import java.util.Observable;

import rzeznika.ndtClient.model.Player;

/**
 * Created by F.Arian on 30.06.17.
 *
 * Class that represents the PlayerObserver in multiplayer mode
 */

public class PlayerObserver extends Observable {
    /**
     *List of Player,in Joining list
     */
    private ArrayList<Player> playersJoining;
    /**
     * List of Player ,They are Ready for Game
     */
    private ArrayList<Player> playersReady;


    public PlayerObserver() {
        this.playersJoining = new ArrayList<Player>();
        this.playersReady = new ArrayList<Player>();
    }


    /**
     * Updates the players in the list playeersJoining, when a new player connects
     * to the game or a player in the game changes color or name
     *
     * @param id
     * @param color
     * @param name
     */
    public void updatePlayer(int id, String color, String name) {
        boolean idChecker = false;
        for (int i = 0; i < playersJoining.size(); i++) {
            if (playersJoining.get(i).getId() == id) {
                idChecker = true;
            }
        }
        if (!idChecker) {
            Player player = new Player();
            player.setId(id);
            if (color != null && name != null) {
                player.setColor(color);
                player.setName(name);
            }
            playersJoining.add(player);
            setChanged();
            notifyObservers(new Information("new Player"));
        } else if (color != null) {
            Player player = getPlayersJoining(id);
            player.setColor(color);
            player.setName(name);
            setChanged();
            notifyObservers(new Information("Color changed"));

        }

    }

    /**
     * Updates the players in the list playersReady. Moves a player that is
     * ready from the list playersJoining to the list playersReady
     *
     * @param id
     * @param color
     * @param name
     */
    public void updatePlayerWaiting(int id, String color, String name) {
        Player player = getPlayersJoining(id);
        if (player == null) {
            player = new Player();
            player.setId(id);
            player.setColor(color);
            player.setName(name);
        }
        removeFromJoining(id);
        playersReady.add(player);
        setChanged();
        notifyObservers(new Information("NewPlayerWaiting"));

    }

    /**
     * removes the player with that id from the list playersJoining
     *
     * @param id
     */

    private void removeFromJoining(int id) {
        for (int i = 0; i < playersJoining.size(); i++) {
            if (playersJoining.get(i).getId() == id) {
                playersJoining.remove(i);
            }
        }
    }

    public Player getPlayersJoining(int id) {
        Player result = null;
        for (int i = 0; i < playersJoining.size(); i++) {
            if (playersJoining.get(i).getId() == id) {
                result = playersJoining.get(i);
            }
        }
        return result;
    }

    /**
     * Getter for the player who are waiting for the start of the game
     *
     * @return ArrayList Player  PlayersReady
     */

    public ArrayList<Player> getPlayersReady() {
        return playersReady;
    }

    /**
     * Getter for the list PlayersJoining
     *
     * @return
     */
    public ArrayList<Player> getPlayersJoining() {
        return playersJoining;
    }

    public void removePlayerWithConnectionLost(int id) {
        for (int i = 0; i < playersJoining.size(); i++) {
            if (playersJoining.get(i).getId() == id) {
                playersJoining.remove(i);
                setChanged();
                notifyObservers(new Information("ConnectionLost_Joining-Player"));
                return;
            }
        }
        for (int i = 0; i < playersReady.size(); i++) {
            if (playersReady.get(i).getId() == id) {
                playersReady.remove(i);
                setChanged();
                notifyObservers(new Information("ConnectionLost_Ready-Player"));
            }
        }
    }

    /**
     * Getter for the player with that id. Searches all players in the joining.
     *
     * @param id
     * @return Player
     */
    public Player getPlayer(int id) {
        Player player = null;
        for (Player player1 : playersJoining) {
            if (player1.getId() == id) {
                player = player1;
            }
        }
        for (Player player1 : playersReady) {
            if (player1.getId() == id) {
                player = player1;
            }
        }
        return player;
    }

}
