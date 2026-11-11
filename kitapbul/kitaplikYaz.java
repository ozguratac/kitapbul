package com.mozosoft.zgr.kitapbul;

import android.webkit.WebView;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by DELL on 27.11.2019.
 */

public class kitaplikYaz {
    public kitaplikYaz(WebView web, String KitapAd, String KitapYazar, String YayinEvi, String Aciklama, String ResimAdres, String CihazId )
   // public kitaplikYaz( )
    {
        String KayitBilgisi ="http://www.bilisimkalesi.com/mobil/kitaplikYaz.php?CihazId="+CihazId +"&KitapAd="+KitapAd+"&KitapYazar="+KitapYazar+"&YayinEvi="+YayinEvi+"&Aciklama="+Aciklama+"&ResimAdres="+ResimAdres;
        web.loadUrl(KayitBilgisi);
    }
}
