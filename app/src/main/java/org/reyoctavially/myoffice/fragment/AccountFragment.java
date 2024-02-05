package org.reyoctavially.myoffice.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.activity.LoginActivity;
import org.reyoctavially.myoffice.util.SharedPrefManager;
import org.reyoctavially.myoffice.util.api.BaseApiServices;
import org.reyoctavially.myoffice.util.api.UtilsApi;

public class AccountFragment extends Fragment {

    private SharedPrefManager sharedPrefManager;
    private BaseApiServices mApiService;
    private ImageView iv_pegawai;
    private TextView tv_nama, tv_kode, tv_jabatan, tv_telp, tv_alamat, tv_status;
    private Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        iv_pegawai = root.findViewById(R.id.iv_account);
        tv_nama = root.findViewById(R.id.tv_nama_pegawai_akun);
        tv_kode = root.findViewById(R.id.tv_kode_pegawai_akun);
        tv_jabatan = root.findViewById(R.id.tv_jabatan_pegawai_akun);
        tv_telp = root.findViewById(R.id.tv_telp_akun);
        tv_alamat = root.findViewById(R.id.tv_alamat_akun);
        tv_status = root.findViewById(R.id.tv_status_akun);
        logout = root.findViewById(R.id.btnLogout);

        sharedPrefManager = new SharedPrefManager(getContext());
        mApiService = UtilsApi.getAPIService();

        tv_nama.setText(sharedPrefManager.getSPNama());
        tv_kode.setText(sharedPrefManager.getSPKode());
        tv_jabatan.setText(sharedPrefManager.getSPJabatan());
        tv_telp.setText(sharedPrefManager.getSPTelp());
        tv_alamat.setText(sharedPrefManager.getSPJalan() +
                ", No." + sharedPrefManager.getSPRumah() +
                " RT " + sharedPrefManager.getSPRt() +
                " RW " + sharedPrefManager.getSPRw() +
                ", Kec." + sharedPrefManager.getSPKec() +
                ", Kab/kota." + sharedPrefManager.getSPKota() +
                ", " + sharedPrefManager.getSPPos());
        tv_status.setText(sharedPrefManager.getSPStatus());

        String urlPhoto = "https://sisga.majalengkakab.go.id/assets/images/profile/"+ sharedPrefManager.getSPFoto();
        Picasso.get().load(urlPhoto).into(iv_pegawai);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setTitle("Logout").setMessage("Apakah anda yakin akan keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                startActivity(new Intent(getContext(), LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                getActivity().finish();
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
        return root;
    }
}