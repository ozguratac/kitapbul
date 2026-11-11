package com.mozosoft.zgr.kitapbul;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mozosoft.zgr.kampanyam.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by For on 4/14/2017.
 */

public class kitaplik extends AppCompatActivity {
    String android_id;
    ProgressDialog progressDialog;
    AdView mAdView;
    AdRequest adRequest;
    List<kitaplikSablon> kitapDetayBilgiler;
    List<kitaplikSablon> kitapListesi;
    int ustMenuKonum = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);





        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        yukseklik = displayMetrics.heightPixels;


        progressDialog = new ProgressDialog(kitaplik.this);
        // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progressDialog.setMessage("Kitaplık yükleniyor...");
        progressDialog.setIndeterminate(false);

        progressDialog.show();
        setContentView(R.layout.kitaplik);
        ImageView evButon = (ImageView) findViewById(R.id.imageView3);
        ImageView kitaplikButon = (ImageView)findViewById(R.id.imageView4);
        ImageView barkodButon = (ImageView)findViewById(R.id.imageView6);
        ImageView sepetButon = (ImageView)findViewById(R.id.imageView7);

        evButon.setAlpha(0.2f);
        kitaplikButon.setAlpha(0.2f);
        barkodButon.setAlpha(0.2f);
        sepetButon.setAlpha(0.2f);

        kitaplikButon.setAlpha(1f);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                try {
                    kitapListesi = new kitaplikVeriOku(android_id).execute().get();
                    kitapDetayBilgiler = kitapListesi;

                    kitaplikyazdir(kitapListesi);
                    mAdView = (AdView) findViewById(R.id.adView);
                    adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);

                    //progressDialog.dismiss();
                }
                catch (Exception e)
                {
                }
            }
        }, 100);






    }

    int yukseklik;
    String silinecekKitapNumara;
    public void kitapDetayKatmanAc(int kayitNO)
    {






        kitaplikAna = false;

        RelativeLayout resimKat = (RelativeLayout)findViewById(R.id.resimKatman);
        ImageView kitapDetayRes = (ImageView)findViewById(R.id.kitapDetayResim);
        resimKat.setBackground(null);
        kitapDetayRes.setImageDrawable(null);





        ImageView kitapSilButon = (ImageView)findViewById(R.id.kitapSilButon);
        kitapSilButon.setVisibility(View.INVISIBLE);
        kitapSilButon.setEnabled(false);


        final int knotmp=kayitNO;
        kitapSilButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silinecekKitapAdres = "http://www.bilisimkalesi.com/mobil/kitaplikSil.php?cihazID="+android_id+"&id="+kitapDetayBilgiler.get(knotmp).Kitapid;
                silinecekKitapNumara=kitapDetayBilgiler.get(knotmp).Kitapid;
                kitapSilOnay(new View(kitaplik.this));
            }

        });






        ImageView kitapAraButon = (ImageView)findViewById(R.id.kitapAraButon);
        kitapAraButon.setVisibility(View.INVISIBLE);
        kitapAraButon.setEnabled(false);

        kitapAraButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //    silinecekKitapNumara=kitapDetayBilgiler.get(knotmp).Kitapid;
               // kitapSilOnay(new View(kitaplik.this));

                System.out.println("aranacak kitap  ISBN:" + kitapDetayBilgiler.get(knotmp).IsbnY);

                kitaplikAramaBilgiler.Isbn = kitapDetayBilgiler.get(knotmp).IsbnY;
                kitaplikAramaBilgiler.Aciklama = kitapDetayBilgiler.get(knotmp).AciklamaY;
                kitaplikAramaBilgiler.KitapAd = kitapDetayBilgiler.get(knotmp).KitapAdY;
                kitaplikAramaBilgiler.KitapYazar = kitapDetayBilgiler.get(knotmp).KitapYazarY;
                kitaplikAramaBilgiler.ResimAdres = kitapDetayBilgiler.get(knotmp).ResimAdresY;
                kitaplikAramaBilgiler.YayinEvi = kitapDetayBilgiler.get(knotmp).YayinEviY;



                cikis=true;
                dwn=null;
                menu.menuNo=5;
                startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));




            }

        });



        final RelativeLayout kitapDetayKatman = (RelativeLayout)findViewById(R.id.kitapDetay);
        kitapDetayKatman.setVisibility(View.VISIBLE);
        System.out.println("TIKLANAN:" + kayitNO);

        TextView kitapDetayAd = (TextView)findViewById(R.id.kitapDetayAd);
        TextView kitapDetayYazar = (TextView)findViewById(R.id.kitapDetayYazar);
        TextView kitapDetayAciklama = (TextView)findViewById(R.id.kitapdetayAciklama);
        ImageView kitapDetayResim = (ImageView)findViewById(R.id.kitapDetayResim);

        final ImageView okudumButon = (ImageView)findViewById(R.id.imageView8);
        final ImageView okumadimButon = (ImageView)findViewById(R.id.imageView9);

        kitapDetayAciklama.setText("");


        System.out.println("DURUM" + kitapDetayBilgiler.get(kayitNO).KitapDurumY);
    if(kitapDetayBilgiler.get(kayitNO).KitapDurumY.equals("1"))
    {
        okudumButon.setImageResource(R.drawable.okudumsecildi);
        okumadimButon.setImageResource(R.drawable.okumadim);
    }
    else if(kitapDetayBilgiler.get(kayitNO).KitapDurumY.equals("2"))
    {
        okudumButon.setImageResource(R.drawable.okudum);
        okumadimButon.setImageResource(R.drawable.okumadimsecildi);
    }
    else
    {
        okudumButon.setImageResource(R.drawable.okudum);
        okumadimButon.setImageResource(R.drawable.okumadim);
    }



        final int kNo = kayitNO;
        okudumButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kitapDetayKatmanKapat();
               // WebView web = (WebView)findViewById(R.id.kitaplikWeb);
               // web.loadUrl("http://www.bilisimkalesi.com/mobil/kitapOkumaDurum.php?cihazID="+android_id+"&id="+kitapDetayBilgiler.get(kNo).Kitapid+"&durum=1");

                okumaButonAdres = "http://www.bilisimkalesi.com/mobil/kitapOkumaDurum.php?cihazID="+android_id+"&id="+kitapDetayBilgiler.get(kNo).Kitapid+"&durum=1";
                okumaButonDurum = true;
                kitapDetayBilgiler.get(kNo).KitapDurumY = "1";
                new okumaButonTiklama().execute();

            }

        });

        okumadimButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // kitapDetayKatmanKapat();
              //  WebView web = (WebView)findViewById(R.id.kitaplikWeb);
               // web.loadUrl("http://www.bilisimkalesi.com/mobil/kitapOkumaDurum.php?cihazID="+android_id+"&id="+kitapDetayBilgiler.get(kNo).Kitapid+"&durum=2");

                okumaButonAdres = "http://www.bilisimkalesi.com/mobil/kitapOkumaDurum.php?cihazID="+android_id+"&id="+kitapDetayBilgiler.get(kNo).Kitapid+"&durum=2";
                okumaButonDurum = false;
                kitapDetayBilgiler.get(kNo).KitapDurumY = "2";
               new okumaButonTiklama().execute();

            }

        });










        kitapDetayAd.setText(kitapDetayBilgiler.get(kayitNO).KitapAdY);
        kitapDetayYazar.setText(kitapDetayBilgiler.get(kayitNO).KitapYazarY);

        aciklamaAlinacakAdres = kitapDetayBilgiler.get(kayitNO).AciklamaY;
        new bekleme().execute(); //buradan ekranı beklemeye alıp açıklama arkaplanda çekilecek.

        /*
        String okunanAciklama = aciklamaAl(kitapDetayBilgiler.get(kayitNO).AciklamaY);



        if((okunanAciklama=="")||(okunanAciklama.length()<10))
        {
            okunanAciklama = "Bu kitap ile ilgili açıklama bulunamadı.";
        }
        kitapDetayAciklama.setText(okunanAciklama);
*/
        dwn2 = new DownloadImageTask2(kitapDetayResim);
        dwn2.execute(kitapDetayBilgiler.get(kayitNO).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar","1000kitap.com/resimler/kitaplar"));


        Display display = getWindowManager().getDefaultDisplay();
        final int ekranGenislik = display.getWidth();

        int en=Math.round((ekranGenislik-Math.round(ekranGenislik/9))/2);
        int boy = Math.round((en*190)/120);

        kitapDetayResim.getLayoutParams().width = en;
        kitapDetayResim.getLayoutParams().height = boy;


    }


    public void kitapDetayKatmanKapat()
    {
        final RelativeLayout kitapDetayKatman = (RelativeLayout)findViewById(R.id.kitapDetay);
        kitapDetayKatman.setVisibility(View.INVISIBLE);
        kitaplikAna = true;
    }



    DownloadImageTask dwn;
    DownloadImageTask2 dwn2;

    int satirSayisi;
    public void kitaplikyazdir(List<kitaplikSablon> kitapListesi2)
    {

        progressDialog.dismiss();

        int s=0;
        if(kitapListesi2.size()==0)
            progressDialog.dismiss();

        kitaplikYuklenenResimSayisi=0;
        kitapDetayBilgiler = kitapListesi2;

        LayoutInflater inflater = LayoutInflater.from(kitaplik.this);
        RelativeLayout[] layout = new RelativeLayout[kitapListesi2.size()];
        LinearLayout linear = (LinearLayout) findViewById(R.id.kitaplikListe);


        TextView kitapSayi = (TextView)findViewById(R.id.kitaplikKitapSayi);
        kitapSayi.setText(kitapListesi2.size()+" Adet");



        // ScrollView kaydir = (ScrollView) findViewById(R.id.kaydirma);
        // kaydir.smoothScrollTo(0,0);

        linear.removeAllViews();
        System.out.println( "KİTAPLIKTAKİ KİTAP SAYISI: " +kitapListesi2.size());

        int i=0;
        int x=0;
        try {


            satirSayisi =  kitapListesi2.size() / 3;

            if(kitapListesi2.size()%3 > 0)
                satirSayisi++;


            for (int m = 0; m<satirSayisi; m++) {

                layout[i] = (RelativeLayout) inflater.inflate(R.layout.kitaplikicyeni, null, false);

              //  TextView yazar = (TextView) layout[i].findViewById(R.id.kYazar);
              //  TextView ad = (TextView) layout[i].findViewById(R.id.kAd);
              //  TextView aciklama = (TextView) layout[i].findViewById(R.id.kitapAciklama);
              //  TextView yayinEvi = (TextView) layout[i].findViewById(R.id.kYayinevi);
              //  ImageView kitapSil = (ImageView) layout[i].findViewById(R.id.kitapSil2);
                ImageView kitaplikResimSol = (ImageView) layout[i].findViewById(R.id.kitaplikResimSol2);
                ImageView kitaplikResimSag = (ImageView) layout[i].findViewById(R.id.kitaplikResimSag2);
                ImageView kitaplikResimOrta = (ImageView) layout[i].findViewById(R.id.kitaplikResimOrta2);


                Display display = getWindowManager().getDefaultDisplay();
                final int ekranGenislik = display.getWidth();

                int en=Math.round((ekranGenislik-Math.round(ekranGenislik/9))/3);
                int boy = Math.round((en*190)/120);

                kitaplikResimSol.getLayoutParams().width = en;
                kitaplikResimSag.getLayoutParams().width = en;
                kitaplikResimOrta.getLayoutParams().width = en;

                kitaplikResimSol.getLayoutParams().height = boy;
                kitaplikResimSag.getLayoutParams().height = boy;
                kitaplikResimOrta.getLayoutParams().height = boy;
/*
                kitaplikResimSol.getLayoutParams();
                kitaplikResimSag.getLayoutParams();
                kitaplikResimOrta.getLayoutParams();
*/




                s = (yukseklik / boy)*3;
                System.out.println("KİTAPLIK SATIR SAYISI : "+s);
                //en son hata verdi. bulunan kitapları alta kaydırınca.
                if(kitaplikYuklenenResimSayisi<s) {


                    if(x<kitapListesi2.size()) {

                        final int icX1=x;
                        kitaplikResimSol.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                kitapDetayKatmanAc(icX1);
                            }

                        });

                        dwn = new DownloadImageTask(kitaplikResimSol);
                        dwn.execute(kitapListesi2.get(x).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                        x++;
                        kitaplikYuklenenResimSayisi++;
                    }
                    else
                    {
                        kitaplikResimSol.setImageDrawable(null);
                    }
                    if(x<kitapListesi2.size()) {
                        final int icX2 = x;
                        kitaplikResimOrta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                kitapDetayKatmanAc(icX2);
                            }

                        });


                        dwn = new DownloadImageTask(kitaplikResimOrta);
                        dwn.execute(kitapListesi2.get(x).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                        x++;
                        kitaplikYuklenenResimSayisi++;
                    }
                    else
                    {
                        kitaplikResimOrta.setImageDrawable(null);
                    }

                    if(x<kitapListesi2.size()) {
                        final int icX3 = x;

                        kitaplikResimSag.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                kitapDetayKatmanAc(icX3);
                            }

                        });

                        dwn = new DownloadImageTask(kitaplikResimSag);
                        dwn.execute(kitapListesi2.get(x).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                        x++;
                        kitaplikYuklenenResimSayisi++;
                    }
                    else
                    {
                        kitaplikResimSag.setImageDrawable(null);
                    }

                }














/*
                float oran = 0.7f;

                kitaplikResimSag.getLayoutParams().height = Math.round (kitaplikResimSag.getLayoutParams().height * oran);
                kitaplikResimSag.getLayoutParams().width = Math.round (kitaplikResimSag.getLayoutParams().width * oran);

                kitaplikResimSol.getLayoutParams().height = Math.round (kitaplikResimSol.getLayoutParams().height * oran);
                kitaplikResimSol.getLayoutParams().width = Math.round (kitaplikResimSol.getLayoutParams().width * oran);
*/


              //  yazar.setText(kitap.KitapYazarY);
              //  ad.setText(kitap.KitapAdY);
             //   aciklama.setText(kitap.AciklamaY);
              //  yayinEvi.setText(kitap.YayinEviY);
//

                //new DownloadImageTask(kitaplikResim).execute(kitap.ResimAdresY);
/*
                WebView KitaplikResimWeb = (WebView) layout[i].findViewById(R.id.KitaplikResimWeb);

                if (kitap.ResimAdresY.contains("ucuzkitapal.com")) {
                    KitaplikResimWeb.loadData("<html><body><center><img style='margin-left:-30px;' width='100%' src='" + kitap.ResimAdresY + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");
                } else if (kitap.ResimAdresY.contains("kitapsec.com")) {
                    KitaplikResimWeb.loadData("<html><body><center><img style='margin-left:-40px;' width='100%' src='" + kitap.ResimAdresY + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");
                } else {
                    KitaplikResimWeb.loadData("<html><body><center><img width='100%' src='" + kitap.ResimAdresY + "'></center></body></html>", "text/html; charset=utf-8", "UTF-8");
                }


                KitaplikResimWeb.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getAction() == MotionEvent.ACTION_MOVE);
                    }
                });

*//*
                kitapSil.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        //System.out.println("TIKLANDI");
                        kitapSilIslem(kitap.Kitapid);

                        Utils a = new Utils();
                        a.showToast(kitaplik.this, "Kitap Silindi.");

                    }

                });

*/
                linear.addView(layout[i]);
                i++;

            }

        }
        catch(Exception e)
        {
            System.out.println("HATANIN SEBEBİ"+e.getMessage());
        }


        kitaplikkaydirmaDurum ((ScrollView) findViewById(R.id.kitaplikListeKaydir),(LinearLayout)findViewById(R.id.kitaplikListe),s);

    }


    int oku=0;

    public void kitapSilIslem(String id)
    {

        WebView web = (WebView)findViewById(R.id.kitaplikWeb);
        web.loadUrl("http://www.bilisimkalesi.com/mobil/kitaplikSil.php?cihazID="+android_id+"&id="+id);
        oku=1;
        web.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if(oku==1) {
                    try {
                       // System.out.println("TIKLANDI2");

                        List<kitaplikSablon> kitapListesi = new kitaplikVeriOku(android_id).execute().get();
                        kitaplikyazdir(kitapListesi);
                    }
                    catch (Exception e)
                    {
                       // System.out.println("HATA");

                    }
                    oku=0;
                }
            }
        });
    }




    public void kitapSilOnay(View view)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        new kitapSilmeIslemi().execute();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(kitaplik.this);
        builder.setMessage("Seçtiğiniz kitabı silmek istediğinize emin misiniz?").setPositiveButton("Evet", dialogClickListener)
                .setNegativeButton("Hayır", dialogClickListener).show();


    }


    public class Utils{

        public void showToast(Context mContext, String message){
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    boolean cikis=false;

    boolean kitaplikAna = true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(kitaplikAna) {
                menu.menuNo=0;
                startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
            }
            else
                kitapDetayKatmanKapat();

            /*
            Intent anaAktivite = new Intent(this,MainActivity.class);
           // startActivity(anaAktivite);
            cikis=true;
            dwn=null;
            anaAktivite.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            anaAktivite.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(anaAktivite);
            overridePendingTransition(0, 0);
*/
            return true;
        }
        return false;
    }


    int m=0;
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;


        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
            m++;
           // System.out.println(m+". resim");
        }

        protected Bitmap doInBackground(String... urls) {



            if (cikis)
                return null;

           // System.out.println(m+". resim yükleniyor"+cikis);
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
               // InputStream in = new java.net.URL(urldisplay).openStream();

                java.net.URL url = new java.net.URL(urldisplay);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.setReadTimeout(2000);
                connection.connect();
                InputStream in = connection.getInputStream();



                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //  Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
           // System.out.println(m+". resim yüklendi");





            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);


        }


    }




    private class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;


        public DownloadImageTask2(ImageView bmImage) {
            this.bmImage = bmImage;
            m++;
            // System.out.println(m+". resim");
        }

        protected Bitmap doInBackground(String... urls) {



            if (cikis)
                return null;

            // System.out.println(m+". resim yükleniyor"+cikis);
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                // InputStream in = new java.net.URL(urldisplay).openStream();

                java.net.URL url = new java.net.URL(urldisplay);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.setReadTimeout(2000);
                connection.connect();
                InputStream in = connection.getInputStream();



                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //  Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            // System.out.println(m+". resim yüklendi");





            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            RelativeLayout resimKatman = (RelativeLayout)findViewById(R.id.resimKatman);
            Drawable resim = new BitmapDrawable(myblur(result));
            resimKatman.setBackground(resim);


        }


    }



    public Bitmap myblur(Bitmap image) {
        final float BITMAP_SCALE = 0.2f;
        final float BLUR_RADIUS = 8.5f;
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        RenderScript rs = RenderScript.create(this);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }



     String aciklamaAl(String kitaplikVeriAdres)
    {
        String AciklamaAdres="";
         String Aciklama="";
        System.out.println("BU DA ADRES :" + kitaplikVeriAdres);
        if(kitaplikVeriAdres.indexOf("http")>=0) {
            try {
                Document doc;

                doc = Jsoup.connect(kitaplikVeriAdres).timeout(3000).userAgent("Mozilla").validateTLSCertificates(false).get();

                //  System.out.println("ADRES:"+kitaplikVeriAdres);
                Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                AciklamaAdres = kitaplikVeriAdres;
                if (AciklamaAdres.contains("idefix.com")) {

                    elements = doc.select("section[class=page-productDetails] div[class=container] div[class=product-description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        Aciklama = data;

                        break;

                    }
                } else if (AciklamaAdres.contains("dr.com")) {

                    elements = doc.select("div[class=container] div[class=summary]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("hepsiburada.com")) {

                    elements = doc.select("div[id=productDescriptionContent]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("kitapyurdu.com")) {

                    elements = doc.select("div[class=pr_description] span[class=info__text]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("pandora.com")) {

                    elements = doc.select("div[class=row urunAciklama]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("bkmkitap.com")) {

                    elements = doc.select("div[id=productDetailTab]");  // class ismi post-content olan verileri çekmek için
                    elements.select("h4").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {
                        String data = baslik.text();
                        // int yer=0;
                        //yer=data.indexOf("Kısa Özet");
                        //yer=yer+20;

                        // AciklamaBilgisi = data.substring(yer,data.length()-1);
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("kitapsec.com")) {
                    elements = doc.select("div[class=TabDivList] div[id=tab1] p");  // class ismi post-content olan verileri çekmek için
                    elements.select("h2").remove();
                    elements.select("magaza-bilgi").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("kidega.com")) {
                    elements = doc.select("div[class=col-md-8 col-xs-12] div[id=bookDescription]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("ucuzkitapal.com")) {
                    elements = doc.select("div[id=content_description]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("amazon.com")) {
                    elements = doc.select("div[id=bookDescription_feature_div]");  // class ismi post-content olan verileri çekmek için
                    elements.select("div[id=psPlaceHolder]").remove();
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("kitapkolik.com")) {
                    elements = doc.select("div[id=divOnyazi]");  // class ismi post-content olan verileri çekmek için
                    for (org.jsoup.nodes.Element baslik : elements) {

                        String data = baslik.text();
                        Aciklama = data;
                        break;
                    }
                } else if (AciklamaAdres.contains("1000kitap.com")) {
                    // elements = doc.select("div[class=oge metin]");  // class ismi post-content olan verileri çekmek için
                    //  AciklamaBilgisi=elements.get(0).text();

                    elements = doc.select("div[class=css-1dbjc4n] span[class=css-901oao css-16my406]");  // class ismi post-content olan verileri çekmek için
                    Aciklama = elements.get(0).text();

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
*/

                }

                // AciklamaY=AciklamaBilgisi.substring(0,200).replaceAll("\"","");


                //  System.out.println("YAZDIR:   "+AciklamaY);


            } catch (Exception e) {

            }
        }
        else
            Aciklama = kitaplikVeriAdres;


        return Aciklama;

    }







    int kitaplikYuklenenResimSayisi=0;
    void kitaplikkaydirmaDurum(final ScrollView kaydirmaKutusu, final LinearLayout eleman, final int a)
    {


        System.out.println("KİTAPLIK KAYDIRILDI: ");


        kaydirmaKutusu.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                try {
                    int cubuk = kaydirmaKutusu.getScrollY();
                    int resimSayi = (a/3 + cubuk / (yukseklik/(a/3)))*3;
                    System.out.println("RESİM SAYISI: "+resimSayi);
                    System.out.println("ÇUBUK : "+cubuk);
                    System.out.println("YÜKLENEN : "+kitaplikYuklenenResimSayisi);


                    if (kitaplikYuklenenResimSayisi < kitapDetayBilgiler.size()) {
                        for (int i = kitaplikYuklenenResimSayisi; i <= resimSayi; i++) {

                           // ImageView resim = (ImageView) eleman.getChildAt(i).findViewById(R.id.resim);
                           // new DownloadImageTask(resim).execute(fotolar[i]);


                            System.out.println("NUMARA : "+i);


                            ImageView kitaplikResimSol = (ImageView) eleman.getChildAt(i/3).findViewById(R.id.kitaplikResimSol2);
                            ImageView kitaplikResimSag = (ImageView) eleman.getChildAt(i/3).findViewById(R.id.kitaplikResimSag2);
                            ImageView kitaplikResimOrta = (ImageView) eleman.getChildAt(i/3).findViewById(R.id.kitaplikResimOrta2);


                            Display display = getWindowManager().getDefaultDisplay();
                            final int ekranGenislik = display.getWidth();

                            int en=Math.round((ekranGenislik-Math.round(ekranGenislik/9))/3);
                            int boy = Math.round((en*190)/120);

                            kitaplikResimSol.getLayoutParams().width = en;
                            kitaplikResimSag.getLayoutParams().width = en;
                            kitaplikResimOrta.getLayoutParams().width = en;

                            kitaplikResimSol.getLayoutParams().height = boy;
                            kitaplikResimSag.getLayoutParams().height = boy;
                            kitaplikResimOrta.getLayoutParams().height = boy;


                            if(i<kitapDetayBilgiler.size()) {

                                final int icX1=i;
                                kitaplikResimSol.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        kitapDetayKatmanAc(icX1);
                                    }

                                });

                                dwn = new DownloadImageTask(kitaplikResimSol);
                                dwn.execute(kitapDetayBilgiler.get(i).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                                i++;
                                kitaplikYuklenenResimSayisi++;
                            }
                            else
                            {
                                kitaplikResimSol.setImageDrawable(null);
                            }
                            if(i<kitapDetayBilgiler.size()) {
                                final int icX2 = i;
                                kitaplikResimOrta.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        kitapDetayKatmanAc(icX2);
                                    }

                                });


                                dwn = new DownloadImageTask(kitaplikResimOrta);
                                dwn.execute(kitapDetayBilgiler.get(i).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                                i++;
                                kitaplikYuklenenResimSayisi++;
                            }
                            else
                            {
                                kitaplikResimOrta.setImageDrawable(null);
                            }

                            if(i<kitapDetayBilgiler.size()) {
                                final int icX3 = i;

                                kitaplikResimSag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        kitapDetayKatmanAc(icX3);
                                    }

                                });

                                dwn = new DownloadImageTask(kitaplikResimSag);
                                dwn.execute(kitapDetayBilgiler.get(i).ResimAdresY.replaceAll("1000kitap.com/k/resimler/kitaplar", "1000kitap.com/resimler/kitaplar"));
                                i++;
                                kitaplikYuklenenResimSayisi++;
                            }
                            else
                            {
                                kitaplikResimSag.setImageDrawable(null);
                            }








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




public void kitaplikToAnasayfa(View view)
{
    cikis=true;
    dwn=null;
    menu.menuNo=1;
    startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
}


    public void kitaplikToBarkod(View view)
    {
        menu.menuNo=3;
        startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
    }

    public void kitaplikToSepet(View view)
    {
        menu.menuNo=4;
        startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
    }



        String okumaButonAdres;
        Boolean okumaButonDurum;

     private class okumaButonTiklama extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(kitaplik.this);
            // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Kaydediliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InputStream is = null;
                URL url = new URL(okumaButonAdres);
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
                    final ImageView okudumButon = (ImageView)findViewById(R.id.imageView8);
                    final ImageView okumadimButon = (ImageView)findViewById(R.id.imageView9);

                    if(okumaButonDurum)
                    {
                        okudumButon.setImageResource(R.drawable.okudumsecildi);
                        okumadimButon.setImageResource(R.drawable.okumadim);
                    }
                    else
                    {
                        okudumButon.setImageResource(R.drawable.okudum);
                        okumadimButon.setImageResource(R.drawable.okumadimsecildi);
                    }



                }
                if (is != null) is.close();
            }
            catch (Exception e)
            {}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();

        }
    }





    String silinecekKitapAdres;

    private class kitapSilmeIslemi extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(kitaplik.this);
            // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Siliniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InputStream is = null;
                URL url = new URL(silinecekKitapAdres);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(20000);       // if you want to set the Read timeout
                conn.setConnectTimeout(30000);    // if you want to set the Connect timeout
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    try {
                        System.out.println("UZUNLUK"  +   kitapDetayBilgiler.size());
                       //kitapListesi.remove(silinecekKitapNumara);

                        kitapListesi = listeSil(silinecekKitapNumara,kitapListesi);

                        System.out.println("UZUNLUK"  +   kitapDetayBilgiler.size());


                            //List<kitaplikSablon> kitapListesi = new kitaplikVeriOku(android_id).execute().get();
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if(acikBolum == 0)

                                    kitaplikyazdir(kitapListesi);

                                else
                                {
                                    listeAyarla(String.valueOf(acikBolum));
                                }
                            }
                        });






                       // kitapListesi =  kitapDetayBilgiler;
                        //kitaplikyazdir(kitapDetayBilgiler);
                    }
                    catch (Exception e)
                    {
                         System.out.println("HATA" + e.getMessage());

                    }


                }
                if (is != null) is.close();
            }
            catch (Exception e)
            {}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            kitapDetayKatmanKapat();
            progressDialog.dismiss();

        }
    }





















    String aciklamaAlinacakAdres;
    private class bekleme extends AsyncTask<Void, Void, Void> {
        String AciklamaAdres="";
        String Aciklama="";
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(kitaplik.this);
            // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Bilgiler alınıyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            System.out.println("BU DA ADRES :" + aciklamaAlinacakAdres);
            if(aciklamaAlinacakAdres.indexOf("http")>=0) {
                try {
                    Document doc;

                    doc = Jsoup.connect(aciklamaAlinacakAdres).timeout(3000).userAgent("Mozilla").validateTLSCertificates(false).get();

                    //  System.out.println("ADRES:"+kitaplikVeriAdres);
                    Elements elements = doc.select("div[class=container] div[class=list-cell] div[class=details] a[class=item-name] h3");  // class ismi post-content olan verileri çekmek için

                    AciklamaAdres = aciklamaAlinacakAdres;
                    if (AciklamaAdres.contains("idefix.com")) {

                        elements = doc.select("section[class=page-productDetails] div[class=container] div[class=product-description]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            Aciklama = data;

                            break;

                        }
                    } else if (AciklamaAdres.contains("dr.com")) {
                        elements = doc.select("div[class=product-description-header section-cover mb-40 fs-3_5]");  // class ismi post-content olan verileri çekmek için
                        elements.select("h2").remove();

                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("hepsiburada.com")) {

                        elements = doc.select("div[id=productDescriptionContent]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("kitapyurdu.com")) {

                        elements = doc.select("div[class=pr_description] span[class=info__text]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("pandora.com")) {

                        elements = doc.select("div[class=row urunAciklama]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("bkmkitap.com")) {

                        elements = doc.select("div[id=productDetailTab]");  // class ismi post-content olan verileri çekmek için
                        elements.select("h4").remove();

                        for (org.jsoup.nodes.Element baslik : elements) {
                            String data = baslik.text();
                            // int yer=0;
                            //yer=data.indexOf("Kısa Özet");
                            //yer=yer+20;

                            // AciklamaBilgisi = data.substring(yer,data.length()-1);
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("kitapsec.com")) {
                        elements = doc.select("div[class=TabDivList] div[id=tab1]");  // class ismi post-content olan verileri çekmek için
                        elements.select("h2").remove();
                        elements.select("strong").remove();
                        elements.select("div[class=magaza-bilgi]").remove();
                        for (org.jsoup.nodes.Element baslik : elements) {

                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("kidega.com")) {
                        elements = doc.select("div[class=col-md-8 col-xs-12] div[id=bookDescription]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {

                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("ucuzkitapal.com")) {
                        elements = doc.select("div[id=content_description]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {

                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("amazon.com")) {
                        elements = doc.select("div[id=bookDescription_feature_div]");  // class ismi post-content olan verileri çekmek için
                        elements.select("div[id=psPlaceHolder]").remove();
                        for (org.jsoup.nodes.Element baslik : elements) {

                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("kitapkolik.com")) {
                        elements = doc.select("div[id=divOnyazi]");  // class ismi post-content olan verileri çekmek için
                        for (org.jsoup.nodes.Element baslik : elements) {

                            String data = baslik.text();
                            Aciklama = data;
                            break;
                        }
                    } else if (AciklamaAdres.contains("1000kitap.com")) {
                        // elements = doc.select("div[class=oge metin]");  // class ismi post-content olan verileri çekmek için
                        //  AciklamaBilgisi=elements.get(0).text();

                        elements = doc.select("div[class=css-1dbjc4n] span[class=css-901oao css-16my406]");  // class ismi post-content olan verileri çekmek için
                        Aciklama = elements.get(0).text();


                    }

                } catch (Exception e) {

                }
            }
            else {
                Aciklama = aciklamaAlinacakAdres;
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            TextView kitapDetayAciklama = (TextView)findViewById(R.id.kitapdetayAciklama);

            if((Aciklama=="")||(Aciklama.length()<10))
            {
                Aciklama = "Bu kitap ile ilgili açıklama bulunamadı.";
            }
            kitapDetayAciklama.setText(Aciklama);

            ImageView kitapSilButon = (ImageView)findViewById(R.id.kitapSilButon);
            kitapSilButon.setVisibility(View.VISIBLE);
            kitapSilButon.setEnabled(true);


            ImageView kitapAraButon = (ImageView)findViewById(R.id.kitapAraButon);
            kitapAraButon.setVisibility(View.VISIBLE);
            kitapAraButon.setEnabled(true);


            progressDialog.dismiss();
        }
    }



    void okundu(View view)
    {
        try {
            InputStream is = null;
            URL url = new URL("http://my_url/index.php?par1=val1&par2=val2");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000);       // if you want to set the Read timeout
            conn.setConnectTimeout(30000);    // if you want to set the Connect timeout
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                Reader reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[10240];
                int len = reader.read(buffer);

            }
            if (is != null) is.close();
        }
        catch (Exception e)
        {}
    }

    void okunmadi(View view)
    {

    }




    int acikBolum=0;
     public void hepsiGoster(View view)
    {
        ImageView cizgi1 = (ImageView)findViewById(R.id.cizgi1);
        ImageView cizgi2 = (ImageView)findViewById(R.id.cizgi2);
        ImageView cizgi3 = (ImageView)findViewById(R.id.cizgi3);


        TextView ustMenu1 = (TextView)findViewById(R.id.ustMenu1);
        TextView ustMenu2 = (TextView)findViewById(R.id.ustMenu2);
        TextView ustMenu3 = (TextView)findViewById(R.id.ustMenu3);


        cizgi1.setImageResource(R.drawable.kalin);
        cizgi2.setImageResource(R.drawable.ince);
        cizgi3.setImageResource(R.drawable.ince);

        ustMenu1.setTypeface(null, Typeface.BOLD);
        ustMenu1.setTextColor(Color.parseColor("#2B2A2A"));

        ustMenu2.setTypeface(null, Typeface.NORMAL);
        ustMenu3.setTextColor(Color.parseColor("#4E4D4D"));

        ustMenu3.setTypeface(null, Typeface.NORMAL);
        ustMenu3.setTextColor(Color.parseColor("#4E4D4D"));
        acikBolum=0;
        kitaplikyazdir(kitapListesi);
    }


    public void okunanGoster(View view)
    {
        ImageView cizgi1 = (ImageView)findViewById(R.id.cizgi1);
        ImageView cizgi2 = (ImageView)findViewById(R.id.cizgi2);
        ImageView cizgi3 = (ImageView)findViewById(R.id.cizgi3);


        TextView ustMenu1 = (TextView)findViewById(R.id.ustMenu1);
        TextView ustMenu2 = (TextView)findViewById(R.id.ustMenu2);
        TextView ustMenu3 = (TextView)findViewById(R.id.ustMenu3);


        cizgi1.setImageResource(R.drawable.ince);
        cizgi2.setImageResource(R.drawable.kalin);
        cizgi3.setImageResource(R.drawable.ince);

        ustMenu1.setTypeface(null, Typeface.NORMAL);
        ustMenu1.setTextColor(Color.parseColor("#4E4D4D"));

        ustMenu2.setTypeface(null, Typeface.BOLD);
        ustMenu2.setTextColor(Color.parseColor("#2B2A2A"));

        ustMenu3.setTypeface(null, Typeface.NORMAL);
        ustMenu3.setTextColor(Color.parseColor("#4E4D4D"));
        acikBolum = 1;
        listeAyarla("1");
    }



    //YAZDIRMA İŞLEMİ KİTAPLİSTESİ İSİMLİ DEĞİŞKENDEN YAPILIYO. BUNUN YERİNE BİR ARA DİZİ OLUŞTURULUP BURADAN YAZDIRMA YAPILABİLİR.




    public void okunmayanGoster(View view)
    {
        ImageView cizgi1 = (ImageView)findViewById(R.id.cizgi1);
        ImageView cizgi2 = (ImageView)findViewById(R.id.cizgi2);
        ImageView cizgi3 = (ImageView)findViewById(R.id.cizgi3);


        TextView ustMenu1 = (TextView)findViewById(R.id.ustMenu1);
        TextView ustMenu2 = (TextView)findViewById(R.id.ustMenu2);
        TextView ustMenu3 = (TextView)findViewById(R.id.ustMenu3);


        cizgi1.setImageResource(R.drawable.ince);
        cizgi2.setImageResource(R.drawable.ince);
        cizgi3.setImageResource(R.drawable.kalin);

        ustMenu1.setTypeface(null, Typeface.NORMAL);
        ustMenu1.setTextColor(Color.parseColor("#4E4D4D"));

        ustMenu2.setTypeface(null, Typeface.NORMAL);
        ustMenu2.setTextColor(Color.parseColor("#4E4D4D"));

        ustMenu3.setTypeface(null, Typeface.BOLD);
        ustMenu3.setTextColor(Color.parseColor("#2B2A2A"));
        acikBolum = 2;
        listeAyarla("2");
    }


    void listeAyarla(String deger)
    {

        List<kitaplikSablon> yeniListe=new ArrayList<>();

        for(int i=0;i<kitapListesi.size();i++)
        {
            System.out.println(kitapListesi.get(i).KitapAdY);
            if(kitapListesi.get(i).KitapDurumY.equals(deger))
            {
                yeniListe.add(kitapListesi.get(i));
            }
        }
        System.out.println("SAYI SAYILAR : " + kitapListesi.size());
        kitaplikyazdir(yeniListe);


    }


    void listeAyarla2(String deger, List<kitaplikSablon> eskiListe)
    {

        List<kitaplikSablon> yeniListe=new ArrayList<>();

        for(int i=0;i<eskiListe.size();i++)
        {
            System.out.println(eskiListe.get(i).KitapAdY);
            if(eskiListe.get(i).KitapDurumY.equals(deger))
            {
                yeniListe.add(eskiListe.get(i));
            }
        }
      //  System.out.println("SAYI SAYILAR : " + kitapListesi.size());
        kitaplikyazdir(yeniListe);


    }

    List<kitaplikSablon> listeSil(String kitap_id,List<kitaplikSablon> eskiListe)
    {

        List<kitaplikSablon> yeniListe=new ArrayList<>();

        for(int i=0;i<eskiListe.size();i++)
        {
            if(eskiListe.get(i).Kitapid.equals(kitap_id))
                continue;

          yeniListe.add(eskiListe.get(i));

        }


        return yeniListe;

    }



}