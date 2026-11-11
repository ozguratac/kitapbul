package com.mozosoft.zgr.kitapbul;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.WebResponse;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mozosoft.zgr.kampanyam.R;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class MainActivity extends AppCompatActivity {
    AdView mAdView;
    AdRequest adRequest;

    public int[] populerYazarListe= {
            R.drawable.yazarbir,
            R.drawable.yazariki,
            R.drawable.yazaruc,
            R.drawable.yazardort,
            R.drawable.yazarbes,
    };



    public int[] listeResimlerOnSayfa= {
            R.drawable.idefix,
            R.drawable.dr,
            R.drawable.hepsiburada,
            R.drawable.kitapyurdu,
          //  R.drawable.babil,
            R.drawable.pandora,
            R.drawable.bkm,
            R.drawable.sec,
            R.drawable.kidega,
            //R.drawable.uka,
            // R.drawable.amazon,
            //  R.drawable.kitapkolik,
            // R.drawable.coksatan
    };








    public int[] listeResimler= {
            R.drawable.idefix,
            R.drawable.dr,
            R.drawable.alternatif,
            R.drawable.hepsiburada,
            R.drawable.kitapyurdu,
            R.drawable.babil,
            R.drawable.tamadres,
            R.drawable.kitaponalti,
            R.drawable.ist,
            R.drawable.bkm,
            R.drawable.ks,
            R.drawable.sec,
            R.drawable.pandora,
            R.drawable.kidega,
            R.drawable.uka,
            R.drawable.amazon,
            R.drawable.nadir,
            R.drawable.kitapkolik,
            R.drawable.babil,
            R.drawable.ilknokta,
            R.drawable.matik,
            R.drawable.trendyol,
            R.drawable.kitapuab


    };

    public int yazdirUrunSayi;
    public String android_id;


    private ZXingScannerView mScannerView;

    int gecisReklamSayi=0;
    int genislik;
    int yukseklik;


    int buyukAcButonyukseklik;


    private FrameLayout adContainerView;
    private AdView adView;


    private InterstitialAd mInterstitialAd = null;
    private AppOpenManager appOpenManager;


    @Override
    protected void onStart() {
        super.onStart();
        // Uygulama açıldığında reklamı göster

        System.out.println("açılış burası");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

       // progressDialog= new ProgressDialog(MainActivity.this);


       //SİLİNDİ
       // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       // StrictMode.setThreadPolicy(policy);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);




    //Açılış
        MobileAds.initialize(this, initializationStatus -> {});
        appOpenManager = new AppOpenManager(getApplication());
        appOpenManager.showAdIfAvailable();





        //sepetYaz();

/*
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

*/
        menuIslemler((ImageView)findViewById(R.id.imageView3));



        mAdView = (AdView) findViewById(R.id.bannerReklam);
       adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        AdRequest gecisReklam = new AdRequest.Builder().build();

        /*

        InterstitialAd.load(this,"ca-app-pub-4061139306454785/8827353185", gecisReklam,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                       System.out.println("REKLAM HAZIR");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                       // Log.i(TAG, loadAdError.getMessage());
                        System.out.println("HATA OLUŞTU");
                        mInterstitialAd = null;
                    }
                });

*/



        progressDialog2 = new ProgressDialog(MainActivity.this);
        // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog2.setMessage("Kitaplığa ekleniyor...");
        progressDialog2.setIndeterminate(false);





      //  new populerOku().execute();


        deleteCache(MainActivity.this);

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        System.out.println("cihazın id bilgisi:" + android_id);

        ekarama();



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        genislik = displayMetrics.widthPixels;
        yukseklik = displayMetrics.heightPixels;

        new herseyOku().execute();
        new tavsiyeOku().execute();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
               RelativeLayout ilkEkran = (RelativeLayout)findViewById(R.id.ilkEkran);
               ilkEkran.setVisibility(View.INVISIBLE);

            }
        }, 2500);   //3 seconds

       //////////////System.out.println(android_id);

/*

        try {
            jsonAl();
        }
        catch (Exception e)
        {
            //////////System.out.println("neden olmadı:"+e.getMessage());
        }
*/





        progressDialog = new ProgressDialog(MainActivity.this);



        SharedPreferences preferences = getSharedPreferences("progress2", MODE_PRIVATE);
        int appUsedCount = preferences.getInt("appUsedCount2",0);
        appUsedCount++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("appUsedCount2", appUsedCount);
        editor.apply();

        //Toast.makeText(MainActivity.this, String.valueOf(appUsedCount), Toast.LENGTH_SHORT).show();

        if (appUsedCount%20 == 5){
            AskForRating(appUsedCount);
        }








                //   gecisReklamGoster();
        resimBoyutlandir();





        EditText arananYazi=(EditText)findViewById(R.id.aramaMetin);

        arananYazi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ImageView metinSil =(ImageView)findViewById(R.id.metinSil2);

                if(s.toString().length()==0){
                    metinSil.setVisibility(View.INVISIBLE);
                }
                else
                {
                    metinSil.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




      //  genelWebOku();




        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[listeResimlerOnSayfa.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.anaListeResimler);

        linear.removeAllViews();

        int i=0;
        while(i<listeResimlerOnSayfa.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.anaekran, null, false);

            ImageView resim =(ImageView)layout[i].findViewById(R.id.listeResim);
            resim.setImageResource(listeResimlerOnSayfa[i]);


            final int tmp=i;

            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    RelativeLayout yeniCikan=(RelativeLayout)findViewById(R.id.yeniCikan);
                    RelativeLayout cokOkunan=(RelativeLayout)findViewById(R.id.cokOkunan);
                    TextView yeniMetin=(TextView)findViewById(R.id.yeniMetin);
                    TextView okunanMetin=(TextView)findViewById(R.id.okunanMetin);

                    yeniCikan.setBackgroundColor(Color.rgb(0x9e,0x9e,0x9e));
                    cokOkunan.setBackgroundColor(Color.rgb(0xff,0xff,0xff));
                    yeniMetin.setTextColor(Color.WHITE);
                    okunanMetin.setTextColor(Color.rgb(0x71,0x71,0x71));
                    listeDurum=1;
                   // yazdirSayfaSayi=1;



                    sonrakiSayfalar=false;
                    sonrakiSayfaIcerik="";
                    sonrakiSayfaNo=1;


                    acikSite="";


                    TextView cokSatanlar=(TextView)findViewById(R.id.cokSatanlar);
                    String cokSatanMetin="";



                    switch (tmp)
                    {

                        case 0:
                            cokSatanMetin="İDEFİX";
                            yazdirUrunSayi=9;
                            new idefixOku().execute();
                            acikSite="idefix";
                            break;
                        case 1:
                            cokSatanMetin="D&R";
                            yazdirUrunSayi=100;
                            new drOku().execute();
                            acikSite="dr";
                            break;
                        case 2:
                            cokSatanMetin="HEPSİBURADA";
                            yazdirUrunSayi=35;
                            acikSite="hepsiburada";
                            //new hepsiburadaOku().execute();
                            new GetData().execute();
                            break;
                        case 3:
                            cokSatanMetin="KİTAP YURDU";
                            yazdirUrunSayi=35;
                            new kitapYurduOku().execute();
                            acikSite="ky";
                            break;

                       /* case 4:
                            cokSatanMetin="EGENBA";
                            yazdirUrunSayi=35;
                            babilVeriOkuAl();
                            acikSite="bb";
                            break;
                         */

                        case 4:
                            cokSatanMetin="PANDORA";
                            yazdirUrunSayi=35;
                            new pandoraOku().execute();
                            acikSite="pandora";
                            break;
                        case 5:
                            cokSatanMetin="BKM KİTAP";
                            yazdirUrunSayi=35;
                            new bkmOku().execute();
                            acikSite="bkm";
                            break;

                        case 6:
                            cokSatanMetin="KİTAP SEÇ";
                            yazdirUrunSayi=35;
                            new secOku().execute();
                            acikSite="sec";
                            break;

                        case 7:
                            cokSatanMetin="KİDEGA KİTAP";
                            yazdirUrunSayi=35;
                            new kidegaOku().execute();
                            acikSite="kidega";
                            break;
                        case 8:
                            cokSatanMetin="UCUZ KİTAP AL";
                            yazdirUrunSayi=35;
                            new ukaOku().execute();
                            acikSite="uka";
                            break;

                      //  case 9:
                       //     cokSatanMetin="AMAZON KİTAP";
                      //      yazdirUrunSayi=35;
                      //      new amazonOku().execute();
                      //      acikSite="amazon";
                      //      break;
                        case 9:
                            cokSatanMetin="KİTAPKOLİK";
                            yazdirUrunSayi=35;
                            new kitapkolikOku().execute();
                            acikSite="kitapkolik";
                            break;

                        case 10:
                            cokSatanMetin="POPÜLER KİTAPLAR";
                            yazdirUrunSayi=35;
                            new populerOku().execute();
                            acikSite="populer";
                            break;
                    }

                   // cokSatanlar.setText(cokSatanMetin+" | ÇOK SATANLAR");
                    cokSatanlar.setText(cokSatanMetin);


                }

            });


            /*
            silButon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    kiloSilKontrol(numara);
                    kiloBoyListeYenile();
                }
            });

            duzenleButon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    kiloDuzenleAc(Integer.parseInt(numara));
                }
            });
*/

            linear.addView(layout[i]);
            i++;
        }


/*
        HandleXML obj = new HandleXML("http://www.teknosa.com/");
        obj.fetchXML();


        while (obj.parsingComplete);


        String[] haberler = obj.baslikAl();
        final String[] adresler = obj.adresAl();
        String[] aciklama = obj.aciklamaAl();
        String[] resim = obj.resimAl();
*/






        EditText aramaMetin = (EditText) findViewById(R.id.aramaMetin);
        aramaMetin.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                //System.out.println("tuş :" + keyCode);
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            klavyeGizle();
                            aramaYap(new View(MainActivity.this));
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });





        ImageView barkodButon = (ImageView)findViewById(R.id.barkodButon);
        barkodButon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               ////////////System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(activity2Intent);
            }
        });




    ara=(EditText)findViewById(R.id.aramaMetin);
    arama=this;



    //new idefixBarkod().execute();

        // DÜZELTME: Arayüz çizildikten sonra arka planda çalışması için post metodu kullanımı
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                girisYap();
            }
        });

        new sepetOkuSay().execute();

        //   Intent kitaplik = new Intent(this,kitaplik.class);
         //  startActivity(kitaplik);



        ImageView buyukAcButon =(ImageView)findViewById(R.id.buyukAcButon);
        buyukAcButonyukseklik = buyukAcButon.getLayoutParams().height;





        bildirimIslemleriBasla();








     //   deneme();

                //program sonu
    }


    public static EditText ara;
    public static MainActivity arama;
    public static void barkodArama(String arananMetin)
    {
       ara.setText(arananMetin);
       arama.tmp();
    }


    public void tmp()
    {
        aramaYap(findViewById(R.id.araButon));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    int sayfaNo=0;




    DownloadImageTask dwn;



    ProgressDialog  progressDialog;
    public static  ProgressDialog  progressDialog2;
    String [] basliklar = new String[500];
    String [] fotolar = new String[500];
    String [] fiyatlar = new String[500];
    String [] adresler = new String[500];
    String [] yazarlar = new String[500];
    String [] yayinEvi = new String[500];
    String [] isbnler = new String[500];









    List<String> basliklarListe = new ArrayList<>();
    List<String> fotolarListe = new ArrayList<>();
    List<String> fiyatlarListe = new ArrayList<>();
    List<String> adreslerListe = new ArrayList<>();
    List<String> yazarlarListe = new ArrayList<>();
    List<String> yayinEviListe = new ArrayList<>();
    List<String> isbnlerListe = new ArrayList<>();

    
    
    
    
    
    

    //isnbn dizisi oluştur favorilerden arama için

    String acikSite="";


    String ISBNsayfaAl="";

    String sepetResimtmp;

   // int yazdirSayfaSayi=1;


    
    

    String detayResim, detayAd, detayYazar,detayYayinEvi, detayAciklama, detayAdres;
    
    
    
    Boolean kitapSecildi = false;
    Boolean detayTiklandi=false;
    public void yazdir()
    {
        bulunanYuklenenResimSayisi=0;

        ////System.out.println(sonrakiSayfaIcerik);
            ////System.out.println();
        int s=0;
       // sayac=0;
        kitapSecildi = false;

            TextView cokSatanlar = (TextView) findViewById(R.id.cokSatanlar);
            RelativeLayout bar = (RelativeLayout) findViewById(R.id.bar);
            if (cokSatanlar.getText().toString().contains("ARAMA SONUÇLARI")) {
                bar.getLayoutParams().height = 0;
            } else {
                bar.getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
            }


            TextView yeniMetin = (TextView) findViewById(R.id.yeniMetin);
            TextView okunanMetin = (TextView) findViewById(R.id.okunanMetin);
            yeniMetin.setText("YENİ ÇIKANLAR");
            okunanMetin.setText("ÇOK SATANLAR");


            ////System.out.println("YAZDIR");
            try {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                RelativeLayout[] layout = new RelativeLayout[yazdirUrunSayi];
                LinearLayout linear = (LinearLayout) findViewById(R.id.listem);

                ScrollView kaydir = (ScrollView) findViewById(R.id.kaydirma);
                int konum = kaydir.getScrollY();




                if ((!sonrakiSayfalar)||(degisti)||(tamAramaBasladi)) {
                    kaydir.smoothScrollTo(0, 0);
                    linear.removeAllViews();
                    degisti=false;
                    tamAramaBasladi=false;

                    basliklarListe.clear();
                    fiyatlarListe.clear();
                    yazarlarListe.clear();
                    yayinEviListe.clear();
                    fotolarListe.clear();
                    adreslerListe.clear();
                    isbnlerListe.clear();
                    sonrakiSayfaNo=1;
                    
                    
                    
                    
                } else {
                    linear.removeViewAt(linear.getChildCount() - 1);
                    //  kaydir.smoothScrollTo(0,kaydir.getScrollY()+10);


                }
















                //  yazdirSayfaSayi++;

                ////System.out.println("URUN SAYI " + yazdirUrunSayi);


                int i = 0;
                while (i < yazdirUrunSayi) {
                    final int listeNumara=linear.getChildCount();
                  //  ////System.out.println(i);

                    layout[i] = (RelativeLayout) inflater.inflate(R.layout.mylist, null, false);

                    final TextView baslik = (TextView) layout[i].findViewById(R.id.baslik);
                    TextView fiyat = (TextView) layout[i].findViewById(R.id.fiyat);
                    TextView yazar = (TextView) layout[i].findViewById(R.id.yazar);
                    TextView yayinEvim = (TextView) layout[i].findViewById(R.id.yayinevi);
                    ImageView resim = (ImageView) layout[i].findViewById(R.id.tiklaButon);
                    ImageView kitaplikekleButon = (ImageView) layout[i].findViewById(R.id.kitaplikekleButon);
                    ImageView sepetEkleButon = (ImageView) layout[i].findViewById(R.id.sepetEkleButon);
                    ImageView detayButon = (ImageView) layout[i].findViewById(R.id.detayButon);



                    WebView resimWebBul = (WebView) layout[i].findViewById(R.id.resimWebBul);
                    final ImageView kitapResim = (ImageView) layout[i].findViewById(R.id.resim);

                    s = yukseklik / kitapResim.getLayoutParams().height + 1;
                    System.out.println("RESİM SAYI İÇ : "+s);
          //en son hata verdi. bulunan kitapları alta kaydırınca.
                    if(bulunanYuklenenResimSayisi<s) {
                        dwn = new DownloadImageTask(kitapResim);
                        dwn.execute(fotolar[i]);
                        bulunanYuklenenResimSayisi++;
                    }



              //      dwn = new DownloadImageTask(kitapResim);
                //    dwn.execute(fotolar[i]);


                    if ((acikSite == "bb") || (acikSite == "populer"))
                        kitaplikekleButon.setVisibility(View.INVISIBLE);
                    else
                        kitaplikekleButon.setVisibility(View.VISIBLE);


                    ////System.out.println("SIRA "+linear.getChildCount());



                    baslik.setText(basliklar[i]);
                    fiyat.setText(fiyatlar[i]);
                    yazar.setText(yazarlar[i]);
                    yayinEvim.setText(yayinEvi[i]);


                    basliklarListe.add(basliklar[i]);
                    fiyatlarListe.add(fiyatlar[i]);
                    yazarlarListe.add(yazarlar[i]);
                    yayinEviListe.add(yayinEvi[i]);
                    fotolarListe.add(fotolar[i]);
                    adreslerListe.add(adresler[i]);
                    isbnlerListe.add(isbnler[i]);









                    // resimWeb.loadData("<html><body><center><img height='100%' src='"+fotolar[i]+"'></center></body></html>", "text/html; charset=utf-8", "UTF-8" );

                    //new DownloadImageTask(resim).execute(fotolar[i]);

/*
                if (fotolar[i].contains("ucuzkitapal.com")) {
                    resimWebBul.loadData("<html><body><center><img style='margin-left:-30px;' height='100%' src='" + fotolar[i] + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");
                } else if (fotolar[i].contains("kitapsec.com")) {
                    resimWebBul.loadData("<html><body><center><img style='margin-left:-40px;' height='100%' src='" + fotolar[i] + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");

                } else {
                    resimWebBul.loadData("<html><body><center><img height='100%' src='" + fotolar[i] + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");
                }


                resimWebBul.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getAction() == MotionEvent.ACTION_MOVE);
                    }
                });

*/
                    if (yazarlar[0] == "")
                        yazar.getLayoutParams().height = 0;

                    final int tmp = i;





                /*
                final int x = i;
                sepetEkleButon.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        int sSayi=diziSay(sepetResim);

                        sepetResim[sSayi]=fotolar[x];
                        sepetFiyat[sSayi]=fiyatlar[x];
                        sepetSite[sSayi]=acikSite;
                        sepetAd[sSayi]=basliklar[x]+"***"+yazarlar[x];
                        sepetNumara[sSayi]=String.valueOf(System.currentTimeMillis());


                        sepetEkle(android_id,sepetAd[sepetSayi],sepetSite[sepetSayi],sepetFiyat[sepetSayi],sepetResim[sepetSayi],sepetNumara[sepetSayi],"");
                        Toast.makeText(getApplicationContext(), "Kitap sepete eklendi.", Toast.LENGTH_SHORT).show();

                    }

                });

             */


                    kitaplikekleButon.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {


                            WebView webKitaplik = (WebView) findViewById(R.id.webKitaplik);

                            new kitaplikVeriYaz(basliklarListe.get(listeNumara), yazarlarListe.get(listeNumara), yayinEviListe.get(listeNumara), fotolarListe.get(listeNumara), adreslerListe.get(listeNumara), android_id, webKitaplik).execute();


                        }

                    });








                    detayButon.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            detayTiklandi=true;
                            detayAd = basliklarListe.get(listeNumara);
                            detayYazar = yazarlarListe.get(listeNumara);
                            detayYayinEvi = yayinEviListe.get(listeNumara);
                            detayResim = fotolarListe.get(listeNumara);
                            AciklamaAdresDetay = adreslerListe.get(listeNumara);
                            detayAdres = adreslerListe.get(listeNumara);

                            progressDialog5 = new ProgressDialog(MainActivity.this);
                            progressDialog5.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog5.setMessage("Detaylar Yükleniyor...");
                            progressDialog5.setIndeterminate(false);
                            progressDialog5.show();



                            if(acikSite=="bb")
                            {
                                babilVeriAlSayfa(adreslerListe.get(listeNumara));
                            }
                            else {
                                new AciklamaAlDetay().execute();
                            }
                        }

                    });



/*
                    ImageView  kitaplikEkleYeniButon = (ImageView) findViewById(R.id.kitaplikEkleYeniButon);
                    kitaplikEkleYeniButon.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            String kBaslik = basliklarListe.get(listeNumara);
                            String kYazar = yazarlarListe.get(listeNumara);
                            String kYayin = yayinEviListe.get(listeNumara);
                            String kFoto = fotolarListe.get(listeNumara);
                            String disAcilacakAdres = adreslerListe.get(listeNumara);

                          //  WebView webKitaplik = (WebView) findViewById(R.id.webKitaplik);
                         //   new kitaplikVeriYaz(kBaslik, kYazar, kYayin, kFoto, disAcilacakAdres, android_id, webKitaplik).execute();

                            System.out.println("TIKLANDIIIIIII");
                        }

                    });



*/




                    //  if(acikSite=="idefix") {
                    resim.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
/*

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(MainActivity.this);
                            } else {
                                Log.d("TAG", "The interstitial ad wasn't ready yet.");
                            }
*/


                            // TODO Auto-generated method stub
                            //  Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(adresler[tmp]));
                            //  startActivity(in);
                            kitapSecildi = true;


                            kitap = basliklarListe.get(listeNumara);
                            yazarAd = yazarlarListe.get(listeNumara);
                            yayinEviMetin = yayinEviListe.get(listeNumara);
                            sepetResimtmp = fotolarListe.get(listeNumara);


                            detayAd=kitap;
                            detayYazar=yazarAd;
                            detayYayinEvi=yayinEviMetin;
                            detayResim=sepetResimtmp;
                            detayAdres = adreslerListe.get(listeNumara);



                          //  kitap = basliklar[tmp];
                          //  yazarAd = yazarlar[tmp];
                          //  yayinEviMetin = yayinEvi[tmp];
                          //  sepetResimtmp = fotolar[tmp];

                            //kirap yurdu için arama yaptık
                            kyAramakitap = kitap;
                            kyAramayazar = yazarAd;
                            kyAramayayinevi=yayinEviListe.get(listeNumara);


                            // //////////System.out.println("ADRES YAZDIR:"+adresler[tmp]);

                            try {
                                String[] parts = yazarAd.split(",");
                                kyAramayazar = parts[0].trim();
                            } catch (Exception e) {
                                kyAramayazar = yazarAd;
                            }

                            TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
                            TextView ad5 = (TextView) findViewById(R.id.kAd5);

                            //TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                            TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
                            ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);






                            yazar5.setText(yazarlarListe.get(listeNumara));
                            ad5.setText(basliklarListe.get(listeNumara));
                            //aciklama5.setText("");


                            yayinEvi5.setText(yayinEviListe.get(listeNumara));

                           // kitaplikResim5.setImageDrawable(kitapResim.getDrawable());
                            new DownloadImageTask(kitaplikResim5).execute(fotolarListe.get(listeNumara));


                            progressDialog = new ProgressDialog(MainActivity.this);
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog.setMessage("Lütfen Bekleyiniz...");
                            progressDialog.setIndeterminate(false);
                            progressDialog.show();


                            if (acikSite == "populer") {
                                ISBN = isbnlerListe.get(listeNumara);
                                siralamaPasif();
                                bulX = System.currentTimeMillis();
                                fiyatListeAc();
                                TextView aciklama5 = (TextView) findViewById(R.id.kitapAciklama5);
                                aciklama5.setText("");
                                new drBul().execute();
                                kitapArama(ISBN);

                            } else if (acikSite == "bb") {
                                AciklamaAdres = adreslerListe.get(listeNumara);
                                babilVeriAlSayfa(adreslerListe.get(listeNumara));

                            } else {
                                AciklamaAdres = adreslerListe.get(listeNumara);

                                new AciklamaAl().execute();
                                //   ISBNsayfaAl = adresler[tmp];
                            }


                        }

                    });


                    linear.addView(layout[i]);
                    i++;
                }


                if ((acikSite != "pandora") && (acikSite != "kitapkolik")&&(acikSite != "arama")) {

                    RelativeLayout dahaFazlaSatir = (RelativeLayout) inflater.inflate(R.layout.dahafazla, null, false);
                    dahaFazlaSatir.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            genelWebOku();

                        }

                    });
                    linear.addView(dahaFazlaSatir);
                }



                urunListeAc();


                //  klavyeKapat();

            } catch (Exception e) {

                //  //////////System.out.println("Bir hata Oluştu");
            }
        bulunanlarkaydirmaDurum ((ScrollView) findViewById(R.id.kaydirma),(LinearLayout)findViewById(R.id.listem),s);


    }

/*
    InterstitialAd mInterstitialAd;
    public void gecisReklamGoster()
    {
        MobileAds.initialize(MainActivity.this, "ca-app-pub-4061139306454785/8827353185");
        MobileAds.setAppMuted(true);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4061139306454785/8827353185");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });




    }


*/



    public void aramaYap(View view)
    {
        ekarama();


        EditText aramaMetin = (EditText)findViewById(R.id.aramaMetin);
        if(aramaMetin.length()>0) {
            aranacakKitap = aramaMetin.getText().toString();
           // new tamArama().execute();









            Arrays.fill(basliklar, null);
            Arrays.fill(fotolar, null);
            Arrays.fill(fiyatlar, null);
            Arrays.fill(adresler, null);
            Arrays.fill(yazarlar, null);
            Arrays.fill(yayinEvi, null);


            yazdirUrunSayi=0;
            bulunanSayisi=0;
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();


            if(ekaramaDurum.contains("kitapsec"))
                new secArama().execute();
            else if(ekaramaDurum.contains("kitapyurdu"))
                new kitapYurduArama().execute();
            else
                new idefixArama().execute();






            acikSite = "idefix";
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Aranacak bir metin giriniz.", Toast.LENGTH_SHORT).show();
        }
    }




    public void aramaYapMetin(String aranacakMetin)
    {
        if(aranacakMetin.length()>0) {
            aranacakKitap = aranacakMetin;
            new tamArama().execute();
            acikSite = "idefix";
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Aranacak bir metin giriniz.", Toast.LENGTH_SHORT).show();
        }
    }



    List<String> fiyatSonListe;
    List<String> adresSonListe;
    List<String> fiyatSonListeSirali;
    List<String> adresSonListeSirali;
    public void fiyatlariListeyeAktar()
    {
     fiyatSonListe = new ArrayList<>(Arrays.asList(drFiyat,idefixFiyat,alternatifFiyat,hbFiyat,kyFiyat,bbFiyat,kediFiyat,kitap16Fiyat,istFiyat,bkmFiyat,secFiyat,pandoraFiyat,kidegaFiyat,ukaFiyat,amazonFiyat,n11Fiyat,kitapkolikFiyat,nadirFiyat,ilknoktaFiyat,koalaFiyat,trendyolFiyat,kitap365Fiyat,kSepetiFiyat));
     adresSonListe = new ArrayList<>(Arrays.asList(drAdres,idefixAdres,alternatifAdres,hbAdres,kyAdres,bbAdres,kediAdres,kitap16Adres,istAdres,bkmAdres,secAdres,pandoraAdres,kidegaAdres,ukaAdres,amazonAdres,n11Adres,kitapkolikAdres,nadirAdres,ilknoktaAdres,koalaAdres,trendyolAdres,kitap365Adres,kSepetiAdres));


     fiyatSonListeSirali = new ArrayList<>(Arrays.asList(drFiyat,idefixFiyat,alternatifFiyat,hbFiyat,kyFiyat,bbFiyat,kediFiyat,kitap16Fiyat,istFiyat,bkmFiyat,secFiyat,pandoraFiyat,kidegaFiyat,ukaFiyat,amazonFiyat,n11Fiyat,kitapkolikFiyat,nadirFiyat,ilknoktaFiyat,koalaFiyat,trendyolFiyat,kitap365Fiyat,kSepetiFiyat));
     adresSonListeSirali = new ArrayList<>(Arrays.asList(drAdres,idefixAdres,alternatifAdres,hbAdres,kyAdres,bbAdres,kediAdres,kitap16Adres,istAdres,bkmAdres,secAdres,pandoraAdres,kidegaAdres,ukaAdres,amazonAdres,n11Adres,kitapkolikAdres,nadirAdres,ilknoktaAdres,koalaAdres,trendyolAdres,kitap365Adres,kSepetiAdres));


    }







    boolean cikis=true;
    boolean fiyatListeBasladi=false;
    int acilanKitapSayisi = 0;

    String [] bulunanFiyatlar;
    String [] bulunanAdresler;

    RelativeLayout [] katmanlar;
    boolean ilkAcilis = true;
    public void fiyatListeAc()
    {
        acilanKitapSayisi++;


        if ((acilanKitapSayisi>0) && (mInterstitialAd != null)) {
          //  mInterstitialAd.show(MainActivity.this);

        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }


        herseyiDurdur =true;
        urunListeKapat();

        //RelativeLayout teknosa = (RelativeLayout)findViewById(R.id.teknosaListe);
        //teknosa.setVisibility(View.INVISIBLE);

        RelativeLayout fiyatListe = (RelativeLayout)findViewById(R.id.fiyatListe);
        fiyatListe.setVisibility(View.VISIBLE);

        sayfaNo=2;
        cikis=false;

        kitapBulDurum=true;


        kitapBegenOku(ISBN);




/*
        RelativeLayout [] katmanlar={(RelativeLayout)findViewById(R.id.drKatman),
                (RelativeLayout)findViewById(R.id.idefixKatman),
                (RelativeLayout)findViewById(R.id.hbKatman),
                (RelativeLayout)findViewById(R.id.kyKatman),
                (RelativeLayout)findViewById(R.id.babilKatman),
                (RelativeLayout)findViewById(R.id.kediKatman),
                (RelativeLayout)findViewById(R.id.kitap16Katman),
                (RelativeLayout)findViewById(R.id.istKatman),
                (RelativeLayout)findViewById(R.id.bkmKatman),
                (RelativeLayout)findViewById(R.id.secKatman),
                (RelativeLayout)findViewById(R.id.pandoraKatman),
                (RelativeLayout)findViewById(R.id.kadigaKatman),
                (RelativeLayout)findViewById(R.id.ukaKatman),
                (RelativeLayout)findViewById(R.id.amazonKatman),
                (RelativeLayout)findViewById(R.id.n11Katman),
                (RelativeLayout)findViewById(R.id.kitapkolikKatman),
                (RelativeLayout)findViewById(R.id.nadirKatman),
                (RelativeLayout)findViewById(R.id.ilknoktaKatman),
                (RelativeLayout)findViewById(R.id.koalaKatman),
                (RelativeLayout)findViewById(R.id.trendyolKatman),
                (RelativeLayout)findViewById(R.id.kitap365Katman),
                (RelativeLayout)findViewById(R.id.ksKatman)

        };
*/




        //Burada Fiyatların her bir satırı döngü ile alınacak böylece kaç tane varsa o akdar kullanılacak. ÇALIŞTIIIIIIII



      if(ilkAcilis) {

          LinearLayout fiyatListeKatmanlar = (LinearLayout) findViewById(R.id.listeFiyatlar);
          int fiyatsayisi = fiyatListeKatmanlar.getChildCount();
          katmanlar = new RelativeLayout[fiyatsayisi];

          bulunanFiyatlar = new String[fiyatsayisi];
          bulunanAdresler = new String[fiyatsayisi];


          for (int i = 0; i < fiyatsayisi; i++) {
              katmanlar[i] = (RelativeLayout) fiyatListeKatmanlar.getChildAt(i);
          }
          //-----------------------------------------------------

          ilkAcilis = false;
      }







        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listeFiyatlar);
        linearLayout.removeAllViews();

        for(int i=0;i<katmanlar.length;i++)
        {

            linearLayout.addView(katmanlar[i]);
            TextView fiyatMetin = (TextView) katmanlar[i].getChildAt(1);
            fiyatMetin.setText("Bekleniyor...");
            bulunanFiyatlar[i] ="";
            bulunanAdresler[i] = null;

            //System.out.println("FİYATLAR BURAYA:" + fiyatMetin.getText());
        }






/*


        TextView a =(TextView)findViewById(R.id.drFiyat);
        TextView b =(TextView)findViewById(R.id.idefixFiyat);
        TextView c =(TextView)findViewById(R.id.hbFiyat);
        TextView d =(TextView)findViewById(R.id.kyFiyat);
        TextView e =(TextView)findViewById(R.id.babilFiyat);
        TextView f =(TextView)findViewById(R.id.pandoraFiyat);
        TextView g =(TextView)findViewById(R.id.bkmFiyat);
        TextView h =(TextView)findViewById(R.id.secFiyat);
        TextView i =(TextView)findViewById(R.id.kadigaFiyat);
        TextView j =(TextView)findViewById(R.id.ukaFiyat);
        TextView k =(TextView)findViewById(R.id.amazonFiyat);
        TextView l =(TextView)findViewById(R.id.n11Fiyat);
        TextView m =(TextView)findViewById(R.id.kitapkolikFiyat);
        TextView n =(TextView)findViewById(R.id.nadirFiyat);
        TextView o =(TextView)findViewById(R.id.ilknoktaFiyat);
        TextView p =(TextView)findViewById(R.id.ksFiyat);
        TextView r =(TextView)findViewById(R.id.kediFiyat);
        TextView s =(TextView)findViewById(R.id.kitap16Fiyat);
        TextView t =(TextView)findViewById(R.id.istFiyat);
        TextView u =(TextView)findViewById(R.id.koalaFiyat);
        TextView v =(TextView)findViewById(R.id.trendyolFiyat);
        TextView y =(TextView)findViewById(R.id.kitap365Fiyat);



        a.setText("Bekleniyor...");
        b.setText("Bekleniyor...");
        c.setText("Bekleniyor...");
        d.setText("Bekleniyor...");
        e.setText("Bekleniyor...");
        f.setText("Bekleniyor...");
        g.setText("Bekleniyor...");
        h.setText("Bekleniyor...");
        i.setText("Bekleniyor...");
        j.setText("Bekleniyor...");
        k.setText("Bekleniyor...");
        l.setText("Bekleniyor...");
        m.setText("Bekleniyor...");
        n.setText("Bekleniyor...");
        o.setText("Bekleniyor...");
        p.setText("Bekleniyor...");
        r.setText("Bekleniyor...");
        s.setText("Bekleniyor...");
        t.setText("Bekleniyor...");
        u.setText("Bekleniyor...");
        v.setText("Bekleniyor...");
        y.setText("Bekleniyor...");


*/






        drFiyat="";
        idefixFiyat="";
        hbFiyat="";
        kyFiyat="";
        bbFiyat="";
        kediFiyat="";
        pandoraFiyat="";
        kitap16Fiyat="";
        bkmFiyat="";
        kSepetiFiyat="";
        secFiyat="";
        kidegaFiyat="";
        ukaFiyat="";
        amazonFiyat="";
        kitapkolikFiyat="";
        n11Fiyat="";
        nadirFiyat="";
        ilknoktaFiyat="";
        istFiyat="";
        koalaFiyat="";
        trendyolFiyat="";
        kitap365Fiyat="";
        alternatifFiyat="";





        drAdres=null;
        idefixAdres=null;
        hbAdres=null;
        kyAdres=null;
        bbAdres=null;
        kediAdres=null;
        pandoraAdres=null;
        kitap16Adres=null;
        bkmAdres=null;
        kSepetiAdres=null;
        secAdres=null;
        kidegaAdres=null;
        ukaAdres=null;
        amazonAdres=null;
        kitapkolikAdres=null;
        n11Adres=null;
        nadirAdres=null;
        ilknoktaAdres=null;
        istAdres=null;
        koalaAdres=null;
        trendyolAdres=null;
        kitap365Adres=null;
        alternatifAdres=null;





     //   fiyatlariListeyeAktar();

        /*
        fiyatSonListeSirali.clear();
        adresSonListeSirali.clear();
        fiyatSonListe.clear();
        adresSonListe.clear();
*/



        ScrollView kaydir = (ScrollView) findViewById(R.id.scrollView5);
        kaydir.smoothScrollTo(0,0);

    }

    String kitap365Fiyat, kitap365Adres;


    public void fiyatListeKapat()
    {
        kitapSecildi = false;
        bulunanYuklenenResimSayisi=0;
        sayfaNo=0;
        anaListeAc();
        kitapBulDurum=false;
        bulX=System.currentTimeMillis();
        RelativeLayout fiyatListe = (RelativeLayout)findViewById(R.id.fiyatListe);
        fiyatListe.setVisibility(View.INVISIBLE);


    }



    public void fiyatListeKapat2()
    {
        kitapSecildi = false;
        bulunanYuklenenResimSayisi=0;
        sayfaNo=0;
        urunListeAc();
        kitapBulDurum=false;
        bulX=System.currentTimeMillis();
        RelativeLayout fiyatListe = (RelativeLayout)findViewById(R.id.fiyatListe);
        fiyatListe.setVisibility(View.INVISIBLE);



    }

    public void anaListeKapat()
    {
      //  RelativeLayout anaListe = (RelativeLayout)findViewById(R.id.anaListe);
      //  anaListe.setVisibility(View.INVISIBLE);

        RelativeLayout yeni = (RelativeLayout)findViewById(R.id.yeni);
        yeni.setVisibility(View.INVISIBLE);


    }

    public void urunListeAc()
    {
        cikis=false;
        anaListeKapat();
        sepetKatmanKapat();
        RelativeLayout fiyatListe = (RelativeLayout)findViewById(R.id.fiyatListe);
        fiyatListe.setVisibility(View.INVISIBLE);

        RelativeLayout teknosa = (RelativeLayout)findViewById(R.id.teknosaListe);
        teknosa.setVisibility(View.VISIBLE);

    }


    public void anaListeAc()
    {
       // RelativeLayout anaListe = (RelativeLayout)findViewById(R.id.anaListe);
      //  anaListe.setVisibility(View.VISIBLE);

        RelativeLayout yeni = (RelativeLayout)findViewById(R.id.yeni);
        yeni.setVisibility(View.VISIBLE);


        cikis=true;
    }

    public void urunListeKapat()
    {
        anaListeAc();
        RelativeLayout teknosa = (RelativeLayout)findViewById(R.id.teknosaListe);
        teknosa.setVisibility(View.INVISIBLE);

    }



    boolean anaSayfadanGeldi = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            if(cikis)
            {
                super.finish();
            }

            if(kitapWebAcik)
            {
                webGorunumKapat(new View(MainActivity.this));
            }
            else if(buyukResimAcildi)
            {
                buyukResimKapat();
            }
            else if(sepetAcildi)
            {
                sepetKatmanKapat();
            }
            else if(sayfaNo==2) {
                fiyatListeKapat2();
                webGorunumKapat(new View(MainActivity.this));
                if(anaSayfadanGeldi) {
                    anaSayfaGit(new View(MainActivity.this));
                }

            }
            else
            {
                fiyatListeKapat();
                urunListeKapat();
                dwn=null;
                webGorunumKapat(new View(MainActivity.this));
            }



            return true;
        }
        return false;
    }








    //TEKNOSA OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class populerOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="http://www.bilisimkalesi.com/kitap/populerAramaOku.php";
                else
                    sayfa="http://www.bilisimkalesi.com/kitap/populerAramaOku.php";

                //  //////////System.out.println("sayafa"+yazdirSayfaSayi);

                Document doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("kitap ad");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }
                yazdirUrunSayi=i;



                //REsim adresleri alındı
                elements = doc.select("kitap resim");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("kitap yazar");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    fiyatlar[i]="";
                    adresler[i]="";

                    i++;
                }


                //yayınevi  alındı
                elements = doc.select("kitap isbn");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    isbnler[i]=data;
                  //  yayinEvi[i]=data;
                    i++;
                    // Log.v("yayınevi: ",data);
                }

                //yayınevi  alındı
                elements = doc.select("kitap yayinevi");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    yayinEvi[i]=data;

                    i++;

                }


                elements = doc.select("kitap adres");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    adresler[i]=data;

                    i++;

                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

     //      cokArananlarKatmaniGoster();
          //  yazarKatmanGoster();
         //   siteKatmanGoster();


        }
    }




    //TEKNOSA OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class sonArananlarOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="http://bilisimkalesi.com/kitap/sonAramalarOku.php";
                else
                    sayfa="http://bilisimkalesi.com/kitap/sonAramalarOku.php";

                //  //////////System.out.println("sayafa"+yazdirSayfaSayi);

                Document doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("kitap ad");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                  //  sonbasliklar[i]=data;
                    i++;
                }
                yazdirUrunSayi=i;



                //REsim adresleri alındı
                elements = doc.select("kitap resim");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                  //  sonfotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("kitap yazar");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                   // sonyazarlar[i]=data;
                   // sonfiyatlar[i]="";

                    i++;
                }


                //yayınevi  alındı
                elements = doc.select("kitap isbn");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                  //  sonisbnler[i]=data;
                    //  yayinEvi[i]=data;
                    i++;
                    // Log.v("yayınevi: ",data);
                }

                //yayınevi  alındı
                elements = doc.select("kitap yayinevi");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                   // sonyayinEvi[i]=data;

                    i++;

                }


                elements = doc.select("kitap adres");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    //sonadresler[i]=data;
                    //System.out.println("adresler: "+data);
                    i++;

                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

           // populerKatmaniGoster();
        }
    }






    //TEKNOSA OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------


    String [] yazarBilgi;
    String [] yazarResim;
    String [] yazarBilgiAdres;


    private class yazarlarOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="http://bilisimkalesi.com/kitap/yazarlarOku.php";
                else
                    sayfa="http://bilisimkalesi.com/kitap/yazarlarOku.php";

                //  //////////System.out.println("sayafa"+yazdirSayfaSayi);

                Document doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("yazarlar yazar");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarBilgi[i]=baslik.text();
                    i++;
                }


                elements = doc.select("yazarlar resim");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarResim[i]=baslik.text();
                    i++;
                }

                elements = doc.select("yazarlar adres");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarBilgiAdres[i]=baslik.text();
                    i++;
                }



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            yazarKatmanGoster();
        }
    }







    String [] tanitimAd, tanitimYazar, tanitimResim, tanitimISBN, tanitimYayinevi,tanitimAciklama, tanitimAdres;



    private class tanitimOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="http://bilisimkalesi.com/kitap/tanitimOku.php";
                else
                    sayfa="http://bilisimkalesi.com/kitap/tanitimOku.php";

                //  //////////System.out.println("sayafa"+yazdirSayfaSayi);

                Document doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("tanitim");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    i++;
                }

                int kayitSayisi = i;

                tanitimAd = new String[kayitSayisi];
                tanitimYazar = new String[kayitSayisi];
                tanitimResim = new String[kayitSayisi];
                tanitimISBN = new String[kayitSayisi];
                tanitimYayinevi = new String[kayitSayisi];
                tanitimAciklama = new String[kayitSayisi];
                tanitimAdres = new String[kayitSayisi];


                elements = doc.select("tanitim ad");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimAd[i]=baslik.text();
                    //System.out.println("TANITIM ISIMLER:"+baslik.text());
                    i++;
                }



                elements = doc.select("tanitim yazar");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimYazar[i]=baslik.text();
                    i++;
                }

                elements = doc.select("tanitim resim");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimResim[i]=baslik.text();
                    i++;
                }

                elements = doc.select("tanitim isbn");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimISBN[i]=baslik.text();
                    i++;
                }

                elements = doc.select("tanitim yayinevi");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimYayinevi[i]=baslik.text();
                    i++;
                }

                elements = doc.select("tanitim aciklama");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimAciklama[i]=baslik.text();
                    i++;
                }

                elements = doc.select("tanitim adres");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    tanitimAdres[i]=baslik.text();
                    i++;
                }



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            tanitimKatmanGoster();
        }
    }













    


    //HEPSİBURADA OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class hepsiburadaOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc;
                if(sonrakiSayfalar)
                    doc = Jsoup.parse(sonrakiSayfaIcerik);
                else
                    doc = Jsoup.parse(hepsiburadaMetin);


                //fiyatlar  alındı
                Elements elements = doc.select("div[class=_18vgy _3Wn9b hbBox]");  // class ismi post-content olan verileri çekmek için
                int i=0;
                int x=0;
                List<Integer> atla = new ArrayList<>();
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    // ////////System.out.println("SATIR: "+data);
                    if((data.contains("alabilirsiniz"))||(data.contains("edilememektedir"))) {
                        atla.add(i);
                        x++;
                        ////////System.out.println("hatalı satır "+i);
                    }
                    i++;
                }










                elements = doc.select("div[class=_18vgy _3Wn9b hbBox] div[class=_1W97b hbBoxName]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;

                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    try {
                        String[] parts = data.split("-");
                        basliklar[i] = parts[0].trim();
                        yazarlar[i] = parts[1].trim();
                    }
                    catch(Exception e)
                    {
                        basliklar[i]=data;
                        yazarlar[i]=null;
                    }
                   // //System.out.println(yazarlar[i]);

                    i++;
                }
                yazdirUrunSayi=i;
                // Log.v("sayı:",String.valueOf(i));








                //REsim adresleri alındı
                elements = doc.select("div[class=_18vgy _3Wn9b hbBox] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                String sorgu="";
                for (org.jsoup.nodes.Element baslik : elements) {
                    if (!sorgu.equals(baslik.attr("alt")))
                    {
                        String data = baslik.attr("src");
                        fotolar[i] = data;
                        //Log.w("resimler", data);
                        i++;
                        sorgu=baslik.attr("alt");
                        // Log.v("baslık:",sorgu);
                    }


                }


                //Yazar  alındı
                /*
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-2 pName] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }
*/


                elements = doc.select("div[class=_18vgy _3Wn9b hbBox]");  // class ismi post-content olan verileri çekmek için
                i=0;
                List<Boolean> sepette = new ArrayList<>();

                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.html();
                    if(data.contains("_3DpWc _2XE3f priceWrapper")) {
                        sepette.add(true);
                    }
                    else {
                        sepette.add(false);
                    }
                    i++;
                }







                //fiyatlar  alındı
//                elements = doc.select("div[id=productresults] li div[class=product-detail] div[class=price-container highlight-badge  hb-pl-cn] span[class=price product-price]");  // class ismi post-content olan verileri çekmek için
                elements = doc.select("div[class=_18vgy _3Wn9b hbBox]");  // class ismi post-content olan verileri çekmek için
                //  Elements sepetsiz= doc.select("div[id=productresults] li div[class=product-detail] div[class=price-container highlight-badge  hb-pl-cn] span[class=price product-price]");
                //  Elements sepetli= doc.select("div[id=productresults] li div[class=product-detail] div[class=price-container highlight-badge hb-pl-cn] span[class=price product-price]");

                i=0;

                for (org.jsoup.nodes.Element baslik : elements) {

                 /*
                    String data;
                    baslik.text(baslik.text().replaceAll("price product-price","price-value"));
                    data= baslik.select("div[class=last-price hb-pl-cn] div[class=price-value]").get(0).text();
                    if(data.contains("TL")) {
                        fiyatlar[i] = data;

                    }
                    else
                    {
                        fiyatlar[i]="---";
                    }

                    i++;
                    */


                    String data="";
                    if(sepette.get(i)) {

                        data= baslik.select("div[class=_3tolU hbPriceContent] div[class=_3DpWc _2XE3f priceWrapper]").get(0).text();
                        //   ////////System.out.println(data);
                    }else {
                        try {
                            data = baslik.select("div[class=_3tolU hbPriceContent] div[class=_3DpWc _3bvB8 priceWrapper]").get(0).text();
                        }catch (Exception e){
                            data="---";
                        }
                        //  ////////System.out.println(data);
                    }

                    //String data = baslik.text() ;

                    if(data.contains("TL")) {
                        fiyatlar[i] = data;

                    }
                    else
                    {
                        fiyatlar[i]="---";
                    }


                    i++;





                }





                //Adresler  alındı
                elements = doc.select("div[class=_18vgy _3Wn9b hbBox] a[class=_3yPhD]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    if( (i>0) && (data == adresler[i-1]) )
                        continue;
                    else {


                        System.out.println(data + " " + i);
                        adresler[i] = data;
                        yayinEvi[i] = "";
                        i++;
                    }

                }


/*
                elements = doc.select("div[class=_18vgy _3Wn9b hbBox] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.hepsiburada.com"+baslik.attr("href");
                    //////////System.out.println(data);
                    adresler[i]=data;
                    yayinEvi[i]="";

                    i++;
                }
*/

                int hataSayisi=fiyatlar.length+atla.size();


                String [] temp= new String[hataSayisi];

                int a=0;
                for (String bilgi:fiyatlar) {
                    for (int hata:atla) {
                        if(hata==a)
                        {
                            temp[a]="SATIŞTA DEĞİL";

                            break;

                        }
                        a++;
                    }
                    temp[a]=bilgi;
                    a++;
                }

                fiyatlar=temp;






            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
                //   if(a!=null)
                //Log.w("deneme", a);
            }
            yazdir();
            progressDialog.dismiss();
        }
    }






















    //TEKNOSA OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class idefixOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ////////System.out.println("GIRIS YAPILDI");
            if(progressDialog.isShowing()==true)
                progressDialog.dismiss();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.idefix.com/Kategori_/Kitap/En-Yeniler/11693/3";
                else
                    sayfa="https://www.idefix.com/CokSatanlar/Kitap";

              //  //////////System.out.println("sayafa"+yazdirSayfaSayi);
                Document doc;
                if(sonrakiSayfalar)
                    doc = Jsoup.parse(sonrakiSayfaIcerik);

                else
                    doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();


                Elements elements = doc.select("div[class=item product-box-alternative] div[class=box-title] a");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("title");
                    basliklar[i]=data;
                    i++;
                }
                yazdirUrunSayi=i;



                //REsim adresleri alındı
                elements = doc.select("div[class=item product-box-alternative] a[class=product-image] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-2 pName] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-4] span[id=prices]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-title] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.idefix.com"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }


                //yayınevi  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-3 manufacturerName] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    yayinEvi[i]=data;
                    i++;
                    // Log.v("yayınevi: ",data);
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();

        }
    }






    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class secOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.kitapsec.com/Yeni-Cikanlar/Edebiyat/";
                else
                    sayfa="https://www.kitapsec.com/Cok-Satanlar/Edebiyat/";



                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;

                Document doc = Jsoup.connect(sayfa).timeout(10000).get();
                Elements elements = doc.select("tbody tr td a[class=text]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("tbody tr td a[class=img] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https:"+baslik.attr("src");
                    fotolar[i]=data;
                   // //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("tbody tr td span[class=fiyat] font[class=satis]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                //fiyatlar  alındı
                elements = doc.select("tbody tr td span[itemprop=author] span");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("tbody tr td a[class=img]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }

                elements = doc.select("tbody tr td span[class=yynImg] span");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }





                for(i=0;i<basliklar.length;i++)
                {
                    basliklar[i]=basliklar[i].replaceAll(yazarlar[i],"");
                    basliklar[i]=basliklar[i].replaceAll(yayinEvi[i],"");
                }




            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
             //   if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }









    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class kidegaOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa;
                if(listeDurum==1)
                    sayfa="https://kidega.com/cok-satan-kitaplar";
                else
                    sayfa="https://kidega.com/cok-satan-kitaplar";



                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;

                Document doc = Jsoup.connect(sayfa).timeout(10000).get();

                Elements elements = doc.select("div[class=prd-body itemHeader] h3");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }

                yazdirUrunSayi=i;



                elements = doc.select("div[class=prd-body itemHeader] span[class=manufacturer-name]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                 i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //REsim adresleri alındı
                elements = doc.select("div[class=prd-image image] div[id=plhUrun_urunResim] img[class=lazyload]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-original");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    ////////////System.out.println(data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=prd-footer itemFooter] div[class=urunListe_satisFiyat]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text().replaceAll("₺","TL");
                    fiyatlar[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("div[class=prd-inner itemWrap] a[class=prd-lnk]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://kidega.com" + baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }

                elements = doc.select("div[class=prd-body itemHeader] span[class=distributor-name]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
              //  if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }









    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class ukaOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{


                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.ucuzkitapal.com/yeni-urunler/?items_per_page=16&page=1";
                else
                    sayfa="https://www.ucuzkitapal.com/cok-satanlar/?items_per_page=16&page=1";

                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;

                Document doc = Jsoup.connect(sayfa).timeout(10000).get();
                Elements elements = doc.select("div[class=ty-grid-list__item] div[class=tyurunadi]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    yazarlar[i]="";
                    yayinEvi[i]="";

                    String data = baslik.text();
                    String[] parts = data.split(" - ");

                 //   //////////System.out.println("UZUNLUK:"+parts.length);

                    if(parts.length>0)
                        basliklar[i] = parts[0];
                    if(parts.length>1)
                        yazarlar[i] = parts[1];
                    if(parts.length>2)
                        yayinEvi[i] = parts[2];

                    i++;
                }

                yazdirUrunSayi=i;





                //REsim adresleri alındı
                elements = doc.select("div[class=ty-grid-list__item] div[class=ty-grid-list__image] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    ////////////System.out.println(data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=ty-grid-list__item] div[class=ty-grid-list__price ] span[class=ty-price-bloklar]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                elements = doc.select("div[class=ty-grid-list__item] div[class=ty-grid-list__image] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }







            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }







    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class amazonOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc = Jsoup.connect("https://www.amazon.com.tr/gp/bestsellers/books/ref=sv_books_0").timeout(10000).get();
                Elements elements = doc.select("span[class=a-list-item] a[class=a-link-normal] div[class=p13n-sc-truncate p13n-sc-line-clamp-1]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    yayinEvi[i]="";
                    i++;
                }

                yazdirUrunSayi=i;



                elements = doc.select("span[class=a-list-item] span[class=a-size-small a-color-base]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //REsim adresleri alındı
                elements = doc.select("span[class=a-list-item] div[class=a-section a-spacing-small] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    data=data.replaceAll("SR200,200","SR88,150");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    ////////////System.out.println(data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("span[class=a-list-item] span[class=p13n-sc-price]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data.replaceAll("₺","")+" TL";
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("span[class=a-list-item] a[class=a-link-normal]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.amazon.com.tr"+baslik.attr("href");
                    if(!data.contains("product-reviews"))
                    {
                        adresler[i] = data;
                        i++;
                       ////////////System.out.println("ADRESLER:"+data);

                    }
                    //break;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
              //  if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }














    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class kitapkolikOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{


                String sayfa="";
                if(listeDurum==1)
                    sayfa="http://www.bilisimkalesi.com/sadeManset/besleme.php?url=https://www.kitapkolik.com/yeni-cikan-kitaplar";
                else
                    sayfa="http://www.bilisimkalesi.com/sadeManset/besleme.php?url=https://www.kitapkolik.com/cok-satan-kitaplar";



                Document doc = Jsoup.connect(sayfa).timeout(10000).get();
                Elements elements = doc.select("div[id=list-slide1041] a[class=col col-12 productDescription text-center text-description detailLink]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                   ////////////System.out.println(data);
                }


                yazdirUrunSayi=i;



                elements = doc.select("div[id=list-slide1041] a[class=col productModel text-center col-12 text-title text-semibold]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //REsim adresleri alındı
                elements = doc.select("div[id=list-slide1041] img[class=active btn-radius]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    ////////////System.out.println(data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[id=list-slide1041] div[class=text-custom-pink text-bold currentPrice]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("div[id=list-slide1041] a[class=image-wrapper fl detailLink]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.kitapkolik.com"+baslik.attr("href");
                   adresler[i] = data;
                   i++;

                }

                elements = doc.select("div[id=list-slide1041] a[class=col col-12 text-center productBrand text-semibold]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
            yazdir();
            progressDialog.dismiss();
        }
    }

























    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class trendyolOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc = Jsoup.connect("https://www.trendyol.com/kitap?siralama=4").timeout(10000).get();
                Elements elements = doc.select("div[class=prdct-cntnr-wrppr] div[class=p-card-wrppr] div[class=prdct-desc-cntnr-wrppr] span[class=prdct-desc-cntnr-name hasRatings]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    yazarlar[i]="";
                    i++;
                }

                yazdirUrunSayi=i;




                //REsim adresleri alındı
                elements = doc.select("div[class=prdct-cntnr-wrppr] div[class=p-card-wrppr] div[class=prdct-desc-cntnr-wrppr] span[clas");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    ////////////System.out.println(data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("span[class=a-list-item] span[class=p13n-sc-price]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data.replaceAll("₺","")+" TL";
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("span[class=a-list-item] a[class=a-link-normal]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.amazon.com.tr"+baslik.attr("href");
                    if(!data.contains("product-reviews"))
                    {
                        adresler[i] = data;
                        i++;
                       ////////////System.out.println("ADRESLER:"+data);

                    }
                    //break;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
              //  if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }

























    //D&R OKUMA BÖLÜMÜ
    //-----------------------------------------------------------------------------------
    private class drOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.dr.com.tr/Kategori_/Kitap/En-Yeniler/10001/3";
                else
                    sayfa="https://www.dr.com.tr/CokSatanlar/Kitap";



                Document doc;
                if(sonrakiSayfalar)
                    doc = Jsoup.parse(sonrakiSayfaIcerik);
                else
                    doc = Jsoup.connect(sayfa).timeout(10000).get();

                Elements elements = doc.select("div[class=prd-main-wrapper] a[class=prd-name]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=prd-main-wrapper] div[class=product-img] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=prd-main-wrapper] div[class=prd-price]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }

                elements = doc.select("div[class=prd-main-wrapper] div[class=prd-row]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[class=prd-main-wrapper] div[class=product-img] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "http://www.dr.com.tr"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }


                elements = doc.select("div[class=prd-main-wrapper] a[class=prd-publisher]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }



    private class kitapYurduOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=2&filter_in_stock=1&filter_in_stock=1&page=1";
                else
                    sayfa="https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=1";

                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;

                Document  doc = Jsoup.connect(sayfa).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                doc.select("div[class=price-old price-passive]").remove();

                Elements elements = doc.select("div[class=name ellipsis]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
                // Log.v("KİTAP SAYISI:",String.valueOf(i));

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=image] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=price] span[class=value]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text()+" TL";
                    fiyatlar[i]=data;
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[class=author compact ellipsis] ");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[class=image] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }


                elements = doc.select("div[class=publisher]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data.replaceAll(",","");
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }









    private class babilOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc;
                if(sonrakiSayfalar)
                    doc = Jsoup.parse(sonrakiSayfaIcerik);
                else
                    doc = Jsoup.parse(babilDokumanOku);

                Elements elements = doc.select("div[class=row] div[class=plist-item clearfix] div[class=col-md-9 col-sm-9 col-xs-12] h2");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
                // Log.v("KİTAP SAYISI:",String.valueOf(i));

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=row] div[class=plist-item clearfix] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=row] div[class=plist-item clearfix] div[class=col-md-9 col-sm-9 col-xs-12] div span[class=new-price]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[class=row] div[class=plist-item clearfix] div[class=col-md-9 col-sm-9 col-xs-12] h3[class=author] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                elements = doc.select("div[class=row] div[class=plist-item clearfix] div[class=col-md-9 col-sm-9 col-xs-12] h4[class=store] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("div[class=row] div[class=plist-item clearfix] div[class=plist-image-wrapper] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.babil.com"+baslik.attr("href");
                    //////////System.out.println("ADRES"+data);

                    adresler[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
                //if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }


















    private class pandoraOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{


                Document doc = Jsoup.connect("https://www.pandora.com.tr/Cok_Satan_Kitaplar").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                Elements elements = doc.select("div[class=liste sol] ul li h4[class=yeniKitapBaslik]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
                // Log.v("KİTAP SAYISI PANDORA:",String.valueOf(i));

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=liste sol] ul li div[class=coverWrapper posRel] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.pandora.com.tr"+baslik.attr("src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=liste sol] ul li p[class=yeniKitapIndirimli indirimliFiyat]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text().replace("Site Fiyatı: ","");
                    fiyatlar[i]=data;
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[class=cokSatanlarWr] div[class=liste sol] ul li p[class=yeniKitapYazar] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[class=cokSatanlarWr] div[class=liste sol] ul li div[class=coverWrapper posRel] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.pandora.com.tr"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }

                elements = doc.select("div[class=cokSatanlarWr] div[class=liste sol] ul li h4[class=yeniKitapYayinEvi]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
             //   if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }










    private class bkmOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa="https://www.bkmkitap.com/yeni-cikan-kitaplar";
                else
                    sayfa="https://www.bkmkitap.com/kitap/cok-satan-kitaplar";


                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;


                Document doc = Jsoup.connect(sayfa).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                Elements elements = doc.select("div[id=list-slide1003] a[class=fl col-12 text-description detailLink]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
               // Log.v("KİTAP SAYISI PANDORA:",String.valueOf(i));

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[id=list-slide1003] span[class=imgInner] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[id=list-slide1003] div[class=col col-12 currentPrice]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[id=list-slide1003] a[id=productModelText]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[id=list-slide1003] a[class=fl col-12 text-description detailLink]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.bkmkitap.com"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }

                elements = doc.select("div[id=list-slide1003] a[class=col col-12 text-title mt]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }


























    String aranacakKitap;

    //D&R ARAMA YAPMA
    //-----------------------------------------------------------------------------------
    private class drArama extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc = Jsoup.connect("https://www.dr.com.tr/Search?q="+aranacakKitap).timeout(10000).get();
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=container] div[class=list-cell] figure a img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=container] div[class=list-cell] div[class=details] span[class=price]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }

                elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=who]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "http://www.dr.com.tr"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
             yazdir();
            progressDialog.dismiss();
        }
    }







    String barkodSayfa="";
    String ISBN="";
    private class idefixBarkod extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                //   Document doc = Jsoup.connect("https://www.idefix.com/search/?ActiveCategoryId=11693&Q="+aranacakKitap+"&SortType=soldcount&SortOrder=desc").timeout(10000).validateTLSCertificates(false).get();
                Document doc = Jsoup.connect(barkodSayfa).timeout(5000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=product-description] div[class=row] li a");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    //basliklar[i]=data;
                    ////Log.w("ISBN", data);
                    ISBN=data;
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


        }
    }



    public void idefixBarkod2 (String ISBNsayfa)
    {
            try{
                Document doc = Jsoup.connect(ISBNsayfa).timeout(3000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=product-description] div[class=row] li a");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    //basliklar[i]=data;
                    //Log.w("ISBN", data);
                    ISBN=data;
                }


            }catch (Exception e){

                e.printStackTrace();
            }

    }


    public void hbBarkod (String ISBNsayfa)
    {

        try{
            Document doc = Jsoup.connect(ISBNsayfa).get();
            Elements elements = doc.select("script");  // class ismi post-content olan verileri çekmek için

            // //Log.w("ISBN", "ARAMAAAAAA");
            //Başlıklar Alındı
            for (org.jsoup.nodes.Element baslik : elements) {
                String data = baslik.html();
               //////////////System.out.println("BASLIK"+data);
                //basliklar[i]=data;
                if(data.indexOf("978")>0) {
                    int sira=data.indexOf("978");
                    ISBN = data.substring(sira,sira+13);
                   ////////////System.out.println(ISBN);
                    break;


                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void drBarkod (String ISBNsayfa)
    {

        try{
            Document doc = Jsoup.connect(ISBNsayfa).get();
            Elements elements = doc.select("div[class=specs] ul");  // class ismi post-content olan verileri çekmek için

            // //Log.w("ISBN", "ARAMAAAAAA");
            //Başlıklar Alındı
            for (org.jsoup.nodes.Element baslik : elements) {
                String data = baslik.html();


                    int sira=data.indexOf("978");
                    ISBN = data.substring(sira,sira+13);
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public void siteBarkodAl (String ISBNsayfa)
    {

        try{
            Document doc = Jsoup.connect(ISBNsayfa).get();
            Elements elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

            // //Log.w("ISBN", "ARAMAAAAAA");
            //Başlıklar Alındı
            for (org.jsoup.nodes.Element baslik : elements) {
                String data = baslik.html();


                if(ISBNsayfa.indexOf("amazon.com.tr")>0)
                {
                    int sira=data.indexOf("978-");
                    ISBN = data.substring(sira,sira+14);
                    ISBN=ISBN.replaceAll("-","");





                   ////////////System.out.println(ISBN);

                }
                else
                {
                    int sira=data.indexOf("978");
                    ISBN = data.substring(sira,sira+13);
                }





                break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }






    public class siteBarkodAlOku extends AsyncTask<Void, Void, Void> {
        String authors;


        @Override
        protected Void doInBackground(Void... params) {
            try{
                Document doc;
                if(ISBNsayfaAl.indexOf("hepsiburada")>0)
                {
                    doc = Jsoup.parse(veriAl(ISBNsayfaAl,"978","978"));
                }
                 else  if(ISBNsayfaAl.indexOf("babil")>0)
                {
                    doc = Jsoup.parse(babilDokumanSayfa);
                }
            //     else if(ISBNsayfaAl.indexOf("1000kitap")>0)
             //   {
              //      String binKitapMetin=yazbakalim(ISBNsayfaAl);
//                     doc = Jsoup.parse(binKitapMetin);
            //    }
                else {
                    doc = Jsoup.parse(aciklamaDokuman);
               //     doc = Jsoup.connect(ISBNsayfaAl).timeout(5000).userAgent("Mozilla").validateTLSCertificates(false).get();
                }


                Elements elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

                // //Log.w("ISBN", "ARAMAAAAAA");
                //Başlıklar Alındı
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.html();

                    if(ISBNsayfaAl.indexOf("kitapyurdu.com")>0)
                    {
                        //data = data.replaceAll("isbn\">", "978");
                        Elements isbnNumarasi = baslik.select("div[class=attributes] tbody tr");

                        for (org.jsoup.nodes.Element a:isbnNumarasi) {
                            //  //////////System.out.println("GOSTER:"+a.text());
                            if(a.text().contains("ISBN"))
                            {
                                if(a.text().contains("978"))
                                    ISBN = a.text().replaceAll("ISBN: ","");
                                else
                                    ISBN = a.text().replaceAll("ISBN: ","978");

                                break;
                            }
                        }




                    }
                    else if(ISBNsayfaAl.indexOf("amazon.com.tr")>0)
                    {
                        Elements isbnBilgiler =baslik.select("div[class=content] li");
                     //  //////////System.out.println("BURADAYDINIZ:"+baslik.text());

                        for (org.jsoup.nodes.Element a:isbnBilgiler) {
                          //  //////////System.out.println("GOSTER:"+a.text());
                            if(a.text().contains("978"))
                            {
                                ISBN=a.text().replaceAll("-","");
                                break;
                            }
                        }

                    }

                    else if(ISBNsayfaAl.indexOf("1000kitap.com")>0)
                    {
                        System.out.println("100 ADRES: "+ISBNsayfaAl);

                        Elements isbnBilgiler =baslik.select("meta[property=book:isbn]");

                                data=isbnBilgiler.attr("content");
                                data=data.replaceAll("-","");
                                ISBN=data;

                                System.out.println(ISBN + " 10000000000000000");
                                break;
                    }
/*
                   else if(ISBNsayfaAl.indexOf("1000kitap.com")>0)
                    {
                        Elements isbnBilgiler =baslik.select("div[class=kutu bilgiler] div[class=oge]");
                        for (org.jsoup.nodes.Element a:isbnBilgiler) {
                            if(a.outerHtml().contains("ISBN"))
                            {
                                data=a.select("div[class=bilgi]").get(0).text();
                                if(data.contains("-"))
                                    data=data.replaceAll("-","");
                                ISBN=data;
                                break;
                            }
                        }
                       // data = data.replaceAll("isbn\">", "978");
                    }*/
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
                           ////////////System.out.println("ISBN BULDUM 2:" + ISBN);
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

                           ////////////System.out.println("ISBN YAZDIR:" + ISBN);

                        }











                    }



                    //978 nerde olsa buluyor ve yanılıyor
                    //0123456789 şeklinde bir sayı ile karşılaştırılabilir.



                }
            }catch (Exception e){

               // //////////System.out.println("AMAZON "+e.getMessage());
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();

           // //////////System.out.println("BULUNAN ISBN:"+ISBN);
            siralamaPasif();

            bulX=System.currentTimeMillis();
            fiyatListeAc();

           // ISBN.replaceFirst(".$","");
            new drBul().execute();
          /*
            new idefixBul().execute();
            new hbBul().execute();
            new kyBul().execute();
            //  new bbBul().execute();
            new bkmBul().execute();
            new secBul().execute();
            new pandoraBul().execute();
            new kidegaBul().execute();
            new ukaBul().execute();
            new amazonBul().execute();
            new kitapkolikBul().execute();
           ////////////System.out.println("SON");
*/
            kitapArama(ISBN);

        }
    }




























    Document doc1,doc2,doc3,doc4,doc5,doc6,doc7,doc8,doc9,doc10;











/*


    boolean kitapBulDurum=false;
    String yayinEviMetin;
    int bulunanSayisi=0;
    //İDEFİX ARAMA YAPMA
    //-----------------------------------------------------------------------------------
    private class idefixArama extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
               // //////////System.out.println(aranacakKitap);
                if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    aranacakKitap=aranacakKitap.replaceAll(" ","-");
                }
                bulunanSayisi=0;
             //   Document doc = Jsoup.connect("https://www.idefix.com/search/?ActiveCategoryId=11693&Q="+aranacakKitap+"&SortType=soldcount&SortOrder=desc").timeout(10000).validateTLSCertificates(false).get();
                Document doc = Jsoup.connect("https://www.idefix.com/search/?ActiveCategoryId=11693&Q="+aranacakKitap).timeout(5000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=item product-box-alternative] div[class=box-title] a");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("title");
                    basliklar[i]=data;
                    i++;
                }
                yazdirUrunSayi=i;
                bulunanSayisi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=item product-box-alternative] a[class=product-image] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-2 pName] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-4] span[id=prices]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]="";
                   // fiyatlar[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-title] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.idefix.com"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }



                //yayınevi  alındı
                elements = doc.select("div[class=item product-box-alternative] div[class=box-line-3 manufacturerName] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    yayinEvi[i]=data;
                    i++;
                    // Log.v("yayınevi: ",data);
                }

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
               // if(a!=null)
                    //Log.w("deneme", a);
            }
            progressDialog.dismiss();
            if(bulunanSayisi==0)
            {
                Toast.makeText(getApplicationContext(), "Aradığınız kriterlere uygun kayıt bulunamadı.", Toast.LENGTH_SHORT).show();
            }
            else
            {
               // klavyeKapat();
                klavyeGizle();
                ////System.out.println("DENEME"); yazdir();
            }




        }
    }




*/


 public String yazbakalim(String s)
 {
     String veri="";
     try{
         URL google = new URL(s);
         BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
         String input;
         StringBuffer stringBuffer = new StringBuffer();
         while ((input = in.readLine()) != null)
         {
             stringBuffer.append(input);
         }
         in.close();
         String htmlData = stringBuffer.toString();
         System.out.println("YAZ BAKALIM "+htmlData);
         veri=htmlData;
     }
     catch(Exception e)
     {}
     return veri;
 }


    String kitapLink="";

    private class tamArama extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {


            Arrays.fill(basliklar, null);
            Arrays.fill(fotolar, null);
            Arrays.fill(fiyatlar, null);
            Arrays.fill(adresler, null);
            Arrays.fill(yazarlar, null);
            Arrays.fill(yayinEvi, null);


            yazdirUrunSayi=0;
            bulunanSayisi=0;
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
            //progressDialog.setCancelable(false);
            super.onPreExecute();



        }

        @Override
        protected Void doInBackground(Void... params) {
            if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                aranacakKitap=aranacakKitap.replaceAll(" ","-");
            }

            try {
               // String binKitapMetin=yazbakalim("https://1000kitap.com/ara?q=" + aranacakKitap);
                Document doc = Jsoup.connect("https://1000kitap.com/ara?q=" + aranacakKitap).userAgent("Mozilla").timeout(7000).validateTLSCertificates(true).get();
                  //Document doc = Jsoup.parse(binKitapMetin);
                System.out.println("YAZDIR BAKALIM" + doc.text());



                Elements elements = doc.select("meta[property=og:url]");  // class ismi post-content olan verileri çekmek için

                kitapLink = elements.attr("content");

              if(kitapLink.contains("ara?q="))
                  kitapLink = kitapLink+ "&b=kitaplar";
              else
                  kitapLink = kitapLink + "/kitaplar";

             //   System.out.println(kitapLink);
                /*
                if(!kitapLink.contains("http"))
                {
                    kitapLink="https://1000kitap.com/"+kitapLink;
                }
                */

             System.out.println("EVREKA:"+kitapLink);


            }catch (Exception e){
                System.out.println("HATA ŞU : " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new tamAramaAlt().execute();
        }
    }




    boolean kitapBulDurum=false;
    String yayinEviMetin;
    int bulunanSayisi=0;
    //İDEFİX ARAMA YAPMA
    //-----------------------------------------------------------------------------------
    private class tamAramaAlt extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    kitapLink=kitapLink.replaceAll(" ","-");
                }
                bulunanSayisi=0;


             //   String binKitapMetin=yazbakalim(kitapLink);
                Document doc = Jsoup.connect(kitapLink).timeout(10000).userAgent("Mozilla").validateTLSCertificates(true).get();
              //  Document doc = Jsoup.parse(binKitapMetin);


                System.out.println("YAZDIR AÇILANI" + doc.html());

                //Document doc = Jsoup.connect(kitapLink).timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("article[class=css-1dbjc4n] div[class=css-901oao css-1hf3ou5 r-cqee49 r-l30xua r-ubezar r-b88u0q r-waer2z r-13wfysu]");

                // class ismi post-content olan verileri çekmek için
               // elements.select("span[class=alt-baslik]").remove();
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();

                    try{
                         int silinecekKonum = data.indexOf(". ");
                         data = data.substring(silinecekKonum+2,data.length());
                         basliklar[i]=data;
                    }
                    catch (Exception e)
                    {
                        basliklar[i]=data;
                    }
                    i++;
                    System.out.println(data);
                }
                yazdirUrunSayi=i;
                bulunanSayisi=i;

             //   System.out.println("BULUNAN KİTAP SAYISI: "+bulunanSayisi );
                //REsim adresleri alındı
                elements = doc.select("article[class=css-1dbjc4n] div[class=css-1dbjc4n r-z2wwpe r-1wfhzrg r-1udh08x r-1glkqn6] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");

                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    System.out.println("BULUNAN RESİM:" + data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("article[class=css-1dbjc4n] div[class=css-901oao css-1hf3ou5 r-1quzx5i r-l30xua r-a023e6 r-7ptqe7 r-13wfysu]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }


                //Adresler  alındı
                elements = doc.select("article[class =css-1dbjc4n] a[class=css-4rbku5 css-18t94o4 css-1dbjc4n r-1loqt21 r-1otgn73]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data ="https://1000kitap.com" + baslik.attr("href");
                    adresler[i]=data;
                    yayinEvi[i]="";
                    fiyatlar[i]="";
                    i++;

                  //  System.out.println("1000ADRESLER : "+ data);
                }


/*
                i=0;
              //  System.out.println("YAYINEVLERİ" + doc.outerHtml());
                Matcher matcher = Pattern.compile("yayinevi\":\"(.*?)\",\"baskiayi").matcher(doc.outerHtml());
                // String begenmeSayi="";
                while (matcher.find()) {
                 //   System.out.println(matcher.group().replace("yayinevi\":\"","").replace("\",\"baskiayi",""));
                    yayinEvi[i]=matcher.group().replace("yayinevi\":\"","").replace("\",\"baskiayi","");
                    //yayinEvi[i]="aaa"+i;
                    i++;
                }
*/


            }catch (Exception e){

             System.out.println("YİNE HATA:"+ e.getMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

/*
            progressDialog.dismiss();
            if(bulunanSayisi==0)
            {
                Toast.makeText(getApplicationContext(), "Aradığınız kriterlere uygun kayıt bulunamadı.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // klavyeKapat();
                klavyeGizle();
                ////System.out.println("DENEME"); yazdir();
            }
*/


            if(ekaramaDurum.contains("kitapsec"))
                new secArama().execute();
            else if(ekaramaDurum.contains("kitapyurdu"))
                new kitapYurduArama().execute();
            else
                new idefixArama().execute();

        }
    }


































    String kitap,yazarAd;

double bulX;
int aramaSira;
String drFiyat;
String drAdres="";

    private class drBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;
        String authors;
        @Override
        protected void onPreExecute() {
          //  bkmVeriAl();
        //    ksepetiVeriAl();
            super.onPreExecute();



        }

        @Override
        protected Void doInBackground(Void... params) {
            drFiyat=null;
            drAdres=null;
            try{
                aramaSira=1;

                Document doc = Jsoup.connect("https://www.dr.com.tr/search?q="+ISBN).timeout(3000).validateTLSCertificates(false).get();
               // Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

/*
                int i=0;
                elements = doc.select("div[class=container] div[class=list-cell] div[class=details] div[class=summary] a[class=who mb10]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();

                    if(data.contains(yayinEviMetin))
                        break;
                    i++;
                    // Log.v("yayınevi: ",data);
                }


*/




               // String [] fiyatlar = new String[100];
              //  String [] webAdresler = new String[100];


               // int m=0;
                //fiyatlar  alındı
                Elements elements = doc.select("meta[property=og:url]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("content");
                    drAdres=data;
                   // m++;
                }




               // m=0;
                //fiyatlar  alındı
                elements = doc.select("div[class=price-info] span[class=current-price js-text-current-price]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    drFiyat=data;
                 //   m++;
                }
              //  drFiyat=fiyatlar[i];
               // drAdres=webAdresler[i];




            }catch (Exception e){

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            if(bulX==bulY)
            {  TextView dF = (TextView)findViewById(R.id.drFiyat);
                if(drFiyat==null)
                    drFiyat="-----";
                dF.setText(drFiyat);
                herseyiDurdur=false;
               // new idefixBul().execute();

                System.out.println("DÖNGÜ BAŞLASIN");
                callSitesInLoop();
            }
            else
            {
                drFiyat="-----";
            }

        }
    }




    String kyAramakitap,kyAramayazar,kyAramayayinevi;




    String idefixAdres="";

    String idefixFiyat;
    private class idefixBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            idefixFiyat=null;
            idefixAdres=null;
            try{
               // Document doc = Jsoup.connect("https://www.idefix.com/search/?Q="+kitap+" "+yazarAd+"&SortType=soldcount&SortOrder=desc").timeout(100000).validateTLSCertificates(false).get();
                aramaSira=2;

                Document doc = Jsoup.connect("http://bilisimkalesi.com/kitap/kitap_bul/siteler/idefix.php?isbn="+ISBN).timeout(5000).validateTLSCertificates(false).get();


                idefixAdres = doc.select("kitap adres").first().text();
                idefixFiyat = doc.select("kitap fiyat").first().text();




            }catch (Exception e){

                e.printStackTrace();
                System.out.println("idefix hata");
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.idefixFiyat);
                if(idefixFiyat==null)
                    idefixFiyat="-----";
                ideF.setText(idefixFiyat);
                sirala(new View(MainActivity.this));
               // new hbBul().execute();
                new alternatifBul().execute();
                System.out.println("BAŞLADIM");
              //  new GetDataBKM().execute();
              //  new hepsiBuradaBul().execute();

            }
            else
            {
                idefixFiyat="-----";
            }

        }
    }














    String aciklamaDokuman="";

    String AciklamaAdres="";
    String AciklamaBilgisi="";
    private class AciklamaAl extends AsyncTask<Void, Void, Void> {
        String tamAramaYayinEvi=null;
        String authors;
        @Override
        protected void onPreExecute() {
            tamAramaYayinEvi="";
            AciklamaBilgisi="";
            super.onPreExecute();




        }


        @Override
        protected Void doInBackground(Void... params) {
           System.out.println("ADRES BURADA: "+AciklamaAdres);
            try{
                Document doc;
                if(AciklamaAdres.indexOf("hepsiburada")>0)
                {
                    doc = Jsoup.parse(veriAl(AciklamaAdres,"productDescriptionContent","ctl00_ContentPlaceHolder1"));
                }
                else if(AciklamaAdres.indexOf("babil")>0)
                {
                    doc = Jsoup.parse(babilDokumanSayfa);
                }

             //   else if(AciklamaAdres.indexOf("1000kitap")>0)
             //   {
              //      String binKitapMetin=yazbakalim(AciklamaAdres);
              //      // Document doc = Jsoup.connect("https://1000kitap.com/ara?q=" + aranacakKitap).timeout(10000).validateTLSCertificates(false).get();
              //      doc = Jsoup.parse(binKitapMetin);
              //  }
                else {
                    System.out.println("AÇIKLAMA AZ KALDI ADRES: "+AciklamaAdres);
                    doc = Jsoup.connect(AciklamaAdres).timeout(3000).userAgent("Mozilla").validateTLSCertificates(false).get();
                }

                aciklamaDokuman = doc.html();
                //BURADA BABİL İÇİN DE SİTEDEN VERİ ALINACAK. ISBN VE AÇIKLAMA

               // //////////System.out.println("ADRES:"+AciklamaAdres);
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için



                if(AciklamaAdres.contains("istanbulkitapcisi.com")) {

                    elements = doc.select("div[id=product-fullbody]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                       // //////////System.out.println("ACIKLAMA:"+AciklamaBilgisi);

                        break;

                    }
                }

                else if(AciklamaAdres.contains("dr.com")) {

                    elements = doc.select("div[class=product-description-header section-cover mb-40 fs-3_5]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("hepsiburada.com")) {

                    elements = doc.select("div[id=productDescriptionContent]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("kitapyurdu.com")) {

                    elements = doc.select("div[class=pr_description] span[class=info__text]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("babil.com")) {

                    elements = doc.select("div[id=description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("pandora.com")) {

                    elements = doc.select("div[class=row urunAciklama]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("bkmkitap.com")) {

                    elements = doc.select("div[id=productDetailTab]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                       // int yer=0;
                        //yer=data.indexOf("Kısa Özet");
                        //yer=yer+20;

                       // AciklamaBilgisi = data.substring(yer,data.length()-1);
                        AciklamaBilgisi=data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("kitapsec.com")) {
                    elements = doc.select("div[class=TabDivList] div[id=tab1]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    elements.select("strong").remove();
                    elements.select("div[class=magaza-bilgi]").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("kidega.com")) {
                    elements = doc.select("div[class=col-md-8 col-xs-12] div[id=bookDescription]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("ucuzkitapal.com")) {
                    elements = doc.select("div[id=content_description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("amazon.com")) {
                    elements = doc.select("div[id=bookDescription_feature_div]");  // class ismi post-content olan verileri çekmek için
                    elements.select("div[id=psPlaceHolder]").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("kitapkolik.com")) {
                    elements = doc.select("div[id=divOnyazi]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("1000kitap.com")) {
                    elements = doc.select("div[class=dr w-full overflow-hidden max-h-28 cursor] span[class=text-alt]");  // class ismi post-content olan verileri çekmek için
                 //   AciklamaBilgisi=elements.get(0).text();
                    System.out.println("BURAYA KADAR DOĞRU" + doc);



/*



                    String geciciAciklama="";
                    //  System.out.println("YAYINEVLERİ" + doc.outerHtml());
                    Matcher matcher = Pattern.compile(",\"bilgi\":\"(.*?)\",\"alisveris").matcher(doc.toString());
                    // String begenmeSayi="";
                    while (matcher.find()) {
                        //   System.out.println(matcher.group().replace("yayinevi\":\"","").replace("\",\"baskiayi",""));
                        geciciAciklama=matcher.group().replace("bilgi\":\"","").replace("\",\"alisveris","");
                        System.out.println("BULDUM BEEEEEE");
                        break;

                    }

                    AciklamaBilgisi = geciciAciklama;
                    System.out.println("BULUNAN ACIKLAMA:" + AciklamaBilgisi);
*/
                   // elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=bilgi]");  // class ismi post-content olan verileri çekmek için
                   // TextView yevi = (TextView)findViewById(R.id.kYayinevi5);
                 //   yevi.setText("deneme");



/*
                    int say=0;
                    elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=baslik]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        if(data.contains("Yayınevi"))
                        {
                            break;
                        }
                        say++;
                    }
                    elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=bilgi]");  // class ismi post-content olan verileri çekmek için


                  //  TextView yevi = (TextView)findViewById(R.id.kYayinevi5);
                    try {
                        tamAramaYayinEvi=elements.get(say).text();
                      //  //////////System.out.println(tamAramaYayinEvi);
                     //   yevi.setText(tamAramaYayinEvi);

                    }catch (Exception e)
                    {
                        tamAramaYayinEvi="xxx";
                       // //////////System.out.println("HATA NEREN?"+e.getMessage());
                      //  yevi.setText(tamAramaYayinEvi);

                    }
                    //burayada yayın evi tagları eklenerek yayıne vi alınailir.
*/

                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(AciklamaBilgisi=="")
            {
                AciklamaBilgisi="Bu kitaba ait açıklama bulunamadı.";
            }

            TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
            aciklama5.setText(AciklamaBilgisi);
            detayAciklama=AciklamaBilgisi;



            //anasayfada açılışarda isbn zaten belli.o yüzden sadace açıklama alındı.
            if(anaSayfadanAc)
            {
                fiyatListeAc();
                anaSayfadanAc=false;
                progressDialog.dismiss();
                new drBul().execute();
            }
            else {
                ISBNsayfaAl = AciklamaAdres;
                new siteBarkodAlOku().execute();
            }
            if(!tamAramaYayinEvi.isEmpty()) {
                TextView yevi = (TextView) findViewById(R.id.kYayinevi5);
                yevi.setText(tamAramaYayinEvi);
                tamAramaYayinEvi="";


            }
        }
    }














    String veriAl(String adrs,String bulunacak,String son)
    {

/*

        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL(adrs);
            urlConnection = (HttpURLConnection) url.openConnection();
            int code = urlConnection.getResponseCode();

            if(code==200){

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();


            }

            return result;
        } catch ( MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.disconnect();

*/
String result="";
try {
    ////////System.out.println("WEBBU ADRS "+adrs);
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
    //////////System.out.println("HATAAAA:"+e.getMessage());
}

        result = "<html>"+result+"</html>";
        ////////System.out.println("WEBBU "+result);
        return result;



    }




    class GetData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();


        }
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String result = "";
            //////////System.out.println("BASLADI");

            String sayfa="";
            if(listeDurum==1)
                sayfa="https://www.hepsiburada.com/kampanyalar/cok-satan-kitaplar?siralama=enyeni";
            else
                sayfa="https://www.hepsiburada.com/kampanyalar/cok-satan-kitaplar?siralama=coksatan";

            try {
                URL url = new URL(sayfa);
                urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();

                if(code==200){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";


                       boolean okumayBasla=false;



                        while ((line = bufferedReader.readLine()) != null) {
                            if(line.indexOf("class=\"_2xRHg\"")>0) {
                                result = line;
                             //   //System.out.println(result);
                                break;
                            }
                        }
                    }
                    in.close();
                }

                return result;
            } catch ( MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                urlConnection.disconnect();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
        hepsiburadaMetin=result;

        new hepsiburadaOku().execute();

        /*
         Matcher matcher = Pattern.compile("product_names\":\\[\"(.*?)\"").matcher(result);
            while (matcher.find()) {
                String meto=matcher.group().replaceAll("product_names\":\\[\"","").replaceAll("\"","");

                //////////System.out.println(meto);
            }
         */



        }
    }


    String hepsiburadaMetin="";
    class hepsiBuradaBul extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            System.out.println("HEPSİBURADA ARAMALARI BAŞLADI");
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();
            //////////System.out.println("BASLADI");
            try {
                URL url = new URL("https://www.hepsiburada.com/ara?q="+ISBN);
                urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();

                if(code==200){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                            result.append(line);;

                            /*
                           if(line.indexOf("_18vgy _3Wn9b hbBox")>0) {
                               result = line;
                           }

                             */
                    }
                    in.close();
                }

                return result.toString();
            } catch ( MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                urlConnection.disconnect();
            }
            return result.toString();

        }

        @Override
        protected void onPostExecute(String result) {
            hepsiburadaMetin=result;
            System.out.println("HEPSİBURADA::::" + hepsiburadaMetin);
            if(!herseyiDurdur)
            {
                new hbBul().execute();
            }


        }
    }















    String hbFiyat;
    String hbAdres="";
    private class hbBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            hbFiyat=null;
            hbAdres=null;
            try{
                aramaSira=3;


              //  Document doc = Jsoup.connect("https://www.hepsiburada.com/ara?q="+ISBN).userAgent("Mozilla/5.0(Linux; U; Android 4.2; en-gb; LG-P500 Build/FRF91) AppleWebKit/533.0 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1").ignoreContentType(true).timeout(3000).get();
                longLog("HBYAZ" + hepsiburadaMetin);
                Document doc = Jsoup.parse(hepsiburadaMetin);
              //  //System.out.println(hepsiburadaMetin);
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("HB aranan kitap",kitap);
                // Log.v("aranan yazar",yazarAd);

                System.out.println("HEPSİBURADA BURDAaaa:");
                elements = doc.select("div[class=wk2OIwVTQApspSmRQrPB hbPriceContent]");  // class ismi post-content olan verileri çekmek için

                String metin="";
                for (org.jsoup.nodes.Element baslikS : elements) {
                    String dataS = baslikS.text();
                    metin = dataS;


                if (metin.contains("%"))
                {
                    elements = baslikS.select("div[class=OOJXkBZkNkyU5n1qXUCg price]");  // class ismi post-content olan verileri çekmek için

                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        hbFiyat = data;
                       // break;
                    }
                }
                else
                {

             //   elements = doc.select("div[class=_18vgy _3Wn9b hbBox] div[class=_1umWP price]");  // class ismi post-content olan verileri çekmek için

                    elements = baslikS.select("div[class=OOJXkBZkNkyU5n1qXUCg price]");  // class ismi post-content olan verileri çekmek için
                  //  elements.get(0).select("div[class=_1umWP price]");
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    hbFiyat = data;
                    break;
                }


                }


                    break;
                }



/*
                //fiyatlar  alındı
                elements = doc.select("div[id=productresults] div[class=last-price hb-pl-cn] div[class=price-value]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                        hbFiyat = data;
                        break;
                }
*/
                elements = doc.select("a[class=S4ebqe1kE6jsD7TEwa7N]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    hbAdres = "https://www.hepsiburada.com" + data;
                    break;
                }

                // // Log.v("hepsiburada:",hbFiyat);


/*
                if(hbFiyat==null)
                {

                    elements = doc.select("div[id=productresults] span[class=price product-price]");  // class ismi post-content olan verileri çekmek için

                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        hbFiyat = data;
                        break;
                    }
                  //  // Log.v("hepsiburada:",hbFiyat);



                }

*/



            }catch (Exception e){

                  Log.e("HEPSİBURADA",e.getMessage());
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            { TextView hbF = (TextView)findViewById(R.id.hbFiyat);
                if(hbFiyat==null)
                    hbFiyat="-----";
                hbF.setText(hbFiyat);
                sirala(new View(MainActivity.this));
                new kyBul().execute();
            }
            else
            {
                hbFiyat = "-----";
            }

        }
    }









    String bkmMetin="";
    class GetDataBKM extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String result = "";
            //////////System.out.println("BASLADI");
            try {
                URL url = new URL("https://www.bkmkitap.com/arama?q="+ISBN);
                urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();

           //     if(code==200){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                           // if(line.indexOf("_18vgy _3Wn9b hbBox")>0)
                        {
                            result = result + "\n" + line;
                            System.out.println(line);
                        }
                    }
                    in.close();
              //  }

                return result;
            } catch ( MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                urlConnection.disconnect();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            bkmMetin=result;
            /*
            if(!herseyiDurdur)
            {
                new hbBul().execute();
            }

*/


        }
    }

































    String kyFiyat;
    String kyAdres="";
    private class kyBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kyFiyat=null;
            kyAdres=null;

            try {

                String hbYazar = "";
                if (yazarAd.contains("..."))
                    hbYazar = "";
                else
                    hbYazar = kyAramayazar;


                String[] eski = {" ", "ü", "Ü", "ö", "Ö", "ı", "İ", "ğ", "Ğ", "ş", "Ş", "ç", "Ç"};
                String[] yeni = {"+", "u", "U", "o", "O", "i", "I", "g", "G", "s", "S", "c", "C"};

                String kitapX, yazarAdX, yayinEviMetinX;

                kitapX = kyAramakitap;
                yazarAdX = hbYazar;
                yayinEviMetinX = kyAramayayinevi;

                if (yazarAdX == null)
                    yazarAdX="";

                if (yayinEviMetinX == null)
                    yayinEviMetinX="";


                // //////////System.out.println("YAZAR: "+kitapX);
                // //////////System.out.println("YAZAR: "+yazarAdX);


                if ((kitapX != null) && (yazarAdX != null)) {
                    for (int m = eski.length - 1; m >= 0; m--) {
                        kitapX = kitapX.replaceAll(eski[m], yeni[m]);
                        yayinEviMetinX = yayinEviMetinX.replaceAll(eski[m], yeni[m]);
                        yazarAdX = yazarAdX.replaceAll(eski[m], yeni[m]);
                    }
                    System.out.println("KİTAP YURDU LINK: " +  kitapX);


                    try {
                        aramaSira = 4;
                        Document doc = Jsoup.connect("https://www.kitapyurdu.com/index.php?route=product/search&filter_name=" + kitapX+"&limit=200").timeout(5000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0").get();
                        //  //////////System.out.println("KITAP YURDU ADRES: "+"https://www.kitapyurdu.com/index.php?route=produ ct/search&sort=purchased&order=DESC&filter_name=" + kitapX + "+" + yazarAdX);
                        // Elements elements = doc.select("div[class=box-content] ul li div[itemtype=http://schema.org/Book] div[class=price-new ] span[class=value]");  // class ismi post-content olan verileri çekmek için


                        Elements elements = doc.select("div[class=product-grid] div[class=product-cr]");  // class ismi post-content olan verileri çekmek için

                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = null;

                            //System.out.println("ARANAN ISBN: "+ISBN.substring(3,12));

                            if (baslik.outerHtml().contains(ISBN.substring(3, 12))) {
                                if (baslik.outerHtml().contains("price-new"))
                                    data = baslik.select("div[class=price-new ] span[class=value]").get(0).text();

                                else
                                    data = baslik.select("span[class=price-old ] span[class=value]").get(0).text();


                                kyFiyat = data + " TL";

                                //  //////////System.out.println("deneme"+data);
                                break;
                            }
                        }


                        elements = doc.select("div[class=product-grid] div[class=product-cr]");  // class ismi post-content olan verileri çekmek için

                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = null;
                          //  //System.out.println("ARANAN ISBN: "+ISBN.substring(3,12));
                            if (baslik.outerHtml().contains(ISBN.substring(3,12))) {
                                kyAdres = baslik.select("a[class=pr-img-link]").get(0).attr("href");
                                break;
                            }
                        }


                    } catch (Exception e) {

                        // //////////System.out.println("GO GO GO :"+e.getMessage());
                        //Log.v("KITAPYURDU",e.getMessage());

                    }

                }
            }
            catch (Exception ex)
            {
                //System.out.println("KİTAP YURDU HATA: "+ ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {TextView kyF = (TextView)findViewById(R.id.kyFiyat);
                if(kyFiyat==null)
                    kyFiyat="-----";

                kyF.setText(kyFiyat);
                sirala(new View(MainActivity.this));
               // babilVeriAl();
                //new bbBul2().execute();
              //  new bbBul().execute();
            }
            else
            {
                kyFiyat = "-----";
            }

        }
    }












    String bbFiyat;
    String bbAdres;

    private class bbBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;
        String authors;
        @Override
        protected void onPreExecute() {
            bulY=bulX;
            super.onPreExecute();
            //System.out.println(bulY + " --- " + bulX);
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            bbFiyat=null;
            bbAdres=null;

            try{
             //   aramaSira = 5;

                System.out.println("BABLİL DENENİYOR");
                //Document doc = Jsoup.parse(babilDokuman);
                Document doc = Jsoup.connect("https://www.babil.com/search?q=" + ISBN).timeout(5000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36").get();

               // Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                //elements = doc.select("div[class=box-content] ul[class=product-grid small jcarousel-skin-opencart] li div[itemtype=http://schema.org/Book] div[class=price] div[class=price-new] span[class=value]");  // class ismi post-content olan verileri çekmek için
                Elements elements = doc.select("div[class=products-content] ul[class=list-view] ul[class=price]");  // class ismi post-content olan verileri çekmek için
                //elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        bbFiyat = data;
                        break;

                }
               // Log.v("BB FIYAT",bbFiyat);



                elements = doc.select("div[class=products-content] ul[class=list-view] a");  // class ismi post-content olan verileri çekmek için
                //elements = doc.select("html");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.babil.com"+baslik.attr("href");
                    bbAdres = data;
                    break;

                }
                // Log.v("deneme",bbFiyat);

               // Log.v("BABIL","BURASI");

            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView ideF = (TextView)findViewById(R.id.babilFiyat);
            if(aramaSira==4)
            {

            if(bulX==bulY)
            {
                if(bbFiyat==null)
                    bbFiyat="-----";
                ideF.setText(bbFiyat);
                sirala(new View(MainActivity.this));
               // new kediBul().execute();
            }
            else
            {
                bbFiyat = "-----";
            }
            }
            else
            {
                bbFiyat="";
                ideF.setText("Bekleniyor...");
            }

        }
    }









  //  String bkmFiyat;
  //  String bkmAdres;

    private class bbBul99 extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            bbFiyat=null;
            bbAdres=null;
            try{

                aramaSira=5;

                Document doc = Jsoup.connect("https://www.kitabist.com/arama?q="+ISBN).timeout(10000).get();

                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //System.out.println("EGANBAAAAAA:"+ doc.outerHtml());

                //fiyatlar  alındı
                Elements elements = doc.select("span[itemprop=price]");  // class ismi post-content olan verileri çekmek için
             //   elements.select("span").remove();

             //   System.out.println("KİTAPİST" + doc.html());
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("content") + " TL";
                   // data = data.replaceAll(".",",");
                    bbFiyat=data;
                  //  System.out.println("K_ADI:" + data);
                    break;
                }


                elements = doc.select("div[id=products-list] div[id=product-list-imageContent] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.kitabist.com"+baslik.attr("href");
                    bbAdres=data;
                    break;
                }
                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
                //   //System.out.println("BKM SORUN");
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.babilFiyat);
                if(bbFiyat==null)
                    bbFiyat="-----";
                ideF.setText(bbFiyat);
                sirala(new View(MainActivity.this));
                //ksepetiVeriAl();
                new kediBul().execute();
            }
            else
            {
                bbFiyat = "-----";
            }

        }
    }

































    String bkmFiyat;
    String bkmAdres;

    private class bkmBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            bkmFiyat=null;
            bkmAdres=null;
            try{

/*
                WebClient webClient = new WebClient();
                Page page = webClient.getPage("https://www.bkmkitap.com/arama?q=9789750738609");
                WebResponse response = page.getWebResponse();
                String content = response.getContentAsString();
                System.out.println("GELDİİİİİİİ \n " + content);
*/



                aramaSira=9;

               // Document doc = Jsoup.connect("https://www.bkmkitap.com/arama?q="+ISBN).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                Document doc =Jsoup.parse(bkmDokuman);
                print("bkmDokuman" + bkmDokuman);

                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                Elements elements = doc.select("div[class=waw-content-product] a[class=waw-content-basket]");  // class ismi post-content olan verileri çekmek için

                if(elements.size()>1)
                     doc =Jsoup.parse("");


                 elements = doc.select("div[class=waw-content-product] a[class=waw-content-basket]");  // class ismi post-content olan verileri çekmek için


                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    bkmFiyat=data.replaceAll(" Sepete Ekle","");
                    if(bkmFiyat.contains("Tükendi"))
                        bkmFiyat=null;
                    if(bkmFiyat.contains("tok"))
                        bkmFiyat=null;
                    break;
                }


                elements = doc.select("div[class=waw-content-product] div[class=waw-content-product-item-area] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    bkmAdres=data;
                    break;
                }
                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
             //   //System.out.println("BKM SORUN");
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.bkmFiyat);
                if(bkmFiyat==null)
                    bkmFiyat="-----";
                ideF.setText(bkmFiyat);
                sirala(new View(MainActivity.this));
             //   ksepetiVeriAl();
               // new kSepetiBul().execute();
                new secBul().execute();


            }
            else
            {
                bkmFiyat = "-----";
            }

        }
    }


    String nadirFiyat;
    String nadirAdres;

    private class bbBul2 extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            n11Adres=null;
            n11Fiyat=null;
            try{

                aramaSira=18;

                try
                {
                    URL url = new URL("https://www.nadirkitap.com/kitapara.php?ara=aramayap&isbn="+ISBN+"&siralama=fiyatartan");
                    URLConnection uc = url.openConnection();
                    //String j = (String) uc.getContent();
                    uc.setDoInput(true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                    String inputLine;
                    StringBuilder a = new StringBuilder();
                    while ((inputLine = in.readLine()) != null)
                        a.append(inputLine);
                    in.close();
                    if(a.indexOf("TUTUNAMAYANLAR")>0)
                        System.out.println("NADİR SONUÇ: " + "bulundu");



                   // Document doc = Jsoup.connect("https://www.nadirkitap.com/kitapara.php?ara=aramayap&satici=0&tip=kitap&kitap_Adi=&kategori=all&yazar=&yayin_Evi=&ceviren=&yayin_Yeri=&saticisec=all&dil=0&isbn="+ISBN+"&eskiyeni=1&tarih1=&tarih2=&fiyat1=&fiyat2=&siralama=fiyatartan&cilt=0").timeout(1000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").get();
                    Document doc = Jsoup.parse(String.valueOf(a));
                    Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                    // Log.v("aranan kitap",kitap);

                    //fiyatlar  alındı
                    elements = doc.select("ul[class=product-list] li div[class=col-md-6 col-xs-12 no-padding text-right product-list-price]");  // class ismi post-content olan verileri çekmek için
                    elements.select("span").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        n11Fiyat=data;
                        System.out.println("NADİR FİYAT:" + n11Fiyat);
                        break;
                    }


                    elements = doc.select("ul[class=product-list] li h4 a");  // class ismi post-content olan verileri çekmek için

                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.attr("href");
                        n11Adres=data;
                        break;
                    }




                    elements = doc.select("div[class=ratingCont]");
                    if(elements.size()>0)
                    {
                        String m = elements.get(0).text().toString();
                        ImageView nadirResim = findViewById(R.id.n11Resim);
                        if(m!="Yeni")
                        {

                            nadirResim.setImageResource(R.drawable.nadiriki);
                        }
                        else
                        {
                            nadirResim.setImageResource(R.drawable.nadir);
                        }
                    }


                }
                catch (Exception e)
                {

                }




            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY) {
                TextView ideF = (TextView) findViewById(R.id.n11Fiyat);
                if (n11Fiyat == null)
                    n11Fiyat = "-----";
                ideF.setText(n11Fiyat);
                sirala(new View(MainActivity.this));
                //   new kitapkolikBul().execute();
                //new ilknoktaBul().execute();
            }
            else
            {
                n11Fiyat = "-----";
            }

        }
    }


    String ilknoktaFiyat;
    String ilknoktaAdres;

    private class ilknoktaBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            ilknoktaFiyat=null;
            ilknoktaAdres=null;
            try{

                aramaSira=19;


                Document doc = Jsoup.connect("https://www.ilknokta.com/index.php?p=Products&q_field_active=0&ctg_id=&q="+ISBN+"&search=&q_field=").timeout(4000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").get();

                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                elements = doc.select("span[id=prd_final_price_display]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    ilknoktaFiyat=data+" TL";
                    break;
                }


                elements = doc.select("meta[property=og:url]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("content");
                    ilknoktaAdres=data;
                    break;
                }

                boolean tukendi=false;
                elements = doc.select("div[class=prd_no_sell]");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    tukendi=true;
                    break;
                }

                if(tukendi)
                {
                    ilknoktaFiyat=null;
                    ilknoktaFiyat=null;
                }


                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.ilknoktaFiyat);
                if(ilknoktaFiyat==null)
                    ilknoktaFiyat="-----";
                ideF.setText(ilknoktaFiyat+"");
                sirala(new View(MainActivity.this));
                new koalaBul().execute();
            }
            else
            {
                ilknoktaFiyat = "-----";
            }

        }
    }









    String koalaFiyat;
    String koalaAdres;

    private class koalaBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            koalaFiyat=null;
            koalaAdres=null;
            try{

                aramaSira=20;


                Document doc = Jsoup.connect("https://dostkitabevi.com/search?q="+ISBN+"&spellCheck=false").timeout(5000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").get();

                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                elements = doc.select("div[class=product-price] div[class=urun-fiyati]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    koalaFiyat=data;
                    System.out.println("KİTAPMATİK FİYAT:" + koalaFiyat);
                    break;
                }


                elements = doc.select("div[class=mb-2 urungorseller-box] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    koalaAdres="https://dostkitabevi.com" + data;
                    break;
                }


                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.koalaFiyat);
                if(koalaFiyat==null)
                    koalaFiyat="-----";
                ideF.setText(koalaFiyat+"");
                sirala(new View(MainActivity.this));
                new trendyolBul().execute();
            }
            else
            {
                koalaFiyat = "-----";
            }

        }
    }






    private class kitap365Bul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kitap365Fiyat=null;
            kitap365Adres=null;
            try{


                System.out.println("365 YAZDIR" + kitap365Metin);

                Document doc = Jsoup.parse(kitap365Metin);

                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                elements = doc.select("div[class=price] span[class=new]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    kitap365Fiyat=data;
                    break;
                }


                elements = doc.select("div[class=bookout] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.kitap365.com"+baslik.attr("href");
                    kitap365Adres=data;
                    break;
                }


                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView ideF = (TextView) findViewById(R.id.kitap365Fiyat);
           // if(aramaSira==9) {
                if (bulX == bulY) {

                    if (kitap365Fiyat == null)
                        kitap365Fiyat = "-----";
                    ideF.setText(kitap365Fiyat + "");
                    //new kSepetiBul().execute();
                    ksepetiVeriAl();

                } else {
                    kitap365Fiyat = "-----";
                }
           // }
         //   else
           // {
             //   kitap365Fiyat="";
            //    ideF.setText("Bekleniyor...");

            //}

        }
    }





























    String trendyolFiyat;
    String trendyolAdres;

    private class trendyolBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            trendyolFiyat=null;
            trendyolAdres=null;
            try{

                aramaSira=21;


                Document doc = Jsoup.connect("https://www.trendyol.com/sr?q="+ISBN+"&sst=PRICE_BY_ASC").timeout(5000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0").get();

               // Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                Elements x = doc.select("div[class=p-card-chldrn-cntnr card-border]");

                if(x.get(0).html().contains("prc-box-dscntd"))
                {
                    trendyolFiyat = x.select("div[class=prc-box-dscntd]").get(0).text();
                }
                else if(x.get(0).html().contains("prc-box-sllng"))
                {
                    trendyolFiyat = x.select("div[class=prc-box-sllng]").get(0).text();
                }
                else
                {
                    trendyolFiyat = x.select("div[class=prc-box-orgnl]").get(0).text();
                }

                //  //System.out.println("trendyolfiyat: "+ trendyolFiyat);

                /*
                elements = doc.select("div[class=prc-box-sllng]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    trendyolFiyat=data;
                    break;
                }
*/


               Elements elements = doc.select("div[class=p-card-chldrn-cntnr card-border] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    trendyolAdres="https://www.trendyol.com"+data;
                  //  //System.out.println("TRENDYOL ADRES: "+trendyolAdres);
                    break;
                }


                // Log.v("Yazdır BKM:",bkmFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.trendyolFiyat);
                if(trendyolFiyat==null)
                    trendyolFiyat="-----";
                ideF.setText(trendyolFiyat+"");
                sirala(new View(MainActivity.this));
                ksepetiVeriAl();

                //   kitap365();
            }
            else
            {
                trendyolFiyat = "-----";
            }

        }
    }











    String secFiyat;
    String secAdres;

    private class secBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            secFiyat=null;
            secAdres=null;
            try{


                aramaSira=11;

                Document doc = Jsoup.connect("https://www.kitapsec.com/Arama/index.php?a="+ISBN).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(3000).validateTLSCertificates(false).get();


               Elements elements = doc.select("span[class=fiyat] font[class=satis]");  // class ismi post-content olan verileri çekmek için7

                //System.out.println("KİTAPSEÇ : " + elements.size() );
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    secFiyat=data;
                    break;
                }


                elements = doc.select("div[class=Ks_UrunSatir] a[class=img]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    secAdres=data;
                    break;
                }
                // Log.v("Yazdır:",secFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            if(bulY==bulX)
            {  TextView ideF = (TextView)findViewById(R.id.secFiyat);
                if(secFiyat==null)
                    secFiyat="-----";
                ideF.setText(secFiyat);
                sirala(new View(MainActivity.this));
                new pandoraBul().execute();
            }
        }
    }





    String pandoraFiyat;
    String pandoraAdres;

    private class pandoraBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            String bulunduKitapAd="";
            pandoraFiyat=null;
            pandoraAdres=null;
            try{

                aramaSira=12;


                Document doc = Jsoup.connect("https://www.pandora.com.tr/Arama/?type=9&isbn="+ISBN).timeout(3000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0").get();

                Elements elements = doc.select("ul[id=urunler] li div p[class=indirimliFiyat]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    pandoraFiyat=data.replaceAll("Site Fiyatı: ","");;

                }


                elements = doc.select("ul[id=urunler] li div p[class=edebiyatIsim] a");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.pandora.com.tr"+baslik.attr("href");
                    pandoraAdres=data;
                }








            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.pandoraFiyat);
                if(pandoraFiyat==null)
                    pandoraFiyat="-----";
                ideF.setText(pandoraFiyat);
                sirala(new View(MainActivity.this));
                //new kidegaBul().execute();

                new ukaBul().execute();
            }
            else
            {
                pandoraFiyat = "-----";
            }

        }
    }





    String kidegaFiyat;
    String kidegaAdres;

    private class kidegaBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kidegaFiyat=null;
            kidegaAdres=null;
            try{


                aramaSira=13;

                Document doc = Jsoup.connect("https://www.kitapsahaf.net/index.php?p=Products&q_field_active=0&search_param=all&q="+ISBN).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(3000).validateTLSCertificates(false).get();
                System.out.println("kidega:" + doc);
               // Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                Elements elements = doc.select("meta[itemprop=price]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("content");
                    //data=data.trim();
                    kidegaFiyat=data;
                    kidegaFiyat=kidegaFiyat+" TL";
                    System.out.println("kidega:" + kidegaFiyat);
                    kidegaAdres = "https://www.kitapsahaf.net/index.php?p=Products&q_field_active=0&search_param=all&q="+ISBN;
                    break;

                }

/*
                elements = doc.select("div[class=prd-inner itemWrap] a[class=prd-lnk]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://kidega.com" + baslik.attr("href");
                    kidegaAdres=data;
                    break;

                }
*/
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.kadigaFiyat);
                if(kidegaFiyat==null)
                    kidegaFiyat="-----";
                ideF.setText(kidegaFiyat);
                sirala(new View(MainActivity.this));
                new ukaBul().execute();
            }
            else
            {
                kidegaFiyat = "-----";
            }

        }
    }






    String ukaFiyat;
    String ukaAdres;

    private class ukaBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            ukaFiyat=null;
            ukaAdres=null;
            try{


                aramaSira=14;

                Document doc = Jsoup.connect("https://www.ucuzkitapal.com/"+ISBN).timeout(5000).validateTLSCertificates(false).get();

                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                Elements elements = doc.select("div[id=product_code_view_pagination_contents] div[class=ty-column4] span[class=ty-price]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    ukaFiyat=data;

                    ukaFiyat=ukaFiyat.replaceAll("TL","");
                    ukaFiyat=ukaFiyat.trim();
                    ukaFiyat=ukaFiyat.substring(0,ukaFiyat.length()-1);
                    ukaFiyat=ukaFiyat+" TL";

                    break;
                }


                elements = doc.select("div[id=product_code_view_pagination_contents] div[class=ty-column4] div[class=ut2-gl__image] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    ukaAdres=data;
                    break;
                }


                System.out.println("UKA" + ukaFiyat);

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.ukaFiyat);
                if(ukaFiyat==null)
                    ukaFiyat="-----";
                ideF.setText(ukaFiyat);
                sirala(new View(MainActivity.this));
                new amazonBul().execute();
            }
            else
            {
                ukaFiyat = "-----";
            }


        }
    }





    String amazonGidilecekAdres;
    private class amazonBulOnce extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{


                Document doc = Jsoup.connect("https://www.amazon.com.tr/s?k="+ISBN).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0").timeout(5000).validateTLSCertificates(false).get();
                amazonGidilecekAdres = "https://www.amazon.com.tr" + doc.select("a[class=a-link-normal s-no-outline]").get(0).attr("href");  // class ismi post-content olan verileri çekmek için

                print("amazongidilecekadres" + amazonGidilecekAdres);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new amazonBul().execute();
        }
    }





    void amazonYukle()
    {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // WebView'i programatik olarak oluştur
                WebView webViewAmazon = new WebView(MainActivity.this);

                // WebView ayarlarını yapılandır
                WebSettings webSettings = webViewAmazon.getSettings();
                webSettings.setJavaScriptEnabled(true);  // JavaScript'i etkinleştir

                // WebViewClient ayarla, sayfa yüklenmesini takip et
                webViewAmazon.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        // Sayfa yüklendiğinde, JavaScript ile HTML kaynağını al
                        webViewAmazon.evaluateJavascript(
                                "(function() { return document.documentElement.outerHTML; })();",
                                new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html) {
                                        // HTML kaynağını log ile konsola yaz
                                        System.out.println("AMAZON KAYNAK: " + html);
                                    }
                                });
                    }
                });

                // Bir URL yükle (Amazon sayfası örneği)
                webViewAmazon.loadUrl("https://www.amazon.com.tr/s?k=9786051738871");
            }
        });

    }








    String amazonFiyat;
    String amazonAdres;
    private class amazonBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            amazonFiyat=null;
            amazonAdres=null;
            try{



                aramaSira=15;

             //   System.out.println("AMAZONA GİDİYORUZ: " + amazonGidilecekAdres);




                Document doc = Jsoup.connect("http://www.bilisimkalesi.com/sadeManset/besleme.php?url=https://www.amazon.com.tr/s?k="+ISBN).timeout(10000).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36").get();
                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                //Log.v("aranan kitap",kitap);

                //fiyatlar  alındı

                System.out.println("AMAZON :" + doc.toString());
                String amazonBulunamadi = doc.select("div[class=sg-col-4-of-24 sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col s-widget-spacing-small sg-col-4-of-20] div[class=a-section a-spacing-small puis-padding-left-small puis-padding-right-small]").get(0).text();  // class ismi post-content olan verileri çekmek için

                if(!amazonBulunamadi.contains("mevcut değil")) {
                    //  System.out.println("amazon metin: "+ doc);

                    if(!amazonBulunamadi.contains("teklif yok")) {
                        amazonFiyat = doc.select("div[class=sg-col-4-of-24 sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col s-widget-spacing-small sg-col-4-of-20] div[class=a-row a-size-base a-color-base] span[class=a-offscreen]").get(0).text();  // class ismi post-content olan verileri çekmek için
                        amazonFiyat = amazonFiyat.replaceAll("TL", "");
                        amazonFiyat = amazonFiyat.trim();
                        amazonFiyat = amazonFiyat.substring(0, amazonFiyat.length() - 1);
                        amazonFiyat = amazonFiyat + " TL";
                    }
                    else
                    {
                        amazonFiyat = doc.select("div[class=a-row a-size-base a-color-secondary] span[class=a-color-base]").get(0).text();  // class ismi post-content olan verileri çekmek için
                        amazonFiyat = amazonFiyat.substring(0, amazonFiyat.length() - 3);
                        amazonFiyat = amazonFiyat + " TL";

                    }

                    //   System.out.println(amazonFiyat);


                    amazonAdres = "https://www.amazon.com.tr" + doc.select("div[class=sg-col-4-of-24 sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col s-widget-spacing-small sg-col-4-of-20] span[class=rush-component] a[class=a-link-normal s-no-outline]").get(0).attr("href");  // class ismi post-content olan verileri çekmek için
                    System.out.println("AMAZON ADRESİ: " + amazonAdres);

                }



                ////////////System.out.println(amazonAdres);
            }catch (Exception e){
                //System.out.println("AMAZON HATA");
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.amazonFiyat);
                if(amazonFiyat==null)
                    amazonFiyat="-----";
                ideF.setText(amazonFiyat);
                sirala(new View(MainActivity.this));
                //new n11Bul().execute();
                new bbBul2().execute();
            }
            else
            {
                amazonFiyat = "-----";
            }


        }
    }









/*

    String amazonFiyat;
    String amazonAdres;
    private class amazonBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            amazonFiyat=null;
            amazonAdres=null;
            try{

                aramaSira=15;

                System.out.println("AMAZONA GİDİYORUZ: " + amazonGidilecekAdres);




                Document doc = Jsoup.connect(amazonGidilecekAdres).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0").timeout(8000).get();
                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı


               int acilacakNumara = doc.select("table[class=a-normal a-spacing-none a-size-mini]").size();

               if(acilacakNumara>0)
               {
                   doc.select("table[class=a-normal a-spacing-none]").remove();
               }


                amazonFiyat = doc.select("td[class=a-text-right dp-new-col] a[class=a-link-normal]").get(0).text();  // class ismi post-content olan verileri çekmek için



                amazonFiyat=amazonFiyat.replaceAll("TL","");
                amazonFiyat=amazonFiyat.trim();
                amazonFiyat=amazonFiyat.substring(0,amazonFiyat.length()-1);
                amazonFiyat=amazonFiyat+" TL";


                System.out.println(amazonFiyat);


                amazonAdres=amazonGidilecekAdres;




               ////////////System.out.println(amazonAdres);
            }catch (Exception e){
                //System.out.println("AMAZON HATA");
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.amazonFiyat);
                if(amazonFiyat==null)
                    amazonFiyat="-----";
                ideF.setText(amazonFiyat);
                sirala(new View(MainActivity.this));
                new n11Bul().execute();
            }
            else
            {
                amazonFiyat = "-----";
            }


        }
    }


*/




/*


    String inklapFiyat;
    String inklapAdres;

    private class inklapBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            inklapFiyat=null;
            inklapAdres=null;
            try{

                aramaSira=10;


                Document doc = Jsoup.connect("https://www.inklap.com.tr/s?k="+ISBN).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0").validateTLSCertificates(false).timeout(3000).get();
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı
                elements = doc.select("div[class=sg-row] div[class=a-row a-size-base a-color-base] div[class=a-row] a span[class=a-offscreen]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    inklapFiyat=data.replaceAll("₺","")+" TL";
                    break;
                }


                elements = doc.select("div[class=sg-row] div[class=a-row a-size-base a-color-base] div[class=a-row] a");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    inklapAdres="https://www.inklap.com.tr"+data;
                }
                ////////////System.out.println(inklapAdres);
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.inklapFiyat);
                if(inklapFiyat==null)
                    inklapFiyat="-----";
                ideF.setText(inklapFiyat);
                sirala(new View(MainActivity.this));
                new n11Bul().execute();
            }

        }
    }



*/


    String n11Fiyat;
    String n11Adres;

    private class n11Bul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            n11Fiyat=null;
            n11Adres=null;

            try{

                aramaSira=16;
                Document doc = Jsoup.connect("https://www.arkadas.com.tr/arama?q="+ISBN).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:130.0) Gecko/20100101 Firefox/130.0").timeout(10000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);



                //fiyatlar  alındı
                elements = doc.select("div[class=fl currentPrice cur-disc]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    n11Fiyat=data;
                    break;
                }


                elements = doc.select("a[class=image-wrapper fl detailLink]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    n11Adres="https://www.arkadas.com.tr" + data;
                    break;
                }
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

           if(bulX==bulY) {
               TextView ideF = (TextView) findViewById(R.id.n11Fiyat);
               if (n11Fiyat == null)
                   n11Fiyat = "-----";
               ideF.setText(n11Fiyat);
               sirala(new View(MainActivity.this));
            //   new kitapkolikBul().execute();
                    new ilknoktaBul().execute();
           }
           else
           {
               n11Fiyat = "-----";
           }


        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
            cancel(true);
        }
    }












    String kediFiyat;
    String kediAdres;

    private class kediBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kediFiyat=null;
            kediAdres=null;

            try{
                aramaSira=6;

                print("TAM ADRES GİRDİ");
                Document doc = Jsoup.connect("https://tamadres.com/arama?q="+ISBN).timeout(5000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.3").validateTLSCertificates(false).get();
              //  Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                System.out.println("tamadresburasi" + doc.select("span[class=font-bold text-primary tabular-nums min-w-0]").get(0).text());




                //fiyatlar  alındı
               Elements elements = doc.select("span[class=font-bold text-primary tabular-nums min-w-0]");  // class ismi post-content olan verileri çekmek için
               // elements.select("span").remove();
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text().substring(0,baslik.text().length()-2) + " TL";

                    print(data);
                    kediFiyat=data;
                    break;
                }


                elements = doc.select("link[rel=canonical]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    kediAdres=data;
                    break;
                }
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY) {
                TextView ideF = (TextView) findViewById(R.id.kediFiyat); //KIRMIZI KEDİ
                if (kediFiyat == null)
                    kediFiyat = "-----";
                ideF.setText(kediFiyat);
                sirala(new View(MainActivity.this));
                //new kitap16Bul().execute();

            }
            else
            {
                kediFiyat = "-----";
            }

        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
            cancel(true);
        }
    }











    void print(String met)
    {
        System.out.println(met);
    }




    String kitapkolikFiyat;
    String kitapkolikAdres;

    private class kitapkolikBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kitapkolikFiyat=null;
            kitapkolikAdres=null;

            try{
                aramaSira=17;


                Document doc = Jsoup.connect("https://www.kitapkolik.com/arama?q="+ISBN).timeout(5000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);



                //fiyatlar  alındı
                elements = doc.select("div[class=text-custom-pink text-bold currentPrice]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    kitapkolikFiyat=data;
                    break;
                }


                elements = doc.select("a[class=image-wrapper fl detailLink]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    kitapkolikAdres="https://www.kitapkolik.com"+data;
                }
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY) {
                TextView ideF = (TextView) findViewById(R.id.kitapkolikFiyat);
                if (kitapkolikFiyat == null)
                    kitapkolikFiyat = "-----";
                ideF.setText(kitapkolikFiyat);
                sirala(new View(MainActivity.this));
                //new nadirBul().execute();
                new ilknoktaBul().execute();

            }
            else
            {
                kitapkolikFiyat = "-----";
            }

        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
            cancel(true);
        }
    }










    String kitap16Fiyat;
    String kitap16Adres;

    private class kitap16Bul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kitap16Fiyat=null;
            kitap16Adres=null;

            try{
                aramaSira=7;


                Document doc = Jsoup.connect("https://www.kitapvadisi.com.tr/arama?q="+ISBN).timeout(5000).validateTLSCertificates(false).get();

                String kitap16Metin="";

                    //fiyatlar  alındı
                Elements elements = doc.select("div[class=current-price]");  // class ismi post-content olan verileri çekmek için

                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        kitap16Fiyat=data;
                        break;
                    }


                    elements = doc.select("a[class=col-12 product-title text-center]");  // class ismi post-content olan verileri çekmek için

                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.attr("href");
                        kitap16Adres="https://www.kitapvadisi.com.tr" + data;
                    }





            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY) {
                TextView ideF = (TextView) findViewById(R.id.kitap16Fiyat);
                if (kitap16Fiyat == null)
                    kitap16Fiyat = "-----";
                ideF.setText(kitap16Fiyat);
                sirala(new View(MainActivity.this));
                //new istBul().execute();

            }
            else
            {
                kitap16Fiyat = "-----";
            }

        }



        @Override
        protected void onCancelled() {
            super.onCancelled();
            cancel(true);
        }
    }









    String istFiyat;
    String istAdres;

    private class istBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            istFiyat=null;
            istAdres=null;
            try{

                aramaSira=8;

                Document doc = Jsoup.connect("https://www.istanbulkitapcisi.com/arama?q="+ISBN).validateTLSCertificates(false).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();

                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);

                //fiyatlar  alındı

                doc.select("span").remove();
                Elements elements = doc.select("div[class=current-price]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    istFiyat=data;
                    break;
                }


                elements = doc.select("a[class=image-wrapper image-animate-zoom]");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.istanbulkitapcisi.com"+baslik.attr("href");
                    istAdres=data;
                    break;
                }
                // Log.v("Yazdır ist:",istFiyat);

            }catch (Exception e){

                e.printStackTrace();
                //   //System.out.println("ist SORUN");
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if(bulX==bulY)
            {  TextView ideF = (TextView)findViewById(R.id.istFiyat);
                if(istFiyat==null)
                    istFiyat="-----";
                ideF.setText(istFiyat);
                sirala(new View(MainActivity.this));
                new bkmBul().execute();

            }
            else
            {
                istFiyat = "-----";
            }

        }
    }




boolean herseyiDurdur=false;



    String kSepetiFiyat;
    String kSepetiAdres;

    private class kSepetiBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            kSepetiFiyat=null;
            kSepetiAdres=null;

            try{
              //  aramaSira=10;

             //   System.out.println("KİTAPSEPETİ" + kSepetiDokuman);

                Document doc = Jsoup.parse(kSepetiDokuman);
                Elements elements = doc.select("span[class=product-price]");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    kSepetiFiyat = data + " TL";
                    break;
                }

                elements = doc.select("div[class=w-100 p-1 product-detail-card] a");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.attr("href");
                    kSepetiAdres = "https://www.kitapsepeti.com" + data;
                    break;
                }

            }catch (Exception e){

                e.printStackTrace();

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView ideF = (TextView) findViewById(R.id.ksFiyat);
          //  if(aramaSira==21) {

                if (bulX == bulY) {

                    if (kSepetiFiyat == null)
                        kSepetiFiyat = "-----";
                    ideF.setText(kSepetiFiyat);
                    sirala(new View(MainActivity.this));
                 //   new secBul().execute();
                    siralamaAktif();
                } else {
                    kSepetiFiyat = "-----";
                }

          //  }
          //  else
           // {
          //      kSepetiFiyat="";
          //      ideF.setText("Bekleniyor...");
           // }

        }
    }








































    Boolean siralamaBasla=false;

    public void siralamaAktif()
    {
        siralamaBasla=true;
        ImageView sb1 = (ImageView)findViewById(R.id.siralamaButon);
        ImageView sb2 = (ImageView)findViewById(R.id.siralamaButon2);

        sb1.setAlpha(1f);
        sb2.setAlpha(1f);

    }

    public void siralamaPasif()
    {
        siralamaBasla=false;
        ImageView sb1 = (ImageView)findViewById(R.id.siralamaButon);
        ImageView sb2 = (ImageView)findViewById(R.id.siralamaButon2);

        sb1.setAlpha(0.3f);
        sb2.setAlpha(0.3f);
    }





    String disAcilacakAdres="";
    public void webGorunumAc(View view)
    {
        String acAdres="";
        String acFiyat="";

        System.out.println(view);
        LinearLayout tmp = (LinearLayout)view.getParent();


        for(int i=0; i<tmp.getChildCount();i++)
        {
            System.out.println(tmp.getChildAt(i).getId() );
            if(tmp.getChildAt(i).getId() == view.getId())
            {
                acAdres=adresSonListeSirali.get(i);
                acFiyat=fiyatSonListeSirali.get(i);
                break;
            }

        }


       /*
        if(view.getId()==R.id.drKatman)
        {
            acAdres=drAdres;
            acFiyat=drFiyat;
        }
        else if(view.getId()==R.id.idefixKatman)
        {
            acAdres=idefixAdres;
            acFiyat=idefixFiyat;

        }
        else if(view.getId()==R.id.hbKatman)
        {
            acAdres=hbAdres;
            acFiyat=hbFiyat;

        }
        else if(view.getId()==R.id.kyKatman)
        {
            acAdres=kyAdres;
            acFiyat=kyFiyat;

        }
        else if(view.getId()==R.id.babilKatman)
        {
            acAdres=bbAdres;
            acFiyat=bbFiyat;

        }
        else if(view.getId()==R.id.kediKatman)
        {
            acAdres=kediAdres;
            acFiyat=kediFiyat;

        }
        else if(view.getId()==R.id.pandoraKatman)
        {
            acAdres=pandoraAdres;
            acFiyat=pandoraFiyat;

        }else if(view.getId()==R.id.kitap16Katman)
        {
            acAdres=kitap16Adres;
            acFiyat=kitap16Fiyat;

        }
        else if(view.getId()==R.id.istKatman)
        {
            acAdres=istAdres;
            acFiyat=istFiyat;

        }
        else if(view.getId()==R.id.bkmKatman)
        {
            acAdres=bkmAdres;
            acFiyat=bkmFiyat;

        }
        else if(view.getId()==R.id.ksKatman)
        {
            acAdres=kSepetiAdres;
            acFiyat=kSepetiFiyat;

        }
        else if(view.getId()==R.id.secKatman)
        {
            acAdres=secAdres;
            acFiyat=secFiyat;

        }
        else if(view.getId()==R.id.kadigaKatman)
        {
            acAdres=kidegaAdres;
            acFiyat=kidegaFiyat;

        }
        else if(view.getId()==R.id.ukaKatman)
        {
            acAdres=ukaAdres;
            acFiyat=ukaFiyat;
        }
        else if(view.getId()==R.id.amazonKatman)
        {
            acAdres=amazonAdres;
            acFiyat=amazonFiyat;
        }
        else if(view.getId()==R.id.kitapkolikKatman)
        {
            acAdres=kitapkolikAdres;
            acFiyat=kitapkolikFiyat;
        }
        else if(view.getId()==R.id.n11Katman)
        {
            acAdres=n11Adres;
            acFiyat=n11Fiyat;
        }
        else if(view.getId()==R.id.nadirKatman)
        {
            acAdres=nadirAdres;
            acFiyat=nadirFiyat;
        }
        else if(view.getId()==R.id.ilknoktaKatman)
        {
            acAdres=ilknoktaAdres;
            acFiyat=ilknoktaFiyat;
        }
        else if(view.getId()==R.id.koalaKatman)
        {
            acAdres=koalaAdres;
            acFiyat=koalaFiyat;
        }
        else if(view.getId()==R.id.trendyolKatman)
        {
            acAdres=trendyolAdres;
            acFiyat=trendyolFiyat;
        }
        else if(view.getId()==R.id.kitap365Katman)
        {
            acAdres=kitap365Adres;
            acFiyat=kitap365Fiyat;
        }
,*/
        disAcilacakAdres = acAdres;


        if((acAdres!=null)&&(!acFiyat.contains("-----"))) {
            RelativeLayout webGorunum = (RelativeLayout) findViewById(R.id.webGorunum);
            webGorunum.setVisibility(View.VISIBLE);
            WebView kitapWeb = (WebView) findViewById(R.id.kitapWeb);
            kitapWeb.setWebViewClient(new WebViewClient());
            kitapWeb.getSettings().setJavaScriptEnabled(true);
            kitapWeb.loadUrl(acAdres);
            kitapWebAcik=true;

            ImageView buyukAcButon =(ImageView)findViewById(R.id.buyukAcButon);
            buyukAcButon.getLayoutParams().height = buyukAcButonyukseklik;
            buyukAcButon.requestLayout();


        }










    }

    public void detayGoster(View view)
    {

        RelativeLayout webGorunum = (RelativeLayout) findViewById(R.id.webGorunum);
        webGorunum.setVisibility(View.VISIBLE);
        WebView kitapWeb = (WebView) findViewById(R.id.kitapWeb);
        kitapWeb.setWebViewClient(new WebViewClient());
        kitapWeb.getSettings().setJavaScriptEnabled(true);

        ImageView buyukAcButon =(ImageView)findViewById(R.id.buyukAcButon);
        buyukAcButon.getLayoutParams().height = 0;
        buyukAcButon.requestLayout();



        String html = "<html>\n" +
                "<head>\n" +
                "<script> \n" +
                "        function scrollToTop() { \n" +
                "            window.scrollTo(0, 0); \n" +
                "        } \n" +
                "    </script> "+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"3\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\" valign=\"middle\"><img width='150' height='210' src=\"" + detayResim.replaceAll("1000kitap.com/k/resimler/kitaplar","1000kitap.com/resimler/kitaplar") + "\" /></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\" valign=\"middle\"><h3>"+detayAd+"</h3></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\" valign=\"middle\"><h4>"+detayYazar+" - "+detayYayinEvi+"</h4></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td align=\"center\" valign=\"middle\"><div style=\"text-align:justify; font-size:13px\">"+detayAciklama+"</div></td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";


        kitapWeb.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
        kitapWebAcik=true;

        kitapWeb.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                view.loadUrl("javascript:scrollToTop()");
            }
        });


    }






    boolean kitapWebAcik=false;


    public void webGorunumKapat(View view)
    {

        RelativeLayout webGorunum = (RelativeLayout)findViewById(R.id.webGorunum);
        webGorunum.setVisibility(View.INVISIBLE);
        WebView kitapWeb = (WebView)findViewById(R.id.kitapWeb);
        kitapWeb.getSettings().setJavaScriptEnabled(true);
        kitapWeb.loadUrl("about:blank");
        kitapWebAcik=false;
    }


    public void bos(View view){}


    public void anaSayfaGit(View view)
    {
        menuIslemler((ImageView) findViewById(R.id.imageView3));
        webGorunumKapat(new View(MainActivity.this));
        buyukResimKapat();
        sepetKatmanKapat();
        fiyatListeKapat2();
        fiyatListeKapat();
        urunListeKapat();
        dwn=null;
        anaSayfadanGeldi=false;

    }


    public void klavyeKapat()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText())
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }




    protected void klavyeGizle() {

        EditText aramaMetin = (EditText) findViewById(R.id.aramaMetin);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(aramaMetin.getWindowToken(), 0);
    }




    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }



    @Override
    public void onResume(){
        super.onResume();
        overridePendingTransition(0, 0);
        if(menu.menuNo == 1) {
            ScrollView yeniKaydir = (ScrollView) findViewById(R.id.yeniKaydir);
            yeniKaydir.smoothScrollTo(0,0);
            anaSayfaGit(new View(MainActivity.this));
            menu.menuNo = 0;
        }

        if(menu.menuNo == 3) {
            barkodAc(new View(MainActivity.this));
            menu.menuNo = 0;
        }
        if(menu.menuNo == 4) {
            sepetKatmanAc(new View(MainActivity.this));
            menu.menuNo = 0;
        }
        if(menu.menuNo == 5) {
            anaSayfaGit(new View(MainActivity.this));
            kitapliktanAramaBasla();
            menu.menuNo = 0;
        }




        System.out.println("BAŞLADIK");

    }

    // 3. ÖNERİ: WebView yüklemesi ana iş parçacığını bloklamayacak şekilde post kuyruğuna alındı.
    public void girisYap() {
        final WebView sayfa = (WebView) findViewById(R.id.web);

        if (sayfa != null) {
            sayfa.post(new Runnable() {
                @Override
                public void run() {
                    sayfa.loadUrl("http://www.bilisimkalesi.com/kitap/giris.php");
                }
            });
        }
    }


    public void kitapArama(String kitapisim)
    {

        WebView sayfa = (WebView)findViewById(R.id.web);
        sayfa.loadUrl("http://www.bilisimkalesi.com/kitap/kitapArama.php?arama="+kitapisim+"&id="+android_id);

    }




    public void kitapArama(String kitapisim, int yer)
    {

       // WebView sayfa = (WebView)findViewById(R.id.web);
      //  sayfa.loadUrl("http://www.bilisimkalesi.com/kitap/kitapArama.php?arama="+kitapisim+"&id="+android_id+"&yer="+yer);

    }




    int sepetSayi=0;
    public void tiklandi(View view)
    {

        sepetSayi=diziSay(sepetResim);
        String acAdres="";
        int sayfa=((View) view.getParent()).getId();
        boolean atla=false;

        TextView ad=findViewById(R.id.kAd5);
        TextView yazar=findViewById(R.id.kYazar5);
        sepetAd[sepetSayi]=ad.getText()+"***"+yazar.getText();
        sepetNumara[sepetSayi]=String.valueOf(System.currentTimeMillis());

        TextView sepetKitapsayi = (TextView)findViewById(R.id.sepetSayi);
        sepetKitapsayi.setText(String.valueOf(Integer.parseInt(sepetKitapsayi.getText().toString())));


        RelativeLayout ustKatman = (RelativeLayout) view.getParent();
        TextView f=(TextView) ustKatman.getChildAt(1);
        sepetResim[sepetSayi]=sepetResimtmp;
        sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
        sepetSite[sepetSayi]=(String) view.getTag();

        System.out.println("TAM BURAYA TIKLANDI: " + sepetSite[sepetSayi]+" " + android_id );


        /*



        if(sayfa==R.id.drKatman)
        {
            TextView f=findViewById(R.id.drFiyat);


            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="dr";

        }
        else if(sayfa==R.id.idefixKatman)
        {
            TextView f=findViewById(R.id.idefixFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="idefix";
        }
        else if(sayfa==R.id.hbKatman)
        {
            TextView f=findViewById(R.id.hbFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="hb";
        }
        else if(sayfa==R.id.kyKatman)
        {
            TextView f=findViewById(R.id.kyFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="ky";
        }
        else if(sayfa==R.id.babilKatman)
        {
            TextView f=findViewById(R.id.babilFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="bb";
        }
        else if(sayfa==R.id.kediKatman)
        {
            TextView f=findViewById(R.id.kediFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="kedi";
        }
        else if(sayfa==R.id.pandoraKatman)
        {
            TextView f=findViewById(R.id.pandoraFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="pandora";
        }
        else if(sayfa==R.id.kitap16Katman)
        {
            TextView f=findViewById(R.id.kitap16Fiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="kitap16";
        }
        else if(sayfa==R.id.istKatman)
        {
            TextView f=findViewById(R.id.istFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="ist";
        }
        else if(sayfa==R.id.bkmKatman)
        {
            TextView f=findViewById(R.id.bkmFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="bkm";
        }
        else if(sayfa==R.id.ksKatman)
        {
            TextView f=findViewById(R.id.ksFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="ks";
        }
        else if(sayfa==R.id.secKatman)
        {
            TextView f=findViewById(R.id.secFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="sec";
        }
        else if(sayfa==R.id.kadigaKatman)
        {
            TextView f=findViewById(R.id.kadigaFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="kidega";
        }
        else if(sayfa==R.id.ukaKatman)
        {
            TextView f=findViewById(R.id.ukaFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="uka";
        }
        else if(sayfa==R.id.amazonKatman)
        {
            TextView f=findViewById(R.id.amazonFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="amazon";
        }
        else if(sayfa==R.id.kitapkolikKatman)
        {
            TextView f=findViewById(R.id.kitapkolikFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="kitapkolik";
        }
        else if(sayfa==R.id.n11Katman)
        {
            TextView f=findViewById(R.id.n11Fiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="n11";
        }
        else if(sayfa==R.id.nadirKatman)
        {
            TextView f=findViewById(R.id.nadirFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="nadir";
        }
        else if(sayfa==R.id.ilknoktaKatman)
        {
            TextView f=findViewById(R.id.ilknoktaFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="ilknokta";
        }
        else if(sayfa==R.id.koalaKatman)
        {
            TextView f=findViewById(R.id.koalaFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="koala";
        }
        else if(sayfa==R.id.trendyolKatman)
        {
            TextView f=findViewById(R.id.trendyolFiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="trendyol";
        }
        else if(sayfa==R.id.kitap365Katman)
        {
            TextView f=findViewById(R.id.kitap365Fiyat);
            sepetResim[sepetSayi]=sepetResimtmp;
            sepetFiyat[sepetSayi]=f.getText().toString().replaceAll(" TL","");
            sepetSite[sepetSayi]="kitap365";
        }


        */


        if((sepetFiyat[sepetSayi].contains("Bekleniyor"))||(sepetFiyat[sepetSayi].contains("---")))
        {
            sepetResim[sepetSayi]=null;
            sepetFiyat[sepetSayi]=null;
            sepetSite[sepetSayi]=null;
            sepetAd[sepetSayi]=null;
            sepetNumara[sepetSayi]=null;

        }
        else
        {
            sepetEkle(android_id,sepetAd[sepetSayi],sepetSite[sepetSayi],sepetFiyat[sepetSayi],sepetResim[sepetSayi],sepetNumara[sepetSayi],ISBN);
            Toast.makeText(getApplicationContext(), "Kitap sepete eklendi.", Toast.LENGTH_SHORT).show();
        }


    }




    public void sepeteGonder( )
    {

        sepetSayi=diziSay(sepetResim);


            sepetResim[sepetSayi]=null;
            sepetFiyat[sepetSayi]=null;
            sepetSite[sepetSayi]=null;
            sepetAd[sepetSayi]=null;
            sepetNumara[sepetSayi]=null;


            sepetEkle(android_id,sepetAd[sepetSayi],sepetSite[sepetSayi],sepetFiyat[sepetSayi],sepetResim[sepetSayi],sepetNumara[sepetSayi],ISBN);
            Toast.makeText(getApplicationContext(), "Kitap sepete eklendi.", Toast.LENGTH_SHORT).show();



    }







    public int diziSay(String dizi[])
    {
        int s=0;
        while (dizi[s]!=null)
        {
            s++;
        }
        return s;
    }

    String [] sepetSite =  new String[1000];
    String [] sepetFiyat =  new String[1000];
    String [] sepetResim =  new String[1000];
    String [] sepetAd =  new String[1000];
    String [] sepetNumara =  new String[1000];
    String [] sepetISBN =  new String[1000];

    float sepetToplamFiyat=0;


public void sepetYazdir()
    {
        sepetToplamFiyat=0;
       for(int i=0;i<20;i++)
       {
          ////////////System.out.println(sepetFiyat[i]);

           if(sepetFiyat[i]!=null)
                sepetToplamFiyat=sepetToplamFiyat+Float.parseFloat(sepetFiyat[i].replaceAll(",","."));
          ////////////System.out.println("Kitap "+i+" ____________________________");
          ////////////System.out.println(sepetSite[i]);
          ////////////System.out.println(sepetFiyat[i]);
          ////////////System.out.println(sepetResim[i]);
          ////////////System.out.println("________________________________________");
       }
       ////////////System.out.println("TOPLAM FİYAT: "+String.valueOf(sepetToplamFiyat));

    }













    public void sepetYaz()
    {


        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[1000];
        LinearLayout linear = (LinearLayout) findViewById(R.id.kitapList);


       // ScrollView kaydir = (ScrollView) findViewById(R.id.kaydirma);
       // kaydir.smoothScrollTo(0,0);

        linear.removeAllViews();

        int i=0;
        while(i<10)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.sepetsablon, null, false);

         //   TextView yazar =(TextView)layout[i].findViewById(R.id.yazar);
          //  ImageView resim =(ImageView)layout[i].findViewById(R.id.resim);


            linear.addView(layout[i]);
            i++;
        }

    }


    public void sepetYaz2(final LinearLayout icListe, String site)
    {


        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[1000];
        LinearLayout linear = icListe;

        // ScrollView kaydir = (ScrollView) findViewById(R.id.kaydirma);
        // kaydir.smoothScrollTo(0,0);

        linear.removeAllViews();

        int i=0;
        int k=0;
        while(k<1000)
        {

           ////////////System.out.println("site:"+site);
           ////////////System.out.println("sepet:"+sepetBilgiler[k][1]);

            if(sepetBilgiler[k][1]==site) {

                layout[i] = (RelativeLayout) inflater.inflate(R.layout.sepetsablon, null, false);
              // ////////////System.out.println(sepetResim[Integer.parseInt(sepetBilgiler[k][0])]);
                TextView kitapFiyat =(TextView)layout[i].findViewById(R.id.kfiyat);
                kitapFiyat.setText(sepetFiyat[Integer.parseInt(sepetBilgiler[k][0])]+" TL");


                ImageView kitapResim =(ImageView)layout[i].findViewById(R.id.kresim);
                WebView kresimWeb =(WebView)layout[i].findViewById(R.id.kresimWeb);
                ImageView kitapSil =(ImageView)layout[i].findViewById(R.id.kitapSil);

                final int num=Integer.parseInt(sepetBilgiler[k][0]);
                new DownloadImageTask(kitapResim).execute(sepetResim[num]);



                kitapResim.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                       buyukResimGoster(sepetResim[num],num);

                     //   EditText aramaMetin = (EditText) findViewById(R.id.aramaMetin);
                      //  aramaMetin.setText(sepetISBN[num]);
                      //  aramaYap(new View(MainActivity.this));

                    }

                });







                final RelativeLayout l = layout[i];
                kitapSil.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        //icListe.removeView(l);

                        //removeElement(sepetResim,num);
                       // removeElement(sepetFiyat,num);
                       // removeElement(sepetSite,num);


                        sepetSil(sepetNumara[num]);
                      //  sepetResim=sil(sepetResim,num);
                    //    sepetFiyat=sil(sepetFiyat,num);
                      //  sepetSite=sil(sepetSite,num);
                      //  sepetAd=sil(sepetAd,num);
                       // sepetNumara=sil(sepetNumara,num);


                       // sepetKatmanAc( new View(MainActivity.this));

                       // siteSepetYaz();
                    }

                });


                //kresimWeb.loadData("<html><head><style type='text/css'>body {background-color: #e8e8e8;margin-right: 0px;margin-bottom: 0px; margin-left: 0px;margin-top: 0px;}</style> </head><body><center><img height='100%' src='"+sepetResim[Integer.parseInt(sepetBilgiler[k][0])]+"'></center></body></html>", "text/html; charset=utf-8", "UTF-8" );
                //while(kresimWeb.is)

                linear.addView(layout[i]);
                i++;
            }
            k++;

        }

    }


    boolean buyukResimAcildi=false;
    public void buyukResimGoster(String buyukResimAdres, int kitapNumarasi)
    {
        ImageView kRes =(ImageView)findViewById(R.id.buyukResim);
        kRes.setImageResource(0);


        System.out.println("BÜYÜK RESİM AÇILDI:" + buyukResimAdres);


        buyukResimAdres = buyukResimAdres.replaceAll("Small","Big");
        buyukResimAdres = buyukResimAdres.replaceAll("/mnresize/200/307","");

        new DownloadImageTask(kRes).execute(buyukResimAdres);

        RelativeLayout buyukresimKatman =(RelativeLayout)findViewById(R.id.buyukResimKatman);
        buyukresimKatman.setVisibility(View.VISIBLE);

        buyukResimAcildi=true;



        ImageView sepetGuncelFiyatAraButon = (ImageView)findViewById(R.id.sepetGuncelFiyatAraButon);

        final int k=kitapNumarasi;
        sepetGuncelFiyatAraButon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buyukResimKapat();
                barkodArama(sepetISBN[k]);
                menuIslemler((ImageView) findViewById(R.id.imageView3));
            }

        });



        //////////System.out.println("BÜYÜK RESİM GÖSTER");

    }

    public void buyukResimKapat()
    {

        RelativeLayout buyukresimKatman =(RelativeLayout)findViewById(R.id.buyukResimKatman);
        buyukresimKatman.setVisibility(View.INVISIBLE);
        buyukResimAcildi=false;

    }

    public void buyukResimKapat(View view)
    {

        RelativeLayout buyukresimKatman =(RelativeLayout)findViewById(R.id.buyukResimKatman);
        buyukresimKatman.setVisibility(View.INVISIBLE);
        buyukResimAcildi=false;

    }




    String [] sitelerListe={"idefix","dr","alternatif","hb","ky","bb","kedi","kitap16","ist","bkm","ks","sec","pandora","kidega","uka","amazon","n11","kitapkolik","nadir","ilknokta","koala","trendyol","kitap365"};
    public void siteSepetYaz()
    {


        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[1000];
        LinearLayout linear = (LinearLayout) findViewById(R.id.sepetlerListe);

        DecimalFormat df=new DecimalFormat("#.##");
        Float genelToplam=0f;

        // ScrollView kaydir = (ScrollView) findViewById(R.id.kaydirma);
        // kaydir.smoothScrollTo(0,0);

        linear.removeAllViews();

        int i=0;
        sepetSiteSay();





        try {


            for (int m = 0; m < sepetSite.length; m++) {
                if (sepetFiyat[m] != null)
                    genelToplam = genelToplam + Float.parseFloat(sepetFiyat[m].replaceAll(",", "."));
            }
        }
        catch (Exception e)
        {

        }
        String genelToplamMetin=df.format(genelToplam);
        TextView sepetToplamFiyat = (TextView)findViewById(R.id.sepetToplamFiyat);
        sepetToplamFiyat.setText(genelToplamMetin);


        while(i<listeResimler.length)
        {
            int resimSite=0;
            String siteAd="";

            for(int m=0;m<diziSay(sepetSite);m++)
            {
               ////////////System.out.println(sepetSite[m]);
               //////////////System.out.println(sitelerListe[i]);

                if(sepetSite[m].equals(sitelerListe[i])) {
                   ////////////System.out.println("merhaba");
                    resimSite = listeResimler[i];
                    siteAd=sitelerListe[i];
                    break;
                }
            }





            if(resimSite==0)
            {
                i++;
                continue;

            }

            float araToplam=0;
            for(int x=0;x<diziSay(sepetSite);x++)
            {
                if(sepetSite[x].equals(siteAd))
                {
                    araToplam=araToplam+Float.parseFloat(sepetFiyat[x].replaceAll(",","."));
                }

            }

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.sepetliste, null, false);

            //   TextView yazar =(TextView)layout[i].findViewById(R.id.yazar);
            ImageView resim =(ImageView)layout[i].findViewById(R.id.sepetResim);
            TextView fiyat =(TextView)layout[i].findViewById(R.id.drt);



            String dx=df.format(araToplam);
            fiyat.setText("TOPLAM : "+dx+" TL");

            LinearLayout sepetLitapler =(LinearLayout)layout[i].findViewById(R.id.kitapList);
            resim.setImageResource(resimSite);
            sepetYaz2(sepetLitapler,siteAd);



            resim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });





            linear.addView(layout[i]);
            i++;
        }




        ImageView sepetBos = (ImageView) findViewById(R.id.sepetbos);
        RelativeLayout sepetUst = (RelativeLayout) findViewById(R.id.sepetUst);

        if(sepetSiteSay()==0) {
            sepetBos.setVisibility(View.VISIBLE);
            sepetUst.setVisibility(View.INVISIBLE);
        }
        else
        {
            sepetBos.setVisibility(View.INVISIBLE);
            sepetUst.setVisibility(View.VISIBLE);

        }

        //Herşey bittikten sonra ekranda göster

      //  RelativeLayout araKatman = (RelativeLayout) findViewById(R.id.aramaKatmani);
    //    araKatmanBoy = araKatman.getLayoutParams().height;
       // araKatman.getLayoutParams().height=0;
       // araKatman.requestLayout();


        RelativeLayout sepetListe = (RelativeLayout) findViewById(R.id.sepetler);
        sepetListe.setVisibility(View.VISIBLE);
        progressDialog.dismiss();

    }

    int araKatmanBoy;





    String[][] sepetBilgiler = new String [1000][1000];
    public int sepetSiteSay()
    {
        sepetBilgiler = new String [1000][1000];
        int k=0;
        for(int i=0; i<sepetSite.length;i++)
        {
           ////////////System.out.println("SEPET İÇERİK"+sepetSite[i]);

            if(sepetSite[i]==null)
                break;







            switch (sepetSite[i])
            {
                case "idefix": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="idefix";break;
                case "dr": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="dr";break;
                case "hb": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="hb";break;
                case "bb": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="bb";break;
                case "bkm": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="bkm";break;
                case "ks": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="ks";break;
                case "ist": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="ist";break;
                case "kitap16": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="kitap16";break;
                case "kedi": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="kedi";break;
                case "pandora": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="pandora";break;
                case "ky": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="ky";break;
                case "sec": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="sec";break;
                case "kidega": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="kidega";break;
                case "uka": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="uka";break;
                case "amazon": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="amazon";break;
                case "kitapkolik": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="kitapkolik";break;
                case "n11": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="n11";break;
                case "nadir": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="nadir";break;
                case "ilknokta": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="ilknokta";break;
                case "koala": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="koala";break;
                case "trendyol": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="trendyol";break;
                case "kitap365": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="kitap365";break;
                case "alternatif": sepetBilgiler[k][0]=String.valueOf(i); sepetBilgiler[k][1]="alternatif";break;
                default:break;
            }


            System.out.println("SİTEYLE İLGİLİ ŞEYLER:" +sepetBilgiler[k][1]);
            k++;
        }
       System.out.println("Site Sayısı:"+k);
        return k;
    }



    boolean sepetAcildi=false;
    public void sepetKatmanAc(View view) {
       // urunListeKapat();
      //  fiyatListeKapat();
        cikis=false;
       // bulX=System.currentTimeMillis();
        menuIslemler((ImageView) findViewById(R.id.imageView7));
        webGorunumKapat(new View(MainActivity.this));
        new sepetOku().execute();
        sepetAcildi=true;

    }
    public void sepetKatmanKapat() {
        sepetAcildi=false;

    //   RelativeLayout araKatman = (RelativeLayout) findViewById(R.id.aramaKatmani);
      //  araKatman.getLayoutParams().height=araKatmanBoy;
      //  araKatman.requestLayout();


        ImageView sepetBos = (ImageView) findViewById(R.id.sepetbos);
        sepetBos.setVisibility(View.INVISIBLE);

        RelativeLayout sepetListe = (RelativeLayout) findViewById(R.id.sepetler);
        sepetListe.setVisibility(View.INVISIBLE);

    }


    int sayac=0;
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            if (urls == null || urls.length == 0 || urls[0] == null || urls[0].isEmpty()) {
                return null;
            }
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("ImageDownloadError", "Resim indirilemedi: " + e.getMessage());
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            // GÜVENLİK KONTROLÜ: NullPointer / Crash engelleme
            if (bmImage != null && result != null) {
                bmImage.setImageBitmap(result);
            } else {
                Log.w("ImageDownloadWarning", "ImageView veya indirilen resim (Bitmap) null olduğu için atlandı.");
            }
        }
    }

    private class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        RelativeLayout rl;

        public DownloadImageTask2(ImageView bmImage, RelativeLayout rl) {
            this.bmImage = bmImage;
            this.rl = rl;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                java.net.URL url = new java.net.URL(urldisplay);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setReadTimeout(2000);
                connection.connect();
                InputStream in = connection.getInputStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            // DÜZELTME: Görsel veya arka plan rengi hesaplamalarında null çökmesini önler
            if (result != null && bmImage != null) {
                bmImage.setImageBitmap(result);
                if (rl != null) {
                    rl.setBackgroundColor(calculateAverageColor(result, 5));
                }
            }
        }
    }





    public int calculateAverageColor(Bitmap bitmap, int pixelSpacing) {
        int R = 0; int G = 0; int B = 0;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i += pixelSpacing) {
            int color = pixels[i];
            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            n++;
        }
        return Color.rgb(R / n, G / n, B / n);
    }




    public void removeElement(Object[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }



    public String[] sil(String [] dizi, int siraNo)
    {
        String [] tmp= new String[dizi.length];
        int k=0;
        for(int i=0;i<dizi.length;i++)
        {
            if(i==siraNo)
            {
                continue;
            }
            else
            {
                tmp[k]=dizi[i];
                k++;
            }

        }

        return tmp;

    }


    public String ozelKarakterKodla( String str ) {
        String[] escapeCharacters = { "&gt;", "&lt;", "&amp;", "&quot;", "&apos;" };
        String[] onReadableCharacter = {">", "<", "&", "\"\"", "'"};
        for (int i = 0; i < escapeCharacters.length; i++) {
            str = str.replaceAll(escapeCharacters[i], onReadableCharacter[i]);
        } return str;
    }


    public String ozelKarakterCoz( String str ) {
        String[] onReadableCharacter = { "&gt;", "&lt;", "&amp;", "&quot;", "&apos;" };
        String[] escapeCharacters  = {">", "<", "&", "\"\"", "'"};
        for (int i = 0; i < escapeCharacters.length; i++) {
            str = str.replaceAll(escapeCharacters[i], onReadableCharacter[i]);
        } return str;
    }


public void sepetEkle(String id, String ad, String site, String fiyat, String resim, String numara,String isbnNumara)
{
   // //////////System.out.println(ad);
    ad=ozelKarakterCoz(ad);
    ////////////System.out.println(ad);

    String adres,soyad,tcKimlik,telefon,belirtiler;
    ad="asdas";
    soyad="";
    tcKimlik="";
    telefon="";
    belirtiler="";
    adres="";




    WebView web = findViewById(R.id.web);
    web.loadUrl("http://*******.com/yanikayit.php?ad="+ad+
            "&soyad="+soyad+"&tcKimlik="+tcKimlik+"&telefon="+
            telefon+ "&belirtiler="+belirtiler+"&adres="+ adres);






   web.loadUrl("http://www.bilisimkalesi.com/mobil/sepetEkle.php?cihazID="+id+"&ad="+ad+"&site="+site+"&fiyat="+fiyat+"&resim="+resim+"&numara="+numara+"&isbn="+isbnNumara);

   //////////System.out.println("BURADA AD: "+ad);
   web.setWebViewClient(new WebViewClient() {

        public void onPageFinished(WebView view, String url) {
            new sepetOkuSay().execute();
        }
    });
}
    int oku=0;
    RequestQueue queue;
    public void sepetSil(String numara)
    {

        WebView web = (WebView)findViewById(R.id.web);
        web.loadUrl("http://www.bilisimkalesi.com/mobil/sepetSil.php?cihazID="+android_id+"&numara="+numara);
        oku=1;
        web.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
              if(oku==1) {
                  new sepetOku().execute();
                  oku=0;
              }
            }
        });


    }


    public void sepetTopluSil(View view)
    {



        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        WebView web = (WebView)findViewById(R.id.web);
                        web.loadUrl("http://www.bilisimkalesi.com/mobil/sepetTopluSil.php?cihazID="+android_id);
                        oku=1;
                        web.setWebViewClient(new WebViewClient() {

                            public void onPageFinished(WebView view, String url) {
                                if(oku==1) {
                                    new sepetOku().execute();
                                    oku=0;
                                }                            }
                        });
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Sepetinizdeki bütün kitaplar silinecek. Devam etmek istiyor musunuz?").setPositiveButton("Evet", dialogClickListener)
                .setNegativeButton("Hayır", dialogClickListener).show();



    }





    private class sepetOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Sepet yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            try{

                sepetSite =  new String[1000];
                sepetFiyat =  new String[1000];
                sepetResim =  new String[1000];
                sepetAd =  new String[1000];
                sepetNumara =  new String[1000];

                Document doc = Jsoup.connect("http://www.bilisimkalesi.com/mobil/sepetOku.php?cihazID="+android_id).timeout(10000).get();


                //Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);


                Elements elements = doc.select("liste");  // class ismi post-content olan verileri çekmek için
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    sepetAd[i] = baslik.attr("ad");
                    sepetAd[i]=ozelKarakterKodla(sepetAd[i]);
                    sepetSite[i] = baslik.attr("site");
                    sepetFiyat[i] = baslik.attr("fiyat");
                    sepetResim[i] = baslik.attr("resim");
                    sepetNumara[i] = baslik.attr("numara");
                    sepetISBN[i] = baslik.attr("isbn");

                    //////////System.out.println(sepetAd[i]);

                   ////////////System.out.println(sepetAd[i]);
                   ////////////System.out.println(sepetSite[i]);
                   ////////////System.out.println(sepetFiyat[i]);
                   ////////////System.out.println(sepetResim[i]);
                   ////////////System.out.println(sepetNumara[i]);
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            siteSepetYaz();
            TextView sepetKitapsayi = (TextView)findViewById(R.id.sepetSayi);
            sepetKitapsayi.setText(String.valueOf(diziSay(sepetSite)));
        }
    }









    private class sepetOkuSay extends AsyncTask<Void, Void, Void> {

        String authors;
        int i=0;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                Document doc = Jsoup.connect("http://www.bilisimkalesi.com/mobil/sepetOku.php?cihazID="+android_id).timeout(5000).validateTLSCertificates(false).get();
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için
                // Log.v("aranan kitap",kitap);


                elements = doc.select("liste");  // class ismi post-content olan verileri çekmek için

                for (org.jsoup.nodes.Element baslik : elements) {
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView sepetKitapsayi = (TextView)findViewById(R.id.sepetSayi);
            sepetKitapsayi.setText(String.valueOf(i));
        }
    }










    String sayiKontrol(String sMetin, int numara)
    {

/*
                TextView[] fiyatlarKatman = {(TextView) findViewById(R.id.drFiyat),
                (TextView) findViewById(R.id.idefixFiyat),
                (TextView) findViewById(R.id.hbFiyat),
                (TextView) findViewById(R.id.kyFiyat),
                (TextView) findViewById(R.id.babilFiyat),
                (TextView) findViewById(R.id.kitap16Fiyat),
                (TextView) findViewById(R.id.istFiyat),
                (TextView) findViewById(R.id.bkmFiyat),
                (TextView) findViewById(R.id.secFiyat),
                (TextView) findViewById(R.id.pandoraFiyat),
                (TextView) findViewById(R.id.kadigaFiyat),
                (TextView) findViewById(R.id.ukaFiyat),
                (TextView) findViewById(R.id.amazonFiyat),
                (TextView) findViewById(R.id.n11Fiyat),
                (TextView) findViewById(R.id.kitapkolikFiyat),
                (TextView) findViewById(R.id.nadirFiyat),
                (TextView) findViewById(R.id.ilknoktaFiyat),
                (TextView) findViewById(R.id.koalaFiyat),
                (TextView) findViewById(R.id.trendyolFiyat),
                (TextView) findViewById(R.id.kitap365Fiyat),
                (TextView) findViewById(R.id.ksFiyat)
        };


*/


        TextView[] fiyatlarKatman = new TextView[katmanlar.length];
        for(int i=0;i<katmanlar.length;i++)
        {
            fiyatlarKatman[i] = (TextView) katmanlar[i].getChildAt(1);
        }






        String sonuc = sMetin;
        String araMet = sMetin;
      //  System.out.println(araMet);
        araMet = sMetin.replaceAll(" TL", "");
        araMet = araMet.replaceAll(",", ".");
        String dizi = "0123456789.";
        for(int i=0; i<araMet.length();i++)
        {
            String karakter = araMet.charAt(i)+"";
            if(!dizi.contains(karakter))
            {
                System.out.println("***" + araMet +"***");
                fiyatlarKatman[numara].setText("***");
                sonuc = "----";
                break;
            }
        }
        return sonuc;
    }













    public static boolean veriSayiKontrol(String string) {
        Float floatValue;

        string = string.replaceAll(" TL", "");
        string = string.replaceAll(",", ".");



        System.out.println("*" + string + "*");

        if(string == null || string.equals("")) {
           // System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            floatValue = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }






    public void siralaMMM(View view) {


            ImageView sb1 = (ImageView) findViewById(R.id.siralamaButon);
            ImageView sb2 = (ImageView) findViewById(R.id.siralamaButon2);





                //////////System.out.println("BURALARDA");

                sb1.setAlpha(1f);
                sb2.setAlpha(0.3f);


            //  RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            // params.addRule(RelativeLayout.BELOW, R.id.ukaKatman);


        TextView[] fiyatlarKatman = {(TextView) findViewById(R.id.drFiyat),
                (TextView) findViewById(R.id.idefixFiyat),
                (TextView) findViewById(R.id.hbFiyat),
                (TextView) findViewById(R.id.kyFiyat),
                (TextView) findViewById(R.id.babilFiyat),
                (TextView) findViewById(R.id.kitap16Fiyat),
                (TextView) findViewById(R.id.istFiyat),
                (TextView) findViewById(R.id.bkmFiyat),
                (TextView) findViewById(R.id.secFiyat),
                (TextView) findViewById(R.id.pandoraFiyat),
                (TextView) findViewById(R.id.kadigaFiyat),
                (TextView) findViewById(R.id.ukaFiyat),
                (TextView) findViewById(R.id.amazonFiyat),
                (TextView) findViewById(R.id.n11Fiyat),
                (TextView) findViewById(R.id.kitapkolikFiyat),
                (TextView) findViewById(R.id.nadirFiyat),
                (TextView) findViewById(R.id.ilknoktaFiyat),
                (TextView) findViewById(R.id.koalaFiyat),
                (TextView) findViewById(R.id.trendyolFiyat),
                (TextView) findViewById(R.id.kitap365Fiyat),
                (TextView) findViewById(R.id.ksFiyat)
        };




            RelativeLayout[] katmanlar = {(RelativeLayout) findViewById(R.id.drKatman),
                    (RelativeLayout) findViewById(R.id.idefixKatman),
                    (RelativeLayout) findViewById(R.id.hbKatman),
                    (RelativeLayout) findViewById(R.id.kyKatman),
                    (RelativeLayout) findViewById(R.id.babilKatman),
                    (RelativeLayout) findViewById(R.id.kediKatman),
                    (RelativeLayout) findViewById(R.id.kitap16Katman),
                    (RelativeLayout) findViewById(R.id.istKatman),
                    (RelativeLayout) findViewById(R.id.bkmKatman),
                    (RelativeLayout) findViewById(R.id.secKatman),
                    (RelativeLayout) findViewById(R.id.pandoraKatman),
                    (RelativeLayout) findViewById(R.id.kadigaKatman),
                    (RelativeLayout) findViewById(R.id.ukaKatman),
                    (RelativeLayout) findViewById(R.id.amazonKatman),
                    (RelativeLayout) findViewById(R.id.n11Katman),
                    (RelativeLayout) findViewById(R.id.kitapkolikKatman),
                    (RelativeLayout) findViewById(R.id.nadirKatman),
                    (RelativeLayout) findViewById(R.id.ilknoktaKatman),
                    (RelativeLayout) findViewById(R.id.koalaKatman),
                    (RelativeLayout) findViewById(R.id.trendyolKatman),
                    (RelativeLayout) findViewById(R.id.kitap365Katman),
                    (RelativeLayout) findViewById(R.id.ksKatman)
            };






    int[] katmanlarInt = {R.id.drKatman,
                    R.id.idefixKatman,
                    R.id.hbKatman,
                    R.id.kyKatman,
                    R.id.babilKatman,
                    R.id.kediKatman,
                    R.id.kitap16Katman,
                    R.id.istKatman,
                    R.id.bkmKatman,
                    R.id.secKatman,
                    R.id.pandoraKatman,
                    R.id.kadigaKatman,
                    R.id.ukaKatman,
                    R.id.amazonKatman,
                    R.id.n11Katman,
                    R.id.kitapkolikKatman,
                    R.id.nadirKatman,
                    R.id.ilknoktaKatman,
                    R.id.koalaKatman,
                    R.id.trendyolKatman,
                    R.id.kitap365Katman,
                    R.id.ksKatman
    };







        /*
         RelativeLayout k1=(RelativeLayout)findViewById(R.id.drKatman);
        RelativeLayout k2=(RelativeLayout)findViewById(R.id.idefixKatman);
        RelativeLayout k3=(RelativeLayout)findViewById(R.id.hbKatman);
        RelativeLayout k4=(RelativeLayout)findViewById(R.id.kyKatman);
        RelativeLayout k5=(RelativeLayout)findViewById(R.id.bkmKatman);
        RelativeLayout k6=(RelativeLayout)findViewById(R.id.secKatman);
        RelativeLayout k7=(RelativeLayout)findViewById(R.id.pandoraKatman);
        RelativeLayout k8=(RelativeLayout)findViewById(R.id.kadigaKatman);
        RelativeLayout k9=(RelativeLayout)findViewById(R.id.ukaKatman);
        RelativeLayout k10=(RelativeLayout)findViewById(R.id.amazonKatman);
        RelativeLayout k11=(RelativeLayout)findViewById(R.id.kitapkolikKatman);

         */

            String[][] fiyatlarF = {
                    {"0", drFiyat},
                    {"1", idefixFiyat},
                    {"2", hbFiyat},
                    {"3", kyFiyat},
                    {"4", bbFiyat},
                    {"5", kediFiyat},
                    {"6", kitap16Fiyat},
                    {"7", istFiyat},
                    {"8", bkmFiyat},
                    {"9", secFiyat},
                    {"10", pandoraFiyat},
                    {"11", kidegaFiyat},
                    {"12", ukaFiyat},
                    {"13", amazonFiyat},
                    {"14", n11Fiyat},
                    {"15", kitapkolikFiyat},
                    {"16", nadirFiyat},
                    {"17", ilknoktaFiyat},
                    {"18", koalaFiyat},
                    {"19", trendyolFiyat},
                    {"20", kitap365Fiyat},
                    {"21", kSepetiFiyat},

            };

            String a, b;
            Float fiyat1, fiyat2;
            String x, y;


            for (int i = 0; i < fiyatlarF .length; i++) {

                //fiyatlarF[i][1] = sayiKontrol(fiyatlarF[i][1],i);
             //   System.out.println(fiyatlarF[i][1] );


                if (fiyatlarF[i][1]==null)
                    fiyatlarF[i][1] = "1000000";
                else if (fiyatlarF[i][1]=="")
                    fiyatlarF[i][1] = "1000000";
                else if(fiyatlarF[i][1].contains("--"))
                    fiyatlarF[i][1] = "1000000";


                if(!veriSayiKontrol(fiyatlarF[i][1]))
                {
                    fiyatlarF[i][1] = "1000000";
                    fiyatlarKatman[i].setText("-----");
                }


                ////////System.out.println("FIYAT "+i+" "+fiyatlarF[i][1]);
            }


            for (int i = 0; i < fiyatlarF.length - 1; i++) {

                for (int k = i + 1; k < fiyatlarF.length; k++) {
                    x = fiyatlarF[i][1].replaceAll(" TL", "");
                  //  x=x.trim();
                  //  x=x.substring(0,x.length()-1);
                    x = x.replaceAll(",", ".");
                    fiyat1 = Float.parseFloat(x);


                    y = fiyatlarF[k][1].replaceAll(" TL", "");
                    //y=y.trim();
                   // y=y.substring(0,y.length()-1);
                    y = y.replaceAll(",", ".");
                    //////////System.out.println("yaz"+y+"x");
                    fiyat2 = Float.parseFloat(y);
                    // //////////System.out.println("1 "+fiyat1);
                    //  //////////System.out.println("2 "+fiyat2);

                    if (fiyat2 < fiyat1) {
                        //  //////////System.out.println("küçük");
                        a = fiyatlarF[k][0];
                        b = fiyatlarF[k][1];

                        fiyatlarF[k][0] = fiyatlarF[i][0];
                        fiyatlarF[k][1] = fiyatlarF[i][1];

                        fiyatlarF[i][0] = a;
                        fiyatlarF[i][1] = b;

                    }


                }
            }


            for (int i = 0; i < fiyatlarF.length; i++) {
             //   //////////System.out.println(fiyatlar[i][0] + " " + fiyatlar[i][1]);
            }

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            //Listenin başı


            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listeFiyatlar);
            linearLayout.removeAllViews();


            RelativeLayout bulunanx = (RelativeLayout) findViewById(R.id.bulunan);

            //linearLayout.addView(bulunanx);


            int sayi;



                for (int i = 0; i < fiyatlarF .length; i++) {
                    sayi = Integer.parseInt(fiyatlarF[i][0]);
                    linearLayout.addView(katmanlar[sayi]);

                }


    }



    public void siralaA(View view) {
        if (siralamaBasla) {//Herşey sıralama aktif ise çalışacak

            ImageView sb1 = (ImageView) findViewById(R.id.siralamaButon);
            ImageView sb2 = (ImageView) findViewById(R.id.siralamaButon2);


            int siraTip = 0;

            if (view.getId() == R.id.siralamaButon) {
                siraTip = 1;
                sb1.setAlpha(1f);
                sb2.setAlpha(0.3f);
            } else if (view.getId() == R.id.siralamaButon2) {
                siraTip = 2;
                sb1.setAlpha(0.3f);
                sb2.setAlpha(1f);
            }


            //  RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            // params.addRule(RelativeLayout.BELOW, R.id.ukaKatman);
            RelativeLayout[] katmanlar = {(RelativeLayout) findViewById(R.id.drKatman),
                    (RelativeLayout) findViewById(R.id.idefixKatman),
                    (RelativeLayout) findViewById(R.id.hbKatman),
                    (RelativeLayout) findViewById(R.id.kyKatman),
                    (RelativeLayout) findViewById(R.id.babilKatman),
                    (RelativeLayout) findViewById(R.id.kediKatman),
                    (RelativeLayout) findViewById(R.id.kitap16Katman),
                    (RelativeLayout) findViewById(R.id.istKatman),
                    (RelativeLayout) findViewById(R.id.bkmKatman),
                    (RelativeLayout) findViewById(R.id.secKatman),
                    (RelativeLayout) findViewById(R.id.pandoraKatman),
                    (RelativeLayout) findViewById(R.id.kadigaKatman),
                    (RelativeLayout) findViewById(R.id.ukaKatman),
                    (RelativeLayout) findViewById(R.id.amazonKatman),
                    (RelativeLayout) findViewById(R.id.n11Katman),
                    (RelativeLayout) findViewById(R.id.kitapkolikKatman),
                    (RelativeLayout) findViewById(R.id.nadirKatman),
                    (RelativeLayout) findViewById(R.id.ilknoktaKatman),
                    (RelativeLayout) findViewById(R.id.koalaKatman),
                    (RelativeLayout) findViewById(R.id.trendyolKatman),
                     (RelativeLayout) findViewById(R.id.kitap365Katman),
                    (RelativeLayout) findViewById(R.id.ksKatman)


            };



        int[] katmanlarInt = {R.id.drKatman,
                    R.id.idefixKatman,
                    R.id.hbKatman,
                    R.id.kyKatman,
                    R.id.babilKatman,
                    R.id.kediKatman,
                    R.id.kitap16Katman,
                    R.id.istKatman,
                    R.id.bkmKatman,
                    R.id.secKatman,
                    R.id.pandoraKatman,
                    R.id.kadigaKatman,
                    R.id.ukaKatman,
                    R.id.amazonKatman,
                    R.id.n11Katman,
                    R.id.kitapkolikKatman,
                    R.id.nadirKatman,
                    R.id.ilknoktaKatman,
                    R.id.koalaKatman,
                    R.id.trendyolKatman,
                R.id.kitap365Katman,
                R.id.ksKatman
        };







        /*
         RelativeLayout k1=(RelativeLayout)findViewById(R.id.drKatman);
        RelativeLayout k2=(RelativeLayout)findViewById(R.id.idefixKatman);
        RelativeLayout k3=(RelativeLayout)findViewById(R.id.hbKatman);
        RelativeLayout k4=(RelativeLayout)findViewById(R.id.kyKatman);
        RelativeLayout k5=(RelativeLayout)findViewById(R.id.bkmKatman);
        RelativeLayout k6=(RelativeLayout)findViewById(R.id.secKatman);
        RelativeLayout k7=(RelativeLayout)findViewById(R.id.pandoraKatman);
        RelativeLayout k8=(RelativeLayout)findViewById(R.id.kadigaKatman);
        RelativeLayout k9=(RelativeLayout)findViewById(R.id.ukaKatman);
        RelativeLayout k10=(RelativeLayout)findViewById(R.id.amazonKatman);
        RelativeLayout k11=(RelativeLayout)findViewById(R.id.kitapkolikKatman);

         */

            String[][] fiyatlarF = {
                    {"0", drFiyat},
                    {"1", idefixFiyat},
                    {"2", hbFiyat},
                    {"3", kyFiyat},
                    {"4", bbFiyat},
                    {"5", kediFiyat},
                    {"6", kitap16Fiyat},
                    {"7", istFiyat},
                    {"8", bkmFiyat},
                    {"9", secFiyat},
                    {"10", pandoraFiyat},
                    {"11", kidegaFiyat},
                    {"12", ukaFiyat},
                    {"13", amazonFiyat},
                    {"14", n11Fiyat},
                    {"15", kitapkolikFiyat},
                    {"16", nadirFiyat},
                    {"17", ilknoktaFiyat},
                    {"18", koalaFiyat},
                    {"19", trendyolFiyat},
                    {"20", kitap365Fiyat},
                    {"21", kSepetiFiyat}};

            String a, b;
            Float fiyat1, fiyat2;
            String x, y;




            for (int i = 0; i < fiyatlarF .length; i++) {

                fiyatlarF[i][1] = sayiKontrol(fiyatlarF[i][1],i);


                if (fiyatlarF[i][1]==null)
                    fiyatlarF[i][1] = "1000000";
                else if (fiyatlarF[i][1]=="")
                    fiyatlarF[i][1] = "1000000";
                else if(fiyatlarF[i][1].contains("--"))
                    fiyatlarF[i][1] = "1000000";

                ////////System.out.println("FIYAT "+i+" "+fiyatlarF[i][1]);
            }


            for (int i = 0; i < fiyatlarF.length - 1; i++) {

                for (int k = i + 1; k < fiyatlarF.length; k++) {
                    x = fiyatlarF[i][1].replaceAll(" TL", "");
                   // x=x.trim();
                  //  x=x.substring(0,x.length()-1);
                    x = x.replaceAll(",", ".");
                    fiyat1 = Float.parseFloat(x);


                    y = fiyatlarF[k][1].replaceAll(" TL", "");
                   // y=y.trim();
                    //y=y.substring(0,y.length()-1);
                    y = y.replaceAll(",", ".");
                    //////////System.out.println("yaz"+y+"x");
                    fiyat2 = Float.parseFloat(y);
                    // //////////System.out.println("1 "+fiyat1);
                    //  //////////System.out.println("2 "+fiyat2);

                    if (fiyat2 < fiyat1) {
                        //  //////////System.out.println("küçük");
                        a = fiyatlarF[k][0];
                        b = fiyatlarF[k][1];

                        fiyatlarF[k][0] = fiyatlarF[i][0];
                        fiyatlarF[k][1] = fiyatlarF[i][1];

                        fiyatlarF[i][0] = a;
                        fiyatlarF[i][1] = b;

                    }


                }
            }


            for (int i = 0; i < fiyatlarF.length; i++) {
                //   //////////System.out.println(fiyatlar[i][0] + " " + fiyatlar[i][1]);
            }

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


            //Listenin başı


            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listeFiyatlar);
            linearLayout.removeAllViews();


            RelativeLayout bulunanx = (RelativeLayout) findViewById(R.id.bulunan);

            //linearLayout.addView(bulunanx);


            int sayi;


            if (siraTip == 1) {
                for (int i = 0; i < fiyatlarF.length; i++) {
                    sayi = Integer.parseInt(fiyatlarF[i][0]);
                    linearLayout.addView(katmanlar[sayi]);

                }
            } else if (siraTip == 2) {
                for (int i = fiyatlarF.length-1; i >= 0; i--) {
                    sayi = Integer.parseInt(fiyatlarF[i][0]);
                    linearLayout.addView(katmanlar[sayi]);

                }
            }


             ScrollView kaydir = (ScrollView) findViewById(R.id.scrollView5);
             kaydir.smoothScrollTo(0, 0);


            //  x.setLayoutParams(params);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Lütfen bütün fiyatların alınmasını bekleyiniz.", Toast.LENGTH_SHORT).show();

        }
    }



    private void AskForRating(int _appUsedCount){

        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
        alert.setTitle("Puanlamaya Katılın");
        alert.setIcon(R.drawable.book);
        alert.setMessage("Uygulamamızı kullandığınız için teşekkürler. Değerlendirme yapmak için lütfen puanlamaya katılın. Düşünceleriniz bizim için çok önemlidir!");
        alert.setPositiveButton("Puan Ver", new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(i);
                } catch (Exception e) {
                    String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }


            }
        });
        alert.setNegativeButton("Şimdi Değil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }








    public void resimBoyutlandir()
    {
        Display display = getWindowManager().getDefaultDisplay();
        final int ekranGenislik = display.getWidth();

        int boy=Math.round(ekranGenislik/4);
        int en = Math.round((boy*300)/540);


        ImageView siteResim;







        LinearLayout fiyatListeKatmanlar = (LinearLayout) findViewById(R.id.listeFiyatlar);
        int fiyatsayisi = fiyatListeKatmanlar.getChildCount();
        katmanlar = new RelativeLayout[fiyatsayisi];

        for (int i = 0; i < fiyatsayisi; i++) {
            katmanlar[i] = (RelativeLayout) fiyatListeKatmanlar.getChildAt(i);
        }

        for(int i=0;i<katmanlar.length;i++)
        {
            siteResim= (ImageView) katmanlar[i].getChildAt(2);
            siteResim.getLayoutParams().width= boy;
            siteResim.getLayoutParams().height= en;
            siteResim.requestLayout();
            System.out.println("SİTE KÜÇÜLTME:" + i);

        }







/*




        ImageView resim1= (ImageView)findViewById(R.id.drResim);
        ImageView resim2= (ImageView)findViewById(R.id.idefixResim);
        ImageView resim3= (ImageView)findViewById(R.id.hbResim);
        ImageView resim4= (ImageView)findViewById(R.id.kyResim);
        ImageView resim5= (ImageView)findViewById(R.id.bkmResim);
        ImageView resim6= (ImageView)findViewById(R.id.secResim);
        ImageView resim7= (ImageView)findViewById(R.id.pandoraResim);
        ImageView resim8= (ImageView)findViewById(R.id.kadigaResim);
        ImageView resim9= (ImageView)findViewById(R.id.ukaResim);
        ImageView resim10= (ImageView)findViewById(R.id.amazonResim);
        ImageView resim11= (ImageView)findViewById(R.id.kitapkolikResim);
        ImageView resim12= (ImageView)findViewById(R.id.n11Resim);
        ImageView resim13= (ImageView)findViewById(R.id.nadirResim);
        ImageView resim14= (ImageView)findViewById(R.id.ilknoktaResim);
        ImageView resim15= (ImageView)findViewById(R.id.babilResim);
        ImageView resim16= (ImageView)findViewById(R.id.kediResim);
        ImageView resim17= (ImageView)findViewById(R.id.kitap16Resim);
        ImageView resim18= (ImageView)findViewById(R.id.istResim);
        ImageView resim19= (ImageView)findViewById(R.id.ksResim);
        ImageView resim20= (ImageView)findViewById(R.id.koalaResim);
        ImageView resim21= (ImageView)findViewById(R.id.trendyolResim);
        ImageView resim22= (ImageView)findViewById(R.id.kitap365Resim);









        int boy=Math.round(ekranGenislik/4);
        int en = Math.round((boy*300)/540);
        resim1.getLayoutParams().width= boy;
        resim2.getLayoutParams().width= boy;
        resim3.getLayoutParams().width= boy;
        resim4.getLayoutParams().width= boy;
        resim5.getLayoutParams().width= boy;
        resim6.getLayoutParams().width= boy;
        resim7.getLayoutParams().width= boy;
        resim8.getLayoutParams().width= boy;
        resim9.getLayoutParams().width= boy;
        resim10.getLayoutParams().width= boy;
        resim11.getLayoutParams().width= boy;
        resim12.getLayoutParams().width= boy;
        resim13.getLayoutParams().width= boy;
        resim14.getLayoutParams().width= boy;
        resim15.getLayoutParams().width= boy;
        resim16.getLayoutParams().width= boy;
        resim17.getLayoutParams().width= boy;
        resim18.getLayoutParams().width= boy;
        resim19.getLayoutParams().width= boy;
        resim20.getLayoutParams().width= boy;
        resim21.getLayoutParams().width= boy;
        resim22.getLayoutParams().width= boy;








        resim1.getLayoutParams().height= en;
        resim2.getLayoutParams().height= en;
        resim3.getLayoutParams().height= en;
        resim4.getLayoutParams().height= en;
        resim5.getLayoutParams().height= en;
        resim6.getLayoutParams().height= en;
        resim7.getLayoutParams().height= en;
        resim8.getLayoutParams().height= en;
        resim9.getLayoutParams().height= en;
        resim10.getLayoutParams().height= en;
        resim11.getLayoutParams().height= en;
        resim12.getLayoutParams().height= en;
        resim13.getLayoutParams().height= en;
        resim14.getLayoutParams().height= en;
        resim15.getLayoutParams().height= en;
        resim16.getLayoutParams().height= en;
        resim17.getLayoutParams().height= en;
        resim18.getLayoutParams().height= en;
        resim19.getLayoutParams().height= en;
        resim20.getLayoutParams().height= en;
        resim21.getLayoutParams().height= en;
        resim22.getLayoutParams().height= en;





*/

        ////////////System.out.println("RESİM"+ekranGenislik);
      //  //////////System.out.println("RESİM"+resim.getLayoutParams().width);
       // //////////System.out.println("RESİM"+resim.getLayoutParams().height);









        RelativeLayout yeniCikanlar = (RelativeLayout)findViewById(R.id.yeniCikan);
        yeniCikanlar.getLayoutParams().width=Math.round((ekranGenislik-30)/2);
    }


public void metinSil(View view)
{
    EditText metin=(EditText)findViewById(R.id.aramaMetin);
    metin.setText("");
}



public void kitaplikAc(View view)
{


       Intent kitaplik = new Intent(this,kitaplik.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
       startActivity(kitaplik);
       overridePendingTransition(0, 0);

}


/*
public class kitaplikVeriYaz extends AsyncTask<Void, Void, Void> {


        String KitapAdY;
        String KitapYazarY;
        String YayinEviY;
        String AciklamaY;
        String ResimAdresY;
        String AdresY;
        String kitaplikVeriAdres;


        public kitaplikVeriYaz(String KitapAdX, String KitapYazarX, String YayinEviX, String ResimAdresX, String AdresX)
        {
            KitapAdY=KitapAdX;
            KitapYazarY=KitapYazarX;
            YayinEviY=YayinEviX;
            ResimAdresY=ResimAdresX;
            kitaplikVeriAdres=AdresX;

        }

        String authors;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                Document doc = Jsoup.connect(kitaplikVeriAdres).timeout(3000).validateTLSCertificates(false).get();

                //////////System.out.println("ADRES:"+kitaplikVeriAdres);
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                AciklamaAdres=kitaplikVeriAdres;
                if(AciklamaAdres.contains("idefix.com")) {

                    elements = doc.select("section[class=page-productDetails] div[class=container] div[class=product-description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;

                        break;

                    }
                }

                else if(AciklamaAdres.contains("dr.com")) {

                    elements = doc.select("div[class=container] div[class=summary]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("hepsiburada.com")) {

                    elements = doc.select("section[class=product-detail-container container] div[id=tabProductDesc] td[class=no-border] div[id=productDescriptionContent] pre");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("kitapyurdu.com")) {

                    elements = doc.select("div[class=description] span[itemprop=description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("pandora.com")) {

                    elements = doc.select("div[class=row urunAciklama]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisi = data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("bkmkitap.com")) {

                    elements = doc.select("div[id=productDetailTab]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        // int yer=0;
                        //yer=data.indexOf("Kısa Özet");
                        //yer=yer+20;

                        // AciklamaBilgisi = data.substring(yer,data.length()-1);
                        AciklamaBilgisi=data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("kitapsec.com")) {
                    elements = doc.select("div[class=TabDivList] div[id=tab1]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    elements.select("strong").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("kidega.com")) {
                    elements = doc.select("div[class=col-md-8 col-xs-12] div[id=bookDescription]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }
                else if(AciklamaAdres.contains("ucuzkitapal.com")) {
                    elements = doc.select("div[id=content_description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("amazon.com")) {
                    elements = doc.select("div[id=bookDescription_feature_div]");  // class ismi post-content olan verileri çekmek için
                    elements.select("div[id=psPlaceHolder]").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }

                else if(AciklamaAdres.contains("kitapkolik.com")) {
                    elements = doc.select("div[id=divOnyazi]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisi=data;
                        break;
                    }
                }



                kitaplikAciklamaDizi[kitaplikSayac] = AciklamaBilgisi;
                AciklamaY=AciklamaBilgisi;
                AciklamaBilgisi="";



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            kitaplikSayac++;
            WebView webKitaplik=(WebView) findViewById(R.id.webKitaplik);
            String KayitBilgisi ="http://www.bilisimkalesi.com/mobil/kitaplikYaz.php?CihazId="+android_id +"&KitapAd="+KitapAdY+"&KitapYazar="+KitapYazarY+"&YayinEvi="+YayinEviY+"&Aciklama="+AciklamaY+"&ResimAdres="+ResimAdresY;
            webKitaplik.loadUrl(KayitBilgisi);

        }
    }
    */


/*
    static class kitapSinif {
        static String identifier;
    }



    public String metinAl(String metinAdres)
    {
        String htmlVeri="";
        try {
            URL google = new URL(metinAdres);
            BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
            String input;
            StringBuffer stringBuffer = new StringBuffer();
            while ((input = in.readLine()) != null) {
                stringBuffer.append(input);
            }
            in.close();
            String htmlData = stringBuffer.toString();
            //////////System.out.println(htmlData);
            htmlVeri=htmlData;
        }
        catch (Exception e)
        {}
        return htmlVeri;
    }




    public void  jsonAl() throws Exception {




        JSONObject obj = new JSONObject(metinAl("https://www.googleapis.com/books/v1/volumes?q=y%C4%B1lmaz%20%C3%B6zdil&maxResults=40"));
      //  String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("items");
        for (int i = 0; i < arr.length(); i++)
        {


            String post_id = arr.getJSONObject(i).getJSONObject("volumeInfo").getString("title");

            //////////System.out.println(post_id);

        }






        }


*/




    Boolean tamAramaBasladi=false;


    //İDEFİX ARAMA YAPMA
    //-----------------------------------------------------------------------------------
    private class idefixArama extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //  progressDialog = new ProgressDialog(MainActivity.this);
            //   progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            //   progressDialog.setMessage("Ürünler yükleniyor...");
            //   progressDialog.setIndeterminate(false);
            //   progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                // //////////System.out.println(aranacakKitap);
                if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    aranacakKitap=aranacakKitap.replaceAll(" ","-");
                }
                // bulunanSayisi=0;
                //   Document doc = Jsoup.connect("https://www.idefix.com/search/?ActiveCategoryId=11693&Q="+aranacakKitap+"&SortType=soldcount&SortOrder=desc").timeout(10000).validateTLSCertificates(false).get();
                Document doc = Jsoup.connect("https://www.istanbulkitapcisi.com/arama?q="+aranacakKitap).timeout(5000).validateTLSCertificates(false).get();
                Elements elements = doc.select("a[class=col-12 product-title]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=yazdirUrunSayi;
                int baslamaSayisi=yazdirUrunSayi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;
                }
                yazdirUrunSayi=i;
                bulunanSayisi=i;


                //REsim adresleri alındı
                elements = doc.select("picture[class=image-inner] img");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("data-src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }


                //Yazar  alındı
                elements = doc.select("a[class=col-12 fw-bold text-body model-title]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    fiyatlar[i]="";
                    i++;
                }

                //fiyatlar  alındı
                //  elements = doc.select("div[class=item product-box-alternative] div[class=box-line-4] span[id=prices]");  // class ismi post-content olan verileri çekmek için
                //  i=baslamaSayisi;
                //  for (org.jsoup.nodes.Element baslik : elements) {

                //      String data = baslik.text();
                //       fiyatlar[i]="";
                // fiyatlar[i]=data;
                //       i++;
                //   }


                //Adresler  alındı
                elements = doc.select("a[class=image-wrapper image-animate-zoom]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https://www.istanbulkitapcisi.com"+baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }



                //yayınevi  alındı
                elements = doc.select("a[class=col-12 fw-semibold text-primary brand-title]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    yayinEvi[i]=data;
                    i++;
                    // Log.v("yayınevi: ",data);
                }

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            progressDialog.dismiss();
            if(bulunanSayisi==0)
            {
                Toast.makeText(getApplicationContext(), "Aradığınız kriterlere uygun kayıt bulunamadı.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                tamAramaBasladi=true;
                acikSite="arama";
                TextView cokSatanlar=(TextView)findViewById(R.id.cokSatanlar);
                cokSatanlar.setText("ARAMA SONUÇLARI");
                // klavyeKapat();
                klavyeGizle();
              //  bulunanKontrol();
                yazdir();

            }




        }
    }









    private class kitapYurduArama extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String[] eski = {" ", "ü", "Ü", "ö", "Ö", "ı", "İ", "ğ", "Ğ", "ş", "Ş", "ç", "Ç"};
                String[] yeni = {"+", "u", "U", "o", "O", "i", "I", "g", "G", "s", "S", "c", "C"};

                String kyAranacakKitap = aranacakKitap;
                if ((kyAranacakKitap != null)) {
                    for (int m = eski.length - 1; m >= 0; m--) {
                        kyAranacakKitap = kyAranacakKitap.replaceAll(eski[m], yeni[m]);
                    }
                }

                Document  doc = Jsoup.connect("https://www.kitapyurdu.com/index.php?route=product/search&sort=purchased&order=DESC&filter_name="+kyAranacakKitap+"&fuzzy=0&filter_product_type=1").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                doc.select("div[class=price-old price-passive]").remove();

                Elements elements = doc.select("div[id=product-table] div[class=name ellipsis]");  // class ismi post-content olan verileri çekmek için

                int i=yazdirUrunSayi;
                int baslangic = i;
                int baslamaSayisi=yazdirUrunSayi;


                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
                // Log.v("KİTAP SAYISI:",String.valueOf(i));

                yazdirUrunSayi=i;
                bulunanSayisi=i;


                //REsim adresleri alındı
                elements = doc.select("div[id=product-table] div[class=image] img");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    data = data.replaceAll("wi:100","wi:200");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[id=product-table] div[class=price] span[class=value]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text()+" TL";
                    fiyatlar[i]="";
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[id=product-table] div[class=author compact ellipsis] ");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[id=product-table] div[class=image] a");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");


                    for (int m = eski.length - 1; m >= 0; m--) {
                        data = data.replaceAll(eski[m], yeni[m]);
                    }


                    adresler[i]=data;
                    i++;
                }


                elements = doc.select("div[id=product-table] div[class=publisher]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data.replaceAll(",","");
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new idefixArama().execute();
        }
    }











    private class secArama extends AsyncTask<Void, Void, Void> {
        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //  progressDialog = new ProgressDialog(MainActivity.this);
            //   progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            //   progressDialog.setMessage("Ürünler yükleniyor...");
            //   progressDialog.setIndeterminate(false);
            //   progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String[] eski = {" ", "ü", "Ü", "ö", "Ö", "ı", "İ", "ğ", "Ğ", "ş", "Ş", "ç", "Ç"};
                String[] yeni = {"+", "u", "U", "o", "O", "i", "I", "g", "G", "s", "S", "c", "C"};

                String secAranacakKitap = aranacakKitap;
                if ((secAranacakKitap != null)) {
                    for (int m = eski.length - 1; m >= 0; m--) {
                        secAranacakKitap = secAranacakKitap.replaceAll(eski[m], yeni[m]);
                    }
                }


              //  //System.out.println("DENEME");
                Document doc = Jsoup.connect("https://www.kitapsec.com/Arama/index.php?a="+secAranacakKitap).timeout(5000).get();
                Elements elements = doc.select("tbody tr td a[class=text]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=yazdirUrunSayi;
                int baslangic = i;
                int baslamaSayisi=yazdirUrunSayi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    fiyatlar[i]="";
                    i++;
                }

                yazdirUrunSayi=i;
                bulunanSayisi=i;


                //REsim adresleri alındı
                elements = doc.select("tbody tr td a[class=img] img");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = "https:"+baslik.attr("src");
                    fotolar[i]=data;
                    // //Log.w("resimler", data);
                    i++;
                }

             /*   //fiyatlar  alındı
                elements = doc.select("tbody tr td span[class=fiyat] font[class=satis]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    fiyatlar[i]=data;
                    i++;
                }
*/

                //fiyatlar  alındı
                elements = doc.select("tbody tr td span[itemprop=author] span");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("tbody tr td a[class=img]");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    adresler[i]=data;
                    //System.out.println("ADRESLER :" + data);
                    i++;
                }

                elements = doc.select("tbody tr td span[class=yynImg] span");  // class ismi post-content olan verileri çekmek için
                i=baslamaSayisi;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data;
                    i++;
                }





                for(i=baslangic;i<basliklar.length;i++)
                {
                    basliklar[i]=basliklar[i].replaceAll(yazarlar[i],"");
                    basliklar[i]=basliklar[i].replaceAll(yayinEvi[i],"");
                }



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new idefixArama().execute();

        }
    }






























    int listeDurum=1;
    Boolean degisti=false;

    public void listeBarGecis(View view)
    {
        RelativeLayout yeniCikan=(RelativeLayout)findViewById(R.id.yeniCikan);
        RelativeLayout cokOkunan=(RelativeLayout)findViewById(R.id.cokOkunan);
        TextView yeniMetin=(TextView)findViewById(R.id.yeniMetin);
        TextView okunanMetin=(TextView)findViewById(R.id.okunanMetin);



        if((view.getId()==R.id.yeniCikan)&&(listeDurum!=1))
        {
            yeniCikan.setBackgroundColor(Color.rgb(0x9e,0x9e,0x9e));
            cokOkunan.setBackgroundColor(Color.rgb(0xff,0xff,0xff));
            yeniMetin.setTextColor(Color.WHITE);
            okunanMetin.setTextColor(Color.rgb(0x71,0x71,0x71));
            listeDurum=1;
           // yazdirSayfaSayi=1;
            degisti=true;
            sonrakiSayfalar=false;
            sayfaSayi=0;
            listeAl();


        }
        else if((view.getId()==R.id.cokOkunan)&&(listeDurum!=2))
        {
            cokOkunan.setBackgroundColor(Color.rgb(0x9e,0x9e,0x9e));
            yeniCikan.setBackgroundColor(Color.rgb(0xff,0xff,0xff));
            okunanMetin.setTextColor(Color.WHITE);
            yeniMetin.setTextColor(Color.rgb(0x71,0x71,0x71));
            listeDurum=2;
          //  yazdirSayfaSayi=1;
            degisti=true;
            sonrakiSayfalar=false;
            sayfaSayi=0;
            listeAl();


        }

    }

    public void listeAl()
    {

        if(acikSite=="dr")
        {
            new drOku().execute();
        }
        else if(acikSite=="idefix")
        {
            new idefixOku().execute();
        }
        else if(acikSite=="hepsiburada")
        {
           // new hepsiburadaOku().execute();
            new GetData().execute();

        }
        else if(acikSite=="ky")
        {
            new kitapYurduOku().execute();
        }
      //  else if(acikSite=="bb")
       // {
      //      babilVeriOkuAl();
      //  }
        else if(acikSite=="bkm")
        {
            new bkmOku().execute();
        }
        else if(acikSite=="sec")
        {
            new secOku().execute();
        }
        else if(acikSite=="uka")
        {
            new ukaOku().execute();
        }
        else if(acikSite=="kitapkolik")
        {
            new kitapkolikOku().execute();
        }
        else if(acikSite=="kidega")
        {
            new kidegaOku().execute();
        }
    }





    boolean zamanDoldu=true;
    boolean gecikti=false;

    void biabilVerAl()
    {
        final WebView babilWebKatman = (WebView)findViewById(R.id.babilWebKatman);
        babilWebKatman.getSettings().setJavaScriptEnabled(true);
        babilWebKatman.getSettings().setLoadsImagesAutomatically(false);
        babilWebKatman.addJavascriptInterface(new LoadListener(), "HTMLOUT");
        babilWebKatman.loadUrl("https://www.babil.com/arama?q="+ISBN);
        zamanDoldu=true;
        gecikti=false;

        babilWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(final  WebView view, String url, Bitmap favicon) {

                zamanDoldu = true;
                Handler handler77 = new Handler();
                handler77.postDelayed(new Runnable() {
                    public void run() {
                        if (zamanDoldu)
                        {
                         //   view.loadUrl("javascript:window.HTMLOUT.processHTML('<html><head></head><body>sonucyok</body</html>');");
                            //System.out.println("bişeyler oldu");
                            view.stopLoading();

                        }
                    }
                }, 2000);



            }

            public void onPageFinished(WebView view, String url) {
                zamanDoldu=false;
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                //System.out.println("evet oldu");
                view.stopLoading();

            }
        });

    }

    String babilDokuman="";
    class LoadListener{
        @JavascriptInterface
        public void processHTML(String html)
        {
           // Log.e("result2",html);
            babilDokuman=html;
           // //System.out.println("babilsonuc: "+ html);
            if(!herseyiDurdur)
            {
                new bbBul().execute();
            }

        }
    }



    void babilVeriAlSayfa(String syf)
    {


        final WebView babilWebKatman = (WebView)findViewById(R.id.babilWebKatman);
        babilWebKatman.getSettings().setJavaScriptEnabled(true);
        babilWebKatman.getSettings().setLoadsImagesAutomatically(false);
        babilWebKatman.addJavascriptInterface(new LoadListener3(), "HTMLOUT");
        babilWebKatman.loadUrl(syf);

         babilDokumanSayfa="";

        babilWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
            }

            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });

    }

    String babilDokumanSayfa="";
    class LoadListener3{
        @JavascriptInterface
        public void processHTML(String html)
        {
            babilDokumanSayfa=html;
            if(detayTiklandi==true) {
                detayTiklandi=false;
                new AciklamaAlDetay().execute();
            }
            else
                new AciklamaAl().execute();

        }
    }








    void babilVeriOkuAl()
    {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.setMessage("Ürünler yükleniyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        final WebView babilWebKatman = (WebView)findViewById(R.id.babilWebKatman);
        babilWebKatman.getSettings().setJavaScriptEnabled(true);
        babilWebKatman.getSettings().setLoadsImagesAutomatically(false);
        babilWebKatman.addJavascriptInterface(new LoadListener2(), "HTMLOUT");

        String sayfa="";
        if(listeDurum==1)
            sayfa="https://www.babil.com/kitap/yeni-kitaplar";
        else
            sayfa="https://www.babil.com/kitap/cok-satanlar";

        babilWebKatman.loadUrl(sayfa);



        babilWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
            }

            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });

    }

    String babilDokumanOku="";
    class LoadListener2{
        @JavascriptInterface
        public void processHTML(String html)
        {
            //Log.e("result",html);
            ////System.out.println("deneme");
            babilDokumanOku=html;
            new babilOku().execute();
        }
    }








    void ksepetiVeriAl()
    {
        final WebView ksWebKatman = (WebView)findViewById(R.id.ksWebKatman);
        ksWebKatman.getSettings().setJavaScriptEnabled(true);
        ksWebKatman.getSettings().setLoadsImagesAutomatically(false);
        ksWebKatman.addJavascriptInterface(new LoadListenerKsepeti(), "HTMLOUT");
        ksWebKatman.clearHistory();
        ksWebKatman.clearCache(true);
        ksWebKatman.loadUrl("about:blank");
        ksWebKatman.loadUrl("https://www.kitapsepeti.com/arama?q="+ISBN);
        String geciciISBN = ISBN;
        zamanDoldu=true;
        gecikti=false;
        ksWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(final  WebView view, String url, Bitmap favicon) {
                /*
                zamanDoldu = true;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (zamanDoldu)
                        {
                          //  view.loadUrl("javascript:window.HTMLOUT.processHTML('<html></html>');");
                            ////System.out.println("ATLADI");
                            view.stopLoading();

                        }
                    }
                }, 2000);

*/

            }

            public void onPageFinished(final WebView view, String url) {
          //      zamanDoldu=false;



                            view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");


            }
        });

    }

    String kSepetiDokuman="";
    class LoadListenerKsepeti{
        @JavascriptInterface
        public void processHTML(String html)
        {
            // Log.e("KİTAP SEPETİ : ",html);
            kSepetiDokuman=html;
            if(!herseyiDurdur)
            {
                System.out.println(kSepetiDokuman);
                new kSepetiBul().execute();
                //ksepetiVeriAl();
            }

        }
    }







    void bkmVeriAl()
    {
        final WebView bkmWebKatman = (WebView)findViewById(R.id.bkmWebKatman);
        bkmWebKatman.getSettings().setJavaScriptEnabled(true);
        bkmWebKatman.getSettings().setLoadsImagesAutomatically(false);
        bkmWebKatman.addJavascriptInterface(new LoadListenerBKM(), "HTMLOUT");
        bkmWebKatman.clearHistory();
        bkmWebKatman.clearCache(true);
       // bkmWebKatman.loadUrl("about:blank");
        bkmWebKatman.loadUrl("https://www.bkmkitap.com/arama?q="+ISBN);
        String geciciISBN = ISBN;
        zamanDoldu=true;
        gecikti=false;
        bkmWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(final  WebView view, String url, Bitmap favicon) {

             /*

                zamanDoldu = true;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (zamanDoldu)
                        {
                            //  view.loadUrl("javascript:window.HTMLOUT.processHTML('<html></html>');");
                            System.out.println("ATLADI BKM");
                            view.stopLoading();

                        }
                    }
                }, 5000);

*/

            }

            public void onPageFinished(final WebView view, String url) {
            //    zamanDoldu=false;



                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                       // view.stopLoading();

                    }
                }, 1000);

            }
        });

    }

    String bkmDokuman="";
    class LoadListenerBKM{
        @JavascriptInterface
        public void processHTML(String html)
        {
            // Log.e("KİTAP SEPETİ : ",html);
            bkmDokuman=html;
            if(!herseyiDurdur)
            {
             //   new bkmBul().execute();
                System.out.println(bkmDokuman);
            }

        }
    }







































    boolean sonrakiSayfalar=false;
    String sonrakiSayfaIcerik="";
    int sonrakiSayfaNo=1;
    String sonrakiSayfa="";
    int sayfaSayi=0;
    void genelWebOku()
    {

        sayfaSayi=0;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.setMessage("Veriler Alınıyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        sonrakiSayfalar=true;
        sonrakiSayfaNo++;

        final String sayfa = genelSayfaAl();
        sonrakiSayfa=sayfa;
       // //System.out.println("YAZDIRILAN SAYFA: " + sayfa);


        if(acikSite=="hepsiburada") {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    sonrakiSayfaIcerik=veriAl(sayfa, "class=\"headroom-wrapper\"", "class=\"_2Cl7W\"");
                    progressDialog.dismiss();
                    new hepsiburadaOku().execute();
                }
            }, 100);

        }
        else if(acikSite=="ky") {
            progressDialog.dismiss();
            new kitapYurduOku().execute();
        }
        else if(acikSite=="bkm") {
            progressDialog.dismiss();
            new bkmOku().execute();
        }
        else if(acikSite=="sec") {
            progressDialog.dismiss();
            new secOku().execute();
        }
        else if(acikSite=="kidega") {
            progressDialog.dismiss();
            new kidegaOku().execute();
        }
        else if(acikSite=="uka") {
            progressDialog.dismiss();
            new ukaOku().execute();
        }

        else {


            final WebView babilWebKatman = (WebView) findViewById(R.id.babilWebKatman);
            babilWebKatman.getSettings().setJavaScriptEnabled(true);
            // babilWebKatman.getSettings().setUserAgentString("");
            babilWebKatman.getSettings().setLoadsImagesAutomatically(false);

            babilWebKatman.addJavascriptInterface(new LoadListenerGenel(), "HTMLOUT");



            babilWebKatman.loadUrl(sayfa);


            babilWebKatman.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                }

                public void onPageFinished(final WebView view, String url) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                        }
                    }, 1000);
                }
            });

        }

    }

    class LoadListenerGenel{
        @JavascriptInterface
        public void processHTML(String html)
        {
            progressDialog.dismiss();

           // Log.e("GENEL SONUC: ",html);
            ////////System.out.println("GENEL SONUC: "+html);
            sonrakiSayfaIcerik=html;
            switch (acikSite)
            {
                case "idefix" : new idefixOku().execute();break;
                case "dr" : new drOku().execute();break;
                case "bb" : new babilOku().execute();break;
                default:break;
            }

        }
    }

    String [] yeniCikanDizi={
            "https://www.idefix.com/Kategori_/Kitap/En-Yeniler/11693/3#/page=",
            "https://www.dr.com.tr/Kategori_/Kitap/En-Yeniler/10001/3#/page=",
            "https://www.hepsiburada.com/kampanyalar/cok-satan-kitaplar?siralama=enyeni&sayfa=",
            "https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=2&filter_in_stock=1&filter_in_stock=1&page=",
            "https://www.babil.com/kitap/yeni-kitaplar?page=",
            "https://www.bkmkitap.com/yeni-cikan-kitaplar?view=0&pg=",
            "https://www.kitapsec.com/Yeni-Cikanlar/Edebiyat//",
            "https://kidega.com/cok-satan-kitaplar?page=",
            "https://www.ucuzkitapal.com/yeni-urunler/?items_per_page=16&page="
    };


    String [] cokSatanDizi={
            "https://www.idefix.com/CokSatanlar/Kitap#/page=",
            "https://www.dr.com.tr/CokSatanlar/Kitap#/page=",
            "https://www.hepsiburada.com/kampanyalar/cok-satan-kitaplar?sayfa=",
            "https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=",
            "https://www.babil.com/kitap/cok-satanlar?page=",
            "https://www.bkmkitap.com/kitap/cok-satan-kitaplar?view=0&pg=",
            "https://www.kitapsec.com/Cok-Satanlar/Edebiyat//",
            "https://kidega.com/cok-satan-kitaplar?page=",
            "https://www.ucuzkitapal.com/cok-satanlar/?items_per_page=16&page="
    };




    String [] kullanDizi=new String[yeniCikanDizi.length];

  String genelSayfaAl()
  {
     String gonder="";
     // //System.out.println(sonrakiSayfaNo+" YAZDIR");
     if(listeDurum==1)
         kullanDizi=yeniCikanDizi;
     else
         kullanDizi=cokSatanDizi;

      switch (acikSite)
    {
        case "idefix" :

            if(listeDurum==1)
                return kullanDizi[0]+sonrakiSayfaNo+"/sort=newness,desc/parentId=11693/price=-1,-1";
            else
                return kullanDizi[0]+sonrakiSayfaNo+"/sort=groups.group.displayorder,asc/categoryid=0/clog=3569/parentId=0/price=-1,-1";

        case "dr" :
            if(listeDurum==1)
                return kullanDizi[1]+sonrakiSayfaNo+"/sort=soldcount,desc/parentId=10001/price=-1,-1";
            else
                return kullanDizi[1]+sonrakiSayfaNo+"/sort=groups.group.displayorder,asc/categoryid=0/clog=4020/parentId=0/price=-1,-1";


        case "hepsiburada" : return kullanDizi[2]+sonrakiSayfaNo;
        case "ky" : return kullanDizi[3]+sonrakiSayfaNo;
        case "bb" : return kullanDizi[4]+sonrakiSayfaNo;
        case "bkm" : return kullanDizi[5]+sonrakiSayfaNo;
        case "sec" : return kullanDizi[6]+sonrakiSayfaNo+"-6-0a0-0-0-0-0-0.xhtml";
        case "kidega" : return kullanDizi[7]+sonrakiSayfaNo;
        case "uka" : return kullanDizi[8]+sonrakiSayfaNo;




        default:return "";
    }
  }




    ProgressDialog progressDialog5;
    String AciklamaAdresDetay="";
    String AciklamaBilgisiDetay="";
    private class AciklamaAlDetay extends AsyncTask<Void, Void, Void> {
        String tamAramaYayinEvi=null;
        String authors;
        @Override
        protected void onPreExecute() {
            tamAramaYayinEvi="";
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            ////////////System.out.println("ADRES: "+AciklamaAdresDetay);
            try{
                Document doc;
                if(AciklamaAdresDetay.indexOf("hepsiburada")>0)
                {
                    doc = Jsoup.parse(veriAl(AciklamaAdresDetay,"productDescriptionContent","ctl00_ContentPlaceHolder1"));
                }
                else if(AciklamaAdresDetay.indexOf("babil")>0)
                {
                    doc = Jsoup.parse(babilDokumanSayfa);
                }
                else {
                    doc = Jsoup.connect(AciklamaAdresDetay).userAgent("Mozilla").timeout(3000).validateTLSCertificates(false).get();
                }

                //BURADA BABİL İÇİN DE SİTEDEN VERİ ALINACAK. ISBN VE AÇIKLAMA

                // //////////System.out.println("ADRES:"+AciklamaAdresDetay);
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için



                if(AciklamaAdresDetay.contains("istanbulkitapcisi.com")) {

                    elements = doc.select("div[id=product-fullbody]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        // //////////System.out.println("ACIKLAMA:"+AciklamaBilgisiDetay);

                        break;

                    }
                }

                else if(AciklamaAdresDetay.contains("dr.com")) {

                    elements = doc.select("div[class=product-description-header section-cover mb-40 fs-3_5]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        break;
                    }
                }

                else if(AciklamaAdresDetay.contains("hepsiburada.com")) {

                    elements = doc.select("div[id=productDescriptionContent]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("kitapyurdu.com")) {

                    elements = doc.select("div[class=pr_description] span[class=info__text]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("babil.com")) {

                    elements = doc.select("div[id=description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("pandora.com")) {

                    elements = doc.select("div[class=row urunAciklama]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        AciklamaBilgisiDetay = data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("bkmkitap.com")) {

                    elements = doc.select("div[id=productDetailTab]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        // int yer=0;
                        //yer=data.indexOf("Kısa Özet");
                        //yer=yer+20;

                        // AciklamaBilgisiDetay = data.substring(yer,data.length()-1);
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("kitapsec.com")) {
                    elements = doc.select("div[class=TabDivList] div[id=tab1]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    elements.select("strong").remove();
                    elements.select("div[class=magaza-bilgi]").remove();

                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }

                else if(AciklamaAdresDetay.contains("kidega.com")) {
                    elements = doc.select("div[class=col-md-8 col-xs-12] div[id=bookDescription]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("ucuzkitapal.com")) {
                    elements = doc.select("div[id=content_description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }

                else if(AciklamaAdresDetay.contains("amazon.com")) {
                    elements = doc.select("div[id=bookDescription_feature_div]");  // class ismi post-content olan verileri çekmek için
                    elements.select("div[id=psPlaceHolder]").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }

                else if(AciklamaAdresDetay.contains("kitapkolik.com")) {
                    elements = doc.select("div[id=divOnyazi]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        AciklamaBilgisiDetay=data;
                        break;
                    }
                }
                else if(AciklamaAdresDetay.contains("1000kitap.com")) {
                  //  elements = doc.select("div[class=oge metin]");  // class ismi post-content olan verileri çekmek için
                    //AciklamaBilgisiDetay=elements.get(0).text();
                    elements = doc.select("div[class=css-1dbjc4n] span[class=css-901oao css-16my406]");  // class ismi post-content olan verileri çekmek için
                    AciklamaBilgisiDetay=elements.get(0).text();

                    // elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=bilgi]");  // class ismi post-content olan verileri çekmek için
                    // TextView yevi = (TextView)findViewById(R.id.kYayinevi5);
                    //   yevi.setText("deneme");

/*


                    int say=0;
                    elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=baslik]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        if(data.contains("Yayınevi"))
                        {
                            break;
                        }
                        say++;
                    }
                    elements = doc.select("div[class=kutu bilgiler] div[class=oge] div[class=bilgi]");  // class ismi post-content olan verileri çekmek için


                    //  TextView yevi = (TextView)findViewById(R.id.kYayinevi5);
                    try {
                        tamAramaYayinEvi=elements.get(say).text();
                        //  //////////System.out.println(tamAramaYayinEvi);
                        //   yevi.setText(tamAramaYayinEvi);

                    }catch (Exception e)
                    {
                        tamAramaYayinEvi="xxx";
                        // //////////System.out.println("HATA NEREN?"+e.getMessage());
                        //  yevi.setText(tamAramaYayinEvi);

                    }
                    //burayada yayın evi tagları eklenerek yayıne vi alınailir.
*/

                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            detayTiklandi=false;
            progressDialog5.dismiss();
           // //System.out.println(AciklamaBilgisiDetay);
            detayAciklama=AciklamaBilgisiDetay;
            detayGoster(new View(MainActivity.this));


        }





    }











    public void buyukAc(View view)
    {
        String data = disAcilacakAdres;
        Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
        defaultBrowser.setData(Uri.parse(data));
        startActivity(defaultBrowser);
    }



    int cokArananYuklenenResimSayisi=0;
    int sonArananYuklenenResimSayisi=0;
    int tavsiyeYuklenenResimSayisi=0;


    void cokArananlarKatmaniGoster()
{
    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    RelativeLayout[] layout = new RelativeLayout[cokAd.length];
    LinearLayout linear = (LinearLayout) findViewById(R.id.cokArananlarYatay);

    linear.removeAllViews();
    int s=0;
    int i=0;
    while(i<cokAd.length)

        {

        layout[i] = (RelativeLayout) inflater.inflate(R.layout.cokarananliste, null, false);

        RelativeLayout renkliArka =(RelativeLayout) layout[i].findViewById(R.id.renkliArka);
        final ImageView resim =(ImageView)layout[i].findViewById(R.id.kitapGorsel);
        TextView kitapAdi =(TextView) layout[i].findViewById(R.id.kitapAdi);
        TextView kitapYazari =(TextView) layout[i].findViewById(R.id.kitapYazari);
        TextView kitapYayinEvi =(TextView) layout[i].findViewById(R.id.kitapYayinevi);
        RelativeLayout kitapGenel = (RelativeLayout)layout[i].findViewById(R.id.kitapGenel);

        s = genislik / kitapGenel.getLayoutParams().width + 1;
        if(cokArananYuklenenResimSayisi<s) {
            new DownloadImageTask2(resim, renkliArka).execute(cokResim[i]);
            cokArananYuklenenResimSayisi++;
        }
        kitapAdi.setText(cokAd[i]);
        kitapYazari.setText(cokYazar[i]);
        kitapYayinEvi.setText(cokYayinevi[i]);



        final int k=i;
        kitapGenel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                anaSayfadanGeldi=true;

                kitap = cokAd[k];
                yazarAd = cokYazar[k];
                yayinEviMetin = cokYayinevi[k];
                sepetResimtmp = cokResim[k];


                detayAd=kitap;
                detayYazar=yazarAd;
                detayYayinEvi=yayinEviMetin;
                detayResim=sepetResimtmp;
                detayAdres = cokAdres[k];



                kyAramakitap = kitap;
                kyAramayazar = yazarAd;



                try {
                    String[] parts = yazarAd.split(",");
                    kyAramayazar = parts[0].trim();
                } catch (Exception e) {
                    kyAramayazar = yazarAd;
                }

                TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
                TextView ad5 = (TextView) findViewById(R.id.kAd5);

                //TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
                ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);


                yazar5.setText(yazarAd);
                ad5.setText(kitap);
                yayinEvi5.setText(yayinEviMetin);

                //kitaplikResim5.setImageDrawable(resim.getDrawable());

               new DownloadImageTask(kitaplikResim5).execute(sepetResimtmp);


                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Lütfen Bekleyiniz...");
                progressDialog.setIndeterminate(false);
                progressDialog.show();


                anaSayfadanAc=true;
                ISBN = cokISBN[k];
                siralamaPasif();
                bulX = System.currentTimeMillis();
                kitapArama(ISBN,1);
                AciklamaAdres = cokAdres[k];
                TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                aciklama5.setText("");
                new AciklamaAl().execute();

            }

        });









        // final int tmp=i;


        linear.addView(layout[i]);
        i++;
    }
    cokAranankaydirmaDurum((HorizontalScrollView) findViewById(R.id.cokArananlarKaydir),(LinearLayout)findViewById(R.id.cokArananlarYatay),s);


    // new sonArananlarOku().execute();
}


    void kitapliktanAramaBasla()
    {
        anaSayfadanGeldi=true;

        kitap = kitaplikAramaBilgiler.KitapAd;
        yazarAd = kitaplikAramaBilgiler.KitapYazar;
        yayinEviMetin = kitaplikAramaBilgiler.YayinEvi;
        sepetResimtmp = kitaplikAramaBilgiler.ResimAdres;


        detayAd=kitap;
        detayYazar=yazarAd;
        detayYayinEvi=yayinEviMetin;
        detayResim=sepetResimtmp;
        detayAdres = kitaplikAramaBilgiler.Aciklama;



        kyAramakitap = kitap;
        kyAramayazar = yazarAd;



        try {
            String[] parts = yazarAd.split(",");
            kyAramayazar = parts[0].trim();
        } catch (Exception e) {
            kyAramayazar = yazarAd;
        }

        TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
        TextView ad5 = (TextView) findViewById(R.id.kAd5);

        //TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
        TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
        ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);


        yazar5.setText(yazarAd);
        ad5.setText(kitap);
        yayinEvi5.setText(yayinEviMetin);

        //kitaplikResim5.setImageDrawable(resim.getDrawable());

        new DownloadImageTask(kitaplikResim5).execute(sepetResimtmp);


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Lütfen Bekleyiniz...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();


        anaSayfadanAc=true;
        ISBN = kitaplikAramaBilgiler.Isbn;
        siralamaPasif();
        bulX = System.currentTimeMillis();
        kitapArama(ISBN,1);
        AciklamaAdres = kitaplikAramaBilgiler.Aciklama;
        TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
        aciklama5.setText("");
        new AciklamaAl().execute();








    }





    boolean anaSayfadanAc=false;
    void populerKatmaniGoster()
    {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[sonAd.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.populerKitaplarYatay);

        linear.removeAllViews();
        int s=0;
        int i=0;
        while(i<sonAd.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.populerkitaplar, null, false);

            final ImageView resim =(ImageView)layout[i].findViewById(R.id.populerGorsel);


            s = genislik / resim.getLayoutParams().width + 1;
            if(sonArananYuklenenResimSayisi<s) {
                new DownloadImageTask(resim).execute(sonResim[i]);
                sonArananYuklenenResimSayisi++;
            }





            final int k=i;
            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   // System.out.println("POPULEEEER");
                    anaSayfadanGeldi=true;

                    kitap = sonAd[k];
                    yazarAd = sonYazar[k];
                    yayinEviMetin = sonYayinevi[k];
                    sepetResimtmp = sonResim[k];


                    detayAd=kitap;
                    detayYazar=yazarAd;
                    detayYayinEvi=yayinEviMetin;
                    detayResim=sepetResimtmp;
                    detayAdres = sonAdres[k];



                    kyAramakitap = kitap;
                    kyAramayazar = yazarAd;



                    try {
                        String[] parts = yazarAd.split(",");
                        kyAramayazar = parts[0].trim();
                    } catch (Exception e) {
                        kyAramayazar = yazarAd;
                    }

                    TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
                    TextView ad5 = (TextView) findViewById(R.id.kAd5);

                    //TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                    TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
                    ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);


                    yazar5.setText(yazarAd);
                    ad5.setText(kitap);
                    yayinEvi5.setText(yayinEviMetin);

                    kitaplikResim5.setImageResource(R.drawable.ornekkitap);
                    //kitaplikResim5.setImageDrawable(resim.getDrawable());

                    new DownloadImageTask(kitaplikResim5).execute(sepetResimtmp);


                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Lütfen Bekleyiniz...");
                    progressDialog.setIndeterminate(false);
                    progressDialog.show();


                        anaSayfadanAc=true;
                        ISBN = sonISBN[k];
                        siralamaPasif();
                        bulX = System.currentTimeMillis();
                        kitapArama(ISBN,2);
                        AciklamaAdres = sonAdres[k];
                        TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                        aciklama5.setText("");
                        new AciklamaAl().execute();


                }

            });



            linear.addView(layout[i]);
            i++;
        }



        sonAranankaydirmaDurum((HorizontalScrollView) findViewById(R.id.populerKitaplarKAydir),(LinearLayout)findViewById(R.id.populerKitaplarYatay),s);


    }




    String [] tavsiyeAd, tavsiyeYazar, tavsiyeResim, tavsiyeISBN, tavsiyeYayinevi,tavsiyeAdres;


    void tavsiyeKatmaniGoster()
    {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[tavsiyeAd.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.tavsiyeKitaplarYatay);

        linear.removeAllViews();
        int s=0;
        int i=0;
        while(i<tavsiyeAd.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.tavsiyekitaplar, null, false);

            ImageView resim =(ImageView)layout[i].findViewById(R.id.populerGorsel);

            s = genislik / resim.getLayoutParams().width + 1;
            if(tavsiyeYuklenenResimSayisi<s) {
                new DownloadImageTask(resim).execute(tavsiyeResim[i]);
               tavsiyeYuklenenResimSayisi++;
            }



            final int k=i;
            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    anaSayfadanGeldi=true;

                    kitap = tavsiyeAd[k];
                    yazarAd = tavsiyeYazar[k];
                    yayinEviMetin = tavsiyeYayinevi[k];
                    sepetResimtmp = tavsiyeResim[k];


                    detayAd=kitap;
                    detayYazar=yazarAd;
                    detayYayinEvi=yayinEviMetin;
                    detayResim=sepetResimtmp;
                    detayAdres = tavsiyeAdres[k];



                    kyAramakitap = kitap;
                    kyAramayazar = yazarAd;



                    try {
                        String[] parts = yazarAd.split(",");
                        kyAramayazar = parts[0].trim();
                    } catch (Exception e) {
                        kyAramayazar = yazarAd;
                    }

                    TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
                    TextView ad5 = (TextView) findViewById(R.id.kAd5);

                    //TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                    TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
                    ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);


                    yazar5.setText(yazarAd);
                    ad5.setText(kitap);
                    yayinEvi5.setText(yayinEviMetin);

                    kitaplikResim5.setImageResource(R.drawable.ornekkitap);


                    new DownloadImageTask(kitaplikResim5).execute(sepetResimtmp);


                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Lütfen Bekleyiniz...");
                    progressDialog.setIndeterminate(false);
                    progressDialog.show();


                    anaSayfadanAc=true;
                    ISBN = tavsiyeISBN[k];
                    siralamaPasif();
                    bulX = System.currentTimeMillis();
                    kitapArama(ISBN,3);
                    AciklamaAdres = tavsiyeAdres[k];
                    TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                    aciklama5.setText("");
                    new AciklamaAl().execute();


                }

            });



            linear.addView(layout[i]);
            i++;
        }

        tavsiyekaydirmaDurum ((HorizontalScrollView) findViewById(R.id.tavsiyeKitaplarKaydir),(LinearLayout)findViewById(R.id.tavsiyeKitaplarYatay),s);


    }






    void yazarKatmanGoster()
    {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[yazarBilgi.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.populerYazarYatay);

        linear.removeAllViews();

        int i=0;
        while(i<yazarBilgi.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.populeryazarlar, null, false);

            ImageView resim =(ImageView)layout[i].findViewById(R.id.populerYazarGorsel);
            TextView yazarBilgiMetin = (TextView)layout[i].findViewById(R.id.yazarBilgi);
            yazarBilgiMetin.setText(yazarBilgi[i]);
            new DownloadImageTask(resim).execute(yazarResim[i]);
           // resim.setImageResource(yazarResim[i]);

            final int k=i;
            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    yazarAdres = yazarBilgiAdres[k];
                    new yazarBilgiOku().execute();

                }

            });

            linear.addView(layout[i]);
            i++;
        }


    }



    void tanitimKatmanGoster()
    {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[tanitimAd.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.tanitimYatay);

        linear.removeAllViews();

        int i=0;
        while(i<tanitimAd.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.tanitim, null, false);

            ImageView resim =(ImageView)layout[i].findViewById(R.id.populerYazarGorsel);
            TextView yazarBilgiMetin = (TextView)layout[i].findViewById(R.id.yazarBilgi);
          //  yazarBilgiMetin.setText(yazarBilgi[i]);
            new DownloadImageTask(resim).execute(tanitimResim[i]);
            // resim.setImageResource(yazarResim[i]);

            final int k=i;
            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    anaSayfadanGeldi=true;

                    kitap = tanitimAd[k];
                    yazarAd = tanitimYazar[k];
                    yayinEviMetin = tanitimYayinevi[k];
                    sepetResimtmp = tanitimResim[k];


                    detayAd=kitap;
                    detayYazar=yazarAd;
                    detayYayinEvi=yayinEviMetin;
                    detayResim=sepetResimtmp;
                    detayAdres = tanitimAdres[k];



                    kyAramakitap = kitap;
                    kyAramayazar = yazarAd;



                    try {
                        String[] parts = yazarAd.split(",");
                        kyAramayazar = parts[0].trim();
                    } catch (Exception e) {
                        kyAramayazar = yazarAd;
                    }

                    TextView yazar5 = (TextView) findViewById(R.id.kYazar5);
                    TextView ad5 = (TextView) findViewById(R.id.kAd5);

                    TextView yayinEvi5 = (TextView) findViewById(R.id.kYayinevi5);
                    ImageView kitaplikResim5 = (ImageView) findViewById(R.id.kitaplikResim5);


                    yazar5.setText(yazarAd);
                    ad5.setText(kitap);
                    yayinEvi5.setText(yayinEviMetin);

                    kitaplikResim5.setImageResource(R.drawable.ornekkitap);
                    new DownloadImageTask(kitaplikResim5).execute(sepetResimtmp);

                    ISBN = tanitimISBN[k];
                    siralamaPasif();
                    bulX = System.currentTimeMillis();

                    TextView aciklama5 =(TextView)findViewById(R.id.kitapAciklama5);
                    aciklama5.setText(tanitimAciklama[k]);

                    detayAciklama=tanitimAciklama[k];

                    fiyatListeAc();
                    new drBul().execute();


                }

            });

            linear.addView(layout[i]);
            i++;
        }


    }







    void siteKatmanGoster()
    {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        RelativeLayout[] layout = new RelativeLayout[listeResimlerOnSayfa.length];
        LinearLayout linear = (LinearLayout) findViewById(R.id.populerSiteYatay);

        linear.removeAllViews();

        int i=0;
        while(i<listeResimlerOnSayfa.length)
        {

            layout[i] = (RelativeLayout) inflater.inflate(R.layout.populersiteler, null, false);
            RelativeLayout siteGorselArka = (RelativeLayout)layout[i].findViewById(R.id.siteGorselArka);
            ImageView resim =(ImageView)layout[i].findViewById(R.id.siteGorsel);
            // new DownloadImageTask(resim).execute(fotolar[i]);
            resim.setImageResource(listeResimlerOnSayfa[i]);
            Bitmap res = BitmapFactory.decodeResource(getResources(),listeResimlerOnSayfa[i]);
            siteGorselArka.setBackgroundColor(calculateAverageColor(res,1));



            final int tmp=i;

            resim.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    RelativeLayout yeniCikan=(RelativeLayout)findViewById(R.id.yeniCikan);
                    RelativeLayout cokOkunan=(RelativeLayout)findViewById(R.id.cokOkunan);
                    TextView yeniMetin=(TextView)findViewById(R.id.yeniMetin);
                    TextView okunanMetin=(TextView)findViewById(R.id.okunanMetin);

                    yeniCikan.setBackgroundColor(Color.rgb(0x9e,0x9e,0x9e));
                    cokOkunan.setBackgroundColor(Color.rgb(0xff,0xff,0xff));
                    yeniMetin.setTextColor(Color.WHITE);
                    okunanMetin.setTextColor(Color.rgb(0x71,0x71,0x71));
                    listeDurum=1;
                    // yazdirSayfaSayi=1;



                    sonrakiSayfalar=false;
                    sonrakiSayfaIcerik="";
                    sonrakiSayfaNo=1;


                    acikSite="";


                    TextView cokSatanlar=(TextView)findViewById(R.id.cokSatanlar);
                    String cokSatanMetin="";



                    switch (tmp)
                    {

                        case 0:
                            cokSatanMetin="İDEFİX";
                            yazdirUrunSayi=9;
                            new idefixOku().execute();
                            acikSite="idefix";
                            break;
                        case 1:
                            cokSatanMetin="D&R";
                            yazdirUrunSayi=100;
                            new drOku().execute();
                            acikSite="dr";
                            break;
                        case 2:
                            cokSatanMetin="HEPSİBURADA";
                            yazdirUrunSayi=35;
                            acikSite="hepsiburada";
                            //new hepsiburadaOku().execute();
                            new GetData().execute();
                            break;
                        case 3:
                            cokSatanMetin="KİTAP YURDU";
                            yazdirUrunSayi=35;
                            new kitapYurduOku().execute();
                            acikSite="ky";
                            break;
                        /*
                        case 4:
                            cokSatanMetin="BABİL KİTAP";
                            yazdirUrunSayi=35;
                            babilVeriOkuAl();
                            acikSite="bb";
                            break;
                        */

                        case 4:
                            cokSatanMetin="PANDORA";
                            yazdirUrunSayi=35;
                            new pandoraOku().execute();
                            acikSite="pandora";
                            break;
                        case 5:
                            cokSatanMetin="BKM KİTAP";
                            yazdirUrunSayi=35;
                            new bkmOku().execute();
                            acikSite="bkm";
                            break;

                        case 6:
                            cokSatanMetin="KİTAP SEÇ";
                            yazdirUrunSayi=35;
                            new secOku().execute();
                            acikSite="sec";
                            break;

                        case 7:
                            cokSatanMetin="KİDEGA KİTAP";
                            yazdirUrunSayi=35;
                            new kidegaOku().execute();
                            acikSite="kidega";
                            break;
                        case 8:
                            cokSatanMetin="UCUZ KİTAP AL";
                            yazdirUrunSayi=35;
                            new ukaOku().execute();
                            acikSite="uka";
                            break;

                        //  case 9:
                        //     cokSatanMetin="AMAZON KİTAP";
                        //      yazdirUrunSayi=35;
                        //      new amazonOku().execute();
                        //      acikSite="amazon";
                        //      break;
                        case 9:
                            cokSatanMetin="KİTAPKOLİK";
                            yazdirUrunSayi=35;
                            new kitapkolikOku().execute();
                            acikSite="kitapkolik";
                            break;

                        case 10:
                            cokSatanMetin="POPÜLER KİTAPLAR";
                            yazdirUrunSayi=35;
                            new populerOku().execute();
                            acikSite="populer";
                            break;
                    }

                    // cokSatanlar.setText(cokSatanMetin+" | ÇOK SATANLAR");
                    cokSatanlar.setText(cokSatanMetin);


                }

            });
















            linear.addView(layout[i]);
            i++;
        }


    }




    String yazarAdres;
    private class yazarBilgiOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.setMessage("Ürünler yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="";
                if(listeDurum==1)
                    sayfa=yazarAdres;
                else
                    sayfa=yazarAdres;

                if(sonrakiSayfalar)
                    sayfa=sonrakiSayfa;

                Document  doc = Jsoup.connect(sayfa).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36").timeout(10000).get();
                doc.select("div[class=price-old price-passive]").remove();

                Elements elements = doc.select("div[class=name ellipsis]");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    basliklar[i]=data;
                    i++;

                }
                // Log.v("KİTAP SAYISI:",String.valueOf(i));

                yazdirUrunSayi=i;


                //REsim adresleri alındı
                elements = doc.select("div[class=image] img");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("src");
                    fotolar[i]=data;
                    //Log.w("resimler", data);
                    i++;
                }

                //fiyatlar  alındı
                elements = doc.select("div[class=price] span[class=value]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text()+" TL";
                    fiyatlar[i]="";
                    i++;
                }


                //YAZARLAR  alındı
                elements = doc.select("div[class=author compact ellipsis] ");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yazarlar[i]=data;
                    i++;
                }

                //Adresler  alındı
                elements = doc.select("div[class=image] a");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.attr("href");
                    adresler[i]=data;
                    i++;
                }


                elements = doc.select("div[class=publisher]");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    yayinEvi[i]=data.replaceAll(",","");
                    i++;
                }


            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(String a : fiyatlar) {
                // if(a!=null)
                //Log.w("deneme", a);
            }
            acikSite="arama";
            TextView cokSatanlar=(TextView)findViewById(R.id.cokSatanlar);
            cokSatanlar.setText("ARAMA SONUÇLARI");
            yazdir();
            progressDialog.dismiss();
        }
    }


public void barkodAc(View view)
{
    Intent activity2Intent = new Intent(getApplicationContext(), ScanActivity.class);
    startActivity(activity2Intent);
}



    String [] cokAd, cokYazar, cokResim, cokISBN, cokYayinevi, cokAdres;
    String [] sonAd, sonYazar, sonResim, sonISBN, sonYayinevi, sonAdres;







    private class herseyOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{
                Document doc;
                if(anasayfaHata)
                {
                     doc = Jsoup.parse(anasayfa.metin);
                     anasayfaHata=false;
                }
                else {
                    String sayfa = "http://www.bilisimkalesi.com/kitap/kitaphersey.php";
                    doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();
                }
                //populer kitaplar bölümü

                Elements elements = doc.select("populer");
                int kayitSayisi= elements.size();

                cokAd = new String[kayitSayisi];
                cokYazar = new String[kayitSayisi];
                cokISBN = new String[kayitSayisi];
                cokYayinevi = new String[kayitSayisi];
                cokAdres = new String[kayitSayisi];
                cokResim = new String[kayitSayisi];


                elements = doc.select("sonarama");
                kayitSayisi= elements.size();

                sonAd = new String[kayitSayisi];
                sonYazar = new String[kayitSayisi];
                sonISBN = new String[kayitSayisi];
                sonYayinevi = new String[kayitSayisi];
                sonAdres = new String[kayitSayisi];
                sonResim = new String[kayitSayisi];

                elements = doc.select("yazarlar");
                kayitSayisi= elements.size();

                yazarBilgi = new String[kayitSayisi];
                yazarResim = new String[kayitSayisi];
                yazarBilgiAdres = new String[kayitSayisi];




                elements = doc.select("populer ad");  // class ismi post-content olan verileri çekmek için
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    cokAd[i]=data;
                    i++;
                }



                elements = doc.select("populer resim");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    cokResim[i]=data;
                    i++;
                }


                elements = doc.select("populer yazar");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    cokYazar[i]=data;
                    i++;
                }


                elements = doc.select("populer adres");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    cokAdres[i]=data;
                    i++;
                }


                elements = doc.select("populer isbn");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    cokISBN[i]=data;
                    i++;
                }

                elements = doc.select("populer yayinevi");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    cokYayinevi[i]=data;
                    i++;

                }



                //son aranan kitaplar bölümü

                elements = doc.select("sonarama ad");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    sonAd[i]=data;
                    i++;
                }



                elements = doc.select("sonarama resim");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    sonResim[i]=data;
                    i++;
                }


                elements = doc.select("sonarama yazar");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    sonYazar[i]=data;
                    i++;
                }


                elements = doc.select("sonarama adres");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    sonAdres[i]=data;
                    i++;
                }


                elements = doc.select("sonarama isbn");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    sonISBN[i]=data;
                    i++;
                }

                elements = doc.select("sonarama yayinevi");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    sonYayinevi[i]=data;
                    i++;

                }




                // Yazarlar alınıyor

                elements = doc.select("yazarlar yazar");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarBilgi[i]=baslik.text();
                    i++;
                }


                elements = doc.select("yazarlar resim");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarResim[i]=baslik.text();
                    i++;
                }

                elements = doc.select("yazarlar adres");  // class ismi post-content olan verileri çekmek için
                //Başlıklar Alındı
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    yazarBilgiAdres[i]=baslik.text();
                    i++;
                }



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(yazarBilgi!=null) {
                yazarKatmanGoster();
                cokArananlarKatmaniGoster();
                populerKatmaniGoster();
                siteKatmanGoster();
               // new tanitimOku().execute();

            }
            else {
                anasayfaHata = true;
                new herseyOku().execute();
                Toast.makeText(getApplicationContext(), "Bağlantı hatası oluştu. Lütfen internet bağlantınızı kontrol ederek uygulamayı yeniden başlatın.", Toast.LENGTH_LONG).show();
            }

        }
    }
    boolean anasayfaHata = false;


    private class tavsiyeOku extends AsyncTask<Void, Void, Void> {

        String authors;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                String sayfa="http://www.bilisimkalesi.com/kitap/tavsiyeOkuKarma.php";
                Document doc = Jsoup.connect(sayfa).timeout(10000).validateTLSCertificates(false).get();

                //populer kitaplar bölümü

                Elements elements = doc.select("kitap");
                int kayitSayisi= elements.size();


                tavsiyeAd = new String[kayitSayisi];
                tavsiyeYazar = new String[kayitSayisi];
                tavsiyeISBN = new String[kayitSayisi];
                tavsiyeYayinevi = new String[kayitSayisi];
                tavsiyeAdres = new String[kayitSayisi];
                tavsiyeResim = new String[kayitSayisi];



                elements = doc.select("kitap ad");  // class ismi post-content olan verileri çekmek için
                int i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    tavsiyeAd[i]=data;
                    i++;
                }



                elements = doc.select("kitap resim");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data = baslik.text();
                    tavsiyeResim[i]=data;
                    i++;
                }


                elements = doc.select("kitap yazar");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    tavsiyeYazar[i]=data;
                    i++;
                }


                elements = doc.select("kitap adres");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    tavsiyeAdres[i]=data;
                    i++;
                }


                elements = doc.select("kitap isbn");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    tavsiyeISBN[i]=data;
                    i++;
                }

                elements = doc.select("kitap yayinevi");  // class ismi post-content olan verileri çekmek için
                i=0;
                for (org.jsoup.nodes.Element baslik : elements) {

                    String data =baslik.text();
                    tavsiyeYayinevi[i]=data;
                    i++;

                }







            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(tavsiyeAd!=null)
                tavsiyeKatmaniGoster();
            else
                Toast.makeText(getApplicationContext(), "Bağlantı hatası oluştu. Lütfen internet bağlantınızı kontrol ederek uygulamayı yeniden başlatın.", Toast.LENGTH_LONG).show();

    }
    }








    public void kitapBegenTikla(View view)
    {

        if(kitapBegenilmis)
        {
            kitapBegenVazgec(ISBN);
        }
        else
        {
            kitapBegen(ISBN);
        }


    }




    boolean kitapBegenilmis =false;
    void kitapBegen(String isbn) {

        String url = "http://www.bilisimkalesi.com/kitap/kitapBegenme.php?isbn="+isbn+"&cihazid="+android_id;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        Matcher matcher = Pattern.compile("###(.*?)---").matcher(response);
                        String begenmeSayi="";
                        while (matcher.find()) {
                            begenmeSayi=matcher.group();
                        }
                        begenmeSayi = begenmeSayi.replace("###","").replace("---","");
                        begenmeSayi = begenmeSayi+" Defa Beğenildi";

                        TextView begenmeSayiMetin = (TextView)findViewById(R.id.begenmeSayi);
                        begenmeSayiMetin.setText(begenmeSayi);

                        ImageView begenButon = (ImageView)findViewById(R.id.begenButon);
                        Resources resources = getResources();
                        begenButon.setImageDrawable(resources.getDrawable(R.drawable.begenaktif));
                        kitapBegenilmis=true;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }







    void kitapBegenVazgec(String isbn) {

        String url = "http://www.bilisimkalesi.com/kitap/kitapBegenmeVazgec.php?isbn="+isbn+"&cihazid="+android_id;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        Matcher matcher = Pattern.compile("###(.*?)---").matcher(response);
                        String begenmeSayi="";
                        while (matcher.find()) {
                            begenmeSayi=matcher.group();
                        }
                        begenmeSayi = begenmeSayi.replace("###","").replace("---","");
                        begenmeSayi = begenmeSayi+" Defa Beğenildi";

                        TextView begenmeSayiMetin = (TextView)findViewById(R.id.begenmeSayi);
                        begenmeSayiMetin.setText(begenmeSayi);

                        ImageView begenButon = (ImageView)findViewById(R.id.begenButon);
                        Resources resources = getResources();
                        begenButon.setImageDrawable(resources.getDrawable(R.drawable.begenpasif));
                        kitapBegenilmis=false;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }





    void kitapBegenOku(String isbn) {

        String url = "http://www.bilisimkalesi.com/kitap/kitapBegenmeOku.php?isbn="+isbn+"&cihazid="+android_id;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        Matcher matcher = Pattern.compile("###(.*?)---").matcher(response);
                        String begenmeSayi="";
                        while (matcher.find()) {
                            begenmeSayi=matcher.group();
                        }
                        begenmeSayi = begenmeSayi.replace("###","").replace("---","");
                        begenmeSayi = begenmeSayi+" Defa Beğenildi";



                        matcher = Pattern.compile("000(.*?)---").matcher(response);
                        String begenildi="";
                        while (matcher.find()) {
                            begenildi=matcher.group();
                        }
                        begenildi = begenildi.replace("000","").replace("---","");

                        //System.out.println("BEGENİLDİ: "+ begenildi);


                        ImageView begenButon = (ImageView)findViewById(R.id.begenButon);
                        Resources resources = getResources();

                        if(begenildi.contains("1"))
                        {
                            begenButon.setImageDrawable(resources.getDrawable(R.drawable.begenaktif));
                            kitapBegenilmis=true;
                        }
                        else
                        {
                            kitapBegenilmis=false;
                            begenButon.setImageDrawable(resources.getDrawable(R.drawable.begenpasif));
                        }



                        TextView begenmeSayiMetin = (TextView)findViewById(R.id.begenmeSayi);
                        begenmeSayiMetin.setText(begenmeSayi);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }


    String ekaramaDurum="";
    void ekarama() {

        String url = "http://www.bilisimkalesi.com/kitap/ekarama.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                       ekaramaDurum=response;
                      //  //System.out.println("**"+ekaramaDurum+"**");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }


    void cokAranankaydirmaDurum(final HorizontalScrollView kaydirmaKutusu, final LinearLayout eleman, final int a)
    {


        //System.out.println("GENİŞLİK: "+genislik);


        kaydirmaKutusu.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                try {
                int cubuk = kaydirmaKutusu.getScrollX();
                int resimSayi = a + cubuk / (genislik/a);
               // //System.out.println("RESİM SAYISI: "+resimSayi);
               // //System.out.println("ÇUBUK : "+cubuk);


                    if (cokArananYuklenenResimSayisi < eleman.getChildCount()) {
                        for (int i = cokArananYuklenenResimSayisi; i <= resimSayi; i++) {
                            //System.out.println("ELEMAN: " + i);
                            TextView x = (TextView) eleman.getChildAt(i).findViewById(R.id.kitapYazari);
                            //System.out.println(x.getText());

                            RelativeLayout renkliArka = (RelativeLayout) eleman.getChildAt(i).findViewById(R.id.renkliArka);
                            ImageView resim = (ImageView) eleman.getChildAt(i).findViewById(R.id.kitapGorsel);

                            new DownloadImageTask2(resim, renkliArka).execute(cokResim[i]);
                            cokArananYuklenenResimSayisi++;

                        }
                    }
                }
                catch (Exception e)
                {}

                ////System.out.println("scrollX: " + cubuk);
            }
        });

    }




    void sonAranankaydirmaDurum(final HorizontalScrollView kaydirmaKutusu, final LinearLayout eleman,final int a)
    {


        //System.out.println("GENİŞLİK: "+genislik);


        kaydirmaKutusu.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

               // //System.out.println("RESİM SAYISI: "+resimSayi);
               // //System.out.println("ÇUBUK : "+cubuk);

try {
    int cubuk = kaydirmaKutusu.getScrollX();
    int resimSayi = a + cubuk / (genislik/a);
    if (sonArananYuklenenResimSayisi < eleman.getChildCount()) {
        for (int i = sonArananYuklenenResimSayisi; i <= resimSayi; i++) {
            //System.out.println("ELEMAN: " + i);

            ImageView resim = (ImageView) eleman.getChildAt(i).findViewById(R.id.populerGorsel);

            new DownloadImageTask(resim).execute(sonResim[i]);
            sonArananYuklenenResimSayisi++;

        }
    }
}
catch (Exception e)
{}
              //  //System.out.println("scrollX: " + cubuk);
            }
        });

    }







    void tavsiyekaydirmaDurum(final HorizontalScrollView kaydirmaKutusu, final LinearLayout eleman, final int a)
    {


        //System.out.println("GENİŞLİK: "+genislik);


        kaydirmaKutusu.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

              //  //System.out.println("RESİM SAYISI: "+resimSayi);
              //  //System.out.println("ÇUBUK : "+cubuk);

                try {
                 
                    int cubuk = kaydirmaKutusu.getScrollX();
                    int resimSayi = a + cubuk / (genislik/a);
                    if (tavsiyeYuklenenResimSayisi < eleman.getChildCount()) {
                        for (int i = tavsiyeYuklenenResimSayisi; i <= resimSayi; i++) {
                            //System.out.println("ELEMAN: " + i);

                            ImageView resim = (ImageView) eleman.getChildAt(i).findViewById(R.id.populerGorsel);

                            new DownloadImageTask(resim).execute(tavsiyeResim[i]);
                            tavsiyeYuklenenResimSayisi++;

                        }
                    }
                }
                catch (Exception e)
                {
                 //   //System.out.println(e.getMessage());
                }

               // //System.out.println("scrollX: " + cubuk);
            }
        });

    }



    int bulunanYuklenenResimSayisi=0;
    void bulunanlarkaydirmaDurum(final ScrollView kaydirmaKutusu, final LinearLayout eleman, final int a)
    {


        //System.out.println("GENİŞLİK: "+genislik);


        kaydirmaKutusu.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                try {
                    int cubuk = kaydirmaKutusu.getScrollY();
                    int resimSayi = a + cubuk / (yukseklik/a);
                    System.out.println("RESİM SAYISI: "+resimSayi);
                    System.out.println("ÇUBUK : "+cubuk);


                    if (bulunanYuklenenResimSayisi < eleman.getChildCount()) {
                        for (int i = bulunanYuklenenResimSayisi; i <= resimSayi; i++) {
                            //System.out.println("ELEMAN: " + i);
                          //  TextView x = (TextView) eleman.getChildAt(i).findViewById(R.id.kitapYazari);
                            //System.out.println(x.getText());

                           // RelativeLayout renkliArka = (RelativeLayout) eleman.getChildAt(i).findViewById(R.id.renkliArka);
                            ImageView resim = (ImageView) eleman.getChildAt(i).findViewById(R.id.resim);

                            new DownloadImageTask(resim).execute(fotolar[i]);
                            bulunanYuklenenResimSayisi++;

                        }
                    }
                }
                catch (Exception e)
                {
                    System.out.println("RESİM HATA: "+e.getMessage());
                }

                ////System.out.println("scrollX: " + cubuk);
            }
        });

    }













    void kitap365()
    {
/*
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.setMessage("Ürünler yükleniyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();
*/
        final WebView babilWebKatman = (WebView)findViewById(R.id.k365WebKatman);
        babilWebKatman.getSettings().setJavaScriptEnabled(true);
        babilWebKatman.getSettings().setLoadsImagesAutomatically(false);
        babilWebKatman.addJavascriptInterface(new LoadListener365(), "HTMLOUT");

        String sayfa="";
        babilWebKatman.loadUrl("https://www.kitap365.com/arama/?filter="+ISBN);

        zamanDoldu=true;
        gecikti=false;

        babilWebKatman.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            @Override
            public void onPageStarted(final  WebView view, String url, Bitmap favicon) {

                zamanDoldu = true;
                Handler handler77 = new Handler();
                handler77.postDelayed(new Runnable() {
                    public void run() {
                        if (zamanDoldu)
                        {
                            view.stopLoading();
                        }
                    }
                }, 3000);



            }

            public void onPageFinished(WebView view, String url) {
                zamanDoldu=false;
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                //System.out.println("evet oldu");
                view.stopLoading();

            }
        });

    }

    String kitap365Metin;
    class LoadListener365{
        @JavascriptInterface
        public void processHTML(String html)
        {
            //Log.e("result",html);
           kitap365Metin = html;
           new kitap365Bul().execute();
        }
    }



void bulunanKontrol()
{
    String [] basliklarTMP = Arrays.copyOf(basliklar,bulunanSayisi);
    String [] fotolarTMP = Arrays.copyOf(fotolar,bulunanSayisi);
    String [] yazarlarTMP = Arrays.copyOf(yazarlar,bulunanSayisi);
    String [] adreslerTMP = Arrays.copyOf(adresler,bulunanSayisi);
    String [] yayinEviTMP =Arrays.copyOf(yayinEvi,bulunanSayisi);



    for(int i=0; i<bulunanSayisi-1;i++)
    {
        if(basliklarTMP[i]=="")
            continue;

        for(int j=i+1 ; j < bulunanSayisi ; j++) {
            if ((basliklarTMP[i].trim().toUpperCase().equals(basliklarTMP[j].trim().toUpperCase())) && (yayinEviTMP[i].trim().toUpperCase().equals(yayinEviTMP[j].trim().toUpperCase())) && (yazarlarTMP[i].trim().toUpperCase().equals(yazarlarTMP[j].trim().toUpperCase()))) {
                basliklarTMP[j] = "";
                fotolarTMP[j] = "";
                yazarlarTMP[j] = "";
                adreslerTMP[j] = "";
                yayinEviTMP[j] = "";
            }
        }

    }

  //  System.out.println("HAYDIIIII");

    Arrays.fill(basliklar, null);
    Arrays.fill(fotolar, null);
    Arrays.fill(fiyatlar, null);
    Arrays.fill(adresler, null);
    Arrays.fill(yazarlar, null);
    Arrays.fill(yayinEvi, null);
    int doluSay=0;
    for(int i=0; i<bulunanSayisi;i++)
    {
        if(basliklarTMP[i] != "") {
         //   System.out.println(basliklarTMP[i] + " --- " + yazarlarTMP[i] + " --- " + yayinEviTMP[i]);
            basliklar[doluSay] = basliklarTMP[i];
            fotolar[doluSay] = fotolarTMP[i];
            adresler[doluSay] = adreslerTMP[i];
            yazarlar[doluSay] = yazarlarTMP[i];
            yayinEvi[doluSay] = yayinEviTMP[i];

            doluSay++;
        }
    }
    bulunanSayisi = doluSay;
    yazdirUrunSayi = doluSay;


}

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    void menuIslemler(ImageView view)
    {
        ImageView evButon = (ImageView) findViewById(R.id.imageView3);
        ImageView kitaplikButon = (ImageView)findViewById(R.id.imageView4);
        ImageView barkodButon = (ImageView)findViewById(R.id.imageView6);
        ImageView sepetButon = (ImageView)findViewById(R.id.imageView7);

        evButon.setAlpha(0.2f);
        kitaplikButon.setAlpha(0.2f);
        barkodButon.setAlpha(0.2f);
        sepetButon.setAlpha(0.2f);

        view.setAlpha(1f);


    }

    public void kitapligaEkle(View view)
    {
        WebView webKitaplik = (WebView) findViewById(R.id.webKitaplik);
        System.out.println("ADRES" + detayAdres);
        new kitaplikVeriYaz(detayAd, detayYazar, detayYayinEvi, detayResim, detayAdres, android_id, webKitaplik).execute();
    }





    void bildirimIslemleriBasla()
    {
        Calendar calendar = Calendar.getInstance();
        //Returns current time in millis
        long timeMilli2 = calendar.getTimeInMillis();

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(getBaseContext(), bildirimBasla.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
        }
    }









    public void sirala(View view) {
        fiyatlariListeyeAktar();




        ImageView sb1 = (ImageView) findViewById(R.id.siralamaButon);
        ImageView sb2 = (ImageView) findViewById(R.id.siralamaButon2);





        //////////System.out.println("BURALARDA");

        sb1.setAlpha(1f);
        sb2.setAlpha(0.3f);


        //  RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        // params.addRule(RelativeLayout.BELOW, R.id.ukaKatman);


        TextView[] fiyatlarKatman = new TextView[katmanlar.length];
        for(int i=0;i<katmanlar.length;i++)
        {
            fiyatlarKatman[i] = (TextView) katmanlar[i].getChildAt(1);
        }



        String[][] fiyatlarF = new  String[katmanlar.length][2];
        for(int i=0; i<katmanlar.length;i++)
        {
            fiyatlarF[i][0] = String.valueOf(i);
            fiyatlarF[i][1] = String.valueOf(fiyatSonListe.get(i));

        }





        String a, b;
        Float fiyat1, fiyat2;
        String x, y;


        for (int i = 0; i < fiyatlarF .length; i++) {

            //fiyatlarF[i][1] = sayiKontrol(fiyatlarF[i][1],i);
            //   System.out.println(fiyatlarF[i][1] );


            if (fiyatlarF[i][1]==null)
                fiyatlarF[i][1] = "1000000";
            else if (fiyatlarF[i][1]=="")
                fiyatlarF[i][1] = "1000000";
            else if(fiyatlarF[i][1].contains("--"))
                fiyatlarF[i][1] = "1000000";


            if(!veriSayiKontrol(fiyatlarF[i][1]))
            {
                fiyatlarF[i][1] = "1000000";
                fiyatlarKatman[i].setText("-----");
            }


            ////////System.out.println("FIYAT "+i+" "+fiyatlarF[i][1]);
        }


        for (int i = 0; i < fiyatlarF.length - 1; i++) {

            for (int k = i + 1; k < fiyatlarF.length; k++) {
                x = fiyatlarF[i][1].replaceAll(" TL", "");
                //  x=x.trim();
                //  x=x.substring(0,x.length()-1);
                x = x.replaceAll(",", ".");
                fiyat1 = Float.parseFloat(x);


                y = fiyatlarF[k][1].replaceAll(" TL", "");
                //y=y.trim();
                // y=y.substring(0,y.length()-1);
                y = y.replaceAll(",", ".");
                //////////System.out.println("yaz"+y+"x");
                fiyat2 = Float.parseFloat(y);
                // //////////System.out.println("1 "+fiyat1);
                //  //////////System.out.println("2 "+fiyat2);

                if (fiyat2 < fiyat1) {
                    //  //////////System.out.println("küçük");
                    a = fiyatlarF[k][0];
                    b = fiyatlarF[k][1];


                    

                    fiyatlarF[k][0] = fiyatlarF[i][0];
                    fiyatlarF[k][1] = fiyatlarF[i][1];

                    fiyatlarF[i][0] = a;
                    fiyatlarF[i][1] = b;
                    

                    
                    
                    
                    
                    String tmp1 = fiyatSonListeSirali.get(i);
                    String tmp2 = fiyatSonListeSirali.get(k);
                    fiyatSonListeSirali.set(i,tmp2);
                    fiyatSonListeSirali.set(k,tmp1);

                    tmp1 = adresSonListeSirali.get(i);
                    tmp2 = adresSonListeSirali.get(k);
                    adresSonListeSirali.set(i,tmp2);
                    adresSonListeSirali.set(k,tmp1);


                }


            }
        }


        for (int i = 0; i < fiyatlarF.length; i++) {
            //   //////////System.out.println(fiyatlar[i][0] + " " + fiyatlar[i][1]);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        //Listenin başı


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listeFiyatlar);
        linearLayout.removeAllViews();


        RelativeLayout bulunanx = (RelativeLayout) findViewById(R.id.bulunan);

        //linearLayout.addView(bulunanx);


        int sayi;



        for (int i = 0; i < fiyatlarF .length; i++) {
            sayi = Integer.parseInt(fiyatlarF[i][0]);
            linearLayout.addView(katmanlar[sayi]);

        }


    }



















    String alternatifAdres="";

    String alternatifFiyat;
    private class alternatifBul extends AsyncTask<Void, Void, Void> {
        double bulY=bulX;

        String authors;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            if(herseyiDurdur)
            {
                cancel(true);
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            alternatifFiyat=null;
            alternatifAdres=null;
            try{
                aramaSira=2;

                Document doc = Jsoup.connect("https://www.alternatifkitap.com/ara/?q="+ ISBN).timeout(3000).validateTLSCertificates(false).get();

                Elements elements = doc.select("div[id=product_code_view_pagination_contents] div[class=ut2-gl__image] a");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = "https://www.alternatifkitap.com"+baslik.attr("href");
                    alternatifAdres=data;
                    break;
                }


                elements = doc.select("div[id=product_code_view_pagination_contents] span[class=ty-price-num]");  // class ismi post-content olan verileri çekmek için
                for (org.jsoup.nodes.Element baslik : elements) {
                    String data = baslik.text();
                    alternatifFiyat=data;
                    break;
                }

            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {



            if(bulX==bulY)
            {
                TextView ideF = (TextView)findViewById(R.id.alternatifFiyat);
                if(alternatifFiyat==null)
                    alternatifFiyat="-----";
                ideF.setText(alternatifFiyat);
                sirala(new View(MainActivity.this));
              //  new hbBul().execute();
                new hepsiBuradaBul().execute();

                System.out.println("BAŞLADIM");
            }
            else
            {
                alternatifFiyat="-----";
            }

        }
    }






public void deneme()
{
    try
    {
        URL url = new URL("https://www.nadirkitap.com/kitapara.php?ara=aramayap&isbn=9789754700114");
        URLConnection uc = url.openConnection();
        //String j = (String) uc.getContent();
        uc.setDoInput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
        if(a.indexOf("TUTUNAMAYANLAR")>0)
            System.out.println("NADİR SONUÇ: " + "bulundu");
    }
    catch (Exception e)
    {

    }

}

















    public void callSitesInLoop2() {
        List<String> urls = Arrays.asList(
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/alternatif.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/amazon.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/dost.php",
              //  "http://bilisimkalesi.com/kitap/kitap_bul/siteler/dr.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/idefix.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/ilknokta.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/istanbul.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsec.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsepeti.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapvadisi.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/pandora.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/uka.php"
        );

        if (!urls.isEmpty()) {
            long startTime = System.currentTimeMillis(); // Başlangıç zamanı
            new DenemeBul(urls, 0, startTime).execute(urls.get(0));
        }
    }

    String denemeFiyat, denemeAdres;

    private class DenemeBul extends AsyncTask<String, Void, Void> {
        double bulY = bulX; // bulX'in dışarıda tanımlı olduğunu varsayıyorum
        String authors;
        private List<String> urls;
        private int currentIndex;
        private long startTime; // Başlangıç zamanını saklamak için

        // Constructor'a startTime eklendi
        public DenemeBul(List<String> urls, int index, long startTime) {
            this.urls = urls;
            this.currentIndex = index;
            this.startTime = startTime;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0]; // URL parametreden alınıyor
            denemeFiyat = null;
            denemeAdres = null;
            try {
                Document doc = Jsoup.connect(url + "?isbn=" + ISBN)
                        .timeout(5000)
                        .validateTLSCertificates(false)
                        .get();

                denemeAdres = doc.select("kitap adres").first().text();
                denemeFiyat = doc.select("kitap fiyat").first().text();

                System.out.println(denemeFiyat);
                System.out.println(denemeAdres);
                System.out.println("__________________");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("bilişim kalesi hata: " + url);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Bir sonraki URL'yi işle, eğer liste bitmediyse
            if (currentIndex + 1 < urls.size()) {
                new DenemeBul(urls, currentIndex + 1, startTime).execute(urls.get(currentIndex + 1));
            } else {
                // Liste bittiğinde toplam süreyi hesapla ve yazdır
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Döngü tamamlanma süresi: " + duration + " milisaniye");
            }
        }
    }







/*


    public void callSitesInLoop() {
        List<String> urls = Arrays.asList(
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/alternatif.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/amazon.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/dost.php",
              //  "http://bilisimkalesi.com/kitap/kitap_bul/siteler/dr.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/idefix.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/ilknokta.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/istanbul.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsec.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsepeti.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapvadisi.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/pandora.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/uka.php"
        );

        if (!urls.isEmpty()) {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(urls.size()); // Her URL için bir thread

            for (String url : urls) {
                executor.submit(() -> {
                    try {
                        Document doc = Jsoup.connect(url + "?isbn=" + ISBN)
                                .timeout(5000)
                                .validateTLSCertificates(false)
                                .get();

                        String denemeAdres = doc.select("kitap adres").first().text();
                        String denemeFiyat = doc.select("kitap fiyat").first().text();

                        synchronized (System.out) { // Konsol çıktısını senkronize et
                            System.out.println(denemeFiyat);
                            System.out.println(denemeAdres);
                            System.out.println("__________________");
                            cevirici(denemeFiyat,denemeAdres);
                        }
                    } catch (Exception e) {
                        synchronized (System.out) {
                            System.out.println("bilişim kalesi hata: " + url);
                            e.printStackTrace();
                        }
                    }


                });
            }

            executor.shutdown();
            try {
                executor.awaitTermination(30, TimeUnit.SECONDS); // Maksimum bekleme süresi
                long endTime = System.currentTimeMillis();
                System.out.println("Taramalar tamamlandı, toplam süre: " + (endTime - startTime) + " milisaniye");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





 public void cevirici(String fiyatim, String adresim)
 {
     // Adresi küçük harfe çevir

     String adresLower = adresim.toLowerCase();
     System.out.println(adresim);
     if (adresLower.contains("alternatif")) {
         TextView dF = (TextView) findViewById(R.id.alternatifFiyat);
         dF.setText(fiyatim);
         alternatifFiyat = fiyatim;
         alternatifAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("idefix")) {
         TextView dF = (TextView) findViewById(R.id.idefixFiyat);
         dF.setText(fiyatim);
         idefixFiyat = fiyatim;
         idefixAdres = adresim;
         System.out.println("IDEFIX GIRIS YAPILDI");
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("hepsiburada")) {
         TextView dF = (TextView) findViewById(R.id.hbFiyat);
         dF.setText(fiyatim);
         hbFiyat = fiyatim;
         hbAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitapyurdu")) {
         TextView dF = (TextView) findViewById(R.id.kyFiyat);
         dF.setText(fiyatim);
         kyFiyat = fiyatim;
         kyAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kedi")) {
         TextView dF = (TextView) findViewById(R.id.kediFiyat);
         dF.setText(fiyatim);
         kediFiyat = fiyatim;
         kediAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitap16")) {
         TextView dF = (TextView) findViewById(R.id.kitap16Fiyat);
         dF.setText(fiyatim);
         kitap16Fiyat = fiyatim;
         kitap16Adres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("ist")) {
         TextView dF = (TextView) findViewById(R.id.istFiyat);
         dF.setText(fiyatim);
         istFiyat = fiyatim;
         istAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("bkm")) {
         TextView dF = (TextView) findViewById(R.id.bkmFiyat);
         dF.setText(fiyatim);
         bkmFiyat = fiyatim;
         bkmAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitapsec")) {
         TextView dF = (TextView) findViewById(R.id.secFiyat);
         dF.setText(fiyatim);
         secFiyat = fiyatim;
         secAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("pandora")) {
         TextView dF = (TextView) findViewById(R.id.pandoraFiyat);
         dF.setText(fiyatim);
         pandoraFiyat = fiyatim;
         pandoraAdres = adresim;
         sirala(new View(MainActivity.this));
     }  else if (adresLower.contains("ucuzkitapal")) {
         TextView dF = (TextView) findViewById(R.id.ukaFiyat);
         dF.setText(fiyatim);
         ukaFiyat = fiyatim;
         ukaAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("amazon")) {
         TextView dF = (TextView) findViewById(R.id.amazonFiyat);
         dF.setText(fiyatim);
         amazonFiyat = fiyatim;
         amazonAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("n11")) {
         TextView dF = (TextView) findViewById(R.id.n11Fiyat);
         dF.setText(fiyatim);
         n11Fiyat = fiyatim;
         n11Adres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitapkolik")) {
         TextView dF = (TextView) findViewById(R.id.kitapkolikFiyat);
         dF.setText(fiyatim);
         kitapkolikFiyat = fiyatim;
         kitapkolikAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("nadir")) {
         TextView dF = (TextView) findViewById(R.id.nadirFiyat);
         dF.setText(fiyatim);
         nadirFiyat = fiyatim;
         nadirAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("ilknokta")) {
         TextView dF = (TextView) findViewById(R.id.ilknoktaFiyat);
         dF.setText(fiyatim);
         ilknoktaFiyat = fiyatim;
         ilknoktaAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("koala")) {
         TextView dF = (TextView) findViewById(R.id.koalaFiyat);
         dF.setText(fiyatim);
         koalaFiyat = fiyatim;
         koalaAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("trendyol")) {
         TextView dF = (TextView) findViewById(R.id.trendyolFiyat);
         dF.setText(fiyatim);
         trendyolFiyat = fiyatim;
         trendyolAdres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitap365")) {
         TextView dF = (TextView) findViewById(R.id.kitap365Fiyat);
         dF.setText(fiyatim);
         kitap365Fiyat = fiyatim;
         kitap365Adres = adresim;
         sirala(new View(MainActivity.this));
     } else if (adresLower.contains("kitapssepeti")) {
         TextView dF = (TextView) findViewById(R.id.ksFiyat);
         dF.setText(fiyatim);
         kSepetiFiyat = fiyatim;
         kSepetiAdres = adresim;
         sirala(new View(MainActivity.this));
     }
 }
*/






    public void callSitesInLoop() {
        List<String> urls = Arrays.asList(
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/alternatif.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/amazon.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/dost.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/hb.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/idefix.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/ilknokta.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/istanbul.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsec.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/kitapsepeti.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/pandora.php",
                "http://bilisimkalesi.com/kitap/kitap_bul/siteler/uka.php",
                "tamadres",
                "kitapvadisi",
                "nadir",
                "kitapyurdu"
        );

        if (!urls.isEmpty()) {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(urls.size()); // Her URL için bir thread

            for (String url : urls) {

                executor.submit(() -> {
                    if(url=="tamadres") {
                        new kediBul().execute();
                    } else if (url=="kitapvadisi") {
                        new kitap16Bul().execute();
                    } else if (url=="nadir") {
                        new bbBul2().execute();
                    } else if (url=="kitapyurdu") {
                        new kyBul().execute();
                    }
                    else {
                        denemeAdres = "boş";
                        denemeFiyat = "-----";
                        try {

                            Document doc = Jsoup.connect(url + "?isbn=" + ISBN)
                                    .timeout(10000)
                                    .validateTLSCertificates(false)
                                    .get();

                                denemeAdres = doc.select("kitap adres").first().text();
                                denemeFiyat = doc.select("kitap fiyat").first().text() + " TL";

                            synchronized (System.out) { // Konsol çıktısını senkronize et
                                System.out.println(denemeFiyat);
                                System.out.println(denemeAdres);
                                System.out.println("__________________");
                            }
                        } catch (Exception e) {
                            denemeAdres = "";
                            denemeFiyat = "-----";
                           // cevirici(denemeFiyat, denemeAdres);
                            synchronized (System.out) {
                                System.out.println("bilişim kalesi hata: " + url+e.getMessage());
                                System.out.println("hatalar:" + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                        System.out.println("ops:" + url);
                        cevirici(denemeFiyat, denemeAdres, url);

                    }
                });
            }

            executor.shutdown();

            // awaitTermination kaldırıldı, çünkü UI'yi bloklamasın. Task'lar arka planda devam eder ve her biri bittiğinde UI güncellenir.
            // Toplam süre ölçümü için alternatif olarak her task sonunda log atabilirsin, ama toplamı hesaplamak için CountDownLatch kullanabilirsin eğer必要 olursa.
        }
    }

    public void cevirici(String fiyatim, String adresim, String urlem) {
        // Adresi küçük harfe çevir
        String adresLower = urlem.toLowerCase();
        System.out.println("ops:" + adresim);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adresLower.contains("alternatif")) {
                    TextView dF = (TextView) findViewById(R.id.alternatifFiyat);
                    dF.setText(fiyatim);
                    alternatifFiyat = fiyatim;
                    alternatifAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("idefix")) {
                    TextView dF = (TextView) findViewById(R.id.idefixFiyat);
                    dF.setText(fiyatim);
                    idefixFiyat = fiyatim;
                    idefixAdres = adresim;
                    System.out.println("IDEFIX GIRIS YAPILDI");
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("hb.php")) {
                    TextView dF = (TextView) findViewById(R.id.hbFiyat);
                    dF.setText(fiyatim);
                    hbFiyat = fiyatim;
                    hbAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitapyurdu")) {
                    TextView dF = (TextView) findViewById(R.id.kyFiyat);
                    dF.setText(fiyatim);
                    kyFiyat = fiyatim;
                    kyAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kedi")) {
                    TextView dF = (TextView) findViewById(R.id.kediFiyat);
                    dF.setText(fiyatim);
                    kediFiyat = fiyatim;
                    kediAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitap16")) {
                    TextView dF = (TextView) findViewById(R.id.kitap16Fiyat);
                    dF.setText(fiyatim);
                    kitap16Fiyat = fiyatim;
                    kitap16Adres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("ist")) {
                    TextView dF = (TextView) findViewById(R.id.istFiyat);
                    dF.setText(fiyatim);
                    istFiyat = fiyatim;
                    istAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("bkm")) {
                    TextView dF = (TextView) findViewById(R.id.bkmFiyat);
                    dF.setText(fiyatim);
                    bkmFiyat = fiyatim;
                    bkmAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitapsec")) {
                    TextView dF = (TextView) findViewById(R.id.secFiyat);
                    dF.setText(fiyatim);
                    secFiyat = fiyatim;
                    secAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("pandora")) {
                    TextView dF = (TextView) findViewById(R.id.pandoraFiyat);
                    dF.setText(fiyatim);
                    pandoraFiyat = fiyatim;
                    pandoraAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("uka.php")) {
                    TextView dF = (TextView) findViewById(R.id.ukaFiyat);
                    dF.setText(fiyatim);
                    ukaFiyat = fiyatim;
                    ukaAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("amazon")) {
                    TextView dF = (TextView) findViewById(R.id.amazonFiyat);
                    dF.setText(fiyatim);
                    amazonFiyat = fiyatim;
                    amazonAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("n11")) {
                    TextView dF = (TextView) findViewById(R.id.n11Fiyat);
                    dF.setText(fiyatim);
                    n11Fiyat = fiyatim;
                    n11Adres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitapkolik")) {
                    TextView dF = (TextView) findViewById(R.id.kitapkolikFiyat);
                    dF.setText(fiyatim);
                    kitapkolikFiyat = fiyatim;
                    kitapkolikAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("nadir")) {
                    TextView dF = (TextView) findViewById(R.id.nadirFiyat);
                    dF.setText(fiyatim);
                    nadirFiyat = fiyatim;
                    nadirAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("ilknokta")) {
                    System.out.println("ilknoktaoldu");
                    TextView dF = (TextView) findViewById(R.id.ilknoktaFiyat);
                    dF.setText(fiyatim);
                    ilknoktaFiyat = fiyatim;
                    ilknoktaAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("dost.php")) {
                    TextView dF = (TextView) findViewById(R.id.koalaFiyat);
                    dF.setText(fiyatim);
                    koalaFiyat = fiyatim;
                    koalaAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("trendyol")) {
                    TextView dF = (TextView) findViewById(R.id.trendyolFiyat);
                    dF.setText(fiyatim);
                    trendyolFiyat = fiyatim;
                    trendyolAdres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitap365")) {
                    TextView dF = (TextView) findViewById(R.id.kitap365Fiyat);
                    dF.setText(fiyatim);
                    kitap365Fiyat = fiyatim;
                    kitap365Adres = adresim;
                    sirala(new View(MainActivity.this));
                } else if (adresLower.contains("kitapsepeti")) {
                    TextView dF = (TextView) findViewById(R.id.ksFiyat);
                    dF.setText(fiyatim);
                    kSepetiFiyat = fiyatim;
                    kSepetiAdres = adresim;
                    sirala(new View(MainActivity.this));
                }
            }
        });
    }




    public static void longLog(String str) {
        if (str.length() > 4000) {
            Log.d("", str.substring(0, 4000));
            longLog(str.substring(4000));
        } else
            Log.d("", str);
    }

}

