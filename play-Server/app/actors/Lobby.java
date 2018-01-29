package actors;

import models.Personal;
import models.dosimeter.FilmBadge;
import models.dosimeter.TLD;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by F.Arian on 10.12.17.
 */
public class Lobby extends Observable {

    private ArrayList<Personal> personalInLobby;
    private ArrayList<Personal> personalToReady;
    private ArrayList<TLD> tldsInLobby;
    private ArrayList<TLD> tldsToReady;
    private ArrayList<FilmBadge> filmBadgesInLobby;
    private ArrayList<FilmBadge> filmBadgesToReady;

    public Lobby() {
        personalInLobby = new ArrayList<>();
        personalToReady = new ArrayList<>();
        tldsInLobby = new ArrayList<>();
        tldsToReady = new ArrayList<>();
        filmBadgesInLobby = new ArrayList<>();
        filmBadgesToReady = new ArrayList<>();

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
        setChanged();
        notifyObservers();

    }

    public void addFilmBadgeInLobby(FilmBadge filmBadge) {
        if (filmBadgesInLobby.size() == 0) {
            this.filmBadgesInLobby.add(filmBadge);
        }
        for (int i = 0; i < filmBadgesInLobby.size(); i++) {
            if (!filmBadgesInLobby.get(i).getId().equals(filmBadge.getId())) {
                this.filmBadgesInLobby.add(filmBadge);
                return;
            }
            if (filmBadgesInLobby.get(i).isStatus()) {
                addFilmBadgeToReady(filmBadge);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }

    public void addTldInLobby(TLD tld) {
        if (tldsInLobby.size() == 0) {
            this.tldsInLobby.add(tld);
        }
        for (int i = 0; i < tldsInLobby.size(); i++) {
            if (!tldsInLobby.get(i).getId().equals(tld.getId())) {
                this.tldsInLobby.add(tld);
                return;
            }
            if (tldsInLobby.get(i).isStatus()) {
                addTldToReady(tld);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }

    private void addTldToReady(TLD tld) {
        this.tldsToReady.add(tld);
        setChanged();
        notifyObservers();
    }

    /**
     * add a personal to the personalToReady
     *
     * @param personal
     */
    public void addPersonalToReady(Personal personal) {
        this.personalToReady.add(personal);
        setChanged();
        notifyObservers();
    }

    public void addFilmBadgeToReady(FilmBadge filmBadge) {
        this.filmBadgesToReady.add(filmBadge);
        setChanged();
        notifyObservers();
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

    public ArrayList<TLD> getTldsInLobby() {
        return tldsInLobby;
    }

    public ArrayList<TLD> getTldsToReady() {
        return tldsToReady;
    }

    public ArrayList<FilmBadge> getFilmBadgesInLobby() {
        return filmBadgesInLobby;
    }

    public ArrayList<FilmBadge> getFilmBadgesToReady() {
        return filmBadgesToReady;
    }



}
