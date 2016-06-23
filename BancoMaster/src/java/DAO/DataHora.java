/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class DataHora {
    public String getDateTime() {
        //tipo datatime mysql: YYYY-MM-DD HH:MM:SS
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getDataTimePT_br(String data){
         String dia = data.substring(8, 10);
         String mes = data.substring(5, 7);
         String ano = data.substring(0, 4);
         
         data = dia+"/"+mes+"/"+ano;
         return data;
    }
}
