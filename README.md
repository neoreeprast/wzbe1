## How to run in local
```
docker-compose up
```
this will create dockerized postgres on port 54323

```
mvn spring-boot:run
```
this will run the backend at port 8585

## Provided user
```
User: hradmin
Password: password
```
can only access /employees api
```
User: salesmanager
Password: password
```
can access /employees and /sales api

## Postman collection
postman collection file available at "Waizly Test.postman_collection.json"