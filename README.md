# Application to track Covid 19 total Cases in India

web application both web and mobile optimized to track and visualize every single corona virus cases in India.
you can see the live demo of application at [COVI19 India DashBoard](https://piyushpriyadarshi.com/covid19/ "COVI19 India DashBoard")

### DashBoard

![Dashboard!](https://github.com/piyushpriyadarshi/Covid19IndiaTracker/blob/master/screenshots/dashboard.JPG "COVI19 India DashBoard")


### Email Subscribe
![Email Subscription!](https://github.com/piyushpriyadarshi/Covid19IndiaTracker/blob/master/screenshots/email.JPG "COVI19 India DashBoard")


### ML Prediction
![Email Subscription!](https://github.com/piyushpriyadarshi/Covid19IndiaTracker/blob/master/screenshots/mlprediction.JPG "COVI19 India DashBoard")


### Features of This Application
1. User can Track the Total No of Cases in India or Sate 
2. User can Track the Total No of Cured in India or Sate 
3. User can Track the Total No of Deaths in India or Sate 
4. User can See the Predicted  Total No of Case 
5. User can See the Predicted  Total No of Cured 
6. User can See the Predicted  Total No of Death 
7. User Can Subscribe to daily mail Updates



#### Steps to Setup this application

##### Step 1. Clone the Repository
        1. git clone https://github.com/piyushpriyadarshi/Covid19IndiaTracker.git

##### Step 2. Setup the Datatbase

        1. Download and configure the Mysql Database 
  
        2. Create a Schema named as covid 
            1. create schema covid;
        3. Run the schema.sql file into newly created covid schema.
        4. Update the Database Details in application.properties 
        
            spring.datasource.url=jdbc:mysql://localhost:3306/covid
            spring.datasource.username=root
            spring.datasource.password=<rootpassword>

[MySql Installer](https://dev.mysql.com/downloads/installer/ "MySql Installler")        

##### Step 3. Setup DataExtraction Engine 

    1. Install Python (if possible install python 3.6 or higher)
    2. Install 3 modules (requests, bs4 and mysql-python-connector) using below command
        1. pip install requests
        2. pip install bs4
        3. pip install mysql-python-connector
     
[Python Installer](https://www.python.org/downloads/ "Python Installler")       
     

##### Step 4. Setup SendGrid Api

    1. Create an acccount on sendgrid,
    2. Create API from Sendgrid account
    3. Update the Email Template  from below link into Dynamic template of your account
    4. update your Api key and Template id into application.properties
    
       mailservice.apikey=##################
       mailservice.templateid=#############
    
    
[Template for Email](/EmailTemplate/EmailTemplate.html "MySql Installler")   
    

  
##### Setup 5. Run the Application

    1. Open the Project in Intellij idea or Eclipse or STS
    2. Run the application
 




