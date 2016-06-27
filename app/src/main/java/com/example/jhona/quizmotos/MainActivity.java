package com.example.jhona.quizmotos;


import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jhona.quizmotos.Classes.Perguntas;
import com.example.jhona.quizmotos.dbHelper.dbHelper;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Perguntas> listPerg;
    Perguntas question;
    int index = 0;


    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private RadioButton radioNext = null;
    private TextView pergunta;
    private Button btnNext;
    private String resp;
    private ImageView im1, im2, im3, im4, imAux, imageAux;
    private ArrayList<RadioButton> radioAux;
    private ArrayList<ImageView> arrayImage;
    private int counter;
    private int qtdCertas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final dbHelper dbHelper = new dbHelper(this);
        listPerg = dbHelper.getAllQuestion();
        counter = dbHelper.rowCount();

        question = listPerg.get(index);

        //setando fontes para serem usadas no Views
        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/deftone stylus.ttf");
        final Typeface fontRadio = Typeface.createFromAsset(this.getAssets(), "fonts/year.ttf");


        pergunta = (TextView) findViewById(R.id.PersoName);
        pergunta.setTypeface(font);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setText("RESPONDER");
        btnNext.setTypeface(font);

        im1 = (ImageView) findViewById(R.id.image1);
        im2 = (ImageView) findViewById(R.id.image2);
        im3 = (ImageView) findViewById(R.id.image3);
        im4 = (ImageView) findViewById(R.id.image4);

        im1.setImageResource(R.drawable.star);
        im2.setImageResource(R.drawable.star);
        im3.setImageResource(R.drawable.star);
        im4.setImageResource(R.drawable.star);

        //Set RadioButton , adicionando a funcão onCliCk
        r1 = (RadioButton) findViewById(R.id.A);
        r1.setTypeface(fontRadio);
        r1.setChecked(true);
        r2 = (RadioButton) findViewById(R.id.B);
        r2.setTypeface(fontRadio);
        r3 = (RadioButton) findViewById(R.id.C);
        r3.setTypeface(fontRadio);
        r4 = (RadioButton) findViewById(R.id.D);
        r4.setTypeface(fontRadio);



        //Adicionando primeiros textos para os componentes
        pergunta.setText(question.getPergunta());
        r1.setText(question.getOpcaoA());
        r2.setText(question.getOpcaoB());
        r3.setText(question.getOpcaoC());
        r4.setText(question.getOpcaoD());

        //Criando um arraylist de RadioBox
        radioAux = new ArrayList<>();
        radioAux.add(r1);
        radioAux.add(r2);
        radioAux.add(r3);
        radioAux.add(r4);

        //Criando um arraylist de ImageView
        arrayImage = new ArrayList<>();
        arrayImage.add(im1);
        arrayImage.add(im2);
        arrayImage.add(im3);
        arrayImage.add(im4);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index < counter) {

                    imAux.setImageResource(R.drawable.ok);
                    question = listPerg.get(index);
                    btnNext.setEnabled(false);
                    resposta(question.getResposta());
                    index++;


                    if (index == counter) {

                        btnNext.setEnabled(false);
                        final EditText edittext = new EditText(MainActivity.this);
                        edittext.setHint("Entre com seu nome");
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("PONTUACAO")
                                .setMessage(qtdCertas + " PONTOS")
                                .setCancelable(false)
                                .setView(edittext)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //What ever you want to do with the value

                                        String nome = edittext.getText().toString();
                                        dbHelper.setPontos(nome, qtdCertas);
                                        onBackPressed();

                                    }
                                }).create().show();





                     /*   new AlertDialog.Builder(MainActivity.this)
                                .setTitle("MENSAGEM")
                                .setMessage("PONTUACAO: "+qtdCertas +" PONTOS")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                }).create().show();
                                */
                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                imAux.setImageResource(R.drawable.star);
                                if (imageAux != null) imageAux.setImageResource(R.drawable.star);


                                Radio(true);
                                question = listPerg.get(index);
                                pergunta.setText(question.getPergunta());
                                r1.setText(question.getOpcaoA());
                                r2.setText(question.getOpcaoB());
                                r3.setText(question.getOpcaoC());
                                r4.setText(question.getOpcaoD());

                                r2.setChecked(false);
                                r3.setChecked(false);
                                r4.setChecked(false);
                                r1.setChecked(false);

                                btnNext.setEnabled(true);
                            }
                        }, 3000);


                    }

                }

            }

            private void resposta(String s) {

                if (s.equals(resp)) {
                    Radio(false);
                    imAux.setImageResource(R.drawable.ok);
                    Toast.makeText(MainActivity.this, "Resposta Certa", Toast.LENGTH_LONG).show();
                    qtdCertas++;


                } else {
                    Radio(false);
                    imAux.setImageResource(R.drawable.error);
                    Toast.makeText(MainActivity.this, "Resposta Errada", Toast.LENGTH_LONG).show();
                    String aux[] = {"", "", "", ""};

                    aux[0] = r1.getText().toString();
                    aux[1] = r2.getText().toString();
                    aux[2] = r3.getText().toString();
                    aux[3] = r4.getText().toString();

                    List<ImageView> img = new ArrayList<>();
                    img.add((ImageView) findViewById(R.id.image1));
                    img.add((ImageView) findViewById(R.id.image2));
                    img.add((ImageView) findViewById(R.id.image3));
                    img.add((ImageView) findViewById(R.id.image4));

                    for (int i = 0; i < 4; i++) {
                        if (s.equals(aux[i])) {

                            img.get(i).setImageResource(R.drawable.ok);
                            imageAux = img.get(i);
                            break;

                        }
                    }
                }
            }


        });





    }

    private void Radio(boolean ver) {

        r2.setEnabled(ver);
        r3.setEnabled(ver);
        r4.setEnabled(ver);
        r1.setEnabled(ver);
    }



    public void onClick(View v) {

        im1.setImageResource(R.drawable.star);
        im2.setImageResource(R.drawable.star);
        im3.setImageResource(R.drawable.star);
        im4.setImageResource(R.drawable.star);

        radioNext = changeRadio((RadioButton) findViewById(v.getId()), radioNext);
        resp = radioNext.getText().toString();



    }

    private RadioButton changeRadio(RadioButton id, RadioButton next) {

        id.setChecked(true);
        if (next != null) next.setChecked(false);

        String n1 = id.getResources().getResourceEntryName(id.getId());


        if (n1.equals("A")) {
            im1.setImageResource(R.drawable.starblack);
            imAux = im1;
        } else if (n1.equals("B")) {
            im2.setImageResource(R.drawable.starblack);
            imAux = im2;
        } else if (n1.equals("C")) {
            im3.setImageResource(R.drawable.starblack);
            imAux = im3;
        } else if (n1.equals("D")) {
            im4.setImageResource(R.drawable.starblack);
            imAux = im4;
        }
        return id;

    }

}





















        /*

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);




    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }


    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        return results;
    }
}

*/