import json
from urllib.request import urlopen
import mysql.connector
import datetime


connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Test@1234",
    database='covid_dashboard'
)
link = "https://api.covid19india.org/state_district_wise.json"
db_cursor = connection.cursor()

print("Fetching from url....")
data = urlopen(link).read().decode()
print("Data fetched")

data = json.loads(data)

recordDate = str(datetime.date.today())
data.pop("State Unassigned")

db_cursor.execute('select * from State;')
states = db_cursor.fetchall();
if len(data) != len(states):
	for i in data:
		db_cursor.execute('insert into State values (%s, %s)',(data[i]['statecode'], i))
connection.commit()

print("Data uploading to database...")
nationactiveCases = 0
nationconfirmedCases = 0
nationdeceasedCases = 0
nationrecoveredCases = 0
nationdailyConfirmedCases = 0
nationdailyDeceasedCases = 0
nationdailyRecoveredCases = 0
for i in data:
    stateCode = data[i]['statecode']
    stateactiveCases = 0
    stateconfirmedCases = 0
    statedeceasedCases = 0
    staterecoveredCases = 0
    statedailyConfirmedCases = 0
    statedailyDeceasedCases = 0
    statedailyRecoveredCases = 0
    for j in data[i]["districtData"]:
        district = data[i]["districtData"][j]
        activeCases = district['active']
        confirmedCases = district['confirmed']
        deceasedCases = district['deceased']
        recoveredCases = district['recovered']
        dailyConfirmedCases = district['delta']['confirmed']
        dailyDeceasedCases = district['delta']['deceased']
        dailyRecoveredCases = district['delta']['recovered']
        db_cursor.execute('insert into DistrictData values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)',
                         (stateCode, j, activeCases, confirmedCases, deceasedCases, recoveredCases,
                          dailyConfirmedCases, dailyDeceasedCases, dailyRecoveredCases, recordDate))
        stateactiveCases += activeCases
        stateconfirmedCases += confirmedCases
        statedeceasedCases += deceasedCases
        staterecoveredCases += recoveredCases
        statedailyConfirmedCases += dailyConfirmedCases
        statedailyDeceasedCases += dailyDeceasedCases
        statedailyRecoveredCases += dailyRecoveredCases
    db_cursor.execute('insert into StateData values (%s, %s, %s, %s, %s, %s, %s, %s, %s)',
                         (stateCode, stateactiveCases, stateconfirmedCases, statedeceasedCases, staterecoveredCases,
                          statedailyConfirmedCases, statedailyDeceasedCases, statedailyRecoveredCases, recordDate))
    nationactiveCases += stateactiveCases
    nationconfirmedCases += stateconfirmedCases 
    nationdeceasedCases += statedeceasedCases
    nationrecoveredCases += staterecoveredCases
    nationdailyConfirmedCases += statedailyConfirmedCases
    nationdailyDeceasedCases += statedailyDeceasedCases
    nationdailyRecoveredCases += statedailyRecoveredCases

db_cursor.execute('insert into NationData values (%s, %s, %s, %s, %s, %s, %s, %s)',
                         (recordDate, nationactiveCases, nationconfirmedCases, nationdeceasedCases, nationrecoveredCases,
                          nationdailyConfirmedCases, nationdailyDeceasedCases, nationdailyRecoveredCases))
connection.commit()
print("Done data uploading to database")