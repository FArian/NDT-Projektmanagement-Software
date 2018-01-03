package actors;

import models.Personal;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by F.Arian on 10.12.17.
 */
public class Lobby extends Observable {

    private ArrayList<Personal> personalInLobby;
    private ArrayList<Personal> personalToReady;

    public Lobby() {
        personalInLobby = new ArrayList<>();
        personalToReady = new ArrayList<>();

    }

    /**
     * add a personal to the personalInLobby and if is personals status true,then add it in personalToReady too
     *
     * @param personal
     */
    public void addPersonalInLobby(Personal personal) {
        if (personalInLobby.size() == 0) {
            this.personalInLobby.add(personal);
        }
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (!personalInLobby.get(i).getId().equals(personal.getId())) {
                this.personalInLobby.add(personal);
                return;
            }
            if (personalInLobby.get(i).isStatus() && !(personalInLobby.get(i).getIsInRest())) {
                addPersonalToReady(personal);
                return;
            }
        }

    }

    /**
     * add a personal to the personalToReady
     *
     * @param personal
     */
    public void addPersonalToReady(Personal personal) {
        this.personalToReady.add(personal);
    }

    /**
     * Removes a personal that has move from Company
     *
     * @param id
     */
    public void removeFromPersonalList(String id) {
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getId().equals(id)) {
                personalInLobby.remove(i);
                setChanged();
                notifyObservers();
                return;
            }
        }
        for (int i = 0; i < personalToReady.size(); i++) {
            if (personalToReady.get(i).getId().equals(id)) {
                personalToReady.remove(i);
                setChanged();
                notifyObservers();
                return;
            }
        }

    }

    /**
     * Getter for the personal with that id from the list getLobbyPlayer
     *
     * @param id
     * @return personal
     */
    private Personal getPersonalInLobby(String id) {
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getId().equals(id)) {
                return personalInLobby.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the personal with that id from the list personalToReady
     *
     * @param id
     * @return personal
     */
    public Personal getPersonalFromToReady(String id) {
        for (int i = 0; i < personalToReady.size(); i++) {
            if (personalToReady.get(i).getId().equals(id)) {
                return personalToReady.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the personal with that id from the list personalInLobby
     *
     * @param id
     * @return personal
     */
    public Personal getPersonalFromInLobby(String id) {
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getId().equals(id)) {
                return personalInLobby.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param email
     * @return if new Personal , then true
     */
    public boolean isNewPersonal(String email) {
        boolean result = false;
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getEmail().equals(email)) {
                result = true;
            }

        }
        return result;
    }


    public ArrayList<Personal> getPersonalInLobby() {
        return personalInLobby;
    }

    public ArrayList<Personal> getPersonalToReady() {
        return personalToReady;
    }

    @Override
    public String toString() {
        return "\n" + "Lobby{" +
                "  personalInLobby=" + personalInLobby +
                ",  personalInLobbySize=" + personalInLobby.size() +
                ", personalToReady=" + personalToReady +
                ", personalToReady=" + personalToReady.size() +
                "}" + "\n";
    }
}
