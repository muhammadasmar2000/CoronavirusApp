# CoronavirusApp
To use this app, run the code and being the app at the login screen. Enter in your login credentials to begin using the app. If you do not have an account, click the register button on the bottom of the screen to go to a registration screen. To create an account, enter a name, username and password. Then, click create account and you will automatically be able to use the application. 

To view the coronavirus results for a country, enter the name of a country in the text field that reads "Enter country." If you wish to view results of that country, click on "View Chart."

To view popular countries, click on the menu options in the top right corner and click the options that reads "View Favorites." In this screen, you can click on popular countries and view the highest searched countries relative to the coronavirus. Click on any of the countries to view virus information on that country. 

To view recent news on the novel coronavirus, in the same menu in the top right corner, click on "View Recent News." This will take you to a new screen where there are three articles. Each article with have its title, author and article description on the screen. 

Remember when done using the app, go to the menu and click on "Logout" to log out of your account. If you wish to automatically be signed back into your account when you log back into the app, do not log out of your account when exiting the application.

Commit 1: Login and Account Registration created.

Commit 3: Coronavirus xml file and java file have been created

Commit 5: Coronavirus search implemented

Commit 6: Chart added to project

Commit 7: Favorites Screen and FavoritesResults implemented

Commit 8: Recent News functionality added

For commit 1, Google firebase is used to allow the user to create and register for an account. A progress dialog is used to notify that their credentials for their account is being checked when registering for an account or logging into the app with an existing account. 
The login screen will be used as the home screen for the app. If the user is already logged in, the user will be automatically taken to the coronavirus search activity.

For commit 3, the coronavirus search has been implemented that includes asking the user to enter the name of a country into an edit text that will have the text gathered to perform a get request for coronavirus data for that country. The results are displayed into another activity where the user can click on "view chart" that will open up another activity. The chart activity will be implemented later. 

For Commit 5, the activity used to display the results of the coronavirus search has been implemented. This involves parsing the JSON object into multiple integer data types. The data is then casted to a string and displayed into the results text view on the display screen.

For Commit 6, the chart is added to the project that involves adding all the chart.js files listed on the chart.js website, such as the requred css files and bundles. The cases html file is later added to the android studio project that will display all the results for coronavirus data for a country. 



