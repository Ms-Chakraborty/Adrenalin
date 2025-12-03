## Create Event 
POST /api/v1/events
Request Body: Event

## List Events
GET /api/v1/events

## Retrieve Event
GET /api/v1/events/{event_id}

## Update Event
PUT /api/v1/events/{event_id}
Request Body: Event

## Delete Event
DELETE /api/v1/events/{event_id}

## Validate Ticket 
POST /api/v1/events/{event_id}/tickets-validations

## List Ticket Validations
GET api/v1/events/{event_id}/tickets-validations


## List Ticket Sales
GET /api/v1/events/{event_id}/tickets

## Retrieve Ticket Sale
GET /api/v1/events/{event_id}/tickets/{ticket_id}

## Update Ticket Sale
PUT /api/v1/events/{event_id}/tickets/{ticket_id}
Request Body: Ticket

## Delete Ticket Sale
DELETE /api/v1/events/{event_id}/tickets/{ticket_id}

## Partial  Update
PATCH /ap1/v1/events/{event_id}/tickets
Request Body: Partial Ticket

## List Ticket Type
GET /api/v1/events/{event_id}/ticket-types

## Retrive Ticket Type
GET /api/v1/events/{event_id}/ticket-types/{ticket_type_id}

## Delete Ticket Type
DELETE /api/v1/events/{event_id}/ticket-types/{ticket_type_id}

## Partial Update Ticket Type
PATCH /api/v1/events/{event_id}/ticket-types/{ticket_type_id}
Request Body: Partial Ticket Type

## Search Published Events
GET api/v1/published-event

## Retrive Published events
GET api/v1/published-event/{published_event_id}

## Purchase Ticket
POST api/v1/published-event/{published_event_id}/ticket-types/{ticket_types_id}

## List Tickets(for user)
GET /api/v1/tickets

## Retrieve Tickets(for user)
GET /api/v1/tickets/{ticket_id}

## Retieve Ticket OR Code
GET /api/v1/tickets/{ticket_id}/qr-codes


## TODO:Dedicated endpoint for report data


