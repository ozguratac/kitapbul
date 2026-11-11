package com.mozosoft.zgr.kitapbul;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.mozosoft.zgr.kampanyam.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import androidx.annotation.RequiresApi;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class bildirimBasla extends BroadcastReceiver {
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    @Override
    public void onReceive( Context context, Intent intent)
    {


       // Toast.makeText(context, "Lütfen, internet bağlantınızı kontrol ediniz.", Toast.LENGTH_SHORT).show();


        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
           // Toast.makeText(context, "BAŞTAN AÇILDI", Toast.LENGTH_SHORT).show();

            Intent intent5 = new Intent(context, bildirimBasla.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent5, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);


        } else {
            // Cihaz açılmadıysa, her zamanki gibi bilgileri al
            new bilgiler().execute(context);
            //Toast.makeText(context, "NORMAL AÇILDI", Toast.LENGTH_SHORT).show();

        }

        System.out.println("CALISTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");


    }



    static  class bilgiler extends AsyncTask<Context, Void, Context> {
        String numara ="0";
        String baslik ="";
        String aciklama ="";

        @Override
        protected Context doInBackground(Context... params) {
            Document doc=null;
            try {
                doc = Jsoup.connect("http://bilisimkalesi.com/kitap/kitapBildirim77.php").timeout(30000).validateTLSCertificates(false).get();
                numara = doc.select("sporBilgi numara").get(0).text();
                baslik = doc.select("sporBilgi baslik").get(0).text();
                aciklama = doc.select("sporBilgi aciklama").get(0).text();

                //System.out.println(numara +" "+baslik+" "+aciklama);
                return params[0];
            }
            catch (Exception e)
            {
                //  Toast.makeText(context, "HATA VAR", Toast.LENGTH_SHORT).show();
                //   System.out.println(doc.text());
               // System.out.println("TESPİT EDİLEN HATA");
            }
            return params[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(Context context){

            try {

                SharedPreferences numaraOku = context.getSharedPreferences("progress", MODE_PRIVATE);
                int bildirimNumara = numaraOku.getInt("bildirimNumara", 0);


                int okunanNumara = Integer.parseInt(numara);

                if ((okunanNumara != bildirimNumara) && (okunanNumara != 0) && ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))) {
                    //WEB SAYFASINDAN OKUNAN BİLDİRİME AİT NUMARA MEVCUT NUMARADAN FARKLI İSE BİLDİRİM GÖSTERİLİR VE NUMARA DÜZENLENİR
                    Intent notificationIntent = new Intent(context, MainActivity.class);
                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    String CHANNEL_ID = "MYCHANNEL";
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);
                   // PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, notificationIntent, 0);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 1, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                    Notification notification = new Notification.Builder(context.getApplicationContext(), CHANNEL_ID)
                            .setContentText(aciklama)
                            .setContentTitle(baslik)
                            .setContentIntent(pendingIntent)
                            //  .addAction(android.R.drawable.sym_action_chat,"Title",pendingIntent)
                            .setChannelId(CHANNEL_ID)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.bulogo))
                            .setSmallIcon(R.drawable.bildirimicon)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setPriority(Notification.PRIORITY_MAX)
                            .setStyle(new Notification.BigTextStyle().bigText(aciklama))

                            .build();

                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify(1, notification);


                    //YENİ NUMARA SİSTEME KAYDEDİLDİ
                    bildirimNumara = okunanNumara;
                    SharedPreferences.Editor bildirimDuzenle = numaraOku.edit();
                    bildirimDuzenle.putInt("bildirimNumara", bildirimNumara);
                    bildirimDuzenle.apply();

                }

            }
            catch (Exception e)
            {

            }

        }
    }



}