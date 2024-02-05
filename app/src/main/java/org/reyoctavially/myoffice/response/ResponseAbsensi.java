package org.reyoctavially.myoffice.response;

import com.google.gson.annotations.SerializedName;

import org.reyoctavially.myoffice.model.Absensi;

import java.util.List;

public class ResponseAbsensi {
    @SerializedName("data")
    private List<Absensi> absensi;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Absensi> getAbsensi(){
        return absensi;
    }

    public void setAbsensi(List<Absensi> absensi){
        this.absensi = absensi;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "data{" +
                "absensi/{kode_pegawai} = '" + absensi + '\'' +
                ", error = '" + error + '\'' +
                ", message = '" + message + '\'' +
                "}";
    }
}
