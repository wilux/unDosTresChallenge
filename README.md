# unDosTresChallenge
# Automation Engineer Proficiency Exercise:


#Overview :

The purpose of this exercise is to assess the candidate automation developer’s technical proficiency, coding knowledge and style. The exercise involves building a basic automation framework for web application. The exercise will be evaluated on coding style, understanding of programming concepts, choice of techniques and quality of the final product.

#Specification :
 Create a basic automation framework which supports multiple browsers
 Automate Undostres web application using any of the automation tools(selenium preferable)  in any of the programming languages.
Go to http://xxxxxxxxxx Under recarga celular select operator as Telcel from Operador field. Give this number 8465433546 under Numero de celular field and under Monto de Recarga select 10 dollar normal recharge and click on siguiente. Verify if the user is able to reach the next screen(Payment screen) or not.
On payment screen click on Tarjeta enter the following details under “Usar nueva tarjeta”: Card number:4111111111111111 ,month=11,date=25,cvv=111 and under correo electronico field give email id as test@test.com.
Click on Pagar con Tarjeta to do the recharge 
A popup will appear. Enter the following email xxxxxxx@gmail.com and password “automationUDT123”
Verify if the user gets a success message and recharge gets successful.

#Additional Guidelines:
1. Comment your code where necessary. 
2. Polish your code as much as possible - we expect professional, production quality code.
3. Feel free to use best coding practices .
4. Please commit your code on git and share the url.

#For run this automation tests:

## Install 

* Recommend IDE [IntelliJ](https://www.jetbrains.com/es-es/idea/)
* Open project from source control version link
* Update maven dependences

## Run Tests

* From terminal in path project run: mvn test
