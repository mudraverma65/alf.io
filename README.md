Assignment No. 3

Github Forked repository link: https://github.com/mudraverma65/alf.io 
Github Original repository link: https://github.com/alfio-event/alf.io 

List of Commits: https://github.com/alfio-event/alf.io/compare/master...mudraverma65:alf.io:master

Set I:
Extract Method: 
Class - EventAPIController.java
Method - exportLines()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/controller/api/admin/EventApiController.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/EventApiController.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/1536c2ab2b01e2bdec2b2f199b26a6f97f599587

Class - AbstractFormBasedWebSecurity.java
Method - csrfFilter()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/config/authentication/AbstractFormBasedWebSecurity.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/config/authentication/AbstractFormBasedWebSecurity.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/0bdcd3fcabe9a5f62698d8a807f87abee9c7876c 

Decompose Conditional:
Class - OpenIdAuthenticationFilter.java
Method - doFilter()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/config/authentication/support/OpenIdAuthenticationFilter.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/config/authentication/support/OpenIdAuthenticationFilter.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/0d4e2f5f2189df4c129f8e782b3f624c59b5d91b

Class - PollManager.java
Method - updatePoll()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/PollManager.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/PollManager.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/8614e5e338cfe216caa7b9af75b9c4633c3a4863 


Rename Method/Variable:
Class - GroupManager.java
Method - update() -> updateGroup()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/GroupManager.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/GroupManager.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/66226c454ff63043a2d243758286e95fcf69c434

Class - WaitingQueueManager.java
Method - distributeAvailableSeats()-> distributeAvailableSeatsPeople() and distributeAvailableSeatsStatus()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/WaitingQueueManager.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/test/java/alfio/manager/WaitingQueueManagerTest.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/ac1a0a6e64dd19010684cc13fe0803de6b801d86

Set II:

Move method:
Class - AdminReservationManager.java and TicketReservationManager.java
Method - sendTicketToAttendees()
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/AdminReservationManager.java , https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/TicketReservationManager.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/AdminReservationManager.java, https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/TicketReservationManager.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/258091c1c7afdb96e10e9b580d1a357bcb11ab51

Extract method:
Class - WaitingQueueManager.java -> WaitingQueueManagerSubscription.java, WaitingQueueManagerReservation.java.
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/manager/WaitingQueueManager.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/WaitingQueueManagerReservation.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/manager/WaitingQueueManagerSubscription.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/d0db41005df61475ab8931f11a9d255604fa02e9

Class - AdditionalServiceApiController.java -> AdditionalServiceApiController.java, AdditionalServiceApiControllerDeleteMapping.java, AdditionalServiceApiControllerGetMapping.java, AdditionalServiceApiControllerPostMapping.java and AdditionalServiceApiControllerPutMapping.java
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiController.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiController.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiControllerGetMapping.java ,  https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiControllerPutMapping.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiControllerPostMapping.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/AdditionalServiceApiControllerDeleteMapping.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/e6419a5bbcade8ec562d5f5fa4e660cd69e2b3d1

Class - ConfigurationApiController.java -> ConfigurationApiController.java, ConfigurationApiControllerInvoice.java and ConfigurationApiControllerTicket.java
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/controller/api/admin/ConfigurationApiController.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/ConfigurationApiController.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/ConfigurationApiControllerTicket.java , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/controller/api/admin/ConfigurationApiControllerInvoice.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/c420b0b2b1825c66e413fc0dcc4a99a520bcadf5

Push Down:
Class - Configurable.java and PurchaseContext.java
Method - getOrganizationId() from Configurable.java to PurchaseContext.java
Original class - https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/model/Configurable.java , https://github.com/alfio-event/alf.io/blob/master/src/main/java/alfio/model/PurchaseContext.java 
Changed class - https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/model/Configurable.java  , https://github.com/mudraverma65/alf.io/blob/master/src/main/java/alfio/model/PurchaseContext.java 
Commit Link - https://github.com/alfio-event/alf.io/commit/8101ad90a9cbd9f71a5bddf650399a17ec027695

Final Refactoring Commit - https://github.com/alfio-event/alf.io/commit/359bf38cdc5bf2fb7621a1b97351c7464bd4178d 

Pull Request Status: Rejected

