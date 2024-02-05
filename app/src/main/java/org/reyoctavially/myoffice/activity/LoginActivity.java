package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etEmail, etPassword;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private Context mContext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progressBar_login);
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                requestLogin();
            }
        });
    }

    private void requestLogin(){
        mApiService.auth(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")){

                                    Toast.makeText(mContext, "Berhasil login", Toast.LENGTH_LONG).show();
                                    String kode = jsonRESULTS.getJSONObject("data").getString("kode_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_KODE, kode);
                                    String nama = jsonRESULTS.getJSONObject("data").getString("nama_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                    String foto = jsonRESULTS.getJSONObject("data").getString("foto_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_FOTO, foto);
                                    String jabatan = jsonRESULTS.getJSONObject("data").getString("jabatan_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_JABATAN, jabatan);
                                    String telp = jsonRESULTS.getJSONObject("data").getString("telp_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TELP, telp);
                                    String jalan = jsonRESULTS.getJSONObject("data").getString("jalan_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_JALAN, jalan);
                                    String rumah = jsonRESULTS.getJSONObject("data").getString("no_rumah_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_RUMAH, rumah);
                                    String rt = jsonRESULTS.getJSONObject("data").getString("rt_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_RT, rt);
                                    String rw = jsonRESULTS.getJSONObject("data").getString("rw_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_RW, rw);
                                    String kec = jsonRESULTS.getJSONObject("data").getString("kec_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_KEC, kec);
                                    String kota = jsonRESULTS.getJSONObject("data").getString("kota_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_KOTA, kota);
                                    String pos = jsonRESULTS.getJSONObject("data").getString("kode_pos_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_POS, pos);
                                    String email = jsonRESULTS.getJSONObject("data").getString("email_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                                    String password = jsonRESULTS.getJSONObject("data").getString("pass_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_PASSWORD, password);
                                    String status = jsonRESULTS.getJSONObject("data").getString("status_pegawai");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_STATUS, status);
                                    String nip_kasi = jsonRESULTS.getJSONObject("data").getString("nip_kasi");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NIP, nip_kasi);

                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    startActivity(new Intent(mContext, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                    System.out.println(sharedPrefManager.SP_NAMA);
                                } else {
                                    String message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}