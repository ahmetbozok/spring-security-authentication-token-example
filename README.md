# spring-security-authentication-token-example
This is a basic example to authenticate with username and password and generate a token which related with provided user. After generate a token all request will use this token for authentication.

## Run
  * mvn clean
  * mvn intsall
  * mvn spring-boot:run

## API information
First of all you should have some users which are defined in your user db. You can add some users to try authentication token and authorization process from our api but you should disable ".antMatchers("/users/add").hasRole("ADMIN")" and enable "web.ignoring().antMatchers("/users/add");" code blocks in SecurityConfig class to access link to add a new user.

After you added some users which are had different roles but at least one of them should have an ADMIN role to try AUTHORIZATION process, you should reset differences in SecurityConfig. And then run again application to try authentication token and authorization.

* /auth/token api will be use to create a token for provided username and password to access other API endpoints. And after created a token you shoulld add this token to other request header with "X-Access-Token" parameter name.
* /users/add api will be use just ADMIN users after generate a token with username and password credentials.
* /users endpoint will be access for all users type which have a valid token
