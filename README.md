# Spring Boot RESTful Web Service (v2)

![Spring Boot RESTful logo](https://github.com/Pio-Trek/Spring-Boot-Rest-Service/blob/master/logo.png)

This is a Spring Boot Maven demo app showing how to build a simple RESTful Web Service connected with H2 database and basic validation.

Version 2 contains:
- customise JSON output with Jackson
- JPA query methods
- use of Java Persistence Query Language
- use of native SQL in the JPA
                  

| HTTP method | URI |Description |
| --- | --- | --- |
| POST | `/api/v2/movies` | Create a new movie |
| POST | `/api/v2/movies/(movie_id)/reviews` | Create a review to the movie |
| GET | `/api/v2/movies` | Return a list of movies |
| GET | `/api/v2/movies/reviews` | Return a list of movies with reviews |
| GET | `/api/v2/movies/(movie_id)` | Return a specific movie with reviews |
| GET | `/api/v2/movies?search=(title)` | Return a list movies with given title (ignore case) |
| GET | `/api/v2/movies?year=(year)` | Return a list of movies released in the given year |
| GET | `/api/v2/movies?year>=(year)` | Return a list of movies released in the grater than or equal given year |
| GET | `/api/v2/movies?year<=(year)` | Return a list of movies released in the less than or equal given year |
| GET | `/api/v2/movies/count` | Return the number of movies in the database |
| PUT | `/api/v2/movies/(movie_id)` | Update movie |
| PUT | `/api/v2/movies/(movie_id)/reviews/(review_id)` | Update review of the movie |
| DELETE | `/api/v2/movies/(movie_id)` | Remove movie |
| DELETE | `/api/v2/movies/(movie_id)/reviews/(review_id)` | Remove movie review |


## Pre-requisites

- Java SE Development Kit 8
- Maven 3.0+
- H2 In-Memory Database _(can work with any other relational database)_

## Getting Started

Import the Maven project straight to your Java IDE:
- Intellij IDEA
- Spring Tool Suite (STS)
- Eclipse

_(OPTIONAL) To work with other RDBMS you need to configure the project 'application.properties' file match to your database URL, username, password and add a required Maven dependency._

# License
Copyright 2018 Piotr Przechodzki

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
