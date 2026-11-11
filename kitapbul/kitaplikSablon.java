package com.mozosoft.zgr.kitapbul;


/**
 * Created by DELL on 27.11.2019.
 */

public class kitaplikSablon {
    public String KitapAdY, KitapYazarY, YayinEviY, AciklamaY, ResimAdresY, Kitapid, KitapDurumY, IsbnY;

    public kitaplikSablon(String KitapAdX, String KitapYazarX, String YayinEviX, String AciklamaX, String ResimAdresX, String id, String KitapDurumX, String IsbnX)
    {
        KitapAdY=KitapAdX;
        KitapYazarY=KitapYazarX;
        YayinEviY=YayinEviX;
        ResimAdresY=ResimAdresX;
        AciklamaY=AciklamaX;
        Kitapid=id;
        KitapDurumY=KitapDurumX;
        IsbnY = IsbnX;

    }
}
