This java application helps to sort Csv file1 data column based on file2 column data, 
it will work dynamically based on the input passed through arguments while running the main java. 

To Run this Java application need follow below steps.
1) download the project from Git.
2) import into any IDE, i used Eclipse 
3) I have created CsvSortFileMain.java which will act as main file to run this program. 
4)Run as configuration and pass argument input as ./file1.csv b ./file2.csv d
5)file1.csv and file2.csv is kept in resource folder in the project. 
6) it is mandatory to pass 4 arguments to work this program, else it will through an exception. 
7) Argument 1 and 3 can be dynamic argument which column need to sort. we can pass based on the column exists in the csv file.
8)bo package is used to keep business object, business logic has been written.
9)utill package is to keep reusable methods.  


Limitations: 
Parameter should be in the same order like this ./file1.csv b ./file2.csv d argument 1 and 3 can be dynamic but should pass in the same order.
 
 


