# EventApp Backend (Spring Boot)

This is the backend for **EventApp**, built with Spring Boot.
It exposes REST APIs for managing **Events** and **Tickets**, with best practices like DTOs, validation, unified responses, and Swagger documentation.

<p align="center">
<img width="50%" height="692" alt="backendisevil" src="https://github.com/user-attachments/assets/f6e0cfbc-5ff4-4bbb-bee3-e6c024afe42a" />
</p>
<p>I didn't add a user or an organizer because I wasn't sure if we were going to use them at any point.</p>

---

## **Base URL**

```
http://localhost:42067/api
```

---

## **Table of Contents**

1. [Events API](#events-api)
2. [Tickets API](#tickets-api)
3. [Unified Response Format](#unified-response-format)
4. [Swagger UI](#swagger-ui)
5. [Angular Example Usage](#angular-example-usage)
6. [Quick Start](#quick-start)

---

## **Unified Response Format**

All endpoints return this structure:

```json
{
  "success": true,
  "message": "Descriptive message",
  "data": { ... }
}
```

**Errors** also follow the same structure:

```json
{
  "success": false,
  "message": "Error description",
  "data": null
}
```

---

## **Events API**

### 1. Create Event

**POST** `/events`

**Request Body (JSON)**

```json
{
    "title": "Angular Workshop",
    "description": "Hands-on Angular training",
    "date": "2025-12-01",
    "price": 50,
    "location": "Esprit Tunis",
    "organizerId": 1,
    "imageUrl": "https://example.com/image.jpg",
    "nbrPlaces": 30,
    "nbrLikes": 0
}
```

**Response**

```json
{
  "success": true,
  "message": "Event created successfully",
  "data": { "id": 1, "title": "Angular Workshop", ... }
}
```

### 2. Get All Events

**GET** `/events`

**Response**

```json
{
  "success": true,
  "message": "All events retrieved",
  "data": [
    { "id": 1, "title": "Angular Workshop", ... },
    { "id": 2, "title": "Java Seminar", ... }
  ]
}
```

### 3. Get Event by ID

**GET** `/events/{id}`

**Response**

```json
{
  "success": true,
  "message": "Event retrieved",
  "data": { "id": 1, "title": "Angular Workshop", ... }
}
```

### 4. Update Event

**PUT** `/events/{id}`

**Request Body** – same structure as create.

**Response**

```json
{
  "success": true,
  "message": "Event updated successfully",
  "data": { "id": 1, "title": "Angular Workshop Updated", ... }
}
```

### 5. Delete Event

**DELETE** `/events/{id}`

**Response**

```json
{
  "success": true,
  "message": "Event deleted successfully",
  "data": null
}
```

---

## **Tickets API**

### 1. Create Ticket

**POST** `/tickets`

**Request Body**

```json
{
    "price": 50,
    "userId": 2,
    "date": "2025-12-01",
    "valid": true,
    "description": "Front row ticket",
    "eventId": 1
}
```

**Response**

```json
{
  "success": true,
  "message": "Ticket created successfully",
  "data": { "id": 1, "price": 50, "eventId": 1, ... }
}
```

### 2. Get All Tickets

**GET** `/tickets`

**Response**

```json
{
  "success": true,
  "message": "All tickets retrieved",
  "data": [
    { "id": 1, "price": 50, "eventId": 1, ... }
  ]
}
```

### 3. Get Ticket by ID

**GET** `/tickets/{id}`

**Response**

```json
{
  "success": true,
  "message": "Ticket retrieved",
  "data": { "id": 1, "price": 50, "eventId": 1, ... }
}
```

### 4. Update Ticket

**PUT** `/tickets/{id}`

**Request Body** – same as create.

**Response**

```json
{
  "success": true,
  "message": "Ticket updated successfully",
  "data": { "id": 1, "price": 60, "eventId": 1, ... }
}
```

### 5. Delete Ticket

**DELETE** `/tickets/{id}`

**Response**

```json
{
  "success": true,
  "message": "Ticket deleted successfully",
  "data": null
}
```

### 6. Get Tickets by Event

**GET** `/tickets/event/{eventId}`

**Response**

```json
{
  "success": true,
  "message": "Tickets retrieved for event 1",
  "data": [
    { "id": 1, "price": 50, "eventId": 1, ... }
  ]
}
```

---

## **Swagger UI**

Visit:

```
http://localhost:42067/swagger-ui/index.html
```

Interactive documentation and testing for all endpoints.

---

## **Angular Example Usage**

**1. Install HttpClientModule**

```ts
import { HttpClientModule } from '@angular/common/http';
@NgModule({ imports: [ HttpClientModule, ... ] })
export class AppModule { }
```

**2. Event Service (`event.service.ts`)**

```ts
@Injectable({ providedIn: 'root' })
export class EventService {
  private apiUrl = 'http://localhost:42067/api/events';
  constructor(private http: HttpClient) { }
  getAllEvents(): Observable<any> { return this.http.get<any>(this.apiUrl); }
  getEvent(id: number): Observable<any> { return this.http.get<any>(`${this.apiUrl}/${id}`); }
  createEvent(event: any): Observable<any> { return this.http.post<any>(this.apiUrl, event); }
  updateEvent(id: number, event: any): Observable<any> { return this.http.put<any>(`${this.apiUrl}/${id}`, event); }
  deleteEvent(id: number): Observable<any> { return this.http.delete<any>(`${this.apiUrl}/${id}`); }
}
```

**3. Ticket Service (`ticket.service.ts`)**

```ts
@Injectable({ providedIn: 'root' })
export class TicketService {
  private apiUrl = 'http://localhost:42067/api/tickets';
  constructor(private http: HttpClient) { }
  getAllTickets(): Observable<any> { return this.http.get<any>(this.apiUrl); }
  getTicket(id: number): Observable<any> { return this.http.get<any>(`${this.apiUrl}/${id}`); }
  getTicketsByEvent(eventId: number): Observable<any> { return this.http.get<any>(`${this.apiUrl}/event/${eventId}`); }
  createTicket(ticket: any): Observable<any> { return this.http.post<any>(this.apiUrl, ticket); }
  updateTicket(id: number, ticket: any): Observable<any> { return this.http.put<any>(`${this.apiUrl}/${id}`, ticket); }
  deleteTicket(id: number): Observable<any> { return this.http.delete<any>(`${this.apiUrl}/${id}`); }
}
```

---

## **Quick Start**

1. Start **MySQL** in XAMPP, the database name is `eventdb`.
2. Configure `application.properties`:

```properties
spring.application.name=AngularTraining
spring.datasource.url=jdbc:mysql://localhost:3306/eventdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=42067
```

3. Run the backend in IntelliJ on port `42067`.
4. Open Swagger UI at `http://localhost:42067/swagger-ui/index.html`.
5. Use Angular services to call APIs or test directly in Swagger.
