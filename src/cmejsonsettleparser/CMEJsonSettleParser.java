/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmejsonsettleparser;

import java.io.FileWriter;
import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import static java.lang.System.out;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.json.Json;
import javax.json.JsonString;
import javax.json.JsonException;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mmmoguschiy@gmail.com
 */
public class CMEJsonSettleParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        String month = "05";
        String day = "24";
        String[] year = {"2021"};
        //String tp = year + "-" + month + "-" + day;
        //String tdate = "05/23/2019";
        String tdate = month + "/" + day + "/" + year[0];
        String cletter = "M";
        String oil_letter = "M";
        String com_letter = "M";
        String god = "9"; //" + god + "
        
        String wedweek_num = "4";
        
        class yl
        {
            String year;
            char[] letters;
            
            public yl(String y, char[] l)
            {
                this.year = y;
                this.letters = l;
            }
        }
        
        char[] cl1 = {'M','N','Q','U','V','X','Z'};
        char[] cl2 = {'F','G','H','J','K','M','U','Z'};
        char[] cl3 = {'H'};
        yl[] cletters = new yl[3];
        cletters[0] = new yl("2021",cl1);
        cletters[1] = new yl("2022",cl2);
        cletters[2] = new yl("2023",cl3);
        
        char[] cl_chf_1 = {'M','N','Q','U','V','X','Z'};
        char[] cl_chf_2 = {'F','G','H','J','K'};
        yl[] cletters_chf = new yl[2];
        cletters_chf[0] = new yl("2021",cl_chf_1);
        cletters_chf[1] = new yl("2022",cl_chf_2);
        
        char[] btc1 = {'K','M','N','Q','U','V','Z'};
        char[] btc2 = {'Z'};
        yl[] btcletters = new yl[2];
        btcletters[0] = new yl("2021",btc1);
        btcletters[1] = new yl("2022",btc2);
        
        //char[] ol1 = {'U', 'V', 'X','Z'};
        //char[] ol2 = {'F','G','H','J','K','M','N', 'Q', 'U', 'V', 'X','Z'};
        //yl[] oil_letters = new yl[2];
        //oil_letters[0] = new yl("2019",ol1);
        //oil_letters[1] = new yl("2020",ol2);
        char[] ol1 = {'N', 'Q', 'U', 'V', 'X','Z'};
        char[] ol2 = {'F','G','H','J','K','M','N', 'Q', 'U', 'V', 'X','Z'};
        char[] ol3 = {'F','G','H','J','K','M'};
        yl[] oil_letters = new yl[11];
        //oil_letters[0] = new yl("2020",ol1);
        oil_letters[0] = new yl("2021",ol1);
        oil_letters[1] = new yl("2022",ol2);
        oil_letters[2] = new yl("2023",ol2);
        oil_letters[3] = new yl("2024",ol2);
        oil_letters[4] = new yl("2025",ol2);
        oil_letters[5] = new yl("2026",ol2);
        oil_letters[6] = new yl("2027",ol2);
        oil_letters[7] = new yl("2028",ol2);
        oil_letters[8] = new yl("2029",ol2);
        oil_letters[9] = new yl("2030",ol2);
        oil_letters[10] = new yl("2031",ol3);
        
        //char[] coml1 = {'U', 'V', 'X','Z'};
        //char[] coml2 = {'F','G','H','J','K','M','N', 'Q', 'U', 'V', 'X','Z'};
        //yl[] com_letters = new yl[2];
        //com_letters[0] = new yl("2019",coml1);
        //com_letters[1] = new yl("2020",coml2);
        
        char[] coml1 = {'M','N', 'Q', 'U','V','X','Z'};
        char[] coml2 = {'F','G','H','J','K','M','N', 'Q', 'U','V','X','Z'};
        char[] coml3 = {'F','G','M','Z'};
        char[] coml4 = {'M','Z'};
        yl[] com_letters = new yl[6];
        com_letters[0] = new yl("2021",coml1);
        com_letters[1] = new yl("2022",coml2);
        com_letters[2] = new yl("2023",coml3);
        com_letters[3] = new yl("2024",coml4);
        com_letters[4] = new yl("2025",coml4);
        com_letters[5] = new yl("2026",coml4);
        //com_letters[6] = new yl("2026",coml5);
        
        char[] plat1 = {'M','N','Q','U','V'};
        char[] plat2 = {'F','J'};
        yl[] plat_letters = new yl[2];
        plat_letters[0] = new yl("2021",plat1);
        plat_letters[1] = new yl("2022",plat2);
        
        char[] pall1 = {'M','N','Q','U','Z'};
        char[] pall2 = {'H','M'};
        yl[] pall_letters = new yl[2];
        pall_letters[0] = new yl("2021",pall1);
        pall_letters[1] = new yl("2022",pall2);
        
        char[] sel1 = {'K','M','N','Q','U','V'};
        yl[] sipi_eom_letters = new yl[1];
        sipi_eom_letters[0] = new yl("2021",sel1);
       
        char[] sl1 = {'M','U','Z'};
        char[] sl2 = {'H'};
        yl[] sipi_letters = new yl[2];
        sipi_letters[0] = new yl("2021",sl1);
        sipi_letters[1] = new yl("2022",sl2);
        
        //char[] cletters = {'K', 'M', 'N', 'Q', 'U', 'V', 'Z'};
        //char[] oil_letters = {'N', 'Q', 'U', 'V', 'X','Z'};
        //char[] com_letters = {'M', 'N', 'Q', 'U', 'V', 'X','Z'};
        //char[] sipi_eom_letters = {'K', 'M', 'N', 'Q', 'U'};
        //char[] sipi_letters = {'M', 'U', 'Z'};
        
        for (yl c : cletters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("euro",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8116/OOF?monthYear=EUU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8116&optionExpiration=8116-"+clet+y.charAt(3)+"&tradeDate="+tdate);
                doit("gbp",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8098/OOF?monthYear=GBU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8098&optionExpiration=8098-"+clet+y.charAt(3)+"&tradeDate="+tdate);
                doit("jpy",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8122/OOF?monthYear=JPU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8122&optionExpiration=8122-"+clet+y.charAt(3)+"&tradeDate="+tdate);                                
            }
        }
        
        for (yl c : cletters_chf)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                    doit("cad",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8104/OOF?monthYear=CAU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8104&optionExpiration=8104-"+clet+y.charAt(3)+"&tradeDate="+tdate);                
                doit("aud",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8092/OOF?monthYear=ADU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8092&optionExpiration=8092-"+clet+y.charAt(3)+"&tradeDate="+tdate);
                doit("chf",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8110/OOF?monthYear=CHU"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8110&optionExpiration=8110-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : btcletters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                //                      https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8875/OOF?monthYear=OBCK20&                    strategy=DEFAULT&optionProductId=8875&optionExpiration=8875-K0&tradeDate=04%2F29%2F2020&pageSize=500&_=1588218713280
                doit("btc",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8875/OOF?monthYear=OBC"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=8875&optionExpiration=8875-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : oil_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("usoil",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/190/OOF?monthYear=LO"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=190&optionExpiration=190-"+clet+y.charAt(3)+"&tradeDate="+tdate);
                doit("gas",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//1352/OOF?monthYear=LN"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=1352&optionExpiration=1352-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : com_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("gold",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/192/OOF?monthYear=OG"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=192&optionExpiration=192-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : plat_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("plat",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2910/OOF?monthYear=PO"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=2910&optionExpiration=2910-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : pall_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("pall",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//5380/OOF?monthYear=PAO"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=5380&optionExpiration=5380-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : sipi_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("sipi",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/138/OOF?monthYear=EZ"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=138&optionExpiration=138-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        for (yl c : sipi_eom_letters)
        {
            String tp = year[0] + "-" + month + "-" + day;
            String y = c.year;
            for (char s : c.letters)
            {
                String clet = String.valueOf(s);
                doit("sipi_eom",clet,y,tp,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//136/OOF?monthYear=EW"+clet+y.substring(2)+"&strategy=DEFAULT&optionProductId=136&optionExpiration=136-"+clet+y.charAt(3)+"&tradeDate="+tdate);
            }
        }
        
        
        /*
        
        doit("euro",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8116/OOF?monthYear=EUU"+cletter+"1" + god + "&strategy=DEFAULT&optionProductId=8116&optionExpiration=8116-"+cletter + god + "&tradeDate="+tdate);
        //doit("euro_wedweek",cletter,"http://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8441/OOF?monthYear=WE2K18&strategy=DEFAULT&optionProductId=8440&optionExpiration=8441-K8&tradeDate="+tdate);
        doit("euro_wedweek",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8443/OOF?monthYear=WE"+wedweek_num+"F19&strategy=DEFAULT&optionProductId=8440&optionExpiration=8443-F9&tradeDate="+tdate);
        doit("euro_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8119/OOF?monthYear=3EUF19&strategy=DEFAULT&optionProductId=8117&optionExpiration=8119-F9&tradeDate="+tdate);
        
        doit("gbp",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8098/OOF?monthYear=GBU"+cletter+"1"+god+"&strategy=DEFAULT&optionProductId=8098&optionExpiration=8098-"+cletter+god+"&tradeDate="+tdate);
        //16.05.18 doit("gbp_wedweek",cletter,"http://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8422/OOF?monthYear=WG"+wedweek_num+"K18&strategy=DEFAULT&optionProductId=8420&optionExpiration=8422-K8&tradeDate="+tdate);
        doit("gbp_wedweek",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8423/OOF?monthYear=WG"+wedweek_num+"F19&strategy=DEFAULT&optionProductId=8420&optionExpiration=8423-F9&tradeDate="+tdate);
        doit("gbp_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8101/OOF?monthYear=3BPF19&strategy=DEFAULT&optionProductId=8099&optionExpiration=8101-F9&tradeDate="+tdate);
        
        doit("aud",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8092/OOF?monthYear=ADU"+cletter+"1"+god+"&strategy=DEFAULT&optionProductId=8092&optionExpiration=8092-"+cletter+god+"&tradeDate="+tdate);
        //16.05.18 doit("aud_wedweek",cletter,"http://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8412/OOF?monthYear=WA"+wedweek_num+"K18&strategy=DEFAULT&optionProductId=8410&optionExpiration=8412-K8&tradeDate="+tdate);
        doit("aud_wedweek",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8413/OOF?monthYear=WA"+wedweek_num+"F19&strategy=DEFAULT&optionProductId=8410&optionExpiration=8413-F9&tradeDate="+tdate);
        doit("aud_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8095/OOF?monthYear=3ADF19&strategy=DEFAULT&optionProductId=8093&optionExpiration=8095-F9&tradeDate="+tdate);
        
        doit("chf",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8110/OOF?monthYear=CHU"+cletter+"1"+god+"&strategy=DEFAULT&optionProductId=8110&optionExpiration=8110-"+cletter+god+"&tradeDate="+tdate);
        doit("chf_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8113/OOF?monthYear=3SFF19&strategy=DEFAULT&optionProductId=8111&optionExpiration=8113-F9&tradeDate="+tdate);
        
        doit("cad",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8104/OOF?monthYear=CAU"+cletter+"1"+god+"&strategy=DEFAULT&optionProductId=8104&optionExpiration=8104-"+cletter+god+"&tradeDate="+tdate);
        doit("cad_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8107/OOF?monthYear=3CDF19&strategy=DEFAULT&optionProductId=8105&optionExpiration=8107-F9&tradeDate="+tdate);
        
        doit("jpy",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8122/OOF?monthYear=JPU"+cletter+"1"+god+"&strategy=DEFAULT&optionProductId=8122&optionExpiration=8122-"+cletter+god+"&tradeDate="+tdate);
        //? почему 8452 16.05.18 doit("jpy_wedweek",cletter,"http://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8452/OOF?monthYear=WJ"+wedweek_num+"K18&strategy=DEFAULT&optionProductId=8450&optionExpiration=8452-K8&tradeDate="+tdate);
        doit("jpy_wedweek",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8452/OOF?monthYear=WJ"+wedweek_num+"F19&strategy=DEFAULT&optionProductId=8450&optionExpiration=8452-F9&tradeDate="+tdate);
        doit("jpy_week",cletter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/8125/OOF?monthYear=3JYF19&strategy=DEFAULT&optionProductId=8123&optionExpiration=8125-F9&tradeDate="+tdate);
        
        doit("gold",com_letter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/192/OOF?monthYear=OG"+com_letter+"1"+god+"&strategy=DEFAULT&optionProductId=192&optionExpiration=192-"+com_letter+"9&tradeDate="+tdate);
        doit("gold_week",com_letter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7490/OOF?monthYear=OG3F19&strategy=DEFAULT&optionProductId=7488&optionExpiration=7490-F9&tradeDate="+tdate);
        
        doit("usoil",oil_letter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/190/OOF?monthYear=LO"+oil_letter+"19&strategy=DEFAULT&optionProductId=190&optionExpiration=190-"+oil_letter+"9&tradeDate="+tdate);
        doit("usoil_week",com_letter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7505/OOF?monthYear=LO3F19&strategy=DEFAULT&optionProductId=7503&optionExpiration=7505-F9&tradeDate="+tdate);
        
        
        //doit("sipi","Z","https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//138/OOF?monthYear=EZZ19&strategy=DEFAULT&optionProductId=138&optionExpiration=138-F9&tradeDate=01/08/2019");
        //doit("sipi","H","https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//138/OOF?monthYear=EZH19&strategy=DEFAULT&optionProductId=138&optionExpiration=138-H9&tradeDate=01/08/2019");
        //doit("sipi_eom","Z","https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//136/OOF?monthYear=EWF19&strategy=DEFAULT&optionProductId=136&optionExpiration=136-F9&tradeDate=01/08/2019");
        //doit("sipi_week","Z","https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2917/OOF?monthYear=EW3F19&strategy=DEFAULT&optionProductId=2915&optionExpiration=2917-F9&tradeDate=01/08/2019");
        
        //https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//1352/OOF?monthYear=LNF19&strategy=DEFAULT&optionProductId=1352&optionExpiration=1352-F9&tradeDate=01/08/2018
        doit("gas",oil_letter,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//1352/OOF?monthYear=LN" + oil_letter + "19&strategy=DEFAULT&optionProductId=1352&optionExpiration=1352-" + oil_letter + "9&tradeDate="+tdate);
        */
        
        String tpw = year[0] + "-" + month + "-" + day;
        String y = year[0];
        
        //weeks
        
        //doit("euro_week","1J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8117"+"/OOF?monthYear="+"1"+"EUJ21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8117"+"-J1&tradeDate="+tdate);
        //doit("euro_week","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8119"+"/OOF?monthYear="+"3"+"EUJ21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8119"+"-J1&tradeDate="+tdate);
        //doit("euro_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8120"+"/OOF?monthYear="+"4"+"EUJ21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8120"+"-J1&tradeDate="+tdate);
        //doit("euro_week","5J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8121"+"/OOF?monthYear="+"5"+"EUJ21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8121"+"-J1&tradeDate="+tdate);
        //doit("euro_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8118"+"/OOF?monthYear="+"2"+"EUK21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8118"+"-K1&tradeDate="+tdate);
        doit("euro_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8119"+"/OOF?monthYear="+"3"+"EUK21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8119"+"-K1&tradeDate="+tdate);
        doit("euro_week","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8120"+"/OOF?monthYear="+"4"+"EUK21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8120"+"-K1&tradeDate="+tdate);
        doit("euro_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8118"+"/OOF?monthYear="+"2"+"EUM21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8118"+"-M1&tradeDate="+tdate);
        doit("euro_week","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8119"+"/OOF?monthYear="+"3"+"EUM21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8119"+"-M1&tradeDate="+tdate);
        doit("euro_week","4M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8120"+"/OOF?monthYear="+"4"+"EUM21&strategy=DEFAULT&optionProductId=8117&optionExpiration="+"8120"+"-M1&tradeDate="+tdate);

        //doit("gbp_week","1J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8099"+"/OOF?monthYear="+"1"+"BPJ21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8099"+"-J1&tradeDate="+tdate);
        //doit("gbp_week","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8101"+"/OOF?monthYear="+"3"+"BPJ21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8101"+"-J1&tradeDate="+tdate);
        //doit("gbp_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8102"+"/OOF?monthYear="+"4"+"BPJ21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8102"+"-J1&tradeDate="+tdate);
        //doit("gbp_week","5J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8103"+"/OOF?monthYear="+"5"+"BPJ21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8103"+"-J1&tradeDate="+tdate);
        //doit("gbp_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8100"+"/OOF?monthYear="+"2"+"BPK21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8100"+"-K1&tradeDate="+tdate);
        doit("gbp_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8101"+"/OOF?monthYear="+"3"+"BPK21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8101"+"-K1&tradeDate="+tdate);
        doit("gbp_week","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8102"+"/OOF?monthYear="+"4"+"BPK21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8102"+"-K1&tradeDate="+tdate);
        doit("gbp_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8100"+"/OOF?monthYear="+"2"+"BPM21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8100"+"-M1&tradeDate="+tdate);
        doit("gbp_week","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8101"+"/OOF?monthYear="+"3"+"BPM21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8101"+"-M1&tradeDate="+tdate);
        doit("gbp_week","4M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8102"+"/OOF?monthYear="+"4"+"BPM21&strategy=DEFAULT&optionProductId=8099&optionExpiration="+"8102"+"-M1&tradeDate="+tdate);
        
        //doit("aud_week","1J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8093"+"/OOF?monthYear="+"1"+"ADJ21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8093"+"-J1&tradeDate="+tdate);
        //doit("aud_week","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8095"+"/OOF?monthYear="+"3"+"ADJ21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8095"+"-J1&tradeDate="+tdate);
        //doit("aud_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8096"+"/OOF?monthYear="+"4"+"ADJ21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8096"+"-J1&tradeDate="+tdate);
        //doit("aud_week","5J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8097"+"/OOF?monthYear="+"5"+"ADJ21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8097"+"-J1&tradeDate="+tdate);
        //doit("aud_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8094"+"/OOF?monthYear="+"2"+"ADK21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8094"+"-K1&tradeDate="+tdate);
        doit("aud_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8095"+"/OOF?monthYear="+"3"+"ADK21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8095"+"-K1&tradeDate="+tdate);
        doit("aud_week","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8096"+"/OOF?monthYear="+"4"+"ADK21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8096"+"-K1&tradeDate="+tdate);
        doit("aud_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8094"+"/OOF?monthYear="+"2"+"ADM21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8094"+"-M1&tradeDate="+tdate);
        doit("aud_week","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8095"+"/OOF?monthYear="+"3"+"ADM21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8095"+"-M1&tradeDate="+tdate);
        doit("aud_week","4M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8096"+"/OOF?monthYear="+"4"+"ADM21&strategy=DEFAULT&optionProductId=8093&optionExpiration="+"8096"+"-M1&tradeDate="+tdate);
        
        //doit("usoil_week","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7504"+"/OOF?monthYear=LO"+"2"+"J21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7504"+"-J1&tradeDate="+tdate);
        //doit("usoil_week","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7505"+"/OOF?monthYear=LO"+"3"+"J21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7505"+"-J1&tradeDate="+tdate);
        //doit("usoil_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7506"+"/OOF?monthYear=LO"+"4"+"J21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7506"+"-J1&tradeDate="+tdate);
        //doit("usoil_week","5J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7507"+"/OOF?monthYear=LO"+"5"+"J21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7507"+"-J1&tradeDate="+tdate);
        //doit("usoil_week","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7503"+"/OOF?monthYear=LO"+"1"+"K21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7503"+"-K1&tradeDate="+tdate);
        //doit("usoil_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7504"+"/OOF?monthYear=LO"+"2"+"K21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7504"+"-K1&tradeDate="+tdate);
        doit("usoil_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7505"+"/OOF?monthYear=LO"+"3"+"K21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7505"+"-K1&tradeDate="+tdate);
        doit("usoil_week","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7506"+"/OOF?monthYear=LO"+"4"+"K21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7506"+"-K1&tradeDate="+tdate);
        doit("usoil_week","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7503"+"/OOF?monthYear=LO"+"1"+"M21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7503"+"-M1&tradeDate="+tdate);
        doit("usoil_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7504"+"/OOF?monthYear=LO"+"2"+"M21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7504"+"-M1&tradeDate="+tdate);
        doit("usoil_week","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//"+"7505"+"/OOF?monthYear=LO"+"3"+"M21&strategy=DEFAULT&optionProductId=7503&optionExpiration="+"7505"+"-M1&tradeDate="+tdate);
        
        //doit("gold_week","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7489/OOF?monthYear=OG"+"2"+"J21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7489-J1&tradeDate="+tdate);
        //doit("gold_week","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7490/OOF?monthYear=OG"+"3"+"J21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7490-J1&tradeDate="+tdate);
        //doit("gold_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7491/OOF?monthYear=OG"+"4"+"J21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7491-J1&tradeDate="+tdate);
        //doit("gold_week","5J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7492/OOF?monthYear=OG"+"5"+"J21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7492-J1&tradeDate="+tdate);
        //doit("gold_week","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7488/OOF?monthYear=OG"+"1"+"K21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7488-K1&tradeDate="+tdate);
        //doit("gold_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7489/OOF?monthYear=OG"+"2"+"K21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7489-K1&tradeDate="+tdate);
        doit("gold_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7490/OOF?monthYear=OG"+"3"+"K21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7490-K1&tradeDate="+tdate);
        doit("gold_week","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7491/OOF?monthYear=OG"+"4"+"K21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7491-K1&tradeDate="+tdate);
        doit("gold_week","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7488/OOF?monthYear=OG"+"1"+"M21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7488-M1&tradeDate="+tdate);
        doit("gold_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7489/OOF?monthYear=OG"+"2"+"M21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7489-M1&tradeDate="+tdate);
        doit("gold_week","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/7490/OOF?monthYear=OG"+"3"+"M21&strategy=DEFAULT&optionProductId=7488&optionExpiration=7490-M1&tradeDate="+tdate);
        
        //doit("sipi_week","3J","2021",tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8019/OOF?monthYear=EW"+"3"+"J21&strategy=DEFAULT&optionProductId=2915&optionExpiration=8019-J1&tradeDate="+tdate);
        //doit("sipi_week","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//5222/OOF?monthYear=EW"+"4"+"J21&strategy=DEFAULT&optionProductId=2915&optionExpiration=5222-J1&tradeDate="+tdate);
        //doit("sipi_week","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2915/OOF?monthYear=EW"+"1"+"K21&strategy=DEFAULT&optionProductId=2915&optionExpiration=2915-K1&tradeDate="+tdate);
        //doit("sipi_week","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2916/OOF?monthYear=EW"+"2"+"K21&strategy=DEFAULT&optionProductId=2915&optionExpiration=2916-K1&tradeDate="+tdate);
        doit("sipi_week","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8019/OOF?monthYear=EW"+"3"+"K21&strategy=DEFAULT&optionProductId=2915&optionExpiration=8019-K1&tradeDate="+tdate);
        doit("sipi_week","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2915/OOF?monthYear=EW"+"1"+"M21&strategy=DEFAULT&optionProductId=2915&optionExpiration=2915-M1&tradeDate="+tdate);
        doit("sipi_week","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2916/OOF?monthYear=EW"+"2"+"M21&strategy=DEFAULT&optionProductId=2915&optionExpiration=2916-M1&tradeDate="+tdate);
        doit("sipi_week","4M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//5222/OOF?monthYear=EW"+"4"+"M21&strategy=DEFAULT&optionProductId=2915&optionExpiration=5222-M1&tradeDate="+tdate);
        doit("sipi_week","1N",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//2915/OOF?monthYear=EW"+"1"+"N21&strategy=DEFAULT&optionProductId=2915&optionExpiration=2915-N1&tradeDate="+tdate);
        doit("sipi_week","3N",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8019/OOF?monthYear=EW"+"3"+"N21&strategy=DEFAULT&optionProductId=2915&optionExpiration=8019-N1&tradeDate="+tdate);
        doit("sipi_week","3Q",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8019/OOF?monthYear=EW"+"3"+"Q21&strategy=DEFAULT&optionProductId=2915&optionExpiration=8019-Q1&tradeDate="+tdate);
        doit("sipi_week","3V",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8019/OOF?monthYear=EW"+"3"+"V21&strategy=DEFAULT&optionProductId=2915&optionExpiration=8019-V1&tradeDate="+tdate);
        
        //wedweeks
        
        //doit("euro_wedweek","5H",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8444"+"/OOF?monthYear=WE"+"5"+"H21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8444"+"-H1&tradeDate="+tdate);
        //doit("euro_wedweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8441"+"/OOF?monthYear=WE"+"2"+"J21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8441"+"-J1&tradeDate="+tdate);
        //doit("euro_wedweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8442"+"/OOF?monthYear=WE"+"3"+"J21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8442"+"-J1&tradeDate="+tdate);
        //doit("euro_wedweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8443"+"/OOF?monthYear=WE"+"4"+"J21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8443"+"-J1&tradeDate="+tdate);
        //doit("euro_wedweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8440"+"/OOF?monthYear=WE"+"1"+"K21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8440"+"-K1&tradeDate="+tdate);
        //doit("euro_wedweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8441"+"/OOF?monthYear=WE"+"2"+"K21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8441"+"-K1&tradeDate="+tdate);
        doit("euro_wedweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8442"+"/OOF?monthYear=WE"+"3"+"K21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8442"+"-K1&tradeDate="+tdate);
        doit("euro_wedweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8443"+"/OOF?monthYear=WE"+"4"+"K21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8443"+"-K1&tradeDate="+tdate);
        doit("euro_wedweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8440"+"/OOF?monthYear=WE"+"1"+"M21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8440"+"-M1&tradeDate="+tdate);
        doit("euro_wedweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8441"+"/OOF?monthYear=WE"+"2"+"M21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8441"+"-M1&tradeDate="+tdate);
        doit("euro_wedweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8442"+"/OOF?monthYear=WE"+"3"+"M21&strategy=DEFAULT&optionProductId=8440&optionExpiration="+"8442"+"-M1&tradeDate="+tdate);
        
        //doit("gbp_wedweek","5H",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8424"+"/OOF?monthYear=WG"+"5"+"H21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8424"+"-H1&tradeDate="+tdate);
        //doit("gbp_wedweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8421"+"/OOF?monthYear=WG"+"2"+"J21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8421"+"-J1&tradeDate="+tdate);
        //doit("gbp_wedweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8422"+"/OOF?monthYear=WG"+"3"+"J21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8422"+"-J1&tradeDate="+tdate);
        //doit("gbp_wedweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8423"+"/OOF?monthYear=WG"+"4"+"J21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8423"+"-J1&tradeDate="+tdate);
        //doit("gbp_wedweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8420"+"/OOF?monthYear=WG"+"1"+"K21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8420"+"-K1&tradeDate="+tdate);
        //doit("gbp_wedweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8421"+"/OOF?monthYear=WG"+"2"+"K21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8421"+"-K1&tradeDate="+tdate);
        doit("gbp_wedweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8422"+"/OOF?monthYear=WG"+"3"+"K21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8422"+"-K1&tradeDate="+tdate);
        doit("gbp_wedweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8423"+"/OOF?monthYear=WG"+"4"+"K21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8423"+"-K1&tradeDate="+tdate);
        doit("gbp_wedweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8420"+"/OOF?monthYear=WG"+"1"+"M21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8420"+"-M1&tradeDate="+tdate);
        doit("gbp_wedweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8421"+"/OOF?monthYear=WG"+"2"+"M21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8421"+"-M1&tradeDate="+tdate);
        doit("gbp_wedweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8422"+"/OOF?monthYear=WG"+"3"+"M21&strategy=DEFAULT&optionProductId=8420&optionExpiration="+"8422"+"-M1&tradeDate="+tdate);
       
        //doit("aud_wedweek","5H",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8414"+"/OOF?monthYear=WA"+"5"+"H21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8414"+"-H1&tradeDate="+tdate);
        //doit("aud_wedweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8411"+"/OOF?monthYear=WA"+"2"+"J21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8411"+"-J1&tradeDate="+tdate);
        //doit("aud_wedweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8412"+"/OOF?monthYear=WA"+"3"+"J21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8412"+"-J1&tradeDate="+tdate);
        //doit("aud_wedweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8413"+"/OOF?monthYear=WA"+"4"+"J21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8413"+"-J1&tradeDate="+tdate);
        //doit("aud_wedweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8410"+"/OOF?monthYear=WA"+"1"+"K21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8410"+"-K1&tradeDate="+tdate);
        //doit("aud_wedweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8411"+"/OOF?monthYear=WA"+"2"+"K21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8411"+"-K1&tradeDate="+tdate);
        doit("aud_wedweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8412"+"/OOF?monthYear=WA"+"3"+"K21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8412"+"-K1&tradeDate="+tdate);
        doit("aud_wedweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8413"+"/OOF?monthYear=WA"+"4"+"K21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8413"+"-K1&tradeDate="+tdate);
        doit("aud_wedweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8410"+"/OOF?monthYear=WA"+"1"+"M21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8410"+"-M1&tradeDate="+tdate);
        doit("aud_wedweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8411"+"/OOF?monthYear=WA"+"2"+"M21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8411"+"-M1&tradeDate="+tdate);
        doit("aud_wedweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8412"+"/OOF?monthYear=WA"+"3"+"M21&strategy=DEFAULT&optionProductId=8410&optionExpiration="+"8412"+"-M1&tradeDate="+tdate);
        
        //doit("sipi_wedweek","5N",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8231/OOF?monthYear=E"+"5"+"CN20&strategy=DEFAULT&optionProductId=8227&optionExpiration=8231-N0&tradeDate="+tdate);
        //doit("sipi_wedweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8228/OOF?monthYear=E"+"2"+"CJ21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8228-J1&tradeDate="+tdate);
        //doit("sipi_wedweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8229/OOF?monthYear=E"+"3"+"CJ21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8229-J1&tradeDate="+tdate);
        //doit("sipi_wedweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8230/OOF?monthYear=E"+"4"+"CJ21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8230-J1&tradeDate="+tdate);
        //doit("sipi_wedweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8227/OOF?monthYear=E"+"1"+"CK21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8227-K1&tradeDate="+tdate);
        //doit("sipi_wedweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8228/OOF?monthYear=E"+"2"+"CK21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8228-K1&tradeDate="+tdate);
        doit("sipi_wedweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8229/OOF?monthYear=E"+"3"+"CK21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8229-K1&tradeDate="+tdate);
        doit("sipi_wedweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8230/OOF?monthYear=E"+"4"+"CK21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8230-K1&tradeDate="+tdate);
        doit("sipi_wedweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8227/OOF?monthYear=E"+"1"+"CM21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8227-M1&tradeDate="+tdate);
        doit("sipi_wedweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8228/OOF?monthYear=E"+"2"+"CM21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8228-M1&tradeDate="+tdate);
        doit("sipi_wedweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8229/OOF?monthYear=E"+"3"+"CM21&strategy=DEFAULT&optionProductId=8227&optionExpiration=8229-M1&tradeDate="+tdate);
        
        //monweeks        
        
        //doit("euro_monweek","5Q",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8905"+"/OOF?monthYear=MO"+"5"+"Q20&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8905"+"-Q0&tradeDate="+tdate);
        //doit("euro_monweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8903"+"/OOF?monthYear=MO"+"3"+"J21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8903"+"-J1&tradeDate="+tdate);
        //doit("euro_monweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8904"+"/OOF?monthYear=MO"+"4"+"J21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8904"+"-J1&tradeDate="+tdate);
        //doit("euro_monweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8901"+"/OOF?monthYear=MO"+"1"+"K21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8901"+"-K1&tradeDate="+tdate);
        //doit("euro_monweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8902"+"/OOF?monthYear=MO"+"2"+"K21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8902"+"-K1&tradeDate="+tdate);
        doit("euro_monweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8903"+"/OOF?monthYear=MO"+"3"+"K21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8903"+"-K1&tradeDate="+tdate);
        doit("euro_monweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8904"+"/OOF?monthYear=MO"+"4"+"K21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8904"+"-K1&tradeDate="+tdate);
        doit("euro_monweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8901"+"/OOF?monthYear=MO"+"1"+"M21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8901"+"-M1&tradeDate="+tdate);
        doit("euro_monweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8902"+"/OOF?monthYear=MO"+"2"+"M21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8902"+"-M1&tradeDate="+tdate);
        doit("euro_monweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8903"+"/OOF?monthYear=MO"+"3"+"M21&strategy=DEFAULT&optionProductId=8901&optionExpiration="+"8903"+"-M1&tradeDate="+tdate);
        
        //doit("gbp_monweek","5Q",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8895"+"/OOF?monthYear=MB"+"5"+"Q20&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8895"+"-Q0&tradeDate="+tdate);
        //doit("gbp_monweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8892"+"/OOF?monthYear=MB"+"2"+"J21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8892"+"-J1&tradeDate="+tdate);
        //doit("gbp_monweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8893"+"/OOF?monthYear=MB"+"3"+"J21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8893"+"-J1&tradeDate="+tdate);
        //doit("gbp_monweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8894"+"/OOF?monthYear=MB"+"4"+"J21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8894"+"-J1&tradeDate="+tdate);
        //doit("gbp_monweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8891"+"/OOF?monthYear=MB"+"1"+"K21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8891"+"-K1&tradeDate="+tdate);
        //doit("gbp_monweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8892"+"/OOF?monthYear=MB"+"2"+"K21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8892"+"-K1&tradeDate="+tdate);
        doit("gbp_monweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8893"+"/OOF?monthYear=MB"+"3"+"K21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8893"+"-K1&tradeDate="+tdate);
        doit("gbp_monweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8894"+"/OOF?monthYear=MB"+"4"+"K21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8894"+"-K1&tradeDate="+tdate);
        doit("gbp_monweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8891"+"/OOF?monthYear=MB"+"1"+"M21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8891"+"-M1&tradeDate="+tdate);
        doit("gbp_monweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8892"+"/OOF?monthYear=MB"+"2"+"M21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8892"+"-M1&tradeDate="+tdate);
        doit("gbp_monweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8893"+"/OOF?monthYear=MB"+"3"+"M21&strategy=DEFAULT&optionProductId=8891&optionExpiration="+"8893"+"-M1&tradeDate="+tdate);
        
        //doit("aud_monweek","5Q",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8890"+"/OOF?monthYear=MA"+"5"+"Q20&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8890"+"-Q0&tradeDate="+tdate);
        //doit("aud_monweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8887"+"/OOF?monthYear=MA"+"2"+"J21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8887"+"-J1&tradeDate="+tdate);
        //doit("aud_monweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8888"+"/OOF?monthYear=MA"+"3"+"J21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8888"+"-J1&tradeDate="+tdate);
        //doit("aud_monweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8889"+"/OOF?monthYear=MA"+"4"+"J21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8889"+"-J1&tradeDate="+tdate);
        //doit("aud_monweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8886"+"/OOF?monthYear=MA"+"1"+"K21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8886"+"-K1&tradeDate="+tdate);
        //doit("aud_monweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8887"+"/OOF?monthYear=MA"+"2"+"K21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8887"+"-K1&tradeDate="+tdate);
        doit("aud_monweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8888"+"/OOF?monthYear=MA"+"3"+"K21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8888"+"-K1&tradeDate="+tdate);
        doit("aud_monweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8889"+"/OOF?monthYear=MA"+"4"+"K21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8889"+"-K1&tradeDate="+tdate);
        doit("aud_monweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8886"+"/OOF?monthYear=MA"+"1"+"M21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8886"+"-M1&tradeDate="+tdate);
        doit("aud_monweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8887"+"/OOF?monthYear=MA"+"2"+"M21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8887"+"-M1&tradeDate="+tdate);
        doit("aud_monweek","3M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements/"+"8888"+"/OOF?monthYear=MA"+"3"+"M21&strategy=DEFAULT&optionProductId=8886&optionExpiration="+"8888"+"-M1&tradeDate="+tdate);
        
        //doit("sipi_monweek","5H",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8296/OOF?monthYear=E"+"5"+"AH20&strategy=DEFAULT&optionProductId=8292&optionExpiration=8296-H0&tradeDate="+tdate);
        //doit("sipi_monweek","2J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8293/OOF?monthYear=E"+"2"+"AJ21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8293-J1&tradeDate="+tdate);
        //doit("sipi_monweek","3J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8294/OOF?monthYear=E"+"3"+"AJ21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8294-J1&tradeDate="+tdate);
        //doit("sipi_monweek","4J",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8295/OOF?monthYear=E"+"4"+"AJ21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8295-J1&tradeDate="+tdate);
        //doit("sipi_monweek","1K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8292/OOF?monthYear=E"+"1"+"AK21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8292-K1&tradeDate="+tdate);
        doit("sipi_monweek","2K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8293/OOF?monthYear=E"+"2"+"AK21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8293-K1&tradeDate="+tdate);
        doit("sipi_monweek","3K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8294/OOF?monthYear=E"+"3"+"AK21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8294-K1&tradeDate="+tdate);
        doit("sipi_monweek","4K",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8295/OOF?monthYear=E"+"4"+"AK21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8295-K1&tradeDate="+tdate);
        doit("sipi_monweek","1M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8292/OOF?monthYear=E"+"1"+"AM21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8292-M1&tradeDate="+tdate);
        doit("sipi_monweek","2M",y,tpw,"https://www.cmegroup.com/CmeWS/mvc/Settlements/Options/Settlements//8293/OOF?monthYear=E"+"2"+"AM21&strategy=DEFAULT&optionProductId=8292&optionExpiration=8293-M1&tradeDate="+tdate);
        
        
    }
    
    public static void predoit(String tp, char[] letters)
    {
        
    }
    
    public static void doit(String para, String contr, String year, String timePoint, String link) throws IOException,NumberFormatException,JsonException
    {
        double d = 0.0;
        float f = (float) d;
        
        class settle {
            String strike;
            Float p_settle = f;
            Float c_settle = f;
            int p_interest;
            int c_interest;
            Float c = f;
            Float p = f;
            Float c_itog = f;
            Float p_itog = f;
        }
        
        out.println(link);
        
        List<settle> st=new ArrayList<settle>();
        //LocalDate timePoint = LocalDateTime.now().toLocalDate();     // The current date and time
        //String timePoint = year + "-" + month + "-" + day;
        ///FileWriter writer = new FileWriter("/home/daniels/CME/" + para + "/" + year + "/" + para + "_" + "JS_" + contr + "_settle_" + timePoint + ".csv");
        try {
            File dir = new File("c:\\Users\\daniels\\Documents\\from_linux\\CME\\" + para + "\\" + year);
            if (!dir.exists()) {
                 dir.mkdirs();
            }
        }
        catch(Exception e){
            System.out.println("mkdirs error: " + e.getMessage());
        FileWriter writer = new FileWriter("c:\\Users\\daniels\\Documents\\from_linux\\CME\\" + para + "\\" + year + "\\" + para + "_" + "JS_" + contr + "_settle_" + timePoint + ".csv");
        //FileWriter writer = new FileWriter("/home/daniels/CME/" + para + "/" + para + "_" + "JS_" + contr + "_settle_" + "2019-05-11" + ".csv");
        
        String url = link;
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
        con.setRequestProperty("Host", "www.cmegroup.com");
        con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
        //con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        //con.setRequestProperty("Referer", "http://www.cmegroup.com/trading/fx/g10/euro-fx_quotes_globex_options.html?optionExpiration=F8");
        con.setRequestProperty("Referer", "http://www.cmegroup.com");
        
        try (InputStream is = con.getInputStream();
            JsonReader rdr = Json.createReader(is)) {

            JsonObject obj1 = rdr.readObject();
            //System.out.println("obj = " + obj1);
            JsonArray results = obj1.getJsonArray("settlements");
            System.out.println("results = " + results);
            //Boolean first = false;
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                
                
                boolean y = false;
                
                String strike = result.getJsonString("strike").toString();
                strike = strike.replace("\"", "");
                //System.out.println("strike = " + strike);
                if(para.equals("jpy") || para.equals("cad") || para.equals("jpy_week") || para.equals("cad_week"))
                    {
                        if(!strike.equals("Total"))
                        {
                            //out.println("strike = " +strike);
                            float f_strike;
                            if(para.equals("cad") || para.equals("cad_week"))
                                f_strike = (float)10000 / Integer.parseInt(strike);
                            else
                                f_strike = (float)1000000 / Integer.parseInt(strike);
                            //out.println(f_strike);
                            strike = Float.toString(f_strike);
                        }
                    }
                //System.out.print("strikePrice = " + strike);
                
                String type = result.getJsonString("type").toString();
                type = type.replace("\"", "");
                //System.out.println("type = " + type);
                
                for(settle s:st)
                {
                    if(s.strike.equals(strike))//если страйк присутствует обновляем данные
                    {
                        if(type.equals("Put"))
                        {
                            s.p_settle = pfloat(result.getJsonString("settle").toString().replace("\"", ""));
                            s.p_interest = pint(result.getJsonString("openInterest").toString().replace("\"", ""));
                            s.p = s.p_settle * s.p_interest;
                        }
                        if(type.equals("Call"))
                        {
                            s.c_settle = pfloat(result.getJsonString("settle").toString().replace("\"", ""));
                            s.c_interest = pint(result.getJsonString("openInterest").toString().replace("\"", ""));
                            s.c = s.c_settle * s.c_interest;
                        }
                        y = true;
                        break;
                    }
                }
                
                if(y) continue;
                
                settle s = new settle();
                
                if(type.equals("Put"))
                        {
                            s.p_settle = pfloat(result.getJsonString("settle").toString().replace("\"", ""));
                            s.p_interest = pint(result.getJsonString("openInterest").toString().replace("\"", ""));
                            s.p = s.p_settle * s.p_interest;
                        }
                        if(type.equals("Call"))
                        {
                            s.c_settle = pfloat(result.getJsonString("settle").toString().replace("\"", ""));
                            s.c_interest = pint(result.getJsonString("openInterest").toString().replace("\"", ""));
                            s.c = s.c_settle * s.c_interest;
                        }
                        
                s.strike = strike;
                st.add(s);
                
                //просчитаем call итоги
                
                boolean first = true;
                for(int i=0;i<st.size()-1;i++)
                {
                    if(first)
                    {
                        st.get(i).c_itog = st.get(i).c;
                        first = false;
                        continue;
                    }
                    if(!st.isEmpty())
                    {
                        try
                        {
                            if(st.get(i).c == null)
                                throw new IOException("I am Exception Alpha!");
                            //System.out.println(i);
                            //out.println(st.get(i).c_itog);
                            //out.println(st.get(i-1).c_itog);
                            //out.println(st.get(i).c);
                            //out.println(st.get(i).strike + " " + st.get(i).c_settle + " " + st.get(i).c_interest + " " + st.get(i).c + " " + st.get(i).p_settle + " " + st.get(i).p_interest + " " + st.get(i).p);
                            st.get(i).c_itog = st.get(i).c + st.get(i-1).c_itog;
                            //out.println("c itog after" + st.get(i).c_itog);
                            //out.println("---------------------------------------");
                        }catch (IOException e) {
                            System.out.println("Message: " + e.getMessage());
                        }
                    }
                }
                
                        //просчитаем put итоги
                first = true;
                int size = st.size()-2;
                //out.println("size = " + size);
                for(int k=size;k>=0;k--)
                {
                    //out.println("ku");
                    if(first)
                    {
                        st.get(k).p_itog = st.get(k).p;
                        first = false;
                        continue;
                    }
                    try
                    {
                        if(st.get(k+1).p_itog == null || st.get(k).p == null)
                                throw new IOException("I am Exception Alpha!");
                        st.get(k).p_itog = st.get(k+1).p_itog + st.get(k).p;
                        //out.println("p itog after" + st.get(k).p_itog);
                    }catch (IOException e) {
                            System.out.println("Message: " + e.getMessage());
                        }
                }
            }
            
            for(int i=0;i<st.size();i++)
            {
                //out.println(st.get(i).strike + " " + st.get(i).c_settle + " " + st.get(i).c_interest + " " + st.get(i).c + " " + st.get(i).p_settle + " " + st.get(i).p_interest + " " + st.get(i).p);


                writer.write(st.get(i).strike + " " + st.get(i).c_settle + " " + st.get(i).c_interest + " " + st.get(i).p_settle + " " + st.get(i).p_interest + " " + st.get(i).c + " " + st.get(i).p + " " + st.get(i).c_itog + " " + st.get(i).p_itog + System.getProperty("line.separator"));
            }
            writer.close();
        }
    }
    
    public static Float pfloat(String s)
    {
        if (s.compareTo("-") == 0)
                {
                    s = "0.0";
                    //System.out.println("replace - " + s);
                }
        
        Pattern p = Pattern.compile("[0-9]*\\.[0-9]+");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        if (b)
        {
            //out.println("float sovpal");
            return Float.parseFloat(s);
        }
        
        //System.out.println("s = #" + s + "#");
        Pattern p1 = Pattern.compile("[0-9]*\\.$");
        Matcher m1 = p1.matcher(s);
        boolean b1 = m1.matches();
        if (b1)
        {
            //out.println(". float sovpal");
            s = s + "0";
            return Float.parseFloat(s);
        }
        
        //out.println("float ne sovpal = |" + s + "|");
        double d = 0.00001;
        float f = (float) d;
        return f;
    }
    
    public static int pint(String s)
    {
        if(s.length() <= 3)
            return Integer.parseInt(s);
        else
        {
            s = s.replaceAll(",", "");
            //out.println("replace " + s);
            return Integer.parseInt(s);
        }
        
    }
    
}
