package org.reyoctavially.myoffice.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Pengajuancuti implements Parcelable {

    @SerializedName("kode_pengajuan_cuti")
    private String kode_pengajuan_cuti;

    @SerializedName("alasan_pengajuan_cuti")
    private String alasan_pengajuan_cuti;

    @SerializedName("tgl_pengajuan_cuti")
    private String tgl_pengajuan_cuti;

    @SerializedName("tgl_mulai_cuti")
    private String tgl_mulai_cuti;

    @SerializedName("tgl_selesai_cuti")
    private String tgl_selesai_cuti;

    @SerializedName("status_pengajuan_cuti")
    private String status_pengajuan_cuti;

    @SerializedName("ket_pengajuan_cuti")
    private String ket_pengajuan_cuti;

    protected Pengajuancuti(Parcel in) {
        kode_pengajuan_cuti = in.readString();
        alasan_pengajuan_cuti = in.readString();
        tgl_pengajuan_cuti = in.readString();
        tgl_mulai_cuti = in.readString();
        tgl_selesai_cuti = in.readString();
        status_pengajuan_cuti = in.readString();
        ket_pengajuan_cuti = in.readString();
    }

    public static final Creator<Pengajuancuti> CREATOR = new Creator<Pengajuancuti>() {
        @Override
        public Pengajuancuti createFromParcel(Parcel in) {
            return new Pengajuancuti(in);
        }

        @Override
        public Pengajuancuti[] newArray(int size) {
            return new Pengajuancuti[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kode_pengajuan_cuti);
        dest.writeString(alasan_pengajuan_cuti);
        dest.writeString(tgl_pengajuan_cuti);
        dest.writeString(tgl_mulai_cuti);
        dest.writeString(tgl_selesai_cuti);
        dest.writeString(status_pengajuan_cuti);
        dest.writeString(ket_pengajuan_cuti);
    }

    public String getKode_pengajuan_cuti() {
        return kode_pengajuan_cuti;
    }

    public String getAlasan_pengajuan_cuti() {
        return alasan_pengajuan_cuti;
    }

    public String getTgl_pengajuan_cuti() {
        return tgl_pengajuan_cuti;
    }

    public String getTgl_mulai_cuti() {
        return tgl_mulai_cuti;
    }

    public String getTgl_selesai_cuti() {
        return tgl_selesai_cuti;
    }

    public String getStatus_pengajuan_cuti() {
        return status_pengajuan_cuti;
    }

    public String getKet_pengajuan_cuti() {
        return ket_pengajuan_cuti;
    }

    @Override
    public String toString() {
        return "data{" +
                "kode_pengajuan_cuti='" + kode_pengajuan_cuti + '\'' +
                ", alasan_pengajuan_cuti='" + alasan_pengajuan_cuti + '\'' +
                ", tgl_pengajuan_cuti='" + tgl_pengajuan_cuti + '\'' +
                ", tgl_mulai_cuti='" + tgl_mulai_cuti + '\'' +
                ", tgl_selesai_cuti='" + tgl_selesai_cuti + '\'' +
                ", status_pengajuan_cuti='" + status_pengajuan_cuti + '\'' +
                ", ket_pengajuan_cuti='" + ket_pengajuan_cuti + '\'' +
                '}';
    }
}
