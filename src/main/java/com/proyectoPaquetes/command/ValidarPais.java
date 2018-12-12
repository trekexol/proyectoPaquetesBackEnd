package com.proyectoPaquetes.command;

public class ValidarPais {

    String arrayPaises[] = {"Afganistán", "Albania", "Alemania", "Andorra", "Angola", "Argelia", "Argentina"
            , "Armenia", "Australia", "Bélgica","Brasil","Canadá", "Colombia", "Croacia", "Cuba", "Dinamarca", "Ecuador", "España", "Estados Unidos"
            , "Filipinas", "Francia", "Italia", "Japón", "Luxemburgo", "México", "Perú", "Portugal", "Rusia", "Uruguay", "Venezuela"
    };

    String arrayPaisesDosLetras[] ={"AF","AL","DE","AD","AO","DZ","AR","AM","AU","BE","BR","CA","CO","HR","CU","DK","EC","ES","US"
            ,"PH","FR","IT","JP","LU","MX","PE","PT","RU","UY","VE"};


    public String[] getArrayPaises() {
        return arrayPaises;
    }

    public void setArrayPaises(String[] arrayPaises) {
        this.arrayPaises = arrayPaises;
    }

    public String[] getArrayPaisesDosLetras() {
        return arrayPaisesDosLetras;
    }

    public void setArrayPaisesDosLetras(String[] arrayPaisesDosLetrar) {
        this.arrayPaisesDosLetras = arrayPaisesDosLetrar;
    }
}
