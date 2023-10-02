# System_Integration_assignment-1
## Short summary of project
The project goal is to deliver invitations to partners for the upcomming GA meeting. 

Requirements for the project is:
* Get geo location from ip
* Get gender based on location
* Use one of each API's
    * SOAP
    * REST
* Sending mail to partners
* Attaching a yearly report in the mail

We will be provided with a list of partners with email, name, and ip.

## Project Structure
The project is written in Java 11. 

We have 4 Controllers (Which is a combined Controller/Service file (Might need to change that)):
* GenderController
    * To get gender based on name and location
* GeoLocationController
    * To get location based on IP address 
* InvitationController
    * Creates the body message of the mail
* MailController
    * Sends mail

All 4 controller/services has one functionality each.

We will have a CSV list with the partners and the yearly report in pdf in the resources folder

## Process flow
The process begins by reading the list of partners. Subsequently, we iterate through each one, gathering the necessary information for creating our invitation. Firstly, we need the partner's location, which we obtain using GeoLocationController.getGeoLocationByIP(ipAddress). We then utilize this location and name to determine the partner's gender using GenderController.getGenderByNameAndLocation(location, name).
Once we have knowledge of the partner's gender, we proceed to create our invitation text using InvitationController.createInvitationForm(name, gender). Finally, we send our email using MailController.sendMail(email, invitationMessage, subject, path_to_pdf).

## Packages/Libraries (Not including java packages)
* JUnit
* javax
* org.json
* org.w3c
