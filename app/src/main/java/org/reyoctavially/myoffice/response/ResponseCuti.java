package org.reyoctavially.myoffice.response;

import com.google.gson.annotations.SerializedName;

import org.reyoctavially.myoffice.model.Cuti;

import java.util.List;

public class ResponseCuti {
    @SerializedName("data")
    private List<Cuti> cuti;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Cuti> getCuti(){
        return cuti;
    }

    public void setCuti(List<Cuti> cuti){
        this.cuti = cuti;
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
                "cuti/{kode_pegawai} = '" + cuti + '\'' +
                ", error = '" + error + '\'' +
                ", message = '" + message + '\'' +
                "}";
    }
}
