package com.mozosoft.zgr.kitapbul;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SyncStats;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by DELL on 28.11.2019.
 */

public class kitaplikVeriYaz extends AsyncTask<Void, Void, Void> {

    String ISBNY;
    String KitapAdY;
    String KitapYazarY;
    String YayinEviY;
    String AciklamaY;
    String ResimAdresY;
    String CihazIdY;
    String kitaplikVeriAdres;
    String AciklamaAdres, AciklamaBilgisi;
    WebView WebSayfasiY;
    Document doc;


    String veriAl(String adrs,String bulunacak,String son)
    {
        String result="";
        try {
            URL oracle = new URL(adrs);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            Boolean goster=false;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.indexOf(bulunacak) > 0) {
                    goster=true;
                }

                if(goster)
                    result += inputLine;

                if (inputLine.indexOf(son) > 0) {
                    break;
                }
            }
            in.close();

        }
        catch (Exception e)
        {
            //System.out.println("HATAAAA:"+e.getMessage());
        }

        result = "<html>"+result+"</html>";
        //System.out.println("WEBBU "+result);
        System.out.println(result);
        return result;



    }







    public kitaplikVeriYaz(String KitapAdX, String KitapYazarX, String YayinEviX, String ResimAdresX, String AdresX, String CihazIdX, WebView WebSayfasiX)
    {

        KitapAdY=KitapAdX;
        KitapYazarY=KitapYazarX;
        YayinEviY=YayinEviX;
        ResimAdresY=ResimAdresX;
        kitaplikVeriAdres=AdresX;
        CihazIdY=CihazIdX;
        WebSayfasiY=WebSayfasiX;


    }

    String authors;
    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {

        MainActivity.progressDialog2.show();
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document doc = Jsoup.connect(kitaplikVeriAdres).timeout(3000).userAgent("Mozilla").validateTLSCertificates(false).get();
            AciklamaY=kitaplikVeriAdres;
            try{

                Elements elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

                // //Log.w("ISBN", "ARAMAAAAAA");
                //Başlıklar Alındı
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.html();

                    if(kitaplikVeriAdres.indexOf("kitapyurdu.com")>0)
                    {
                        data = data.replaceAll("isbn\">", "978");
                    }


                    if(kitaplikVeriAdres.indexOf("amazon.com.tr")>0)
                    {

                        int sira = data.indexOf("978-");
                        if(sira>0) {
                            ISBNY = data.substring(sira, sira + 14);
                            ISBNY = ISBNY.replaceAll("-", "");
                            //System.out.println("ISBN BULDUM 1:" + ISBN);
                        }
                        else {
                            if (data.indexOf("asin: '") > 0) {
                                data = data.replaceAll("asin: '", "99999");
                                sira = data.indexOf("99999");
                                ISBNY = data.substring(sira + 5, sira + 15);
                                //System.out.println("ISBN BULDUM ASIN:" + ISBN);
                            }
                        }

                    }
                    else
                    {
                        Boolean numara = false;
                        int ISBNindex=0;

                        while(numara==false) {

                            int sira = data.indexOf("978",ISBNindex);
                            if(sira<0)
                                ISBNY="99999999999999999999";
                            else
                                ISBNY = data.substring(sira, sira + 13);
                            //System.out.println("ISBN BULDUM 2:" + ISBN);
                            ISBNindex=sira+20;
                            Character ISBNchar,dizichar;


                            String sayilarDizisi = "0123456789";

                            for (int i = 0; i < 13; i++) {
                                numara = false;
                                for (int k = 0; k < 10; k++) {
                                    ISBNchar=ISBNY.charAt(i);
                                    dizichar=sayilarDizisi.charAt(k);
                                    if (ISBNchar.equals(dizichar)) {
                                        numara = true;
                                    }
                                }
                                if (numara == false) {
                                    i=100;
                                }

                            }

                            if (numara == false) {
                                ISBNY = "";
                            }

                            //System.out.println("ISBN YAZDIR:" + ISBN);

                        }

                    }


                }
            }catch (Exception e){
                //  System.out.println("HATA VAR1"+e.getMessage());
            }




            System.out.println("ISBN BULDUK BIZ" + ISBNY);

            String KayitBilgisi ="http://www.bilisimkalesi.com/mobil/kitaplikYaz.php?ISBN="+ISBNY+"&CihazId="+CihazIdY +"&KitapAd="+KitapAdY+"&KitapYazar="+KitapYazarY+"&YayinEvi="+YayinEviY+"&Aciklama="+AciklamaY+"&ResimAdres="+ResimAdresY;

            KayitBilgisi=KayitBilgisi.replace("\"","");
            KayitBilgisi=KayitBilgisi.replace("\'","");

            InputStream is = null;
            URL url = new URL(KayitBilgisi);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000);       // if you want to set the Read timeout
            conn.setConnectTimeout(30000);    // if you want to set the Connect timeout
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //   is = conn.getInputStream();
                //  Reader reader = new InputStreamReader(is, "UTF-8");
                //  char[] buffer = new char[10240];
                //  int len = reader.read(buffer);
               // Toast.makeText(new MainActivity(), "Kitaplığa Eklendi.", Toast.LENGTH_SHORT).show();

            }
            if (is != null) is.close();
        }
        catch (Exception e)
        {}


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       // System.out.println("ISBN:"+ISBNY);


      //  String KayitBilgisi ="http://www.bilisimkalesi.com/mobil/kitaplikYaz.php?ISBN="+ISBNY+"&CihazId="+CihazIdY +"&KitapAd="+KitapAdY+"&KitapYazar="+KitapYazarY+"&YayinEvi="+YayinEviY+"&Aciklama="+AciklamaY+"&ResimAdres="+ResimAdresY;

      //  KayitBilgisi=KayitBilgisi.replace("\"","");
      //  KayitBilgisi=KayitBilgisi.replace("\'","");
      //  WebSayfasiY.loadUrl(KayitBilgisi);


        //System.out.println(ISBNY);
        MainActivity.progressDialog2.dismiss();



    }










}