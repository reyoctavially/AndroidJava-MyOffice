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

import java.util.List;

public class DataCutiAdapter extends RecyclerView.Adapter<DataCutiAdapter.CutiHolder>{

    private List<Cuti> cutis;
    private Context context;
    private String status;

    public DataCutiAdapter(List<Cuti> cutis, Context context) {
        this.cutis = cutis;
        this.context = context;
    }

    @NonNull
    @Override
    public CutiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_cuti, parent, false);
        return new CutiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CutiHolder holder, int position) {
        final Cuti cuti = cutis.get(position);
        holder.tvJenisCuti.setText(cuti.getJenis_cuti());
        holder.tvTglMulai.setText(": " + cuti.getTglmulaicuti());
        holder.tvTglSelesai.setText(": " + cuti.getTglselesaicuti());
        holder.tvStatus.setText(cuti.getStatuscuti());
        holder.tvHonor.setText("Pemotongan honor Rp." + cuti.getPemotongan_honor());
        status = holder.tvStatus.getText().toString();
        if (status.equals("Berlaku")){
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.success_200));
        } else if (status.equals("Tidak Berlaku")) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.danger_200));
        }
    }

    @Override
    public int getItemCount() {
        if(cutis ==null) return 0;
        return cutis.size();
    }

    public class CutiHolder extends RecyclerView.ViewHolder {
        final TextView tvJenisCuti;
        final TextView tvTglMulai;
        final TextView tvTglSelesai;
        final TextView tvStatus;
        final TextView tvHonor;
        public CutiHolder(@NonNull View itemView) {
            super(itemView);
            tvJenisCuti = itemView.findViewById(R.id.tv_jenis_cuti);
            tvTglMulai = itemView.findViewById(R.id.tv_tgl_mulai_cuti);
            tvTglSelesai = itemView.findViewById(R.id.tv_tgl_selesai_cuti);
            tvStatus = itemView.findViewById(R.id.tv_status_cuti);
            tvHonor = itemView.findViewById(R.id.tv_honor);
        }
    }
}
