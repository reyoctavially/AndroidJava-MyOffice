package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class FormKesehatanActivity extends AppCompatActivity{

    private EditText etSuhuTubuh;
    private RadioGroup radioPekerjaanGroup, radioSwabGroup, radioVaksinGroup;
    private RadioButton radioPekerjaanButton, radioSwabButton, radioVaksinButton;
    private Button btnSimpanKesehatan;
    private ProgressBar progressBar;

    private int selectedPekerjaan, selectedVaksin, selectedSwab;

    Context mContext;
    BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kesehatan);

        progressBar = findViewById(R.id.progressBar_kesehatan);
        etSuhuTubuh = findViewById(R.id.et_suhu_tubuh);
        radioPekerjaanGroup = findViewById(R.id.rg_pekerjaan);
        radioSwabGroup = findViewById(R.id.rg_swab);
        radioVaksinGroup = findViewById(R.id.rg_vaksinasi);
        btnSimpanKesehatan = findViewById(R.id.btn_kesehatan);

        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnSimpanKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("Data Kesehatan").setMessage("Apakah anda yakin akan menyimpan data?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressBar.setVisibility(View.VISIBLE);
                                requestSimpanKesehatan();
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void requestSimpanKesehatan(){
        selectedPekerjaan = radioPekerjaanGroup.getCheckedRadioButtonId();
        selectedSwab = radioSwabGroup.getCheckedRadioButtonId();
        selectedVaksin = radioVaksinGroup.getCheckedRadioButtonId();
        radioPekerjaanButton = findViewById(selectedPekerjaan);
        radioSwabButton = findViewById(selectedSwab);
        radioVaksinButton = findViewById(selectedVaksin);
        String kode_pegawai = sharedPrefManager.getSPKode();
        String nip_kasi = sharedPrefManager.getSPNip();
        mApiService.addKesehatan(radioPekerjaanButton.getText().toString(),
                etSuhuTubuh.getText().toString(),
                radioSwabButton.getText().toString(),
                radioVaksinButton.getText().toString(),
                kode_pegawai,
                nip_kasi)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            progressBar.setVisibility(View.GONE);
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Berhasil menyimpan data kesehatan", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(FormKesehatanActivity.this, HistoryKesehatanActivity.class);
                                    startActivity(intent);
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
                            Log.i("debug", "onResponse: GAGAL");
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_LONG).show();
                    }
                });
    }
}