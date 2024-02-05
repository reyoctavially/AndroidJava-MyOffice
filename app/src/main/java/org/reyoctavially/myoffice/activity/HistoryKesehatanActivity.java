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
import org.reyoctavially.myoffice.adapter.KesehatanAdapter;
import org.reyoctavially.myoffice.model.Kesehatan;
import org.reyoctavially.myoffice.response.ResponseKesehatan;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryKesehatanActivity extends AppCompatActivity {

    private RecyclerView rvKesehatan;
    private Context mContext;
    private List<Kesehatan> kesehatans = new ArrayList<>();
    private KesehatanAdapter adapterKesehatan;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private String kode_pegawai;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_kesehatan);

        rvKesehatan = findViewById(R.id.rv_kesehatan);
        progressBar = findViewById(R.id.progressBar_historyKesehatan);
        floatingActionButton = findViewById(R.id.fab_kesehatan);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(mContext);
        kode_pegawai = sharedPrefManager.getSPKode();
        adapterKesehatan = new KesehatanAdapter(kesehatans, mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvKesehatan.setLayoutManager(mLayoutManager);
        rvKesehatan.setItemAnimator(new DefaultItemAnimator());

        getResultKesehatan(kode_pegawai);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryKesehatanActivity.this, FormKesehatanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getResultKesehatan(String kode_pegawai){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getKesehatan(kode_pegawai).enqueue(new Callback<ResponseKesehatan>() {
            @Override
            public void onResponse(Call<ResponseKesehatan> call, Response<ResponseKesehatan> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    final List<Kesehatan> kesehatans = response.body().getKesehatan();

                    rvKesehatan.setAdapter(new KesehatanAdapter(kesehatans, mContext));
                    adapterKesehatan.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Gagal mengambil data kesehatan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKesehatan> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, "Anda belum mengisi data kesehatan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}