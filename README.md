# Task manager

Manage tasks.

## Modules

- [x] Authorization Server
  - [x] Application
  - [x] Database
- [x] Backend for Frontend
  - [x] Application
- [x] Reverse Proxy
  - [x] Application
- [x] File Server
  - [x] Application
- [x] Profiles
  - [x] API
  - [x] Database
- [ ] Tasks
  - [ ] API
  - [ ] Database
- [ ] Web
  - [ ] Application

## Configuration

### Requirements

You need to install to following tools in your machine:

- Docker
- Docker Compose

### Environment variables

Please refer to each module's README file to set the environment variables correctly.

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

## Ports mapping

Below You'll find the ports mapping of the project:

| Module               | Ports                 |
| -------------------- | --------------------- |
| Reverse Proxy        | 4001                  |
| Authorization Server | 4101                  |
| Backend for Frontend | 4201                  |
| File Server          | 4301 (api), 4302 (ui) |
| Profiles             | 4401 (api), 4402 (db) |
| Tasks                | 4501 (api), 4502 (db) |
| Web                  | 4601                  |
