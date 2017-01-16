<h1>Cyber Security Base -project</h1>

This project is part of F-Secure Security Base studies. More info of the course can be found <a href="http://mooc.fi/courses/2016/cybersecurity/">Here</a>
<br>
The assigment was "to create a web application that has at least five different flaws from the OWASP top ten list (https://www.owasp.org/index.php/Top_10_2013-Top_10). Starter code for the project is provided on Github at https://github.com/cybersecuritybase/cybersecuritybase-project.

You will then write a brief (1000 words) report that outlines how the flaws can be first identified and then fixed. For the identification process, we suggest that you use tools that have been used in the course, such as Owasp ZAP. "

<br>
The raport of five weird mistakes:

<h2>1. Security Misconfiguration (Flaw no 5)</h2>

In SecurityConfigure HttpSecurity the http.headers are disabled. OWASP Zap gives "X-Frame-Options Header Not Set" among other header errors.

Fixing is easy, just remove the header disabling option as by default it is on. 

<br>
<h2>2. Missing Function Level Access Control (Flaw no 7)</h2>

After signing up, the user is redirected to done page, from which he can enter to event page to view the participants. The event page is not authenticated, so you can enter and view the participants just by going to /events page.

This can be fixed by authenticating the requests, like with httpSession. 


<h2>3. Sensitive Data Exposure (Flaw no 6)</h2>

The event page shows the information (name and address) of the previous person who signed up. Thus if you go straight to events page without entering a new entry, you can see the previous persons address if using the same browser.

Fixing the rather poor use of httpSessions will fix the problem.


<h2>4. Cross-Site Scripting (Flaw no 3)</h2>

The event page shows all participants of the event. How ever the form allows unescaped text and thus an XSS attack. For example the evil cracker can write "<script>alert("This event sucks!")</script>" to the form, and for the shock of everyone else the event page alerts it for everyone.

This can be fixed by using the Thymeleaf text tag instead of utext.


<h2>5. Insecure Direct Object References (Flaw no 4)</h2>

The event page has an admin functionality which can be accessed by logging in with the form. However, the form uses GET instead of POST and a false entry makes the url show the parameter boolean "admin=false". A hacker can notice that and change the parameter to true to log in just by modifying the url.

This can be fixed by using POST instead of GET and not using the redirects in the EventControllers method adminLogin, which needs quite a lot fixing.
