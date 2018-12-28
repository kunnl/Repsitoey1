Feature: Login
@smoketest
Scenario: validate site launching
Given launch site using "chrome"
Then  title contains "free sms"
And close site

Scenario Outline: validate login operation
Given launch site using "chrome"
When Enter mobile number as"<x>"
And  Enter password as"<y>"
And  click login
Then validate output for criteria"<z>"
And  close site
Examples:
|     x    |    y   |    z       |
|8817702181|ankeesms|all_valid   | 
|          |ankeesms|mbno_blank  |
|8817702181|        |pwd_blank   | 
|8811770218|ankeesms|mbno_invalid|
|8817702181|ankesms |pwd_invalid |