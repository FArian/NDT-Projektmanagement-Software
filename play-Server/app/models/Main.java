package models;
/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {

    public static void main(String[] args) {
        NAME name=NAME.SENTINEL;
        MODEL model=MODEL.EXERTUS_VOX_400;
        double activity= 140;
            RadioActiveIsotope isotope= new RadioActiveIsotope(ISOTOPETYPE.COBALT_60,activity);
            RtCamera rtCamera= new RtCamera(name,model,isotope);
            System.out.println(rtCamera);


    }

}
