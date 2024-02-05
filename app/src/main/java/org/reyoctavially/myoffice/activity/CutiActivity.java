package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.adapter.DataCutiAdapter;
import org.reyoctavially.myoffice.adapter.HistoryAbsensiAdapter;
import org.reyoctavially.myoffice.model.Absensi;
import org.reyoctavially.myoffice.model.Cuti;
import org.reyoctavially.myoffice.response.ResponseCuti;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CutiActivity extends AppCompatActivity {

    private RecyclerView rvCuti;
    private Context mContext;
    private List<Cuti> cutis = new ArrayList<>();
    private DataCutiAdapter adapterCuti;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private String kode_pegawai;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);
        rvCuti = findViewById(R.id.rv_cuti);
        progressBar = findViewById(R.id.progressBar_historyCuti);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        kode_pegawai = sharedPrefManager.getSPKode();
        adapterCuti = new DataCutiAdapter(cutis, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCuti.setLayoutManager(mLayoutManager);
        rvCuti.setItemAnimator(new DefaultItemAnimator());

        getResultCuti(kode_pegawai);
    }

    private void getResultCuti(String kode_pegawai){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getCuti(kode_pegawai).enqueue(new Callback<ResponseCuti>() {
            @Override
            public void onResponse(Call<ResponseCuti> call, Response<ResponseCuti> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    final List<Cuti> cutis = response.body().getCuti();

                    rvCuti.setAdapter(new DataCutiAdapter(cutis, mContext));
                    adapterCuti.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal mengambil data cuti", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCuti> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "Anda belum melakukan cuti", Toast.LENGTH_SHORT).show();
            }
        });
    }
}