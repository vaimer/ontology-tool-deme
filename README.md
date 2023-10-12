# Ontology tool demo
-------

### How to launch application
1. To start application run `docker-compose up -d`. 
2. Wait 20-30 sec until backend application start. 
3. Open webpage in the browser `http://localhost:8080/`. You should see the interface below
![img.png](images/img.png)
4. Feel free to search and create ontologies.

### How to launch test:
Tests (`src/test/java/com/example/ontology/tool/demo`) contain one integration test which runs the whole application context and makes endToEnd test.
Unfortunately, I didn't manage to set up running of Mongo DB test container(app had error `Connection refused` when test container is up).
To run all tests we have to have running Mongo DB, following instruction could help solve this issue.
1. Run command `cd ~/ontology-tool-deme/docker/dev` to move to the folder with MongoDb container configuration
2. Run `docker-compose up -d`.
3. Return to the root folder and run command `./mvnw '-Dtest=com.example.ontology.tool.demo.**' test` or use any other approach to run the test as you wish

## Application overview

### Backend
This is Spring Boot Java application, which contains two endpoints implementing business logic and one endpoint for static files distribution.
Maven is used as a build automation tool.

Retrieve ontology endpoint. Call example:
```shell
curl --location --request GET 'http://localhost:8080/ontology/{ontologyId}'
```

Save ontology endpoint. Call example:
```shell
curl --location --request POST 'http://localhost:8080/ontology/' \
--header 'Content-Type: application/json' \
--data '{"id": "id", "title": "title", "description": "description", "definitionProperties": ["https://example.com"], "synonymProperties": ["https://example.com"]}'
```

Backend contains two unit tests `OntologyServiceTest.class`, `OLSSearchServiceTest.class`, and one integration test `OntologyControllerIT.class`, which helps to test the main part of the application.
I didn't add any unit test for Controller classes, because controllers are simple and are fully covered by the integration test, which tests end to end work of the application. 
The bad thing is that integration test should be environment agnostic, due to time limitation, I haven't manged to solve all the issues with mongo test container. 

Also, the simple variant of ["Ports and Adapters architecture"](https://medium.com/the-software-architecture-chronicles/ports-adapters-architecture-d19f2d476eca) was implemented, it helps split models between different layers of application like service, database, API model.

JPA approach without any queries and templates was chosen to avoid over-complication, for the current task no need for any complicated queries, base stuff works fine. 

### Frontend
Frontend was created using React(and hooks from React for state management), Typescript, and Webpack for building the project.
Also added standard configuration files for such projects. nothing special.

To start the application in dev mode run `npm i && npm run start`

The current backend is also used for static file distribution. To achieve it, I used special maven plugins, which help to run `npm` commands and copy bundle.js to the built project to `target/build/static/js/bundle.js`.
`index.html` locates in `templates` folder, and is returned by the backend if `/` url is requested.

**Motivation for such an approach**: it helps me be focused on one project and implementation of test tasks, and simplify infrastructure issues, like deploying a separate frontend instance and organisation communication between frontend and backend applications.  
It brings some complication in the build process, but simplifies the docker configuration and avoids using a separate backend for static file distribution(but it also can be `nginx` without special logic).
It was done only to speed up test task implementation.

Consequences of such an approach:
* Every change of the frontend triggers the redeployment of back end. They bound with each other over.
* Fail intolerance, if a bug exists on frontend or backend, it requires rebuilding/recreate both parts, and produce failing for whole application, not just a part of it.
* Additional effort to managing frontend and backend teams/people. They can not work independently, need to sync PR, merge conflicts, releases.

How to solve it, some steps:
1. Use separate project for frontend, and choose one of the approaches to file distribution (nginx or NodeJs)
2. Pack application to separate Docker image, and run frontend project container with backend container separately
3. Set up docker network for containers communication.

Benefits, after solving the issue: 
* Teams can develop projects separately
* Separate releases
* Fail tolerance, if the problem is in some of the services, it doesn't affect other parts of the systems
