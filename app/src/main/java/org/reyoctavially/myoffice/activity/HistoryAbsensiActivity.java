package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.adapter.HistoryAbsensiAdapter;
import org.reyoctavially.myoffice.model.Absensi;
import org.reyoctavially.myoffice.response.ResponseAbsensi;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAbsensiActivity extends AppCompatActivity {
    private RecyclerView rvAbsen;
    private Context mContext;
    private List<Absensi> absensis = new ArrayList<>();
    private HistoryAbsensiAdapter adapterAbsen;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private String kode_pegawai;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_absensi);
        rvAbsen = findViewById(R.id.rv_absensi);
        progressBar = findViewById(R.id.progressBar_historyAbsensi);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        kode_pegawai = sharedPrefManager.getSPKode();
        adapterAbsen = new HistoryAbsensiAdapter(absensis, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvAbsen.setLayoutManager(mLayoutManager);
        rvAbsen.setItemAnimator(new DefaultItemAnimator());

        getResultAbsen(kode_pegawai);
    }

    private void getResultAbsen(String kode_pegawai){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getAbsensi(kode_pegawai).enqueue(new Callback<ResponseAbsensi>() {
            @Override
            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    final List<Absensi> absensis = response.body().getAbsensi();

                    rvAbsen.setAdapter(new HistoryAbsensiAdapter(absensis, mContext));
                    adapterAbsen.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal mengambil data absensi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "Anda belum melakukan absensi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}