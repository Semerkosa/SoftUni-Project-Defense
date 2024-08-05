# Gimy

Project **Gimy** is about working out. Customers can buy a workout program or hire an online coach to help them on their journey.

Guest users have access to the home, login and register pages. They can also browse through the avaible programs and read the reviews of the satisfied customers, who have previously purchased a given workout program.

Logged in users can:
- See the home page (slightly different)
- Browse through the available workout programs and purchase as many as they wish;
- Browse through the available online coaches and hire one;
- Read reviews of a specific program/coach;
- See the workout programs they have already purchased and the workout details (training splits, exercises, rest periods, etc...);

Admin users can:
- Create new workout programs;
- Edit existing workout programs;
- Delete existing workout programs;

The navigation menu changes dynamically according to the authentication status (whether loggin in or not).
The UX is flawless. Clean and good-looking design. Added error handling - messages (or window alerts) on invalid input data, failed HTTP requests and so on. 

## Instructions to run the project:
> [Json Server](https://www.npmjs.com/package/json-server) and [Json Server Auth](https://www.npmjs.com/package/json-server-auth) are used to replicate a real backend server.

- Clone the project, go into the root directory (named 'project') and open two separate terminals;
- Download all the vital modules with `npm i`
- Download both json servers with `npm i -g json-server` and `npm i -g json-server-auth`
- Start the json server (by default on port 5000) with `npm run json-server`
- Start the Angular app with `ng serve`
