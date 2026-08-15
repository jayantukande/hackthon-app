package com.signbridgecommunication.app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signbridgecommunication.app.R;
import com.signbridgecommunication.app.data.model.Phrase;

import java.util.List;

public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.ViewHolder> {

    private List<Phrase> phrases;
    private OnPhraseClickListener listener;

    public interface OnPhraseClickListener {
        void onPhraseClick(Phrase phrase);
        void onFavoriteClick(Phrase phrase);
    }

    public PhraseAdapter(List<Phrase> phrases, OnPhraseClickListener listener) {
        this.phrases = phrases;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phrase, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phrase phrase = phrases.get(position);
        holder.tvText.setText(phrase.getEnglishText());
        holder.tvSubtext.setText(phrase.getHindiText()); // Default to Hindi as subtext for demo
        
        holder.ivFavorite.setImageResource(phrase.isFavorite() ? 
                android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
        
        holder.itemView.setOnClickListener(v -> listener.onPhraseClick(phrase));
        holder.ivFavorite.setOnClickListener(v -> listener.onFavoriteClick(phrase));
    }

    @Override
    public int getItemCount() {
        return phrases.size();
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText, tvSubtext;
        ImageView ivThumb, ivFavorite;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_phrase_text);
            tvSubtext = itemView.findViewById(R.id.tv_phrase_subtext);
            ivThumb = itemView.findViewById(R.id.iv_phrase_thumb);
            ivFavorite = itemView.findViewById(R.id.iv_favorite);
        }
    }
}