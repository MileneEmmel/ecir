
// ItemEmbarqueAdapter.java
package com.example.ecir;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecir.database.DatabaseHelper;
import java.util.List;

public class ItemEmbarqueAdapter extends RecyclerView.Adapter<ItemEmbarqueAdapter.ViewHolder> {

    private final Context context;
    private final List<Embarque> embarques;
    private final DatabaseHelper databaseHelper;
    private final Runnable onDeleteCallback;

    public ItemEmbarqueAdapter(Context context, List<Embarque> embarques, DatabaseHelper databaseHelper, Runnable onDeleteCallback) {
        this.context = context;
        this.embarques = embarques;
        this.databaseHelper = databaseHelper;
        this.onDeleteCallback = onDeleteCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_embarque, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Embarque embarque = embarques.get(position);

        holder.nomeEmbarqueTextView.setText(embarque.getNomeEmbarcacao());

        // Evento do botão "Ver"
        holder.verEmbarqueButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalhesEmbarqueActivity.class);
            intent.putExtra("EMBARQUE_ID", embarque.getId());
            context.startActivity(intent);
        });

        // Evento do botão "Editar"
        holder.editEmbarqueButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarEmbarqueActivity.class);
            intent.putExtra("EMBARQUE_ID", embarque.getId());
            context.startActivity(intent);
        });

        // Evento do botão "Deletar"
        holder.deleteEmbarqueButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirmar Exclusão")
                    .setMessage("Tem certeza que deseja excluir este embarque?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        boolean deleted = databaseHelper.deleteEmbarqueById(embarque.getId());
                        if (deleted) {
                            embarques.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, embarques.size());
                            onDeleteCallback.run();
                            Toast.makeText(context, "Embarque excluído com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Erro ao excluir embarque", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return embarques.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeEmbarqueTextView;
        Button verEmbarqueButton;
        ImageButton editEmbarqueButton, deleteEmbarqueButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEmbarqueTextView = itemView.findViewById(R.id.nomeEmbarqueTextView);
            verEmbarqueButton = itemView.findViewById(R.id.verEmbarqueButton);
            editEmbarqueButton = itemView.findViewById(R.id.editEmbarqueButton);
            deleteEmbarqueButton = itemView.findViewById(R.id.deleteEmbarqueButton);
        }
    }
}
