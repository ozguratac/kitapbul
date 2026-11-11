package com.mozosoft.zgr.kitapbul;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by DELL on 27.11.2019.
 */





public class kitaplikVeriOku extends AsyncTask<Void, Void, List<kitaplikSablon>> {

    String android_id;
    List<kitaplikSablon> kitapBilgileri;
    public kitaplikVeriOku(String CihazId) {
    android_id=CihazId;
    kitapBilgileri=new ArrayList<>();

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<kitaplikSablon> doInBackground(Void... params) {


        try {

            Document doc = Jsoup.connect("http://www.bilisimkalesi.com/mobil/kitaplikOku.php?CihazId=" + android_id).timeout(5000).get();

            Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
            // Log.v("aranan kitap",kitap);
            elements = doc.select("liste");  // class ismi post-content olan verileri çekmek için

            for (org.jsoup.nodes.Element baslik : elements) {

                kitapBilgileri.add(new kitaplikSablon(baslik.attr("KitapAd"),baslik.attr("KitapYazar"),baslik.attr("YayinEvi"),baslik.attr("Aciklama"),baslik.attr("ResimAdres"),baslik.attr("id"),baslik.attr("durum"),baslik.attr("ISBN")));
            }

        } catch (Exception e) {

           // System.out.println("HATA VAR BURADA"+e.getMessage());
        }


        return kitapBilgileri;
    }

    @Override
    protected void onPostExecute(List<kitaplikSablon> kitapB) {
        super.onPostExecute(kitapB);
      //  System.out.println("SAYI:"+kitapB.size());


    }



}
