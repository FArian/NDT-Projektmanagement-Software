package actors;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Personal;
import models.RT.Film;
import models.RT.RT_Camera;
import models.dosimeter.*;
import models.material.*;
import models.processing.Developer;
import models.processing.Fixer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by F.Arian on 10.12.17.
 */
public class Lobby extends Observable {

    private List<Personal> personalInLobby;
    private List<Personal> personalToReady;
    private List<TLD> tldsInLobby;
    private List<TLD> tldsToReady;
    private List<FilmBadge> filmBadgesInLobby;
    private List<FilmBadge> filmBadgesToReady;
    private List<PocketDosimeter> dosimetersInLobby;
    private List<PocketDosimeter> dosimetersToReady;
    private List<GeigerAlarm> geigerAlarmsInLobby;
    private List<GeigerAlarm> geigerAlarmsToReady;
    private List<Radiometer> radiometersInLobby;
    private List<Radiometer> radiometersToReady;
    private List<LeadAron> leadAronsInLobby;
    private List<LeadAron> leadAronsToReady;
    private List<RadiationSigns> radiationSignsInLobby;
    private List<RadiationSigns> radiationSignsToReady;
    private List<HandlingTongs> handlingTongsInLobby;
    private List<HandlingTongs> handlingTongsToReady;
    private List<EmergencyStorageContainer> containersInLobby;
    private List<EmergencyStorageContainer> containersToReady;
    private List<RT_Camera> rt_camerasInLobby;
    private List<RT_Camera> rt_camerasToReady;
    private List<Film> filmsInLobby;
    private List<Film> filmsToReady;
    private List<IQI> iqisInLobby;
    private List<IQI> iqisToReady;
    private List<Developer> developersInLobby;
    private List<Developer> developersToReady;
    private List<Fixer> fixersInLobby;
    private List<Fixer> fixersToReady;
    private ObjectMapper objectMapper;


    public Lobby() {
        objectsInitialization();
    }

    private void objectsInitialization() {
        personalInLobby = new ArrayList<>();
        personalToReady = new ArrayList<>();
        tldsInLobby = new ArrayList<>();
        tldsToReady = new ArrayList<>();
        filmBadgesInLobby = new ArrayList<>();
        filmBadgesToReady = new ArrayList<>();
        dosimetersInLobby = new ArrayList<>();
        dosimetersToReady = new ArrayList<>();
        geigerAlarmsInLobby = new ArrayList<>();
        geigerAlarmsToReady = new ArrayList<>();
        radiometersInLobby = new ArrayList<>();
        radiometersToReady = new ArrayList<>();
        leadAronsInLobby = new ArrayList<>();
        leadAronsToReady = new ArrayList<>();
        radiationSignsInLobby = new ArrayList<>();
        radiationSignsToReady = new ArrayList<>();
        handlingTongsInLobby = new ArrayList<>();
        handlingTongsToReady = new ArrayList<>();
        containersInLobby = new ArrayList<>();
        containersToReady = new ArrayList<>();
        rt_camerasInLobby = new ArrayList<>();
        rt_camerasToReady = new ArrayList<>();
        filmsInLobby = new ArrayList<>();
        filmsToReady = new ArrayList<>();
        iqisInLobby = new ArrayList<>();
        iqisToReady = new ArrayList<>();
        developersInLobby = new ArrayList<>();
        developersToReady = new ArrayList<>();
        fixersInLobby = new ArrayList<>();
        fixersToReady = new ArrayList<>();
        objectMapper = new ObjectMapper();

    }

    /**
     * add a personal to the personalInLobby and if is personals status true,then add it in personalToReady too
     *
     * @param personal
     */
    public void addPersonalInLobby(Personal personal) {
        if (personalInLobby.isEmpty()) {
            personalInLobby.add(personal);
            setChanged();
            notifyObservers();
        } else {

            if (!checker(getPersonalInLobby(), personal)) {
                personalInLobby.add(personal);
                setChanged();
                notifyObservers();
            }
        }
    }

    /**
     * @param personals
     * @param personal
     * @return check if is personal in personals list ?
     */
    public boolean checker(List<Personal> personals, Personal personal) {
        int counter = getPersonalInLobby().size();
        while (counter > 0) {
            for (int i = 0; i < getPersonalInLobby().size(); i++) {
                if (personals.get(i).getEmail().equals(personal.getEmail())) return true;
                counter--;
            }
        }
        return false;

    }



    public void addFilmBadgeInLobby(FilmBadge filmBadge) {
        if (filmBadgesInLobby.size() == 0) {
            this.filmBadgesInLobby.add(filmBadge);
            if (filmBadgesInLobby.get(0).isStatus()) {
                addFilmBadgeToReady(filmBadge);
            }
        }
        if (filmBadgesInLobby.size() > 0) {
            for (int i = 0; i < filmBadgesInLobby.size(); i++) {
                if (!filmBadgesInLobby.get(i).getId().equals(filmBadge.getId())) {
                    this.filmBadgesInLobby.add(filmBadge);
                }
                if (filmBadgesInLobby.get(i).isStatus() && !filmBadgesInLobby.get(i).getId().equals(filmBadge.getId())) {
                    addFilmBadgeToReady(filmBadge);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addFilmBadgeToReady(FilmBadge filmBadge) {
        this.filmBadgesToReady.add(filmBadge);
        setChanged();
        notifyObservers();
    }

    public void addTldInLobby(TLD tld) {
        if (tldsInLobby.size() == 0) {
            this.tldsInLobby.add(tld);
            if (tldsInLobby.get(0).isStatus()) {
                addTldToReady(tld);
            }

        }
        if (tldsInLobby.size() > 0) {
            for (int i = 0; i < tldsInLobby.size(); i++) {
                if (!tldsInLobby.get(i).getId().equals(tld.getId())) {
                    this.tldsInLobby.add(tld);

                }
                if (tldsInLobby.get(i).isStatus() && !tldsInLobby.get(i).getId().equals(tld.getId())) {
                    addTldToReady(tld);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addTldToReady(TLD tld) {
        this.tldsToReady.add(tld);
        setChanged();
        notifyObservers();
    }

    public void addDosimeterInLobby(PocketDosimeter dosimeter) {
        if (dosimetersInLobby.size() == 0) {
            this.dosimetersInLobby.add(dosimeter);
            if (dosimetersInLobby.get(0).isStatus()) {
                addDosimeterToReady(dosimeter);
            }
        }
        if (dosimetersInLobby.size() > 0) {
            for (int i = 0; i < dosimetersInLobby.size(); i++) {
                if (!dosimetersInLobby.get(i).getId().equals(dosimeter.getId())) {
                    this.dosimetersInLobby.add(dosimeter);
                }
                if (dosimetersInLobby.get(i).isStatus() && !dosimetersInLobby.get(i).getId().equals(dosimeter.getId())) {
                    addDosimeterToReady(dosimeter);
                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addDosimeterToReady(PocketDosimeter dosimeter) {
        this.dosimetersToReady.add(dosimeter);
        setChanged();
        notifyObservers();
    }

    public void addGeigerInLobby(GeigerAlarm geigerAlarm) {
        if (geigerAlarmsInLobby.size() == 0) {
            this.geigerAlarmsInLobby.add(geigerAlarm);
            if (geigerAlarmsInLobby.get(0).isStatus()) {
                addGeigerAlarmToReady(geigerAlarm);
            }
        }
        if (geigerAlarmsInLobby.size() > 0) {
            for (int i = 0; i < geigerAlarmsInLobby.size(); i++) {
                if (!geigerAlarmsInLobby.get(i).getId().equals(geigerAlarm.getId())) {
                    this.geigerAlarmsInLobby.add(geigerAlarm);

                }
                if (geigerAlarmsInLobby.get(i).isStatus() && !geigerAlarmsInLobby.get(i).getId().equals(geigerAlarm.getId())) {
                    addGeigerAlarmToReady(geigerAlarm);
                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addRadiationSignsInLobby(RadiationSigns radiationSigns) {
        if (radiationSignsInLobby.size() == 0) {
            this.radiationSignsInLobby.add(radiationSigns);
            if (radiometersInLobby.get(0).isStatus()) {
                addRadiationSignsToReady(radiationSigns);
            }
        }
        if (radiometersInLobby.size() > 0) {
            for (int i = 0; i < radiationSignsInLobby.size(); i++) {
                if (!radiationSignsInLobby.get(i).getId().equals(radiationSigns.getId())) {
                    this.radiationSignsInLobby.add(radiationSigns);

                }
                if (radiationSignsInLobby.get(i).isStatus() && !radiationSignsInLobby.get(i).getId().equals(radiationSigns.getId())) {
                    addRadiationSignsToReady(radiationSigns);

                }
            }
        }

        setChanged();
        notifyObservers();

    }

    public void addIqiInLobby(IQI iqi) {
        if (iqisInLobby.size() == 0) {
            this.iqisInLobby.add(iqi);
            if (iqisInLobby.get(0).isStatus()) {
                addIqiToReady(iqi);
            }
        }
        if (iqisInLobby.size() > 0) {
            for (int i = 0; i < iqisInLobby.size(); i++) {
                if (!iqisInLobby.get(i).getId().equals(iqi.getId())) {
                    this.iqisInLobby.add(iqi);

                }
                if (iqisInLobby.get(i).isStatus() && !iqisInLobby.get(i).getId().equals(iqi.getId())) {
                    addIqiToReady(iqi);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addIqiToReady(IQI iqi) {
        this.iqisToReady.add(iqi);
        setChanged();
        notifyObservers();
    }

    public void addRadiationSignsToReady(RadiationSigns radiationSigns) {
        this.radiationSignsToReady.add(radiationSigns);
        setChanged();
        notifyObservers();
    }

    public void addGeigerAlarmToReady(GeigerAlarm geigerAlarm) {
        this.geigerAlarmsToReady.add(geigerAlarm);
        setChanged();
        notifyObservers();
    }

    public void addRadiometerInLobby(Radiometer radiometer) {
        if (radiometersInLobby.size() == 0) {
            this.radiometersInLobby.add(radiometer);
            if (radiometersInLobby.get(0).isStatus()) {
                addRadiometerToReady(radiometer);
            }
        }
        if (radiometersInLobby.size() > 0) {
            for (int i = 0; i < radiometersInLobby.size(); i++) {
                if (!radiometersInLobby.get(i).getId().equals(radiometer.getId())) {
                    this.radiometersInLobby.add(radiometer);

                }
                if (radiometersInLobby.get(i).isStatus() && !radiometersInLobby.get(i).getId().equals(radiometer.getId())) {
                    addRadiometerToReady(radiometer);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addDeveloperInLobby(Developer developer) {
        if (developersInLobby.size() == 0) {
            this.developersInLobby.add(developer);
            if (!developersInLobby.get(0).isExpiredDate()) {
                addDeveloperToReady(developer);
            }
        }
        if (developersInLobby.size() > 0) {
            for (int i = 0; i < developersInLobby.size(); i++) {
                if (!developersInLobby.get(i).getID().equals(developer.getID())) {
                    this.developersInLobby.add(developer);
                }
                if (!developersInLobby.get(i).isExpiredDate() && !developersInLobby.get(i).getID().equals(developer.getID())) {
                    addDeveloperToReady(developer);
                }
            }

        }
        setChanged();
        notifyObservers();
    }

    public void addFixerInLobby(Fixer fixer) {
        if (fixersInLobby.size() == 0) {
            this.fixersInLobby.add(fixer);
            if (!fixersInLobby.get(0).isExpiredDate()) {
                addFixerToReady(fixer);
            }
        }
        if (fixersInLobby.size() > 0) {
            for (int i = 0; i < fixersInLobby.size(); i++) {
                if (!fixersInLobby.get(i).getID().equals(fixer.getID())) {
                    this.fixersInLobby.add(fixer);
                }
                if (!fixersInLobby.get(i).isExpiredDate()) {
                    addFixerToReady(fixer);
                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addFixerToReady(Fixer fixer) {
        this.fixersToReady.add(fixer);
        setChanged();
        notifyObservers();
    }

    public void addDeveloperToReady(Developer developer) {
        this.developersToReady.add(developer);
        setChanged();
        notifyObservers();
    }

    public void addRadiometerToReady(Radiometer radiometer) {
        this.radiometersToReady.add(radiometer);
        setChanged();
        notifyObservers();
    }

    public void addLeadAronInLobby(LeadAron leadAron) {
        if (leadAronsInLobby.size() == 0) {
            this.leadAronsInLobby.add(leadAron);
            if (leadAronsInLobby.get(0).isStatus()) {
                addLeadAronToReady(leadAron);

            }
        }
        if (leadAronsInLobby.size() > 0) {
            for (int i = 0; i < leadAronsInLobby.size(); i++) {
                if (!leadAronsInLobby.get(i).getId().equals(leadAron.getId())) {
                    this.leadAronsInLobby.add(leadAron);

                }
                if (leadAronsInLobby.get(i).isStatus()) {
                    addLeadAronToReady(leadAron);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addHandlingTongsInLobby(HandlingTongs handlingTongs) {
        if (handlingTongsInLobby.size() == 0) {
            this.handlingTongsInLobby.add(handlingTongs);
            if (handlingTongsInLobby.get(0).isStatus()) {
                addHandlingTongsToReady(handlingTongs);
            }
        }
        if (handlingTongsInLobby.size() > 0) {
            for (int i = 0; i < handlingTongsInLobby.size(); i++) {
                if (!handlingTongsInLobby.get(i).getId().equals(handlingTongs.getId())) {
                    this.handlingTongsInLobby.add(handlingTongs);
                }
                if (handlingTongsInLobby.get(i).isStatus()) {
                    addHandlingTongsToReady(handlingTongs);
                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addEmergencyStorageContainerInLobby(EmergencyStorageContainer container) {
        if (containersInLobby.size() == 0) {
            this.containersInLobby.add(container);
            if (containersInLobby.get(0).isStatus()) {
                addEmergencyStorageContainerToReady(container);
            }
        }
        if (containersInLobby.size() > 0) {
            for (int i = 0; i < containersInLobby.size(); i++) {
                if (!containersInLobby.get(i).getId().equals(container.getId())) {
                    this.containersInLobby.add(container);
                }
                if (containersInLobby.get(i).isStatus()) {
                    addEmergencyStorageContainerToReady(container);
                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addRT_CameraInLobby(RT_Camera camera) {
        if (rt_camerasInLobby.size() == 0) {
            this.rt_camerasInLobby.add(camera);
            if (rt_camerasInLobby.get(0).ready_RT_CAMERA()) {
                addRT_CameraToReady(camera);

            }

        }
        if (rt_camerasInLobby.size() > 0) {
            for (int i = 0; i < rt_camerasInLobby.size(); i++) {
                if (!rt_camerasInLobby.get(i).getId().equals(camera.getId())) {
                    this.rt_camerasInLobby.add(camera);

                }
                if (rt_camerasInLobby.get(i).ready_RT_CAMERA()) {
                    addRT_CameraToReady(camera);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addFilmInLobby(Film film) {
        if (filmsInLobby.size() == 0) {
            this.filmsInLobby.add(film);
            if (!filmsInLobby.get(0).isFilmIsexpirt()) {
                addFilmToReady(film);

            }
        }
        if (filmsInLobby.size() > 0) {
            for (int i = 0; i < filmsInLobby.size(); i++) {
                if (film.getId() != null && !filmsInLobby.get(i).getId().equals(film.getId())) {
                    this.filmsInLobby.add(film);

                }
                if (!filmsInLobby.get(i).isFilmIsexpirt()) {
                    addFilmToReady(film);

                }
            }

        }

        setChanged();
        notifyObservers();

    }

    public void addFilmToReady(Film film) {
        this.filmsToReady.add(film);
        setChanged();
        notifyObservers();
    }

    public void addRT_CameraToReady(RT_Camera camera) {
        this.rt_camerasToReady.add(camera);
        setChanged();
        notifyObservers();
    }

    public void addEmergencyStorageContainerToReady(EmergencyStorageContainer container) {
        this.containersToReady.add(container);
        setChanged();
        notifyObservers();

    }

    public void addHandlingTongsToReady(HandlingTongs handlingTongs) {
        this.handlingTongsToReady.add(handlingTongs);
        setChanged();
        notifyObservers();
    }

    public void addLeadAronToReady(LeadAron leadAron) {
        this.leadAronsToReady.add(leadAron);
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


    /**
     * Removes a personal that has move from Company
     *
     * @param email
     */
    public void removeFromPersonalList(String email) {

        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getEmail().equals(email)) {
                personalInLobby.remove(i);
                setChanged();
                notifyObservers();
                return;
            }
        }

        for (int i = 0; i < personalToReady.size(); i++) {
            if (personalToReady.get(i).getEmail().equals(email)) {
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
     * @param email
     * @return personal
     */
    public Personal getPersonalInLobby(String email) {

        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getEmail().equals(email)) {
                return personalInLobby.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the personal with that id from the list personalToReady
     *
     * @param email
     * @return personal
     */
    public Personal getPersonalFromToReady(String email) {

        for (int i = 0; i < personalToReady.size(); i++) {
            if (personalToReady.get(i).getId().equals(email)) {
                return personalToReady.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the personal with that id from the list personalInLobby
     *
     * @param email
     * @return personal
     */
    public Personal getPersonalFromInLobby(String email) {
        for (int i = 0; i < personalInLobby.size(); i++) {
            if (personalInLobby.get(i).getEmail().equals(email)) {
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


    public List<Personal> getPersonalInLobby() {
        return personalInLobby;
    }

    public void setPersonalInLobby(ArrayList<Personal> personalInLobby) {
        this.personalInLobby = personalInLobby;
    }

    public List<Personal> getPersonalToReady() {
        return personalToReady;
    }

    public void setPersonalToReady(ArrayList<Personal> personalToReady) {
        this.personalToReady = personalToReady;
    }

    public List<TLD> getTldsInLobby() {
        return tldsInLobby;
    }

    public void setTldsInLobby(ArrayList<TLD> tldsInLobby) {
        this.tldsInLobby = tldsInLobby;
    }

    public List<TLD> getTldsToReady() {
        return tldsToReady;
    }

    public void setTldsToReady(ArrayList<TLD> tldsToReady) {
        this.tldsToReady = tldsToReady;
    }

    public List<FilmBadge> getFilmBadgesInLobby() {
        return filmBadgesInLobby;
    }

    public void setFilmBadgesInLobby(ArrayList<FilmBadge> filmBadgesInLobby) {
        this.filmBadgesInLobby = filmBadgesInLobby;
    }

    public List<FilmBadge> getFilmBadgesToReady() {
        return filmBadgesToReady;
    }

    public void setFilmBadgesToReady(ArrayList<FilmBadge> filmBadgesToReady) {
        this.filmBadgesToReady = filmBadgesToReady;
    }

    public List<PocketDosimeter> getDosimetersInLobby() {
        return dosimetersInLobby;
    }

    public void setDosimetersInLobby(ArrayList<PocketDosimeter> dosimetersInLobby) {
        this.dosimetersInLobby = dosimetersInLobby;
    }

    public List<PocketDosimeter> getDosimetersToReady() {
        return dosimetersToReady;
    }

    public void setDosimetersToReady(ArrayList<PocketDosimeter> dosimetersToReady) {
        this.dosimetersToReady = dosimetersToReady;
    }

    public List<Radiometer> getRadiometersInLobby() {
        return radiometersInLobby;
    }

    public void setRadiometersInLobby(ArrayList<Radiometer> radiometersInLobby) {
        this.radiometersInLobby = radiometersInLobby;
    }

    public List<Radiometer> getRadiometersToReady() {
        return radiometersToReady;
    }

    public void setRadiometersToReady(ArrayList<Radiometer> radiometersToReady) {
        this.radiometersToReady = radiometersToReady;
    }

    public List<LeadAron> getLeadAronsInLobby() {
        return leadAronsInLobby;
    }

    public void setLeadAronsInLobby(ArrayList<LeadAron> leadAronsInLobby) {
        this.leadAronsInLobby = leadAronsInLobby;
    }

    public List<LeadAron> getLeadAronsToReady() {
        return leadAronsToReady;
    }

    public void setLeadAronsToReady(ArrayList<LeadAron> leadAronsToReady) {
        this.leadAronsToReady = leadAronsToReady;
    }

    public List<GeigerAlarm> getGeigerAlarmsInLobby() {
        return geigerAlarmsInLobby;
    }

    public void setGeigerAlarmsInLobby(ArrayList<GeigerAlarm> geigerAlarmsInLobby) {
        this.geigerAlarmsInLobby = geigerAlarmsInLobby;
    }

    public List<GeigerAlarm> getGeigerAlarmsToReady() {
        return geigerAlarmsToReady;
    }

    public void setGeigerAlarmsToReady(ArrayList<GeigerAlarm> geigerAlarmsToReady) {
        this.geigerAlarmsToReady = geigerAlarmsToReady;
    }

    public List<RadiationSigns> getRadiationSignsInLobby() {
        return radiationSignsInLobby;
    }

    public void setRadiationSignsInLobby(ArrayList<RadiationSigns> radiationSignsInLobby) {
        this.radiationSignsInLobby = radiationSignsInLobby;
    }

    public List<RadiationSigns> getRadiationSignsToReady() {
        return radiationSignsToReady;
    }

    public void setRadiationSignsToReady(ArrayList<RadiationSigns> radiationSignsToReady) {
        this.radiationSignsToReady = radiationSignsToReady;
    }

    public List<HandlingTongs> getHandlingTongsInLobby() {
        return handlingTongsInLobby;
    }

    public void setHandlingTongsInLobby(ArrayList<HandlingTongs> handlingTongsInLobby) {
        this.handlingTongsInLobby = handlingTongsInLobby;
    }

    public List<HandlingTongs> getHandlingTongsToReady() {
        return handlingTongsToReady;
    }

    public void setHandlingTongsToReady(ArrayList<HandlingTongs> handlingTongsToReady) {
        this.handlingTongsToReady = handlingTongsToReady;
    }

    public List<EmergencyStorageContainer> getContainersInLobby() {
        return containersInLobby;
    }

    public void setContainersInLobby(ArrayList<EmergencyStorageContainer> containersInLobby) {
        this.containersInLobby = containersInLobby;
    }

    public List<EmergencyStorageContainer> getContainersToReady() {
        return containersToReady;
    }

    public void setContainersToReady(ArrayList<EmergencyStorageContainer> containersToReady) {
        this.containersToReady = containersToReady;
    }

    public List<RT_Camera> getRt_camerasInLobby() {
        return rt_camerasInLobby;
    }

    public void setRt_camerasInLobby(ArrayList<RT_Camera> rt_camerasInLobby) {
        this.rt_camerasInLobby = rt_camerasInLobby;
    }

    public List<RT_Camera> getRt_camerasToReady() {
        return rt_camerasToReady;
    }

    public void setRt_camerasToReady(ArrayList<RT_Camera> rt_camerasToReady) {
        this.rt_camerasToReady = rt_camerasToReady;
    }

    public List<Film> getFilmsInLobby() {
        return filmsInLobby;
    }

    public void setFilmsInLobby(ArrayList<Film> filmsInLobby) {
        this.filmsInLobby = filmsInLobby;
    }

    public List<Film> getFilmsToReady() {
        return filmsToReady;
    }

    public void setFilmsToReady(ArrayList<Film> filmsToReady) {
        this.filmsToReady = filmsToReady;
    }

    public List<IQI> getIqisInLobby() {
        return iqisInLobby;
    }

    public void setIqisInLobby(ArrayList<IQI> iqisInLobby) {
        this.iqisInLobby = iqisInLobby;
    }

    public List<IQI> getIqisToReady() {
        return iqisToReady;
    }

    public void setIqisToReady(ArrayList<IQI> iqisToReady) {
        this.iqisToReady = iqisToReady;
    }

    public List<Developer> getDevelopersInLobby() {
        return developersInLobby;
    }

    public void setDevelopersInLobby(ArrayList<Developer> developersInLobby) {
        this.developersInLobby = developersInLobby;
    }

    public List<Developer> getDevelopersToReady() {
        return developersToReady;
    }

    public void setDevelopersToReady(ArrayList<Developer> developersToReady) {
        this.developersToReady = developersToReady;
    }

    public List<Fixer> getFixersInLobby() {
        return fixersInLobby;
    }

    public void setFixersInLobby(ArrayList<Fixer> fixersInLobby) {
        this.fixersInLobby = fixersInLobby;
    }

    public List<Fixer> getFixersToReady() {
        return fixersToReady;
    }

    public void setFixersToReady(ArrayList<Fixer> fixersToReady) {
        this.fixersToReady = fixersToReady;
    }

    public void save(Object object) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(String.valueOf(object)));

    }


}
