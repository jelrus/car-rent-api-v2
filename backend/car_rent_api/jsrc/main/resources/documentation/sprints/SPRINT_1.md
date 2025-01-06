#### Task Board

-----------------------------------------------------------------
No   | Method |                   URL                 | Status  |
-----------------------------------------------------------------
US_1 | POST   | /api/v1/users/                        | +       |
-----------------------------------------------------------------
US_2 | POST  | /api/v1/users/login                    | +       |
-----------------------------------------------------------------
US_3 | Automatic login role assignment                | +       |
-----------------------------------------------------------------
US_4 | GET   | /api/v1/home/popular-cars              | -       |
     | GET   | /api/v1/home/about-us                  | +       |
     | GET   | /api/v1/home/locations                 | +       |
     | GET   | /api/v1/home/feedbacks                 | -       |
     | GET   | /api/v1/home/faq                       | +       |
-----------------------------------------------------------------
US_5 | GET   | /api/v1/cars/                          | PENDING |
     | GET   | /api/v1/cars/{carId}                   | CHECK   |
     | GET   | /api/v1/cars/{carId}/client-review     | -       |
-----------------------------------------------------------------
US_6 | POST  | /api/v1/bookings/                      | -       |
     | POST  | /api/v1/bookings/{clientId}            | -       |
     | GET   | /api/v1/cars/{carId}/booked-days       | -       |
-----------------------------------------------------------------

#### Figma Refs

https://www.figma.com/proto/3zNMxQp0FvHbR0mxQL18VB/Car-rental?node-id=549-14429&node-type=frame&t=n2dsvANdHYhIrQQD-0&scaling=min-zoom&content-scaling=fixed&page-id=78%3A1072

#### Descriptions

US_1. User Profile Registration (R05)

Role        Client
Description	As a user I want to have the possibility to sign up (register) So that I could create an account with my 
custom settings

Acceptance Criteria:
1. The registration form should contain the following fields: Registration reference, Registration reference 2
   - First name
   - Last name
   - Email
   - Password
2. The system should validate the input fields (e.g., email format, password strength). Registration error reference 1
3. The system should check for the uniqueness of the email.
4. The user should be able to log in with the registered credentials

US_2. User Login (R05)

Role	    Client/Support Agent
Description	As a User, I want to be able to login So that I could access my account and interact with the application.

Acceptance Criteria:
1. The user should be able to access the login page. Login reference
2. The login form should contain the following fields:
   - Email
   - Password
3. The user should be authenticated against the stored credentials. 
4. The system should validate the input fields (e.g., non-empty, correct format) and provide appropriate error messages 
for incorrect credentials. Login error reference
5. Upon successful login, the user should be redirected to their dashboard. 
6. The user should remain logged in across sessions. 
7. The user should be able to log out from their account.

US_3. Automatic Role Assignment (R05)

Role	    Admin
Description	As an Admin, I want the application to automatically assign roles to new users based on predefined criteria,
So that users can have appropriate access and permissions.

Acceptance Criteria:
1. There are four roles in the application: Client, Support Agent, Admin, Visitor
2. List of users with type Support Agent should be manually created/added in DB (by Admin / by app developers)
3. When a new user registers, the system must verify if their email is listed in the Support Agent's list.
4. If the user's email is in the Support Agent's list, the system must automatically assign them the 'Support Agent' role.
5. The users that are not listed in Support Agent list are automatically assigned the 'Client' role upon account creation.
6. The assigned role is correctly displayed in the application header for all newly registered users.
7. Unauthorized users who enter the application without registration and login have Visitor level access.
8. The assignment of the role to new users occurs without any manual intervention by an admin.

US_4. Main page view (R05)

Role	    Client/ Visitor
Description	As a Client/ Visitor - I want to have the possibility to view main page - So that I can review information 
related to car selection, car rent agency locations, general information and available cars.

Acceptance Criteria:
1. Client or Visitor should have access to the Main page upon entering the CarRent application. This page will serve as an 
overview of the CarRent company. Main page reference (when not registered), Main page reference (for registered user)
2. Client/Visitor should be able to see the filtration bar on several criteria.
3. Client/Visitor should be able to see information about popular cars (Optional)
4. Client/Visitor should be able to see "About us" information, describing locations, company history, car brands, etc.  (Optional)
5. Client/Visitor should be able to see "Our locations" information, showing company locations on the map (Optional)
6. Client/Visitor should be able to see feedback/ reviews on most popular cars (Optional)
7. Client/Visitor should be able to see FAQ section (Optional)

US_5. Car selection (R05)

Role	    Client/ Visitor
Description	As a Client/ Visitor - I want to have the possibility to view cars available for reservation, select 
preferable car and choose time period - So that I could start car booking process

Acceptance Criteria:
1. Client or Visitor should have access to the Main page upon entering the CarRent application. This page will serve 
as an overview of the CarRent company.
2. Client/Visitor should be able to filter the cars on several criteria, including Filtering option on the main page
   - Pick up location
   - Drop-off location
   - Pick-up date and time
   - Drop-off date and time
   - Car category (passenger car, LCV, truck, etc.)
   - Type of gearbox
   - Type of engine
   - Daily rental prices for each car.
3. Client/Visitor is able to see the card of the car, with detailed characteristics of the car (including type, brand,
model, seats quantity, fuel consumption, etc.), photos of the car, price and rating. Search result reference, Car 
description reference, Car details reference
4. Clients/Visitors can view the rating of selected car and the feedback given for this car. Car rating reference 2

US_6. Car booking (R05)

Role	    Client
Description	As a Client, I want to have the possibility to book available cars on selected time period -So I am sure 
that required vehicle is reserved for my specific needs and schedule

Acceptance Criteria:
1. A registered and logged-in Client should be able to:
   - Book a car for a selected time period. Selection options for registered user
   - Specify pickup and drop-off locations. Car booking reference
   - Receive a booking confirmation via email or SMS.(Optional)
   - Receive Client support in designated chat inside the application (Optional)
2. Only available cars and time periods should be shown to Client, in order to prevent double booking.
3. When a Visitor selects a car for reservation, the system should not let them proceed with the reservation and should 
prompt them to either log in or register to finish the reservation process. Prompt to login reference
4. If a car becomes unavailable during the booking process, the system should notify the Client and prompt them to 
select a different time period or vehicle. Car is not available
5. The system must allow Clients to modify or cancel their reservation up to 12 hrs. before the reservation time.
Cancelation reference
6. Access to reservation modification or cancelation should be available to the Client who made the booking and CarRent
Support Agent.
7. When made, booking should have "Reserved" status at Clients "My Bookings" tab. Booking confirmation reference