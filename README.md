# CoronavirusApp
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



