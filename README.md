# User Manager

A multi-microservice application for managing users, built with Java 21, Spring Boot, PostgreSQL, OAuth2 (Auth0), and Docker.

## Microservices

| Service | Port | Description | Endpoints |
|---|---|---|---|
| **user-creation-service** | 3000 | Creates new users | `POST /users` |
| **user-update-service** | 3001 | Updates existing users by ID | `PUT /users/{id}` |
| **user-check-service** | 3002 | Queries users (all or by ID) | `GET /users`, `GET /users/{id}` |

All three services share the same PostgreSQL database and Auth0 security configuration.

Each service also includes:
- **Swagger UI** at `/swagger-ui/index.html` (no auth required)
- **API docs** at `/v3/api-docs`

## HTML Pages

| URL | Service | Description |
|---|---|---|
| `http://localhost:3000/` | Creation | Home page with login |
| `http://localhost:3000/users/create` | Creation | Form to create a new user |
| `http://localhost:3000/profile` | Creation | View authenticated user profile |
| `http://localhost:3001/` | Update | List all users with edit links |
| `http://localhost:3001/users/{id}/edit` | Update | Form to edit a user |
| `http://localhost:3002/` | Check | List all users |
| `http://localhost:3002/view/{id}` | Check | View user details |
| `http://localhost:3002/search` | Check | Search user by ID |

## Prerequisites

- Docker and Docker Compose

## Getting Started

### 1. Configure environment variables

Create a `.env` file in the project root with your Auth0 credentials:

```env
OKTA_OAUTH2_ISSUER=https://your-tenant.us.auth0.com/
OKTA_OAUTH2_CLIENT_ID=your-client-id
OKTA_OAUTH2_CLIENT_SECRET=your-client-secret
```

### 2. Start all services

```bash
docker compose build --no-cache && docker compose up
```

This starts:
- PostgreSQL on port 5432
- User Creation Service on port 3000
- User Update Service on port 3001
- User Check Service on port 3002

To start a single service (postgres starts automatically):

```bash
docker compose up --build user-check-service
```

### 3. Login

Open any service in the browser (e.g. `http://localhost:3000`) and click **Login**. You will be redirected to Auth0.

Use the following test credentials:

- **Email:** `user@example.com`
- **Password:** `stringTest1234!`

After login you will be redirected back to the application.

## Tech Stack

- Java 21
- Spring Boot 3.4.1
- Gradle (Groovy DSL)
- PostgreSQL 16
- OAuth2 / Auth0 (via Okta Spring Boot Starter)
- Thymeleaf
- Springdoc OpenAPI (Swagger UI)
- Docker
