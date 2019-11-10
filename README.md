# Installing & Running Code

## Prerequisites:

#### Ensure jdk 1.8 and maven 3.6.1 or above is set in your class path
#### Clone the project to your local machine 


## Running the code on Windows(recomended):

#### Open command prompt navigate to checkout-price-calculator
#### > mvn clean install

#### Run the rest server (enhancement to the task)
#### > java -jar target/flight-aggregation-app-0.0.1-SNAPSHOT.jar

#### Use http://localhost:8080/swagger-ui.html to find the api exposed

# Task side notes
#### The api responds with list of flight details filtered on the basis of request body.
#### The response is sorted in acsending order of time of departure