### Run with Docker
```bash
    docker build -t nuban-java .
```

```bash
    docker run -p 8080:8080 nuban-java
```

### URLS
GET http://localhost:8080/:accountNumber/banks 

POST http://localhost:8080/banks
Data
```bash
    {
        "accountNumber": "1111111111"
    }
```