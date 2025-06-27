# Backend for Frontend application

Application to sign in and forward access tokens to REST APIs.

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

You need to set the environment variables below:

- **TASK_MANAGER_BFF_APP_PORT:** HTTP port (ex: 4201);
- **TASK_MANAGER_BFF_APP_URL:** Self URL using reverse proxy (ex: http://192.168.0.0:4001/bff);
- **TASK_MANAGER_BFF_APP_ISSUER_URL:** Authorization Server issuer URL (ex: http://192.168.0.0:4001/auth/realms/task-manager);
- **TASK_MANAGER_BFF_APP_AUTH_SERVER_CLIENT_ID:** Authorization Server confidential client ID (ex: task-manager-confidential);
- **TASK_MANAGER_BFF_APP_AUTH_SERVER_CLIENT_SECRET:** Authorization Server confidential client secret (ex: CONFIDENTIAL_CLIENT_SECRET);
- **TASK_MANAGER_BFF_APP_PROFILES_API_URL:** Profiles API URL (ex: http://192.168.0.0:4401);
- **TASK_MANAGER_BFF_APP_TASKS_API_URL:** Website URL (ex: http://192.168.0.0:4501).

## Run

### DevOps

To start the application You just need to run the command below inside this folder:

```shell
docker compose up -d
```

To stop the application You just need to run the command below inside this folder:

```shell
docker compose down
```
