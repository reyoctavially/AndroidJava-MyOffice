package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPengajuanCutiActivity extends AppCompatActivity {

    private EditText alasanCuti, tglMulaiCuti, tglSelesaiCuti;
    private Button btnAjukanCuti;
    private ProgressBar progressBar;

    Context mContext;
    BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengajuan_cuti);

        progressBar = findViewById(R.id.progressBar_pengajuan);
        alasanCuti = findViewById(R.id.et_alasan_pengajuan);
        tglMulaiCuti = findViewById(R.id.et_tgl_mulai);
        tglSelesaiCuti = findViewById(R.id.et_tgl_selesai);
        btnAjukanCuti = findViewById(R.id.btn_pengajuan);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateMulai();
            }
        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateSelesai();
            }
        };

        tglMulaiCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormPengajuanCutiActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tglSelesaiCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FormPengajuanCutiActivity.this, date2, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnAjukanCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("Pengajuan Cuti").setMessage("Apakah anda yakin akan mengajukan cuti?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressBar.setVisibility(View.VISIBLE);
                                requestAjukanCuti();
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

    private void updateMulai() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tglMulaiCuti.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateSelesai() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tglSelesaiCuti.setText(sdf.format(myCalendar.getTime()));
    }

    private void requestAjukanCuti(){
        String kode_pegawai = sharedPrefManager.getSPKode();
        String nip_kasi = sharedPrefManager.getSPNip();
        mApiService.addPengajuanCuti(alasanCuti.getText().toString(),
                tglMulaiCuti.getText().toString(),
                tglSelesaiCuti.getText().toString(),
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
                                    Toast.makeText(mContext, "Berhasil mengajukan cuti", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(FormPengajuanCutiActivity.this, HistoryPengajuanCutiActivity.class);
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