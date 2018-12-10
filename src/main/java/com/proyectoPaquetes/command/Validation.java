package com.proyectoPaquetes.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validation {



    public Boolean esMayorDeEdad(Date fecha){

        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("d");//dd/MM/yyyy
        String op = formatar.format(data);
        formatar = new SimpleDateFormat("M");//dd/MM/yyyy
        String op2 = formatar.format(data);
        formatar = new SimpleDateFormat("y");//dd/MM/yyyy
        String op3 = formatar.format(data);

        Calendar calendar = Calendar.getInstance(); // Obtiene una instancia de Calendar
        calendar.setTime(fecha); // Asigna la fecha al Calendar

        if((Integer.parseInt(op3)-calendar.get(Calendar.YEAR)) >18){
            return true;

        }else if((Integer.parseInt(op3)-calendar.get(Calendar.YEAR)) ==18){
            if((calendar.get(Calendar.MONTH)+1<Integer.parseInt(op2))) {
                return true;

            }else if((calendar.get(Calendar.MONTH)+1==Integer.parseInt(op2))){
                if((calendar.get(Calendar.DAY_OF_MONTH)+1<=Integer.parseInt(op))){
                    return true;

                }else return false;

            }else return false;

        }else{
            return false;
        }


    }
}
