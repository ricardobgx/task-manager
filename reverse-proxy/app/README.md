# Reverse Proxy application

Application to route requests.

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

You need to set the environment variables below:

- **TASK_MANAGER_REVERSE_PROXY_APP_PORT:** HTTP port (ex: 4001);
- **TASK_MANAGER_REVERSE_PROXY_APP_AUTH_SERVER_URL:** Authorization Server URL (ex: http://192.168.0.0:4101);
- **TASK_MANAGER_REVERSE_PROXY_APP_BFF_URL:** Backend for Frontend URL (ex: http://192.168.0.0:4201);
- **TASK_MANAGER_REVERSE_PROXY_APP_WEB_URL:** Website URL (ex: http://192.168.0.0:4301).

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
