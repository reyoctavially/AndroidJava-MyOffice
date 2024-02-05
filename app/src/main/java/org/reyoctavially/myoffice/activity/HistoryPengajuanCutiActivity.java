package org.reyoctavially.myoffice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.adapter.PengajuanCutiAdapter;
import org.reyoctavially.myoffice.model.Pengajuancuti;
import org.reyoctavially.myoffice.response.ResponsePengajuanCuti;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPengajuanCutiActivity extends AppCompatActivity {

    private RecyclerView rvPengajuan;
    private Context mContext;
    private List<Pengajuancuti> pengajuancutis = new ArrayList<>();
    private PengajuanCutiAdapter adapterPengajuan;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private String kode_pegawai;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pengajuan_cuti);
        rvPengajuan = findViewById(R.id.rv_pengajuancuti);
        progressBar = findViewById(R.id.progressBar_historyPengajuan);
        floatingActionButton = findViewById(R.id.fab_pengajuancuti);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(mContext);
        kode_pegawai = sharedPrefManager.getSPKode();
        adapterPengajuan = new PengajuanCutiAdapter(pengajuancutis, mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvPengajuan.setLayoutManager(mLayoutManager);
        rvPengajuan.setItemAnimator(new DefaultItemAnimator());

        getResultPengajuan(kode_pegawai);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryPengajuanCutiActivity.this, FormPengajuanCutiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getResultPengajuan(String kode_pegawai){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getPengajuanCuti(kode_pegawai).enqueue(new Callback<ResponsePengajuanCuti>() {
            @Override
            public void onResponse(Call<ResponsePengajuanCuti> call, Response<ResponsePengajuanCuti> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    final List<Pengajuancuti> pengajuancutis = response.body().getPengajuancuti();

                    rvPengajuan.setAdapter(new PengajuanCutiAdapter(pengajuancutis, mContext));
                    adapterPengajuan.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal mengambil data pengajuan cuti", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengajuanCuti> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "Anda belum melakukan pengajuan cuti", Toast.LENGTH_LONG).show();
            }
        });
    }
}