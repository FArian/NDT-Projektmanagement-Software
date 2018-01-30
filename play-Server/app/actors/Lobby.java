package actors;

import models.Personal;
import models.RT.Film;
import models.RT.RT_Camera;
import models.dosimeter.*;
import models.material.*;
import models.processing.Developer;
import models.processing.Fixer;

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
    private ArrayList<PocketDosimeter> dosimetersInLobby;
    private ArrayList<PocketDosimeter> dosimetersToReady;
    private ArrayList<GeigerAlarm> geigerAlarmsInLobby;
    private ArrayList<GeigerAlarm> geigerAlarmsToReady;
    private ArrayList<Radiometer> radiometersInLobby;
    private ArrayList<Radiometer> radiometersToReady;
    private ArrayList<LeadAron> leadAronsInLobby;
    private ArrayList<LeadAron> leadAronsToReady;
    private ArrayList<RadiationSigns> radiationSignsInLobby;
    private ArrayList<RadiationSigns> radiationSignsToReady;
    private ArrayList<HandlingTongs> handlingTongsInLobby;
    private ArrayList<HandlingTongs> handlingTongsToReady;
    private ArrayList<EmergencyStorageContainer> containersInLobby;
    private ArrayList<EmergencyStorageContainer> containersToReady;
    private ArrayList<RT_Camera> rt_camerasInLobby;
    private ArrayList<RT_Camera> rt_camerasToReady;
    private ArrayList<Film> filmsInLobby;
    private ArrayList<Film> filmsToReady;
    private ArrayList<IQI> iqisInLobby;
    private ArrayList<IQI> iqisToReady;
    private ArrayList<Developer> developersInLobby;
    private ArrayList<Developer> developersToReady;
    private ArrayList<Fixer> fixersInLobby;
    private ArrayList<Fixer> fixersToReady;


    public Lobby() {
        objectsInitialization();
    }
    public void objectsInitialization(){
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
        containersInLobby=new ArrayList<>();
        containersToReady=new ArrayList<>();
        rt_camerasInLobby=new ArrayList<>();
        rt_camerasToReady=new ArrayList<>();
        filmsInLobby=new ArrayList<>();
        filmsToReady=new ArrayList<>();
        iqisInLobby=new ArrayList<>();
        iqisToReady=new ArrayList<>();
        developersInLobby=new ArrayList<>();
        developersToReady=new ArrayList<>();
        fixersInLobby=new ArrayList<>();
        fixersToReady=new ArrayList<>();

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

    public void addFilmBadgeToReady(FilmBadge filmBadge) {
        this.filmBadgesToReady.add(filmBadge);
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

    public void addTldToReady(TLD tld) {
        this.tldsToReady.add(tld);
        setChanged();
        notifyObservers();
    }

    public void addDosimeterInLobby(PocketDosimeter dosimeter) {
        if (dosimetersInLobby.size() == 0) {
            this.dosimetersInLobby.add(dosimeter);
        }
        for (int i = 0; i < dosimetersInLobby.size(); i++) {
            if (!dosimetersInLobby.get(i).getId().equals(dosimeter.getId())) {
                this.dosimetersInLobby.add(dosimeter);
                return;
            }
            if (dosimetersInLobby.get(i).isStatus()) {
                addDosimeterToReady(dosimeter);
                return;
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
        }
        for (int i = 0; i < geigerAlarmsInLobby.size(); i++) {
            if (!geigerAlarmsInLobby.get(i).getId().equals(geigerAlarm.getId())) {
                this.geigerAlarmsInLobby.add(geigerAlarm);
                return;
            }
            if (geigerAlarmsInLobby.get(i).isStatus()) {
                addGeigerAlarmToReady(geigerAlarm);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }

    public void addRadiationSignsInLobby(RadiationSigns radiationSigns) {
        if (radiationSignsInLobby.size() == 0) {
            this.radiationSignsInLobby.add(radiationSigns);
        }
        for (int i = 0; i < radiationSignsInLobby.size(); i++) {
            if (!radiationSignsInLobby.get(i).getId().equals(radiationSigns.getId())) {
                this.radiationSignsInLobby.add(radiationSigns);
                return;
            }
            if (radiationSignsInLobby.get(i).isStatus()) {
                addRadiationSignsToReady(radiationSigns);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addIqiInLobby(IQI iqi) {
        if (iqisInLobby.size() == 0) {
            this.iqisInLobby.add(iqi);
        }
        for (int i = 0; i < iqisInLobby.size(); i++) {
            if (!iqisInLobby.get(i).getId().equals(iqi.getId())) {
                this.iqisInLobby.add(iqi);
                return;
            }
            if (iqisInLobby.get(i).isStatus()) {
                addIqiToReady(iqi);
                return;
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
        }
        for (int i = 0; i < radiometersInLobby.size(); i++) {
            if (!radiometersInLobby.get(i).getId().equals(radiometer.getId())) {
                this.radiometersInLobby.add(radiometer);
                return;
            }
            if (radiometersInLobby.get(i).isStatus()) {
                addRadiometerToReady(radiometer);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addDeveloperInLobby(Developer developer) {
        if (developersInLobby.size() == 0) {
            this.developersInLobby.add(developer);
        }
        for (int i = 0; i < developersInLobby.size(); i++) {
            if (!developersInLobby.get(i).getID().equals(developer.getID())) {
                this.developersInLobby.add(developer);
                return;
            }
            if (!developersInLobby.get(i).isExpiredDate()) {
                addDeveloperToReady(developer);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }

    public void addFixerInLobby(Fixer fixer) {
        if (fixersInLobby.size() == 0) {
            this.fixersInLobby.add(fixer);
        }
        for (int i = 0; i < fixersInLobby.size(); i++) {
            if (!fixersInLobby.get(i).getID().equals(fixer.getID())) {
                this.fixersInLobby.add(fixer);
                return;
            }
            if (!fixersInLobby.get(i).isExpiredDate()) {
                addFixerToReady(fixer);
                return;
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
        }
        for (int i = 0; i < leadAronsInLobby.size(); i++) {
            if (!leadAronsInLobby.get(i).getId().equals(leadAron.getId())) {
                this.leadAronsInLobby.add(leadAron);
                return;
            }
            if (leadAronsInLobby.get(i).isStatus()) {
                addLeadAronToReady(leadAron);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addHandlingTongsInLobby(HandlingTongs handlingTongs) {
        if (handlingTongsInLobby.size() == 0) {
            this.handlingTongsInLobby.add(handlingTongs);
        }
        for (int i = 0; i < handlingTongsInLobby.size(); i++) {
            if (!handlingTongsInLobby.get(i).getId().equals(handlingTongs.getId())) {
                this.handlingTongsInLobby.add(handlingTongs);
                return;
            }
            if (handlingTongsInLobby.get(i).isStatus()) {
                addHandlingTongsToReady(handlingTongs);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addEmergencyStorageContainerInLobby(EmergencyStorageContainer container) {
        if (containersInLobby.size() == 0) {
            this.containersInLobby.add(container);
        }
        for (int i = 0; i < containersInLobby.size(); i++) {
            if (!containersInLobby.get(i).getId().equals(container.getId())) {
                this.containersInLobby.add(container);
                return;
            }
            if (containersInLobby.get(i).isStatus()) {
                addEmergencyStorageContainerToReady(container);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addRT_CameraInLobby(RT_Camera camera) {
        if (rt_camerasInLobby.size() == 0) {
            this.rt_camerasInLobby.add(camera);
        }
        for (int i = 0; i < rt_camerasInLobby.size(); i++) {
            if (!rt_camerasInLobby.get(i).getId().equals(camera.getId())) {
                this.rt_camerasInLobby.add(camera);
                return;
            }
            if (rt_camerasInLobby.get(i).ready_RT_CAMERA()) {
                addRT_CameraToReady(camera);
                return;
            }
        }
        setChanged();
        notifyObservers();

    }
    public void addFilmInLobby(Film film) {
        if (filmsInLobby.size() == 0) {
            this.filmsInLobby.add(film);
        }
        for (int i = 0; i < filmsInLobby.size(); i++) {
            if (!filmsInLobby.get(i).getId().equals(film.getId())) {
                this.filmsInLobby.add(film);
                return;
            }
            if (!filmsInLobby.get(i).isFilmIsexpirt()) {
                addFilmToReady(film);
                return;
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
    public Personal getPersonalInLobby(String id) {

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

    public void setPersonalInLobby(ArrayList<Personal> personalInLobby) {
        this.personalInLobby = personalInLobby;
    }

    public void setPersonalToReady(ArrayList<Personal> personalToReady) {
        this.personalToReady = personalToReady;
    }

    public void setTldsInLobby(ArrayList<TLD> tldsInLobby) {
        this.tldsInLobby = tldsInLobby;
    }

    public void setTldsToReady(ArrayList<TLD> tldsToReady) {
        this.tldsToReady = tldsToReady;
    }

    public void setFilmBadgesInLobby(ArrayList<FilmBadge> filmBadgesInLobby) {
        this.filmBadgesInLobby = filmBadgesInLobby;
    }

    public void setFilmBadgesToReady(ArrayList<FilmBadge> filmBadgesToReady) {
        this.filmBadgesToReady = filmBadgesToReady;
    }

    public ArrayList<PocketDosimeter> getDosimetersInLobby() {
        return dosimetersInLobby;
    }

    public void setDosimetersInLobby(ArrayList<PocketDosimeter> dosimetersInLobby) {
        this.dosimetersInLobby = dosimetersInLobby;
    }

    public ArrayList<PocketDosimeter> getDosimetersToReady() {
        return dosimetersToReady;
    }

    public void setDosimetersToReady(ArrayList<PocketDosimeter> dosimetersToReady) {
        this.dosimetersToReady = dosimetersToReady;
    }

    public void setGeigerAlarmsToReady(ArrayList<GeigerAlarm> geigerAlarmsToReady) {
        this.geigerAlarmsToReady = geigerAlarmsToReady;
    }

    public ArrayList<Radiometer> getRadiometersInLobby() {
        return radiometersInLobby;
    }

    public void setRadiometersInLobby(ArrayList<Radiometer> radiometersInLobby) {
        this.radiometersInLobby = radiometersInLobby;
    }

    public ArrayList<Radiometer> getRadiometersToReady() {
        return radiometersToReady;
    }

    public void setRadiometersToReady(ArrayList<Radiometer> radiometersToReady) {
        this.radiometersToReady = radiometersToReady;
    }

    public ArrayList<LeadAron> getLeadAronsInLobby() {
        return leadAronsInLobby;
    }

    public void setLeadAronsInLobby(ArrayList<LeadAron> leadAronsInLobby) {
        this.leadAronsInLobby = leadAronsInLobby;
    }

    public ArrayList<LeadAron> getLeadAronsToReady() {
        return leadAronsToReady;
    }

    public void setLeadAronsToReady(ArrayList<LeadAron> leadAronsToReady) {
        this.leadAronsToReady = leadAronsToReady;
    }

    public ArrayList<GeigerAlarm> getGeigerAlarmsInLobby() {
        return geigerAlarmsInLobby;
    }

    public void setGeigerAlarmsInLobby(ArrayList<GeigerAlarm> geigerAlarmsInLobby) {
        this.geigerAlarmsInLobby = geigerAlarmsInLobby;
    }

    public ArrayList<GeigerAlarm> getGeigerAlarmsToReady() {
        return geigerAlarmsToReady;
    }

    public ArrayList<RadiationSigns> getRadiationSignsInLobby() {
        return radiationSignsInLobby;
    }

    public void setRadiationSignsInLobby(ArrayList<RadiationSigns> radiationSignsInLobby) {
        this.radiationSignsInLobby = radiationSignsInLobby;
    }

    public ArrayList<RadiationSigns> getRadiationSignsToReady() {
        return radiationSignsToReady;
    }

    public void setRadiationSignsToReady(ArrayList<RadiationSigns> radiationSignsToReady) {
        this.radiationSignsToReady = radiationSignsToReady;
    }

    public ArrayList<HandlingTongs> getHandlingTongsInLobby() {
        return handlingTongsInLobby;
    }

    public void setHandlingTongsInLobby(ArrayList<HandlingTongs> handlingTongsInLobby) {
        this.handlingTongsInLobby = handlingTongsInLobby;
    }

    public ArrayList<HandlingTongs> getHandlingTongsToReady() {
        return handlingTongsToReady;
    }

    public void setHandlingTongsToReady(ArrayList<HandlingTongs> handlingTongsToReady) {
        this.handlingTongsToReady = handlingTongsToReady;
    }

    public ArrayList<EmergencyStorageContainer> getContainersInLobby() {
        return containersInLobby;
    }

    public void setContainersInLobby(ArrayList<EmergencyStorageContainer> containersInLobby) {
        this.containersInLobby = containersInLobby;
    }

    public ArrayList<EmergencyStorageContainer> getContainersToReady() {
        return containersToReady;
    }

    public void setContainersToReady(ArrayList<EmergencyStorageContainer> containersToReady) {
        this.containersToReady = containersToReady;
    }

    public ArrayList<RT_Camera> getRt_camerasInLobby() {
        return rt_camerasInLobby;
    }

    public void setRt_camerasInLobby(ArrayList<RT_Camera> rt_camerasInLobby) {
        this.rt_camerasInLobby = rt_camerasInLobby;
    }

    public ArrayList<RT_Camera> getRt_camerasToReady() {
        return rt_camerasToReady;
    }

    public void setRt_camerasToReady(ArrayList<RT_Camera> rt_camerasToReady) {
        this.rt_camerasToReady = rt_camerasToReady;
    }

    public ArrayList<Film> getFilmsInLobby() {
        return filmsInLobby;
    }

    public void setFilmsInLobby(ArrayList<Film> filmsInLobby) {
        this.filmsInLobby = filmsInLobby;
    }

    public ArrayList<Film> getFilmsToReady() {
        return filmsToReady;
    }

    public void setFilmsToReady(ArrayList<Film> filmsToReady) {
        this.filmsToReady = filmsToReady;
    }

    public ArrayList<IQI> getIqisInLobby() {
        return iqisInLobby;
    }

    public void setIqisInLobby(ArrayList<IQI> iqisInLobby) {
        this.iqisInLobby = iqisInLobby;
    }

    public ArrayList<IQI> getIqisToReady() {
        return iqisToReady;
    }

    public void setIqisToReady(ArrayList<IQI> iqisToReady) {
        this.iqisToReady = iqisToReady;
    }

    public ArrayList<Developer> getDevelopersInLobby() {
        return developersInLobby;
    }

    public void setDevelopersInLobby(ArrayList<Developer> developersInLobby) {
        this.developersInLobby = developersInLobby;
    }

    public ArrayList<Developer> getDevelopersToReady() {
        return developersToReady;
    }

    public void setDevelopersToReady(ArrayList<Developer> developersToReady) {
        this.developersToReady = developersToReady;
    }

    public ArrayList<Fixer> getFixersInLobby() {
        return fixersInLobby;
    }

    public void setFixersInLobby(ArrayList<Fixer> fixersInLobby) {
        this.fixersInLobby = fixersInLobby;
    }

    public ArrayList<Fixer> getFixersToReady() {
        return fixersToReady;
    }

    public void setFixersToReady(ArrayList<Fixer> fixersToReady) {
        this.fixersToReady = fixersToReady;
    }

}
