# SoftUni-Project-Defense
This is my final SoftUni Project from the Java Web Module - Spring Advanced. It consists of two separate applications - a client and a server (Restful API).
> A [JWT Token](https://jwt.io/) implementaion is used for authentication. 

# Gimy

Project **Gimy** is about working out. Customers can buy a workout program or hire an online coach to help them on their journey.

Guest users have access to the home, login and register pages. They can also browse through all of the avaible workout programs.

Logged in users can:
- See the home page (slightly different)
- Browse through the available workout programs and purchase as many as they wish;
- Browse through the available online coaches and hire one of them, whom they can later change;
- Read reviews of a specific program/coach;
- Leave a review for a workout program they have purchased;
- See the workout programs they have already purchased and the workout details (training splits, exercises, rest periods, etc...);

Admin users can:
- Create new workout programs;
- Edit existing workout programs;
- Delete existing workout programs;

The navigation menu changes dynamically according to the authentication status (whether logged in or not).
The UX is flawless. Clean and good-looking design. Added error handling - messages on invalid input data, failed HTTP requests and so on. 

## Instructions to run the project:

### 1) Start the server (Spring) - port 80, by default
- Once you clone/download the project, open the `server` directory (server's root) with 'IntelliJ Idea'
- Find the project's configuration file (`application.yaml`) inside `src/main/resources/` and set the three environment variables - `SPRING_DB_USER`, `SPRING_DB_PASS` and `SPRING_JWT_SECRET`, where the first two are the credentials to connect to a local 'MariaDB' server, while the last one can be any random sequence of **at least 64 characters** (256 bits)
- From within 'IntelliJ Idea', find the `GimyApiApplication` java file inside `src/main/java/bg/softuni/GimyApi/`. Right click it and find the `Run` command.

To start the project from a terminal using 'Gradle', open one inside the `server` directory (root) and enter `/gradlew bootRun` for *Windows* and `./gralew bootRun` for *Linux* and *MacOS*. **Remember** to set the environment variables (explanation above).

### 2) Start the client app (Angular.js) - port 4200, by default
- Open a terminal inside `client/project` directory (client's root)
- Download all the vital modules and libraries with `npm i`
- Start the 'Angular' app with `npm start`

Once both servers are running, you can go to `http://localhost:4200/` in the browser (by default), create an account and enjoy the experience.
