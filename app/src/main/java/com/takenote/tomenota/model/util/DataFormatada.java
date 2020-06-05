package com.takenote.tomenota.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatada {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static Date formadaTextoParaData(String textoData) {
        Date data = null;
        try {
            data = sdf.parse(textoData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String formataDataparaTexto(Date data) {
        return sdf.format(data);
    }

}
