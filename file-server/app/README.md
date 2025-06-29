# File Server application

Application to store files.

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

You need to set the environment variables below:

- **TASK_MANAGER_FILE_SERVER_APP_ADMIN_USER:** Admin username (ex: http://192.168.0.0:4001/bff);
- **TASK_MANAGER_FILE_SERVER_APP_ADMIN_PASS:** Admin password (ex: http://192.168.0.0:4001/auth/realms/task-manager);
- **TASK_MANAGER_FILE_SERVER_APP_PORT:** HTTP port (ex: 4301);
- **TASK_MANAGER_FILE_SERVER_APP_UI_PORT:** UI HTTP port (ex: 4302).

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
