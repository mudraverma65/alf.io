Assignment No. 3

Github Forked repository link: https://github.com/mudraverma65/alf.io 
Github Original repository link: https://github.com/alfio-event/alf.io 

List of Commits: https://github.com/alfio-event/alf.io/compare/master...mudraverma65:alf.io:master

Set I:
Extract Method: 
Class - EventAPIController.java
Method - exportLines()
Commit Link - https://github.com/alfio-event/alf.io/commit/1536c2ab2b01e2bdec2b2f199b26a6f97f599587

Class - AbstractFormBasedWebSecurity.java
Method - csrfFilter()
Commit Link - https://github.com/alfio-event/alf.io/commit/0bdcd3fcabe9a5f62698d8a807f87abee9c7876c 

Decompose Conditional:
Class - OpenIdAuthenticationFilter.java
Method - doFilter()
Commit Link - https://github.com/alfio-event/alf.io/commit/0d4e2f5f2189df4c129f8e782b3f624c59b5d91b 

Class - PollManager.java
Method - updatePoll()
Commit Link - https://github.com/alfio-event/alf.io/commit/8614e5e338cfe216caa7b9af75b9c4633c3a4863 

Rename Method/Variable:
Class - GroupManager.java
Method - update() -> updateGroup()
Commit Link - https://github.com/alfio-event/alf.io/commit/66226c454ff63043a2d243758286e95fcf69c434

Class - WaitingQueueManager.java
Method - distributeAvailableSeats()-> distributeAvailableSeatsPeople() and distributeAvailableSeatsStatus()
Commit Link - https://github.com/alfio-event/alf.io/commit/ac1a0a6e64dd19010684cc13fe0803de6b801d86

Set II:

Move method:
Class - AdminReservationManager.java and TicketReservationManager.java
Method - sendTicketToAttendees()
Commit Link - https://github.com/alfio-event/alf.io/commit/258091c1c7afdb96e10e9b580d1a357bcb11ab51

Extract method:
Class - WaitingQueueManager.java -> WaitingQueueManager.java, WaitingQueueManagerReservation.java.
Commit Link - https://github.com/alfio-event/alf.io/commit/d0db41005df61475ab8931f11a9d255604fa02e9

Class - AdditionalServiceApiController.java -> AdditionalServiceApiController.java, AdditionalServiceApiControllerDeleteMapping.java, AdditionalServiceApiControllerGetMapping.java, AdditionalServiceApiControllerPostMapping.java and AdditionalServiceApiControllerPutMapping.java
Commit Link - https://github.com/alfio-event/alf.io/commit/e6419a5bbcade8ec562d5f5fa4e660cd69e2b3d1

Class - ConfigurationApiController.java -> ConfigurationApiController.java, ConfigurationApiControllerInvoice.java and ConfigurationApiControllerTicket.java
Commit Link - https://github.com/alfio-event/alf.io/commit/c420b0b2b1825c66e413fc0dcc4a99a520bcadf5

Push Down:
Class - Configurable.java and PurchaseContext.java
Method - getOrganizationId() from Configurable.java to PurchaseContext.java
Commit Link - https://github.com/alfio-event/alf.io/commit/8101ad90a9cbd9f71a5bddf650399a17ec027695
