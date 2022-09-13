# Jonas Gredig Coding Challenge
## Requirements
The original requirements are in the codingchallenge_smarties.md file in the root directory. 

The assumption was made that the same dialogId can be used for multiple entries, this is why technicalId was introduced.
## Setup

### Database Setup
To keep the setup simple an in-memory H2 Database was used. It is build in to the project and starts automatically when the Spring Boot Application is starting. 

The schema with the empty tables is created from scratch every time the application starts. The Setup script can be found in the 'resources' folder. (File: data.sql) 


Web UI for H2 DB:
```URL
http://localhost:8080/h2-console
Username: jonas
Password: c0d1ngCh4ll3ng32022!!
```

### Build & Run
The Project is a simple Spring Boot Application. Maven is used to build the project, just use: 
```bash
mvn spring-boot:run
```

The API should be available under:
```URL
http://localhost:8080/
```

### PostMan Collection
To make local testing of the endpoints easier I created  a Postman Collection with Example requests. It's saved as a JSON in the postman folder in the root directory.

## Potential Improvements
### Unit Testing
The Unit tests were kept simple, there is potential to test more functionality. E.g. testing for pagination and optional filter criteria in: 

```GET /data/(?language=${language}|customerId=${customerId})```

### Input Validation
Input Validation was not implemented

### Add Business Logic Layer 
At the moment there is not that much business logic, but it would make sense to add a layer between controller and model to separate the business logic. E.g. this code snipped from DataController.getData() would go into the business logic layer:
```java
//create Filter Critera
Dialog dialog = new Dialog();
if (customerId != null) {
    dialog.setCustomer(customerRepository.findById(customerId).orElseThrow());
}
dialog.setConversationLanguage(language);
dialog.setConsent(true);
```

### Add Database Server
At the moment the data is stored in an in-memory H2 database. This should be improved by changing this database to a dedicated system.