# Profiles database

Database to store profiles.

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

You need to set the environment variables below:

- **TASK_MANAGER_PROFILES_DB_ADMIN_USER:** Admin user (ex: admin);
- **TASK_MANAGER_PROFILES_DB_ADMIN_PASS:** Admin password (ex: admin);
- **TASK_MANAGER_PROFILES_DB_DB_NAME:** Database name (ex: auth_server);
- **TASK_MANAGER_PROFILES_DB_PORT:** Connection port (ex: 4002);

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
