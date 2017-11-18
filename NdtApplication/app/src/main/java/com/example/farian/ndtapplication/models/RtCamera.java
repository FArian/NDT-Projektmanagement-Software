package com.example.farian.ndtapplication.models;
/**
 * Created by F.Arian on 06.11.17.
 */
public class RtCamera extends Material {

	private Isotope[] isotope;
	private int maximumCapacity;
	private String shieldingMatrial;
	private String packageType;
	private String designFeatures;
	private String application;
	private String typeClass;
	private String weight;
	private String overallSize;
	private String madeIn;
	private String otherDetail;
	private Type type;

	public RtCamera(String name, String model, Type type, int serialNumber, Location location, Isotope[] isotope,
			int maximumCapacity, String shieldingMatrial, String packageType, String designFeatures, String application,
			String typeClass, String weight, String overallSize, String madeIn, String otherDetail) {
		super(name, model, type, serialNumber, location);
		setIsotope(isotope);
		setMaximumCapacity(maximumCapacity);
		setShieldingMatrial(shieldingMatrial);
		setPackageType(packageType);
		setDesignFeatures(designFeatures);
		setApplication(application);
		setTypeClass(typeClass);
		setWeight(weight);
		setOverallSize(overallSize);
		setMadeIn(madeIn);
		setOtherDetail(otherDetail);
		setType(type.RT_CAMERA);
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Isotope[] getIsotope() {
		return isotope;
	}

	public void setIsotope(Isotope[] isotope) {
		this.isotope = isotope;
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public String getShieldingMatrial() {
		return shieldingMatrial;
	}

	public void setShieldingMatrial(String shieldingMatrial) {
		this.shieldingMatrial = shieldingMatrial;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getDesignFeatures() {
		return designFeatures;
	}

	public void setDesignFeatures(String designFeatures) {
		this.designFeatures = designFeatures;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOverallSize() {
		return overallSize;
	}

	public void setOverallSize(String overallSize) {
		this.overallSize = overallSize;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getOtherDetail() {
		return otherDetail;
	}

	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}

}
