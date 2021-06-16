package coviddashboard.model;

import java.sql.Date;

public class DistrictData {
	String districtName;
	int activeCases;
	int confirmedCases;
	int deceasedCases;
	int recoveredCases;
	int dailyConfirmedCases;
	int dailyDeceasedCases;
	int dailyRecoveredCases;
	Date recordDate;

	public DistrictData(String districtName, int activeCases, int confirmedCases, int deceasedCases, int recoveredCases,
			int dailyConfirmedCases, int dailyDeceasedCases, int dailyRecoveredCases, Date recordDate) {
		super();
		this.districtName = districtName;
		this.activeCases = activeCases;
		this.confirmedCases = confirmedCases;
		this.deceasedCases = deceasedCases;
		this.recoveredCases = recoveredCases;
		this.dailyConfirmedCases = dailyConfirmedCases;
		this.dailyDeceasedCases = dailyDeceasedCases;
		this.dailyRecoveredCases = dailyRecoveredCases;
		this.recordDate = recordDate;
	}

	public DistrictData(String districtName, int activeCases, int confirmedCases, int deceasedCases,
			int recoveredCases) {
		super();
		this.districtName = districtName;
		this.activeCases = activeCases;
		this.confirmedCases = confirmedCases;
		this.deceasedCases = deceasedCases;
		this.recoveredCases = recoveredCases;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getActiveCases() {
		return this.activeCases;
	}

	public void setActiveCases(int activeCases) {
		this.activeCases = activeCases;
	}

	public int getConfirmedCases() {
		return this.confirmedCases;
	}

	public void setConfirmedCases(int confirmedCases) {
		this.confirmedCases = confirmedCases;
	}

	public int getDeceasedCases() {
		return this.deceasedCases;
	}

	public void setDeceasedCases(int deceasedCases) {
		this.deceasedCases = deceasedCases;
	}

	public int getRecoveredCases() {
		return this.recoveredCases;
	}

	public void setRecoveredCases(int recoveredCases) {
		this.recoveredCases = recoveredCases;
	}

	public int getDailyConfirmedCases() {
		return this.dailyConfirmedCases;
	}

	public void setDailyConfirmedCases(int dailyConfirmedCases) {
		this.dailyConfirmedCases = dailyConfirmedCases;
	}

	public int getDailyDeceasedCases() {
		return this.dailyDeceasedCases;
	}

	public void setDailyDeceasedCases(int dailyDeceasedCases) {
		this.dailyDeceasedCases = dailyDeceasedCases;
	}

	public int getDailyRecoveredCases() {
		return this.dailyRecoveredCases;
	}

	public void setDailyRecoveredCases(int dailyRecoveredCases) {
		this.dailyRecoveredCases = dailyRecoveredCases;
	}

	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public String toString() {
		return "DistrictData [districtName=" + this.districtName + ", activeCases=" + this.activeCases
				+ ", confirmedCases=" + this.confirmedCases + ", deceasedCases=" + this.deceasedCases
				+ ", recoveredCases=" + this.recoveredCases + ", dailyConfirmedCases=" + this.dailyConfirmedCases
				+ ", dailyDeceasedCases=" + this.dailyDeceasedCases + ", dailyRecoveredCases="
				+ this.dailyRecoveredCases + ", recordDate=" + this.recordDate + "]";
	}
}
