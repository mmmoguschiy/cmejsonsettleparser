This is a Netbeans project that reads daily settlements reports from the CMEgroup website, presented in JSON format and adds them to separate csv files.

Installation and launch

1) Just clone the given project into your Netbeans projects folder:

git clone https://github.com/mmmoguschiy/cmejsonsettleparser

2) Add the project to Netbeans.
3) Change the path to the CME directory. To do this, replace the line by substituting yours instead of path_to_cme_dir:

FileWriter writer = new FileWriter ("path_to_cme_dir" + para + "\\" + year + "\\" + para + "_" + "JS_" + contr + "_settle_" + timePoint + ".csv");

When installing on linux, adjust the path accordingly.

Since the program does not implement the function of creating directories, you will have to create a directory tree yourself. An example of subdirectories for euro:

CME\euro\2021
CME\euro\2022

4) Run the project.

