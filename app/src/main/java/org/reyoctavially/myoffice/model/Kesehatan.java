package org.reyoctavially.myoffice.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Kesehatan implements Parcelable {
    @SerializedName("kode_kesehatan")
    private String kode_kesehatan;

    @SerializedName("tgl_input_kesehatan")
    private String tgl_input_kesehatan;

    @SerializedName("jenis_pekerjaan")
    private String jenis_pekerjaan;

    @SerializedName("suhu_tubuh_pegawai")
    private String suhu_tubuh_pegawai;

    @SerializedName("hasil_swab_pegawai")
    private String hasil_swab_pegawai;

    @SerializedName("status_vaksinasi_pegawai")
    private String status_vaksinasi_pegawai;

    protected Kesehatan(Parcel in) {
        kode_kesehatan = in.readString();
        tgl_input_kesehatan = in.readString();
        jenis_pekerjaan = in.readString();
        suhu_tubuh_pegawai = in.readString();
        hasil_swab_pegawai = in.readString();
        status_vaksinasi_pegawai = in.readString();
    }

    public static final Creator<Kesehatan> CREATOR = new Creator<Kesehatan>() {
        @Override
        public Kesehatan createFromParcel(Parcel in) {
            return new Kesehatan(in);
        }

        @Override
        public Kesehatan[] newArray(int size) {
            return new Kesehatan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kode_kesehatan);
        dest.writeString(tgl_input_kesehatan);
        dest.writeString(jenis_pekerjaan);
        dest.writeString(suhu_tubuh_pegawai);
        dest.writeString(hasil_swab_pegawai);
        dest.writeString(status_vaksinasi_pegawai);
    }

    public String getKode_kesehatan() {
        return kode_kesehatan;
    }

    public String getTgl_input_kesehatan() {
        return tgl_input_kesehatan;
    }

    public String getJenis_pekerjaan() {
        return jenis_pekerjaan;
    }

    public String getSuhu_tubuh_pegawai() {
        return suhu_tubuh_pegawai;
    }

    public String getHasil_swab_pegawai() {
        return hasil_swab_pegawai;
    }

    public String getStatus_vaksinasi_pegawai() {
        return status_vaksinasi_pegawai;
    }

    @Override
    public String toString() {
        return "data{" +
                "kode_kesehatan='" + kode_kesehatan + '\'' +
                ", tgl_input_kesehatan='" + tgl_input_kesehatan + '\'' +
                ", jenis_pekerjaan='" + jenis_pekerjaan + '\'' +
                ", suhu_tubuh_pegawai='" + suhu_tubuh_pegawai + '\'' +
                ", hasil_swab_pegawai='" + hasil_swab_pegawai + '\'' +
                ", status_vaksinasi_pegawai='" + status_vaksinasi_pegawai + '\'' +
                '}';
    }
}
