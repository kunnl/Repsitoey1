Feature: Logout
Scenario: validate logout operation
Given launch site using "chrome"
When  Do login with valid data
|   mbno   ||  pwd   |
|8817702181||ankeesms|
And Do logout
Then Home page will be Reopend
And close site
