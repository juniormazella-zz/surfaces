# Build Informations
 [![Build Status](https://travis-ci.com/juniormazella/surfaces.svg?token=HbxTpeT5z47326kZsR5c&branch=master)](https://travis-ci.com/juniormazella/surfaces) [![codecov](https://codecov.io/gh/juniormazella/surfaces/branch/master/graph/badge.svg)](https://codecov.io/gh/juniormazella/surfaces) ![Heroku](http://heroku-badge.herokuapp.com/?app=surfaces-crxmarkets&style=flat&svg=1&root=/actuator/health) 

# About Solution / Complexity
According to the guideline written in the email document "... layered, testable, implantable, documented ...", my decision was to develop an application following the hexagonal architectural style, because it is a versatile architectural style , and using DDD as design, even if the problem is not of high complexity, using DDD we can leave the application with very well defined layers in: infrastructure, application, domain.

Our application has only an aggregate root with the name Surface, with a single bounding context, which given a surface configuration, it knows the method and rule to calculate the volume of water after the storm, as described in the document sent by email.

In the Surface class there is the *getVolumeAfterFlood()* method, and this method is responsible for doing the iteration and calculating the requested volume.

The proposed solution consists of sweeping the array of the ends to a point of encounter, and when the value of the is on the right side, the counter of the direct position is decremented and when the calculation occurs on the left side, the counter is incremented, all with the aim of reading from the extremities until the moment the *"pointers"* they encounter.

In order to control the iteration and also to optimize the algorithm, it was necessary to use 5 variables, which change with each iteration, 2 variables to square the values ​​of the indexes, right and left, and also 2 variables that will always have the last highest value on each side of the iteration.

In the structure of the algorithm it is defined that, if the value on the left side is smaller than the one on the right side, the calculation will occur on the left side, a check will be made to know what the highest value is, the previously accumulated value or the current value, if the previous value is smaller the current value will be assigned instead of the previous one, and the calculation will be done looking at the highest value found by subtracting the current value, given the result it will be accumulated and soon afterwards the counter will be incremented towards the center of the array, in the next iteration if the value on the left is greater than the value on the left, it means that the iteration will occur on the other side of the array, except that instead of incrementing the counter, it will be decremented, again in order to center of the array.

The last variable that assists the interaction is the variable that will accumulate the volume value of the current position of the index, and it is not necessary to go through the array again to calculate the volume.

At the end of the method a Volume object that contains the value of the summation object is returned, this object is just a value object.

The complexity of time of the written algorithm is O (n), because it is linear and also because it does not have multiple iterations, and we can also define the complexity of the space of O (1), because only a constant space is required for the variables that control the iteration.

I confess that I spent some time trying to improve the written algorithm, but that was the best I could do.

I chose to write integration tests because it proved more efficient in our scenario, because it is possible to test all the layers of the application, simulating behavior through calls at the endpoint.

It has also been implemented a *RestControllerAdvice* aspect so Spring to handle exceptions thrown by the application, in the application layer it is important that the exported abstractions extend *SurfaceException*, because this exception is the root of the business exceptions. In the class *DefaultRestControllerAdvice* has the *handleException* method that will find who is responsible for translating the thrown exception.

> "An idempotent HTTP method is an HTTP method that can be called many times without different outcomes. It would not matter if the method is called only once, or ten times over. The result should be the same. It essentially means that the result of a successfully performed request is independent of the number of times it is executed. For example, in arithmetic, adding zero to a number is idempotent operation.<br>https://restfulapi.net/idempotent-rest-apis/"

I added Travis(https://travis-ci.com/) to do the *continuous integration* and *continuous delivery*, for this was configured a pipeline where the build process is done, then the coverage report is uploaded to CodeCov(https://codecov.io) and only then deploy application in *Heroku*.

The application is available through the URL http://surfaces-crxmarkets.herokuapp.com (delays may occur in the first request for free tier use), examples of requests to be made:

    https://surfaces-crxmarkets.herokuapp.com/surfaces/volumes?configuration=3,1,1,3,4,1,1,4
    https://surfaces-crxmarkets.herokuapp.com/surfaces/volumes?configuration=9,8,7,6,5,4,3,2,1
    https://surfaces-crxmarkets.herokuapp.com/surfaces/volumes?configuration=3,2,4,1,2
    https://surfaces-crxmarkets.herokuapp.com/surfaces/volumes?configuration=4,1,1,0,2,3

# How to Run The Project
To run the project with the updated source code, run the command: 

    mvn clean spring-boot:run

# How to Build
To build the project and generate the Spring Boot JAR without running the tests, run the command:

    mvn clean package -DskipTests
    
so it is possible to run the JAR through the command: 
    
    java -jar target/surfaces.jar
    
if you want to run the tests together with the build process you need to leave out the 

    '-DskipTests'

# How to Run Tests
As I explained above, integration tests were written with some predefined scenarios, the CodeCov plugin was used to generate the test coverage report, it is possible to run the tests through the command: 

    mvn test

# How to Build Docker Image
To generate an image docker of the generated jar was added a maven plugin called Spotify, you can see more information in the link <a href="https://github.com/spotify/docker-maven-plugin">spotify/docker-maven-plugin</a>, and for this to happen you should run the command: 

    mvn clean package docker:build
    
at the end of the process the image should be available in the local docker repository, to run this image through docker you should run the command: 

    docker run --name surfaces -p 8080:8080 surfaces:1.0
