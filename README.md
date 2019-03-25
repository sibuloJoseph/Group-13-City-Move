Info:
- All files for demo 3 are found in the Demo-3 branch of this repository (City-Move).

- To run the text-based version:
- All .java files need to be compiled, followed by running the Survey Class.
- To do so: compile the code by typing in ---> javac *.java
- To run the Survey class, type in ---> java Survey
- When running the Survey class for the first time, enter a username and password you will use for each time you rerun the program.
- In order to do this, type in 'S' (to sign-up). Then type in a username and push enter. Then type in a password and push enter.
- If account creation is succesful, you will be directed to the main menu.
- If you have already ran the survey class once and have created a username and password, type in 'L' (to log-in).
- To run the survey, type in 'S'.

- To run the GUI based version: 
- All .java files need to be compiled, followed by running the Gui Class.
- To do so: change the directory of the command prompt to be in the same location as where all the files are located, followed by compiling the code by typing in ---> javac *.java (Compilation will be successful if no error messages pop up after compiling)
- To run the Gui class, type in ---> java Gui
- When running the Gui class for the first time, enter a username and password you will use for each time you rerun the program.
- In order to do this, ---> Enter your username and password in the textfields, and click "Signup"
- If account creation is succesful, it will prompt you to click "Login" (or hit the enter key) and will direct you to the main menu.
- If you have already ran the Gui class once and have created a username and password, enter your username and password and click "Login" (or hit the enter key), which will direct you to the main menu.
- To run the survey in the Gui, click on "Do Survey" in the main menu.

- The following files contain JUNIT test files: StudySpotTest.java and StudySpotListTest.java. These are located in the folder called "unitTests"
- To compile the files use the command: javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java 
(Note use semicolons insteads of colons if using windows machine) 
- To run the appropiate test file use the command java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore <Testfile> ex: StudySpotTest 
  
- All map assets used in this demo is property of Google Maps.
- The University of Calgary Logo is property of the University the of Calgary.
