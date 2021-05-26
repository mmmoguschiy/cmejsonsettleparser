This is a Netbeans project that reads daily option settlement reports from the CMEgroup website, presented in JSON format and adds them to separate csv files.

Installation and launch

1) Just clone the given project into your Netbeans projects folder:

git clone https://github.com/mmmoguschiy/cmejsonsettleparser

2) Add the project to Netbeans.
3) Change the path to the CME directory. To do this, replace the line by substituting yours instead of path_to_cme_dir:

FileWriter writer = new FileWriter ("path_to_cme_dir" + para + "\\" + year + "\\" + para + "_" + "JS_" + contr + "_settle_" + timePoint + ".csv");

When installing on linux, adjust the path accordingly.

Since the program does not implement the function of creating directories, you will have to create a directory tree yourself. :D My tree currently looks like this

CME
├── aud
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ └── 2023
├── aud_monweek
│ ├── 2020
│ └── 2021
├── aud_wedweek
│ ├── 2019
│ ├── 2020
│ └── 2021
├── aud_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── btc
│ ├── 2020
│ ├── 2021
│ └── 2022
├── cad
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ └── 2022
├── cad_wedweek
│ ├── 2019
│ └── 2020
├── cad_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── chf
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ └── 2022
├── chf_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── euro
│ ├── 2018
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ └── 2023
├── euro_monweek
│ ├── 2020
│ └── 2021
├── euro_wedweek
│ ├── 2019
│ ├── 2020
│ └── 2021
├── euro_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── gas
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ ├── 2023
│ ├── 2024
│ ├── 2025
│ ├── 2026
│ ├── 2027
│ ├── 2028
│ ├── 2029
│ ├── 2030
│ └── 2031
├── gbp
│ ├── 2018
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ └── 2023
├── gbp_monweek
│ ├── 2020
│ └── 2021
├── gbp_wedweek
│ ├── 2019
│ ├── 2020
│ └── 2021
├── gbp_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── gold
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ ├── 2023
│ ├── 2024
│ ├── 2025
│ └── 2026
├── gold_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── jpy
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ └── 2023
├── jpy_wedweek
│ ├── 2019
│ └── 2020
├── jpy_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── pall
│ ├── 2020
│ ├── 2021
│ └── 2022
├── plat
│ ├── 2020
│ ├── 2021
│ └── 2022
├── rub
│ ├── 2020
│ ├── 2021
│ └── 2022
├── Si
├── sipi
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ └── 2022
├── sipi_eom
│ ├── 2019
│ ├── 2020
│ └── 2021
├── sipi_monweek
│ ├── 2019
│ ├── 2020
│ └── 2021
├── sipi_wedweek
│ ├── 2019
│ ├── 2020
│ └── 2021
├── sipi_week
│ ├── 2019
│ ├── 2020
│ └── 2021
├── usoil
│ ├── 2019
│ ├── 2020
│ ├── 2021
│ ├── 2022
│ ├── 2023
│ ├── 2024
│ ├── 2025
│ ├── 2026
│ ├── 2027
│ ├── 2028
│ ├── 2029
│ ├── 2030
│ └── 2031
└── usoil_week
    ├── 2019
    ├── 2020
    └── 2021

4) Change the date of the reports (usually yesterday's business day) by substituting the appropriate values ​​for the day, month and year:

String month = "05";
String day = "25";
String [] year = {"2021"};

5) Run the project. 
