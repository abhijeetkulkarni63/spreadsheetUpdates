Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean -Darg0=input.txt -Darg1=output.txt -Darg2=0

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml -Darg0=input.txt -Darg1=output.txt -Darg2=0

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=0

-----------------------------------------------------------------------

## To create tarball for submission
tar -cvf abhijeet_kulkarni_assign_3.tar abhijeet_kulkarni_assign_3/
gzip abhijeet_kulkarni_assign_3.tar

## To untar the tarball:
tar -zxvf abhijeet_kulkarni_assign_3.tar.gz

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.”

[Date: ] -- 04/03/2017

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

I have used hashmap to store all the cells in spreadsheet. As storing and retrieving
from hashmap has O(1) complexity, hashmap is the perfect match to store cells.

I have recursively checked list of observers for given subject to find if subject is
also an observer for one of its observers.
-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

