### FakeStore Service

This service helps manage a store through a REST API. It offers two ways to manage the store:

1. **FakeStore API**: This is a third-party service.
2. **PostgreSQL Database**: You can use your own database.

### How to Run the Service

Before starting the service, follow these steps:

- **Configure PostgreSQL**: Add your database details in the `application.properties` file.
  *OR*
- **Use Docker**: If you prefer, install Docker and run this command to set up a PostgreSQL container:
```
docker compose up
```

  
Once your database is set up, you can start the service.
