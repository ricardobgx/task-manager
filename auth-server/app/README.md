# Authorization Server application

UI to access Authorization Server resources.

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

You need to set the environment variables below:

- **TASK_MANAGER_AUTH_SERVER_APP_ADMIN_USER:** Admin user (ex: admin);
- **TASK_MANAGER_AUTH_SERVER_APP_ADMIN_USER:** Admin password (ex: admin);
- **TASK_MANAGER_AUTH_SERVER_APP_PORT:** HTTP port (ex: 4001);
- **TASK_MANAGER_AUTH_SERVER_APP_DB_URL:** Database JDBC URL (ex: jdbc:postgresql://auth-server-db/auth_server);
- **TASK_MANAGER_AUTH_SERVER_APP_DB_USER:** Database user (ex: admin);
- **TASK_MANAGER_AUTH_SERVER_APP_DB_PASS:** Database password (ex: admin).

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
