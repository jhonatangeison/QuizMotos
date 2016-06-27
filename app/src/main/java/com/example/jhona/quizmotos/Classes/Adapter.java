package com.example.jhona.quizmotos.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jhona.quizmotos.R;

import java.util.List;



/**
 * Created by heiderlopes on 08/01/16.
 */
public class Adapter extends BaseAdapter {

    private Context context;
    private List<Perguntas> academias;
    private LayoutInflater layoutInflater;

    public Adapter(Context context, List<Perguntas> academias) {
        this.context = context;
        this.academias = academias;
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return academias.size();
    }

    @Override
    public Object getItem(int i) {
        return academias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return academias.get(i).getPontos();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AcademiaViewHolder holder;

        if(view == null) {
            holder = new AcademiaViewHolder();
            view = layoutInflater.inflate(R.layout.layout_leader, viewGroup, false);
            holder.tvNome = (TextView) view.findViewById(R.id.lider_nome);
            holder.tvPonto = (TextView) view.findViewById(R.id.lider_ponto);
         /*   holder.tvPeso = (TextView) view.findViewById(R.id.tvPeso);
            holder.tvIMC = (TextView) view.findViewById(R.id.tvIMC);
            holder.tvIdade = (TextView) view.findViewById(R.id.tvIdade);
            holder.tvModal = (TextView) view.findViewById(R.id.tvModal);
            holder.tvValor = (TextView) view.findViewById(R.id.tvValor);
            holder.tvDia = (TextView) view.findViewById(R.id.tvDias);*/

            view.setTag(holder);
        } else {
            holder = (AcademiaViewHolder) view.getTag();
        }
        holder.tvNome.setText(academias.get(i).getNome());
        holder.tvPonto.setText(String.valueOf(academias.get(i).getPontos()));

      /*  holder.tvPeso.setText(academias.get(i).getPeso());
        holder.tvIMC.setText(academias.get(i).getImc());
        holder.tvIdade.setText(academias.get(i).getIdade());
        holder.tvModal.setText(academias.get(i).getModal());
        holder.tvValor.setText(academias.get(i).getValor());
        holder.tvDia.setText(academias.get(i).getDias());*/

        return view;
    }

    private class AcademiaViewHolder {
        protected TextView tvNome;
        protected TextView tvPonto;
        protected TextView tvIMC;
        protected TextView tvIdade;
        protected TextView tvModal;
        protected TextView tvValor;
        protected TextView tvDia;
    }
}
