package com.mozosoft.zgr.kitapbul;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by DELL on 28.11.2019.
 */


public class isbnBul extends AsyncTask<Void, Void, String> {

    Document doc;
    String ISBN;
    String arananSayfa;
    public isbnBul(Document doc1, String Sayfa)
    {
        this.arananSayfa=Sayfa;
        this.doc=doc1;
        ISBN="";
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }






    @Override
    protected String doInBackground(Void... params) {
        try{

            Elements elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

            // //Log.w("ISBN", "ARAMAAAAAA");
            //Başlıklar Alındı
            for (org.jsoup.nodes.Element baslik : elements) {
                String data = baslik.html();

                if(arananSayfa.indexOf("kitapyurdu.com")>0)
                {
                    data = data.replaceAll("isbn\">", "978");
                }


                if(arananSayfa.indexOf("amazon.com.tr")>0)
                {

                    int sira = data.indexOf("978-");
                    if(sira>0) {
                        ISBN = data.substring(sira, sira + 14);
                        ISBN = ISBN.replaceAll("-", "");
                        //System.out.println("ISBN BULDUM 1:" + ISBN);
                    }
                    else {
                        if (data.indexOf("asin: '") > 0) {
                            data = data.replaceAll("asin: '", "99999");
                            sira = data.indexOf("99999");
                            ISBN = data.substring(sira + 5, sira + 15);
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
                            ISBN="99999999999999999999";
                        else
                            ISBN = data.substring(sira, sira + 13);
                        //System.out.println("ISBN BULDUM 2:" + ISBN);
                        ISBNindex=sira+20;
                        Character ISBNchar,dizichar;


                        String sayilarDizisi = "0123456789";

                        for (int i = 0; i < 13; i++) {
                            numara = false;
                            for (int k = 0; k < 10; k++) {
                                ISBNchar=ISBN.charAt(i);
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
                            ISBN = "";
                        }

                        //System.out.println("ISBN YAZDIR:" + ISBN);

                    }

                }


            }
        }catch (Exception e){
          //  System.out.println("HATA VAR1"+e.getMessage());
        }


        return ISBN;
    }

    @Override
    protected void onPostExecute(String isbn) {
        super.onPostExecute(isbn);
    }
}