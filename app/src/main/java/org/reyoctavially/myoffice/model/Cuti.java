package org.reyoctavially.myoffice.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cuti implements Parcelable {

    @SerializedName("kode_cuti")
    private String kode_cuti;

    @SerializedName("jenis_cuti")
    private String jenis_cuti;

    @SerializedName("pemotongan_honor")
    private String pemotongan_honor;

    @SerializedName("tglmulaicuti")
    private String tglmulaicuti;

    @SerializedName("tglselesaicuti")
    private String tglselesaicuti;

    @SerializedName("statuscuti")
    private String statuscuti;

    protected Cuti(Parcel in) {
        kode_cuti = in.readString();
        jenis_cuti = in.readString();
        pemotongan_honor = in.readString();
        tglmulaicuti = in.readString();
        tglselesaicuti = in.readString();
        statuscuti = in.readString();
    }

    public static final Creator<Cuti> CREATOR = new Creator<Cuti>() {
        @Override
        public Cuti createFromParcel(Parcel in) {
            return new Cuti(in);
        }

        @Override
        public Cuti[] newArray(int size) {
            return new Cuti[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kode_cuti);
        dest.writeString(jenis_cuti);
        dest.writeString(pemotongan_honor);
        dest.writeString(tglmulaicuti);
        dest.writeString(tglselesaicuti);
        dest.writeString(statuscuti);
    }

    public String getKode_cuti() {
        return kode_cuti;
    }

    public String getJenis_cuti() {
        return jenis_cuti;
    }

    public String getPemotongan_honor() {
        return pemotongan_honor;
    }

    public String getTglmulaicuti() {
        return tglmulaicuti;
    }

    public String getTglselesaicuti() {
        return tglselesaicuti;
    }

    public String getStatuscuti() {
        return statuscuti;
    }

    @Override
    public String toString() {
        return "data{" +
                "kode_cuti='" + kode_cuti + '\'' +
                ", jenis_cuti='" + jenis_cuti + '\'' +
                ", pemotongan_honor='" + pemotongan_honor + '\'' +
                ", tglmulaicuti='" + tglmulaicuti + '\'' +
                ", tglselesaicuti='" + tglselesaicuti + '\'' +
                ", statuscuti='" + statuscuti + '\'' +
                '}';
    }
}
