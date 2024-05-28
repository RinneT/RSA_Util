package rsautil.model;

public class Participant {
	
	public Participant() {
		
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param mobilePhone
	 * @param eMail
	 * @param canDrive
	 * @param numberOfPassengers
	 * @param food
	 */
	public Participant(String firstName, String lastName, String mobilePhone, String eMail, boolean canDrive,
			Integer numberOfPassengers, String food) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilePhone = mobilePhone;
		this.eMail = eMail;
		this.canDrive = canDrive;
		this.numberOfPassengers = numberOfPassengers;
		this.food = food;
	}

	private String firstName;
	private String lastName;
	private String mobilePhone;
	private String eMail;
	private boolean canDrive;
	private Integer numberOfPassengers;
	private String food;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public boolean isCanDrive() {
		return canDrive;
	}

	public void setCanDrive(boolean canDrive) {
		this.canDrive = canDrive;
	}

	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

}
