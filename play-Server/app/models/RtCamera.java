package models;

import actors.serverInterface.ServerLog;

import java.util.ArrayList;

/**
 * Created by F.Arian on 06.11.17.
 */
public class RtCamera {

    private NAME name;
    private MODEL model;
    private static String SERIAL_NUMBER;
    private static String id;
    private String dimensions;
    private String weight;
    private String activity_Of_Depleted_Uranium_Shield;
    private String certification_Type_Package;
    private String accreditation;
    private String construction_Exposure_Device;
    private String removable_Jacket;
    private String materials;
    private RadioactiveIsotope isotope;
    private String controls_and_Guide_Tubes;
    private String inspection_Requirements;
    private String maintenance_Requirements;
    private static String operating_Temperature_Range;
    private String primary_Application;
    private String source_Assembly_and_Authorized_Contents;
    private boolean capacityPermision;
    private boolean isotopeTypePermision;
    private String country_Region;
    private double totalweight;
    private String safetyDetail;
    private ServerLog log=new ServerLog();
    private ArrayList<ISOTOPETYPE> isotopetypes;
    private Project project;

    public RtCamera(NAME name, MODEL model,RadioactiveIsotope isotope) {
        this.setCapacityPermision(false);
        this.setIsotopeTypePermision(false);
        this.setName(name);
        this.setModel(model);
        this.isotopetypes=new ArrayList<>();
        this.setDevice_Source_Maximum_Capacity(model, isotope);
        // is RT Camera SENTINEL
        if (this.getName().equals(NAME.SENTINEL)) {
            this.setDimensions(this.dimensions_For_Sentinel());
            this.setWeight(this.setWeight_Sentinel_Models(model));
            this.setActivity_Of_Depleted_Uranium_Shield(this.activity_of_Depleted_Uranium_Shield_For_Sentinel());
            this.setCertification_Type_Package(this.certification_Type_Package_For_Sentinel());
            this.setAccreditation(this.accreditation_For_Sentinel());
            this.setRemovable_Jacket(this.removable_Jacket_For_Sentinel());
            this.setMaterials(this.materials_For_Sentinel());
            this.setControls_and_Guide_Tubes(this.controls_and_Guide_Tubes_For_Sentinel());
            this.setInspection_Requirements(this.inspection_Requirements_For_Sentinel());
            this.setMaintenance_Requirements(this.maintenance_Requirements_For_Sentinel());
            this.setOperating_Temperature_Range(this.operating_Temperature_Range_For_Sentinel());
            this.setPrimary_Application(this.primary_Application_For_Sentinel());
            this.setSource_Assembly_and_Authorized_Contents(this.Source_Assembly_and_Authorized_Contents_For_Sentinel());
            this.setConstruction_Exposure_Device(this.construction_Exposure_Device_For_Sentinel());
        }

        this.setCountry(this.getName());
        if (!this.isCapacityPermision() && !this.isIsotopeTypePermision()) {
            isIsotopeTypePermision();
            this.getLog().info("RT CAMERA IS NOT ACTIVE," + "\n" +
                    "Safety specification of the camera was violated," + "\n" +
                    "please check the value of the ISOTOPE :" + isotope.getActivity() + "\n" +
                    "unsuitable for :" + this.getName() + "_" + this.getModel());
        } else {
            this.getLog().info(" NEW OBJECT CREATED, NAME : " +getName());
        }
        this.setProject(new Project());
        this.setSerialNumber(DATA.creatId("-"+model.name().toString()));
        this.id=DATA.generateUniqueId();


    }


    public NAME getName() {
        return name;
    }

    public void setName(NAME name) {
        this.name = name;
    }

    public static String getSerialNumber() {
        return SERIAL_NUMBER;
    }

    public static void setSerialNumber(String serialNumber) {
        if(serialNumber!=null){
            serialNumber=serialNumber.toUpperCase();
        }
        SERIAL_NUMBER = serialNumber;
    }


    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getActivity_Of_Depleted_Uranium_Shield() {
        return activity_Of_Depleted_Uranium_Shield;
    }

    public void setActivity_Of_Depleted_Uranium_Shield(String activity_Of_Depleted_Uranium_Shield) {
        this.activity_Of_Depleted_Uranium_Shield = activity_Of_Depleted_Uranium_Shield;
    }

    public String getCertification_Type_Package() {
        return certification_Type_Package;
    }

    public void setCertification_Type_Package(String certification_Type_Package) {
        this.certification_Type_Package = certification_Type_Package;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getConstruction_Exposure_Device() {
        return construction_Exposure_Device;
    }

    public void setConstruction_Exposure_Device(String construction_Exposure_Device) {
        this.construction_Exposure_Device = construction_Exposure_Device;
    }

    public String getRemovable_Jacket() {
        return removable_Jacket;
    }

    public void setRemovable_Jacket(String removable_Jacket) {
        this.removable_Jacket = removable_Jacket;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public RadioactiveIsotope getIsotopes() {
        return isotope;
    }

    public void setIsotopes(RadioactiveIsotope isotopes) {

        this.isotope = isotopes;
    }

    public String getControls_and_Guide_Tubes() {
        return controls_and_Guide_Tubes;
    }

    public void setControls_and_Guide_Tubes(String controls_and_Guide_Tubes) {
        this.controls_and_Guide_Tubes = controls_and_Guide_Tubes;
    }

    public String getInspection_Requirements() {
        return inspection_Requirements;
    }

    public void setInspection_Requirements(String inspection_Requirements) {
        this.inspection_Requirements = inspection_Requirements;
    }

    public String getMaintenance_Requirements() {
        return maintenance_Requirements;
    }

    public void setMaintenance_Requirements(String maintenance_Requirements) {
        this.maintenance_Requirements = maintenance_Requirements;
    }

    public String getOperating_Temperature_Range() {
        return operating_Temperature_Range;
    }

    public void setOperating_Temperature_Range(String operating_Temperature_Range) {
        this.operating_Temperature_Range = operating_Temperature_Range;
    }

    public String getPrimary_Application() {
        return primary_Application;
    }

    public void setPrimary_Application(String primary_Application) {
        this.primary_Application = primary_Application;
    }

    public boolean isCapacityPermision() {
        return capacityPermision;
    }

    public void setCapacityPermision(boolean capacityPermision) {
        this.capacityPermision = capacityPermision;
    }

    public ServerLog getLog() {
        return log;
    }

    public void setLog(ServerLog log) {
        this.log = log;
    }

    public String getSource_Assembly_and_Authorized_Contents() {
        return source_Assembly_and_Authorized_Contents;
    }

    public void setSource_Assembly_and_Authorized_Contents(String source_Assembly_and_Authorized_Contents) {
        this.source_Assembly_and_Authorized_Contents = source_Assembly_and_Authorized_Contents;
    }

    public String getCountry_Region() {
        return country_Region;
    }


    public void setCountry_Region(String country_Region) {
        this.country_Region = country_Region;
    }


    public double getTotalweight() {
        return totalweight;
    }

    public void setTotalweight(double totalweight) {
        this.totalweight = totalweight;
    }

    public String getSafetyDetail() {
        return safetyDetail;
    }

    public void setSafetyDetail(String safetyDetail) {
        this.safetyDetail = safetyDetail;
    }

    public void setCountry(NAME name) {
        switch (name) {
            case GAMMAMAT:
                this.setCountry_Region("GERMANY");
                break;
            case SENTINEL:
                this.setCountry_Region("USA");
                break;
            case Dandong_XY_Electric:
                this.setCountry_Region("CHINA");
                break;
            case OSERIX:
                this.setCountry_Region("BEIGIEN");
        }

    }


    /**
     * just for SENTINEL
     *
     * @return
     */
    private String operating_Temperature_Range_For_Sentinel() {
        String str = "-40oF to 300oF (-40oC to 149oC)";
        return str;
    }

    /**
     * just for SENTINEL
     *
     * @return
     */
    private String maintenance_Requirements_For_Sentinel() {
        String str =
                "Most national regulations require inspection and maintenance of the system at quarterly intervals." + "\n" +
                        "The complete annual servicing ensures the integrity of the system." + "\n" +
                        "Shorter frequencies of inspection and maintenance are required when the system is operated under severe operating environments." + "\n" +
                        "In some cases,the system should be serviced immediately after certain jobs in severe environmental working conditions." + "\n" +
                        "See device operation and maintenance manual for detailed maintenance requirements";
        return str;
    }

    /**
     * just for SENTINEL
     *
     * @return
     */
    private String inspection_Requirements_For_Sentinel() {
        String str = "Daily pre-operational inspection for obvious damage to the system." + "\n" +
                "See device operation and maintenance manual for detailed maintenance requirements.";

        return str;
    }

    /**
     * just for SENTINEL
     *
     * @return
     */
    private String controls_and_Guide_Tubes_For_Sentinel() {
        String str = "Compatible with standard, and extreme remote controls and source guide tubes.";
        return str;
    }

    /**
     * just for SENTINEL
     *
     * @return
     */
    private String Source_Assembly_and_Authorized_Contents_For_Sentinel() {
        String str = "USNRC Model Number: A424-9 source assembly with a double encapsulated Ir-192 sealed source. The\n" +
                "IAEA/USDOT Special Form Certificate number is USA/0335/S. In addition, the following isotopes may also\n" +
                "be utilized in the 880 series exposure devices.\n" +
                "Se-75 (USA/0502/S-96), Co-60 (USA/0165/S-96), Yb-169 (USA/0597/S-96), Cs-137 (USA/0335/S-96)";
        return str;
    }

    private String materials_For_Sentinel() {
        String str = "Titanium 'S' Tube, DU Shield, Stainless Steel Tubular Shell and Plates, Aluminum, Brass, Tungsten, and\n" +
                "Polyurethane";
        return str;
    }

    private String removable_Jacket_For_Sentinel() {
        String str = "One-piece, high impact resistant, plastic jacket incorporating a carrying handle and base.";
        return str;
    }

    private String construction_Exposure_Device_For_Sentinel() {
        String str = "Depleted Uranium (DU) shield encased within a welded tubular stainless steel shell with stainless steel end\n" +
                "plates and stainless steel investment castings. Interior void space filled with rigid foam.";
        return str;
    }

    private String accreditation_For_Sentinel() {
        String str = "SENTINEL_TM 880 Delta, Sigma, Elite, and Omega models are designed, tested and manufactured to meet\n" +
                "the requirements of ANSI N432-1980, ISO 3999-1 2000E, IAEA TS-R-1 (1996), USNRC 10CFR34,\n" +
                "10CFR71, 49CFR173, MA-1059-D-334-S and CNSC R-061-0001-0-2012*. Additionally, the exposure\n" +
                "devices are designed, manufactured and serviced under a QA program that has been accredited to ISO 9001\n" +
                "(2000) and approved in accordance with USNRC 10CFR71, Subpart H. The QA program also includes the\n" +
                "reporting requirements of USNRC 10CFR21 for suppliers of source and byproduct materials.\n" +
                "*Omega is not included in MA-1059-D-334-S and CNSC R-061-0001-0-2012";
        return str;
    }

    private String certification_Type_Package_For_Sentinel() {
        String str = "Type B(U) package, USNRC & USDOT Certification Number USA/9296/B(U)-96\n" +
                "Type B(U) package, CNSC CDN/E199/-96\n" +
                "Type A transport package, 49CFR173.415 and IAEA TS-R-1 (1996 Revised)";
        return str;
    }

    private String activity_of_Depleted_Uranium_Shield_For_Sentinel() {
        String str = "Delta 5.4mCi (200MBq), Sigma 5.4mCi (200MBq), Elite 3.8mCi (141MBq), Omega 2.7mCi (101MBq)";
        return str;
    }

    private String primary_Application_For_Sentinel() {
        String str = "Primary Application";
        return str;
    }

    /**
     * Set Weight for camera models
     *
     * @param model
     * @return weight
     */
    private String setWeight_Sentinel_Models(MODEL model) {
        String str = "";
        switch (model) {
            case DELTA_880:
                str = "52 lb (23.6kg)";
                break;
            case ELITA_880:
                str = "42 lb (19.0kg)";
                break;
            case OMEGA_880:
                str = "33 lb (15.0kg)";
                break;
            case SIGMA_880:
                str = "52 lb (23.6kg)";
                break;
            case SCARPRO_1075_880:
                break;
            default:
                str = "check your parameter";
        }
        return str;
    }

    private String dimensions_For_Sentinel() {
        String str = "Length :" + "13.33in(33.8cm)\n" +
                "Width :" + "7.5in (19.1cm)\n" +
                "Height :" + "9 in (22.9cm)";
        return str;
    }

    /**
     * @param model of RT Camera and here will be check capacity safety!
     */
    private void setDevice_Source_Maximum_Capacity(MODEL model, RadioactiveIsotope isotope) {
        switch (model) {
            case SIGMA_880:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
                this.isotopetypes.add(ISOTOPETYPE.YTTERBIUM_169);
                this.isotopetypes.add(ISOTOPETYPE.CAESIUM_137);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 150) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                //130Ci 4.81TBq
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 130) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                // 0.025 Ci = 25mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60) && isotope.getActivity() <= 0.025) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.YTTERBIUM_169) && isotope.getActivity() <= 20) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                //0.38 Ci = 380mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.CAESIUM_137) && isotope.getActivity() <= 0.38) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;

            case OMEGA_880:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
                this.isotopetypes.add(ISOTOPETYPE.YTTERBIUM_169);
                this.isotopetypes.add(ISOTOPETYPE.CAESIUM_137);

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 80) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 15) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                // 0.025 Ci = 25mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60)) {
                    this.setCapacityPermision(false);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.YTTERBIUM_169) && isotope.getActivity() <= 30) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.CAESIUM_137)) {
                    this.setCapacityPermision(false);
                }
                break;
            case DELTA_880:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
                this.isotopetypes.add(ISOTOPETYPE.YTTERBIUM_169);
                this.isotopetypes.add(ISOTOPETYPE.CAESIUM_137);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 150) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 150) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                // 0.065 Ci = 65mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60) && isotope.getActivity() <= 0.065) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.YTTERBIUM_169) && isotope.getActivity() <= 20) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                //0.38 Ci = 380mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.CAESIUM_137) && isotope.getActivity() <= 0.38) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;
            case ELITA_880:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);
                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);
                this.isotopetypes.add(ISOTOPETYPE.YTTERBIUM_169);
                this.isotopetypes.add(ISOTOPETYPE.CAESIUM_137);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 150) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 50) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                // 0.025 Ci = 25mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60) && isotope.getActivity() <= 0.025) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                if (isotope.getIsotopetype().equals(ISOTOPETYPE.YTTERBIUM_169) && isotope.getActivity() <= 20) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                //0.38 Ci = 380mCi
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.CAESIUM_137) && isotope.getActivity() <= 0.38) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;
            case SCARPRO_1075_880:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 81) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                break;
            case Exertus_Dual_120:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);

                this.setTotalweight(22);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 120) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 200) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
            case Exertus_Dual_60:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);
                this.isotopetypes.add(ISOTOPETYPE.IRIDIUM_192);

                this.setTotalweight(18);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.IRIDIUM_192) && isotope.getActivity() <= 60) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 200) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;
            case EXERTUS_LIGHT:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);

                this.setTotalweight(6);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 140) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
            case EXERTUS_LIGHT_W:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);

                this.setTotalweight(8);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 140) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
            case EXERTUS_CIRCA_120:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);

                this.setTotalweight(40);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 100) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                break;
            case EXERTUS_CIRCA_80:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);

                this.setTotalweight(40);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 80) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                    break;
                }
                break;
            case EXERTUS_RID_Se4P:
                this.isotopetypes.add(ISOTOPETYPE.SELENIUM_75);

                this.setTotalweight(7);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.SELENIUM_75) && isotope.getActivity() <= 120) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;
            case EXERTUS_VOX_100:

                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);

                this.setTotalweight(185);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60) && isotope.getActivity() <= 100) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }

                break;
            case EXERTUS_VOX_400:

                this.isotopetypes.add(ISOTOPETYPE.COBALT_60);

                this.setTotalweight(320);
                if (isotope.getIsotopetype().equals(ISOTOPETYPE.COBALT_60) && isotope.getActivity() <= 400) {
                    this.setCapacityPermision(true);
                    this.setIsotopes(isotope);
                }
                break;


        }
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public RadioactiveIsotope getIsotope() {
        return isotope;
    }

    public void setIsotope(RadioactiveIsotope isotope) {
        this.isotope = isotope;
    }

    /**
     * Check if camera allows to keep the isotopeType
     *
     * @param isotope
     * @return boolean
     */
    private boolean isIsotopePermitToChargeRT_Camera(RadioactiveIsotope isotope) {

        for (int i = 0; i < isotopetypes.size(); i++) {
            if (isotope.getIsotopetype().equals(isotopetypes.get(i))) {

                this.setIsotopeTypePermision(true);
                return true;
            }

        }
        if (!isIsotopeTypePermision()) {
            this.getLog().info("SAFETY MEASURE WAS VIOLATED:\n" +
                    "CAMERA DOES,NOT ALLOWS TO KEEP THE ISOTOPE TYPE:" + isotope.getIsotopetype() + "\n" +
                    "CHECK IT:\n" +
                    "CAMERA  ALLOWS TO KEEP THE ISOTOPETYPES :" + getIsotopetypes());
        }

        return false;
    }

    public boolean isIsotopeTypePermision() {
        return isotopeTypePermision;
    }

    public void setIsotopeTypePermision(boolean isotopeTypePermision) {
        this.isotopeTypePermision = isotopeTypePermision;
    }

    public ArrayList<ISOTOPETYPE> getIsotopetypes() {
        return isotopetypes;
    }

    public void setIsotopetypes(ArrayList<ISOTOPETYPE> isotopetypes) {
        this.isotopetypes = isotopetypes;
    }

    /**
     * is a camera ready for a project ?
     *
     * @return boolean
     */
    public boolean ready_RT_CAMERA() {
        this.isIsotopePermitToChargeRT_Camera(getIsotope());

        if (!this.isCapacityPermision()) {
            this.getLog().info("CHECK why isCapacityPermision(); = " + this.isCapacityPermision() + " ,go to : " + this.getClass());

        }
        if (!this.isotopeTypePermision) {
            this.getLog().info("CHECK  why isotopeTypePermision(); = " + this.isotopeTypePermision + " ,go to : " + this.getClass());

        }

        return this.isIsotopeTypePermision() && isCapacityPermision();
    }

    public static String getId() {
        return id;
    }




    @Override
    public String toString() {
        return "RT_CAMERA{" + "\n" +
                ", NAME=" + name +
                ", ID=" + getId()+
                ", MODEL=" + model + "\n" +
                ", SERIAL_NUMBER=" + SERIAL_NUMBER +
                ", DIMENSIONS=" + dimensions +
                ", WEIGHT=" + weight +
                ", ISOTOPE_TYPES=" + isotopetypes +
                ", ISOTOPE=" + isotope +
                ", ISOTOPE_PERMISSION=" + this.isIsotopePermitToChargeRT_Camera(getIsotope()) +
                ", CAPACITY_PERMISSION=" + capacityPermision +
                ", RT_CAMERA_IS_READY=" + this.ready_RT_CAMERA() +
                ", PRIMARY_APPLICATION=" + primary_Application +
                ", COUNTRY_REGION=" + country_Region +
                ", TOTAL_WEIGHT=" + totalweight +
                ", SAFETY_DETAIL=" + safetyDetail +
                ", PROJECT_NAME=" + project.getName() +
                ", PROJECT_NR=" + project.getProjectNumber() +
                ", PROJECT_LOCATION=" + project.getLocation() + "\n" +
                "}";
    }
}
