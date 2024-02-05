package org.reyoctavially.myoffice.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_MYOFFICE_APP = "spMyofficeApp";

    public static final String SP_KODE = "spKode";
    public static final String SP_NAMA = "spNama";
    public static final String SP_FOTO = "spFoto";
    public static final String SP_JABATAN = "spJabatan";
    public static final String SP_TELP = "spTelp";
    public static final String SP_JALAN = "spJalan";
    public static final String SP_RUMAH = "spRumah";
    public static final String SP_RT = "spRt";
    public static final String SP_RW = "spRw";
    public static final String SP_KEC = "spKec";
    public static final String SP_KOTA = "spKota";
    public static final String SP_POS = "spPos";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_STATUS = "spStatus";
    public static final String SP_NIP = "spNip";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_MYOFFICE_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPKode() {
        return sp.getString(SP_KODE, "");
    }

    public String getSPNama() {
        return sp.getString(SP_NAMA, "");
    }

    public String getSPFoto() {
        return sp.getString(SP_FOTO, "");
    }

    public String getSPJabatan() {
        return sp.getString(SP_JABATAN, "");
    }

    public String getSPTelp() {
        return sp.getString(SP_TELP, "");
    }

    public String getSPJalan() {
        return sp.getString(SP_JALAN, "");
    }

    public String getSPRumah() {
        return sp.getString(SP_RUMAH, "");
    }

    public String getSPRt() {
        return sp.getString(SP_RT, "");
    }

    public String getSPRw() {
        return sp.getString(SP_RW, "");
    }

    public String getSPKec() {
        return sp.getString(SP_KEC, "");
    }

    public String getSPKota() {
        return sp.getString(SP_KOTA, "");
    }

    public String getSPPos() {
        return sp.getString(SP_POS, "");
    }

    public String getSPEmail() {
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPPassword() {
        return sp.getString(SP_PASSWORD, "");
    }

    public String getSPStatus() {
        return sp.getString(SP_STATUS, "");
    }

    public String getSPNip() {
        return sp.getString(SP_NIP, "");
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
