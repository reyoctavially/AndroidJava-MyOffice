package org.reyoctavially.myoffice.response;

import com.google.gson.annotations.SerializedName;

import org.reyoctavially.myoffice.model.Pengajuancuti;

import java.util.List;

public class ResponsePengajuanCuti {
    @SerializedName("data")
    private List<Pengajuancuti> pengajuancuti;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Pengajuancuti> getPengajuancuti(){
        return pengajuancuti;
    }

    public void setPengajuancuti(List<Pengajuancuti> pengajuancuti){
        this.pengajuancuti = pengajuancuti;
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
                "pengajuancuti/{kode_pegawai} = '" + pengajuancuti + '\'' +
                ", error = '" + error + '\'' +
                ", message = '" + message + '\'' +
                "}";
    }
}
