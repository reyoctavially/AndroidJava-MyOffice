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
import org.reyoctavially.myoffice.model.Kesehatan;

import java.util.List;

public class KesehatanAdapter extends RecyclerView.Adapter<KesehatanAdapter.KesehatanHolder> {

    private List<Kesehatan> kesehatans;
    private Context context;
    private String jenis;

    public KesehatanAdapter(List<Kesehatan> kesehatans, Context context) {
        this.kesehatans = kesehatans;
        this.context = context;
    }

    @NonNull
    @Override
    public KesehatanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_kesehatan, parent, false);
        return new KesehatanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KesehatanHolder holder, int position) {
        final Kesehatan kesehatan = kesehatans.get(position);
        holder.tvTglKesehatan.setText(kesehatan.getTgl_input_kesehatan());
        holder.tvHasilSwab.setText(": " + kesehatan.getHasil_swab_pegawai() + " (14 hari terakhir)");
        holder.tvStatusVaksinasi.setText(": " + kesehatan.getStatus_vaksinasi_pegawai() + " melakukan vaksinasi");
        holder.tvSuhuTubuh.setText(kesehatan.getSuhu_tubuh_pegawai() + "C");
        holder.tvJenisPekerjaan.setText(kesehatan.getJenis_pekerjaan());
        jenis = holder.tvJenisPekerjaan.getText().toString();
        if (jenis.equals("Work from home")){
            holder.tvJenisPekerjaan.setTextColor(ContextCompat.getColor(context, R.color.success_200));
        } else if (jenis.equals("Work from office")) {
            holder.tvJenisPekerjaan.setTextColor(ContextCompat.getColor(context, R.color.primary_200));
        }
    }

    @Override
    public int getItemCount() {
        if(kesehatans ==null) return 0;
        return kesehatans.size();
    }

    public class KesehatanHolder extends RecyclerView.ViewHolder {
        final TextView tvTglKesehatan;
        final TextView tvHasilSwab;
        final TextView tvStatusVaksinasi;
        final TextView tvSuhuTubuh;
        final TextView tvJenisPekerjaan;
        public KesehatanHolder(@NonNull View itemView) {
            super(itemView);
            tvTglKesehatan = itemView.findViewById(R.id.tv_tgl_kesehatan);
            tvHasilSwab = itemView.findViewById(R.id.tv_hasil_swab);
            tvStatusVaksinasi = itemView.findViewById(R.id.tv_status_vaksinasi);
            tvSuhuTubuh = itemView.findViewById(R.id.tv_suhu_tubuh);
            tvJenisPekerjaan = itemView.findViewById(R.id.tv_jenis_pekerjaan);
        }
    }
}
