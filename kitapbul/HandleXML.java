package com.mozosoft.zgr.kitapbul;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * Created by Sairamkrishna on 4/11/2015.
 */
public class HandleXML {

    int haberSayi=20;
    int gosterilenHaberSayi=0;
    private String [] adres = new String[haberSayi];
    private String [] baslik = new String[haberSayi];
    private String [] aciklama = new String[haberSayi];
    private String [] resim = new String[haberSayi];
    private String [] zaman = new String[haberSayi];
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    private int sayac=0;
    public int maxAciklama=200;

    public HandleXML(String url){
        this.urlString = url;
    }

    public String[] adresAl(){
        return adres;
    }

    public String[] baslikAl(){
        return baslik;
    }

    public String[] aciklamaAl(){
        return aciklama;
    }

    public String[] resimAl(){
        return resim;
    }

    public String[] zamanAl(){
        return zaman;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        sayac=0;
        try {
            event = myParser.getEventType();

            while ((sayac<haberSayi)&&(event != XmlPullParser.END_DOCUMENT)) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("item")){
                            sayac++;
                            gosterilenHaberSayi++;
                        }
                        else if(name.equals("entry")){
                        sayac++;
                        gosterilenHaberSayi++;
                    }
                        else if(name.equals("link")){
                            adres[sayac] = text.trim();
                            if(adres[sayac].length()<5)
                            {
                                adres[sayac]= myParser.getAttributeValue(null,"href");
                            }

                    }

                        else if(name.equals("title")){
                            baslik[sayac] = ayikla(text).trim();
                    }

                        else if(name.equals("pubDate")){
                            zaman[sayac] = text.trim();
                        }

                        else if(name.equals("image")){
                            resim[sayac] = text.trim();
                    } else if(name.equals("media:content")){
                            resim[sayac] = myParser.getAttributeValue(null,"url");
                    }
                        else if(name.equals("img300x300")){
                            resim[sayac] = text.trim();
                    } else if(name.equals("media:thumbnail")){
                            resim[sayac] = myParser.getAttributeValue(null,"url");
                    }

                        else if(name.equals("description")){
                            aciklama[sayac] = ayikla(text).trim();

                            if (aciklama[sayac].length()>maxAciklama)
                            {
                                aciklama[sayac]=aciklama[sayac].substring(0,maxAciklama)+"...";
                            }


                        }
                        else if(name.equals("summary")){
                            aciklama[sayac] = ayikla(text).trim();

                            if (aciklama[sayac].length()>maxAciklama)
                            {
                                aciklama[sayac]=aciklama[sayac].substring(0,maxAciklama)+"...";
                            }


                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
            Random rn = new Random();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }



    boolean sayfaAcik=false;
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {




                    //InputStream stream = new ByteArrayInputStream(getUrlSource(urlString).getBytes("UTF-8"));

                   // parcala(getUrlSource(urlString));

                   // //Log.w("deneme",getUrlSource(urlString));
                    //stream.close();

/*
                    String[] tokens = getUrlSource(urlString).split("item");


                    for (String t : tokens)

                    {
                        String[] tokens2 = t.split("link");
                        for (String a : tokens2)

                        {

                            if(tokens2)
                            //Log.w("",a);
                        }

                    }

*/


                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();



                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);



                    parseXMLAndStoreIt(myparser);
                    stream.close();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }

    public boolean parcalamaBitti=true;
    public void parcala(String metin)
    {
        String [] titles =new String[3];
        String [] links =new String[3];
        String [] descriptions =new String[3];


        String[] items = metin.split("item");
        for (int i=0;i<3;i++)
        {


            String [] titlesTmp=items[i+1].split("title");
            titles[i]=titlesTmp[1];

            String [] linkTmp=items[i+1].split("link");
            links[i]=linkTmp[1];

            String [] descriptionsTmp=items[i+1].split("description");
            descriptions[i]=descriptionsTmp[1];

        }

        adres=links;
        baslik=titles;
        aciklama=descriptions;
        parcalamaBitti=false;
        parsingComplete=false;

    }


    public String ayikla(String metin)
    {
        int ilk = metin.indexOf("<");
        int son = metin.lastIndexOf(">");
        metin = metin.replaceAll("<.*?>", "");
        metin=metin.replaceAll("&#39;","'");
        metin=metin.replaceAll("&#039;","'");
        return metin;
    }

}