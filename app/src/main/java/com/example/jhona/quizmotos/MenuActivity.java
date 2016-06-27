package com.example.jhona.quizmotos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jhona.quizmotos.Classes.Adapter;
import com.example.jhona.quizmotos.dbHelper.dbHelper;

public class MenuActivity extends AppCompatActivity{

    private TextView titulo;
    private Button quiz,pontos;
    private Intent tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inicia();





    }

    private void inicia() {
        setContentView(R.layout.activity_menu);

        titulo = (TextView)findViewById(R.id.titulo);
        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/deftone stylus.ttf");

        quiz = (Button)findViewById(R.id.btnQuiz);
        pontos = (Button)findViewById(R.id.btnPonto);


        assert titulo != null;
        titulo.setTypeface(font);
    }

    public void quiz(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void pontos(View v){


        dbHelper dbHelper = new dbHelper(this);

        setContentView(R.layout.leader);
        ListView listaScores = (ListView)findViewById(R.id.scores);
        Button btnOk = (Button)findViewById(R.id.btnOk);

        assert listaScores != null;
        listaScores.setAdapter(new Adapter(this,dbHelper.getPontos()));

        assert btnOk != null;
        btnOk.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              inicia();
          }
      });

    }

}
