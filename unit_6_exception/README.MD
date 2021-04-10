### Custom console app Calendar
### Four date formats are implemented:
app checks if input is valid, prevents entering incorrect characters, incorrect number of days in month etc.

#### 1 - dd/mm/yy - 01/12/21 format
NOTICE: user can skip any part, example '//21' or '//' or '/12/' but do not skip slash

It is required to put zero before number if it is less than 2 digits, WRONG: '1/2/9' RIGHT: '01/02/09'

Details which are missing will be filled with '1' for days and months, and '2021' for year

if year is not between 2000 and 2021 a full year should be entered and will be displayed the same way

#### 2 - m/d/yyyy h:m:s:ms - 3/4/2021 8:23:15:10 format
NOTICE1: user can skip any part of time except hours, example '8' or '8::15'

NOTICE2: user can skip any part of date except year, example '2021' or '1//2021' or '6/2021'

it is required not to put zero before numbers if they are less than 2 digits

details which are missing will be filled with '1' for days and months, with '0' for minutes, sec and ms

#### 3 - mmm-d-yy - March 4 21 format
if year is not between 2000 and 2021 a full year should be entered and will be displayed the same way

it is not allowed to skip any part of date in this format or change the order

#### 4 - dd-mmm-yyyy hh:mm:ss - 09 April 789 08:23:15 format
it is not allowed to skip any part of date in this format or change the order

### Four functions are implemented

#### Calculate time difference between 2 dates
input: 2 dates in a chosen format

output: amount of passed time between dates 

example: date1 - 12/04/2010, date2 - 13/05/2012, result - 2 years, 1  month, 1 day 

#### Add to date
input: date, amount of years, months, days, hours, minutes, seconds etc to add

output: new date

examole: 12/04/2010 add 2 years, 3 months, 3 days, result - 15/07/12

#### Subtract from date
The same as above

#### Sort list of dates in Asc and Desc order 

### IMPORTANT: 
##### Calendar starts from 1 on January 0 year
##### Calendar takes into account leap years 
##### User is able to switch format while program is running