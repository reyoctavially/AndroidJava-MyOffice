package org.reyoctavially.myoffice.response;

import com.google.gson.annotations.SerializedName;

import org.reyoctavially.myoffice.model.Cuti;
import org.reyoctavially.myoffice.model.Kesehatan;

import java.util.List;

public class ResponseKesehatan {
    @SerializedName("data")
    private List<Kesehatan> kesehatan;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Kesehatan> getKesehatan(){
        return kesehatan;
    }

    public void setKesehatan(List<Kesehatan> kesehatan){
        this.kesehatan = kesehatan;
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
                "kesehatan/{kode_pegawai}=" + kesehatan +
                ", error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
