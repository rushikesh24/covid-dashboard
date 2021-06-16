package coviddashboard.model;

import java.sql.Date;

public class PassData {
	int passID;
	String name;
	String aadharNumber;
	String sourceState;
	String sourceDistrict;
	String destinationState;
	String destinationDistrict;
	String reason;
	Date travelDate;
	String comments;
	String passStatus;

	public PassData(int passID, String name, String aadharNumber, String sourceState, String sourceDistrict,
			String destinationState, String destinationDistrict, String reason, Date travelDate, String comments,
			String passStatus) {
		super();
		this.passID = passID;
		this.name = name;
		this.aadharNumber = aadharNumber;
		this.sourceState = sourceState;
		this.sourceDistrict = sourceDistrict;
		this.destinationState = destinationState;
		this.destinationDistrict = destinationDistrict;
		this.reason = reason;
		this.travelDate = travelDate;
		this.comments = comments;
		this.passStatus = passStatus;
	}

	public PassData(String name, String aadharNumber, String sourceState, String sourceDistrict,
			String destinationState, String destinationDistrict, String reason, Date travelDate, String comments) {
		super();
		this.name = name;
		this.aadharNumber = aadharNumber;
		this.sourceState = sourceState;
		this.sourceDistrict = sourceDistrict;
		this.destinationState = destinationState;
		this.destinationDistrict = destinationDistrict;
		this.reason = reason;
		this.travelDate = travelDate;
		this.comments = comments;
	}

	public int getPassID() {
		return this.passID;
	}

	public void setPassID(int passID) {
		this.passID = passID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAadharNumber() {
		return this.aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getSourceState() {
		return this.sourceState;
	}

	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}

	public String getSourceDistrict() {
		return this.sourceDistrict;
	}

	public void setSourceDistrict(String sourceDistrict) {
		this.sourceDistrict = sourceDistrict;
	}

	public String getDestinationState() {
		return this.destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getDestinationDistrict() {
		return this.destinationDistrict;
	}

	public void setDestinationDistrict(String destinationDistrict) {
		this.destinationDistrict = destinationDistrict;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTravelDate() {
		return this.travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPassStatus() {
		return this.passStatus;
	}

	public void setPassStatus(String passStatus) {
		this.passStatus = passStatus;
	}

	@Override
	public String toString() {
		return "PassID=" + this.passID + "\n" + "name=" + this.name + "\n" + "aadharNumber=" + this.aadharNumber + "\n"
				+ "sourceState=" + this.sourceState + "\n" + "sourceDistrict=" + this.sourceDistrict + "\n"
				+ "destinationState=" + this.destinationState + "\n" + "destinationDistrict=" + this.destinationDistrict
				+ "\n" + "reason=" + this.reason + "\n" + "travelDate=" + this.travelDate + "\n" + "passStatus="
				+ this.passStatus;
	}

}
