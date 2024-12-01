package com.example.ecir;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmbarqueAdapter extends RecyclerView.Adapter<EmbarqueAdapter.EmbarqueViewHolder> {
    private List<Embarque> embarqueList;
    private OnEmbarqueClickListener listener;

    // Interface para ouvir cliques nos itens
    public interface OnEmbarqueClickListener {
        void onVerEmbarque(Embarque embarque);
        void onEditEmbarque(Embarque embarque);
        void onDeleteEmbarque(Embarque embarque);
    }

    public EmbarqueAdapter(List<Embarque> embarqueList) {
        this.embarqueList = embarqueList;
    }

    @NonNull
    @Override
    public EmbarqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_embarque, parent, false);
        return new EmbarqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmbarqueViewHolder holder, int position) {
        Embarque embarque = embarqueList.get(position);
        holder.nomeEmbarqueTextView.setText(embarque.getNomeEmbarcacao());

        // Configurar eventos para os botões usando a interface
        holder.verEmbarqueButton.setOnClickListener(v -> listener.onVerEmbarque(embarque));
        holder.editEmbarqueButton.setOnClickListener(v -> listener.onEditEmbarque(embarque));
        holder.deleteEmbarqueButton.setOnClickListener(v -> listener.onDeleteEmbarque(embarque));
    }

    @Override
    public int getItemCount() {
        return embarqueList != null ? embarqueList.size() : 0;
    }

    // Método para atualizar a lista de embarques
    public void updateEmbarques(List<Embarque> newEmbarqueList) {
        this.embarqueList = newEmbarqueList;
        notifyDataSetChanged(); // Notifica que os dados mudaram
    }

    public static class EmbarqueViewHolder extends RecyclerView.ViewHolder {
        TextView nomeEmbarqueTextView;
        Button verEmbarqueButton;
        ImageButton editEmbarqueButton, deleteEmbarqueButton;

        public EmbarqueViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEmbarqueTextView = itemView.findViewById(R.id.nomeEmbarqueTextView);
            verEmbarqueButton = itemView.findViewById(R.id.verEmbarqueButton);
            editEmbarqueButton = itemView.findViewById(R.id.editEmbarqueButton);
            deleteEmbarqueButton = itemView.findViewById(R.id.deleteEmbarqueButton);
        }
    }
}