#### Task Board

-----------------------------------------------------------------
No   | Method |                   URL                 | Status  |
-----------------------------------------------------------------
US_7 |        |                                       |        |
-----------------------------------------------------------------
US_8 |        |                                       |        |
-----------------------------------------------------------------
US_9 |        |                                       |        |
-----------------------------------------------------------------
US_10 |       |                                       |        |
-----------------------------------------------------------------
US_11 |       |                                       |        |
-----------------------------------------------------------------

GET /api/v1/reports/
POST /api/v1/reports/{extension}

GET /api/v1/users/clients
GET /api/v1/users/agents

US_7_Support agent booking management (R05) (Optional)

Acceptance Criteria:
1. In a 'Support Agent' role a user should be able to:
    a. Manage Client's bookings (Cancel, change time, change the Car) Support agent board reference
    b. Create new booking for existing Client.
    c. Respond to Clients inquiries in special designated Chat inside the application (Optional)
2. When pressing "Create booking" button the Support Agent is redirected to "Cars" page where he can use selection and 
filter options.
3. When Support agent presses "Book a car" button, a window opens with options to choose existing client for this 
booking, pick-up location, drop-off location, pick-up date and time, drop-off date and time, car characteristics.
4. When Support Agent creates new booking for Client, the App should indicate, that the reservation is made by 
Support Agent, not by Client.
5. When Support agent releases the car to the client at the beginning of reservation, they should indicate the start 
mileage of the car inside the reservation and mark the reservation "Service started" inside the system.
6. When the Support agent accepts the car from the client at the end of reservation, they should indicate the end 
mileage of the car inside the reservation and mark the reservation "Service provided" inside the system.

US_8_Client's feedback (R05)

Acceptance criteria:
1. When the booking is provided to the Client (Car returned to CarRent agency) and marked by the system as 
"Service Provided", after that a Client can leave feedback for rental experience, evaluating the performance of 
the Support Agent, car provided, etc.
2. After submission the feedback should be clearly visible at the Car detailed information page Feedback at Car 
detailed information page.

US_9_Automated reports (R05)

Acceptance Criteria (Backend Scope):

1. Data Capture: The system must automatically capture and store operational data for each CarRent office using an 
AWS Lambda function triggered by booking and order completion events.
2. Data Storage: booking and order statistics must be stored securely in the Database ('Reports' DynamoDB table),
structured to support quick retrieval and aggregation.
3. Report Generation: A Lambda function ('Reports Sender') must compile the booking statistics into a report format,
aggregating data such as booking frequency, client satisfaction, and Car ratings.
4. Scheduling: The scheduler must be configured to trigger the 'Reports Sender' Lambda on a weekly basis to generate 
the reports.
5. Email Distribution: The compiled reports must be sent to the CarRent Agency Manager's email using Mail server, 
ensuring reliability and timeliness of report delivery.
6. Data Integrity and Security: Ensure all processes from data capture to email distribution maintain high levels of
data integrity and comply with data security standards.
7. Report format: Report should be sent in editable file (Excel, CSV, etc).
8. Metrics required:
   a. Report period start
   b. Report period end
   c. Location
   d. Car model
   e. Car ID
   f. How many days each Car was rented per report period.
   g. How many reservations were made during report period.
   h. Mileage at the beginning of the period (km)
   i. Mileage at the end of the period (km)
   j. Total number of kilometers traveled during the period (km)
   k. Average mileage per reservation (km)
   l. Delta of Average mileage per reservation to previous period 
   m. Average Feedback (1 to 5)
   n. Delta of Average Feedback to previous period %
   o. Minimum Feedback (1 to 5)
   p. Delta of Minimum Feedback to previous period %

US_10_Reporting Interface (R05) (Optional)

Acceptance Criteria:

Positive
1. The Admin should be able to navigate to a reporting dashboard from the Admin's panel. Reporting dashboard reference,
2. The dashboard should display a list of available filters, such as:
  a. Location
  b. Support Agent
  c. Time period
  d. Car model
3. The Admin should be able to create report basing on filtered parameters. Report created based on filters
4. Created report is clearly shown at the table format.
5. The interface should provide options to download reports in various formats (e.g., PDF, Excel).Report download reference
6. The UI should be responsive and accessible, with clear visual indicators for loading states and errors.

US_11_Update Profile information (R05) (Optional)

Acceptance Criteria:
1. The profile update form is accessible from the user’s dashboard.
2. User can navigate to the profile update section without issues.
3. The form includes editable fields: Profile change reference
 - First name
 - Last name
 - Password. Password change reference, Successful change reference,
 - Passport  (Optional)
 - Driving license Documents change reference, Documents change reference 2, Documents change confirmation (Optional)
4. The documents uploaded within the registration process should have weight less then 1 MB for each required document.
5. Each field has appropriate validation (e.g., email format).
6. Upon submission, users receive a confirmation message that their profile has been updated successfully 
(The HTTP 200 OK success status response code).
7. If there is an error during submission, users receive an error message detailing the issue. Password change 
error reference
8. Profile update functionality ensures that sensitive data (e.g., passwords) are handled securely.

