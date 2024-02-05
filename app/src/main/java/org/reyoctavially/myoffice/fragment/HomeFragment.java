package org.reyoctavially.myoffice.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;
import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.activity.CutiActivity;
import org.reyoctavially.myoffice.activity.FormKesehatanActivity;
import org.reyoctavially.myoffice.activity.FormPengajuanCutiActivity;
import org.reyoctavially.myoffice.activity.HistoryAbsensiActivity;
import org.reyoctavially.myoffice.activity.HistoryKesehatanActivity;
import org.reyoctavially.myoffice.activity.HistoryPengajuanCutiActivity;
import org.reyoctavially.myoffice.activity.MainActivity;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.checkSelfPermission;

public class HomeFragment extends Fragment {

    private ImageView absen_masuk, absen_keluar, riwayat_absensi;
    private View cuti, pengajuan_cuti, kesehatan;

    private TextView tv_nama, tv_kode, tv_jabatan;
    private TextView tv_location;

    private SharedPrefManager sharedPrefManager;
    private Context mContext;
    private BaseApiServices mApiService;
    private ProgressBar progressBar;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private double latitude, longitude;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        absen_masuk = root.findViewById(R.id.iv_absen_in_small);
        absen_keluar = root.findViewById(R.id.iv_absen_out_small);
        riwayat_absensi = root.findViewById(R.id.iv_absen_history_small);
        cuti = root.findViewById(R.id.view_cuti);
        pengajuan_cuti = root.findViewById(R.id.view_pengajuan_cuti);
        kesehatan = root.findViewById(R.id.view_kesehatan);
        progressBar = root.findViewById(R.id.progressBar_home);

        tv_nama = root.findViewById(R.id.tv_nama_pegawai);
        tv_kode = root.findViewById(R.id.tv_kode_pegawai);
        tv_jabatan = root.findViewById(R.id.tv_jabatan_pegawai);
        tv_location = root.findViewById(R.id.tv_location);

        sharedPrefManager = new SharedPrefManager(getContext());
        mApiService = UtilsApi.getAPIService();
        mContext = getContext();

        tv_nama.setText(sharedPrefManager.getSPNama());
        tv_kode.setText(sharedPrefManager.getSPKode());
        tv_jabatan.setText(sharedPrefManager.getSPJabatan());

        SharedPreferences editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String latitudeString = editor.getString("latitude", "0");
        String longitudeString = editor.getString("longtitude", "0");
        latitude = Double.valueOf(latitudeString);
        longitude = Double.valueOf(longitudeString);

        tv_location.setText("Latitude : " + latitudeString + " & Longitude : " + longitudeString);

        absen_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("Absen Masuk").setMessage("Apakah anda yakin akan melakukan absen masuk?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressBar.setVisibility(View.VISIBLE);
                                if (latitude <= -6.8048000 && latitude >= -6.8050000 && longitude >= 108.2600000 && longitude <= 108.3000000){
                                    absenMasuk();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(mContext, "Anda sedang berada diluar wilayah", Toast.LENGTH_SHORT).show();
                                }
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

        absen_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("Absen Keluar").setMessage("Apakah anda yakin akan melakukan absen keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                progressBar.setVisibility(View.VISIBLE);
                                if (latitude <= -6.8048000 && latitude >= -6.8050000 && longitude >= 108.2600000 && longitude <= 108.3000000){
                                absenKeluar();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(mContext, "Anda sedang berada diluar wilayah", Toast.LENGTH_SHORT).show();
                                }
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

        riwayat_absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryAbsensiActivity.class);
                startActivity(intent);
            }
        });

        cuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CutiActivity.class);
                startActivity(intent);
            }
        });

        pengajuan_cuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryPengajuanCutiActivity.class);
                startActivity(intent);
            }
        });

        kesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryKesehatanActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void absenMasuk(){
        String kode_pegawai = sharedPrefManager.getSPKode();
        String nip_kasi = sharedPrefManager.getSPNip();
            mApiService.absenMasuk(kode_pegawai,
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
                                        Toast.makeText(mContext, "Berhasil melakukan absen masuk", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String error_message = jsonRESULTS.getString("message");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.i("debug", "onResponse: GA BERHASIL");
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                            Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    });
    }

    private void absenKeluar(){
        String kode_pegawai = sharedPrefManager.getSPKode();
        mApiService.absenKeluar(kode_pegawai)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            progressBar.setVisibility(View.GONE);
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Berhasil melakukan absen keluar", Toast.LENGTH_SHORT).show();
                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}