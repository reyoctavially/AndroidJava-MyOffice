package org.reyoctavially.myoffice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.reyoctavially.myoffice.R;
import org.reyoctavially.myoffice.model.Cuti;
import org.reyoctavially.myoffice.model.Pengajuancuti;

import java.util.List;

public class PengajuanCutiAdapter extends RecyclerView.Adapter<PengajuanCutiAdapter.PengajuancutiHolder>{

    private List<Pengajuancuti> pengajuancutis;
    private Context context;
    private String status;

    public PengajuanCutiAdapter(List<Pengajuancuti> pengajuancutis, Context context) {
        this.pengajuancutis = pengajuancutis;
        this.context = context;
    }

    @NonNull
    @Override
    public PengajuancutiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pengajuan_cuti, parent, false);
        return new PengajuancutiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PengajuancutiHolder holder, int position) {
        final Pengajuancuti pengajuancuti = pengajuancutis.get(position);
        holder.tvAlasanPengajuan.setText(pengajuancuti.getAlasan_pengajuan_cuti());
        holder.tvTglPengajuan.setText(pengajuancuti.getTgl_pengajuan_cuti());
        holder.tvTglMulai.setText(": " + pengajuancuti.getTgl_mulai_cuti());
        holder.tvTglSelesai.setText(": " + pengajuancuti.getTgl_selesai_cuti());
        holder.tvStatus.setText(pengajuancuti.getStatus_pengajuan_cuti());
        holder.tvKeterangan.setText(pengajuancuti.getKet_pengajuan_cuti());
        status = holder.tvStatus.getText().toString();
        if (status.equals("Menunggu")){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.warning_200));
        } else if (status.equals("Disetujui")) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.success_200));
        } else if (status.equals("Ditolak")) {
        holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.danger_200));
    }
    }

    @Override
    public int getItemCount() {
        if(pengajuancutis ==null) return 0;
        return pengajuancutis.size();
    }

    public class PengajuancutiHolder extends RecyclerView.ViewHolder {
        final TextView tvAlasanPengajuan;
        final TextView tvTglPengajuan;
        final TextView tvTglMulai;
        final TextView tvTglSelesai;
        final TextView tvStatus;
        final TextView tvKeterangan;
        public PengajuancutiHolder(@NonNull View itemView) {
            super(itemView);
            tvAlasanPengajuan = itemView.findViewById(R.id.tv_alasan_pengajuan_cuti);
            tvTglPengajuan = itemView.findViewById(R.id.tv_tgl_pengajuancuti);
            tvTglMulai = itemView.findViewById(R.id.tv_tgl_mulai_pengajuancuti);
            tvTglSelesai = itemView.findViewById(R.id.tv_tgl_selesai_pengajuancuti);
            tvStatus = itemView.findViewById(R.id.tv_status_pengajuancuti);
            tvKeterangan = itemView.findViewById(R.id.tv_ket_pengajuancuti);
        }
    }
}
