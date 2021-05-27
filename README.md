This is a Netbeans project that reads daily option settlement reports from the CMEgroup website, presented in JSON format and adds them to separate csv files.

Installation and launch

1) Just clone the given project into your Netbeans projects folder:

git clone https://github.com/mmmoguschiy/cmejsonsettleparser

2) Add the project to Netbeans.
3) Change the path to the CME directory. To do this, replace the line by substituting yours instead of path_to_cme_dir:

FileWriter writer = new FileWriter ("path_to_cme_dir" + para + "\\" + year + "\\" + para + "_" + "JS_" + contr + "_settle_" + timePoint + ".csv");

When installing on linux, adjust the path accordingly.

4) Change the date of the reports (usually yesterday's business day) by substituting the appropriate values ​​for the day, month and year:

String month = "05";
String day = "25";
String [] year = {"2021"};

5) Run the project. 
