package org.reyoctavially.myoffice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.model.Absensi;

import java.util.List;

public class HistoryAbsensiAdapter extends RecyclerView.Adapter<HistoryAbsensiAdapter.AbsensiHolder>{

    private List<Absensi> absensis;
    private Context context;

    public HistoryAbsensiAdapter(List<Absensi> absensis, Context context) {
        this.absensis = absensis;
        this.context = context;
    }

    @NonNull
    @Override
    public AbsensiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_absensi, parent, false);
        return new AbsensiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiHolder holder, int position) {
        final Absensi absensi = absensis.get(position);
        holder.tvTglAbsen.setText(absensi.getTanggal_absen());
        holder.tvJamMasuk.setText(": " + absensi.getJam_masuk());
        holder.tvJamKeluar.setText(": " + absensi.getJam_keluar());
    }

    @Override
    public int getItemCount() {
        if(absensis ==null) return 0;
        return absensis.size();
    }

    public class AbsensiHolder extends RecyclerView.ViewHolder {
        final TextView tvTglAbsen;
        final TextView tvJamMasuk;
        final TextView tvJamKeluar;
        public AbsensiHolder(@NonNull View itemView) {
            super(itemView);
            tvTglAbsen = itemView.findViewById(R.id.tv_tgl_absen);
            tvJamMasuk = itemView.findViewById(R.id.tv_jam_masuk);
            tvJamKeluar = itemView.findViewById(R.id.tv_jam_keluar);
        }
    }
}
