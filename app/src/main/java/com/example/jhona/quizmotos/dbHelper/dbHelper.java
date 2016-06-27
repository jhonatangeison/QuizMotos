package com.example.jhona.quizmotos.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;

import com.example.jhona.quizmotos.Classes.Perguntas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhona on 13/06/2016.
 */
public class dbHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION =5;
    private static final String DATABASE_NAME ="Quiz.db";
    private static final String TABLE_NAME ="motoQuiz";
    private static final String KEY_REPOSTA ="resposta";
    private static final String KEY_PERGUNTA ="pergunta";
    private static final String KEY_ID ="id";
    private static final String KEY_OPTA ="opA";
    private static final String KEY_OPTB ="opB";
    private static final String KEY_OPTC ="opC";
    private static final String KEY_OPTD ="opD";

    private SQLiteDatabase dbase;


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




 int id;
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_PERGUNTA + " TEXT, " +
                KEY_REPOSTA+ " TEXT, " + KEY_OPTA + " TEXT, " + KEY_OPTB + " TEXT, " +
                KEY_OPTC + " TEXT, " + KEY_OPTD + " TEXT )";
        db.execSQL(sql);

        String sqlPontos= "CREATE TABLE IF NOT EXISTS pontuacao ( pontos INTEGER, nome TEXT )";
        db.execSQL(sqlPontos);


        addPergutas();
        //db.close();
    }

    private void addPergutas() {




        Perguntas p1 = new Perguntas(" Qual a moto mais veloz do mundo", "Dodge Tomahawk",
                "Suzuki Hayabusa", "MTT Turbine Superbike Y2K", "Honda CBR1100XX Blackbird", "Dodge Tomahawk");
        this.saveDbQuestion(p1);

        Perguntas p2 = new Perguntas("Qual a moto mais cara do mundo", "Dodge Tomahawk",
                "Ecosse Titanium Series FE Ti XX", "Hollister Excite", "NCR M16", "Dodge Tomahawk");
        this.saveDbQuestion(p2);

        Perguntas p3 = new Perguntas("Qual a moto mais veloz vendida no Brasil", "Kawasaki ZX-10R",
                "Suzuki Hayabusa", "BMW S 1000 RR ", "MV Agusta F4 ", "Kawasaki ZX-10R");
        this.saveDbQuestion(p3);

        Perguntas p4 = new Perguntas("Qual é o tipo de moto preferido dos roqueiros", "Custom",
                "Off-road", "Sport", "Naked", "Custom");
        this.saveDbQuestion(p4);


    }
    long res=0;
    private void saveDbQuestion(Perguntas pergunta){

        ContentValues values = new ContentValues();
        values.put(KEY_PERGUNTA, pergunta.getPergunta());
        values.put(KEY_OPTA, pergunta.getOpcaoA());
        values.put(KEY_OPTB, pergunta.getOpcaoB());
        values.put(KEY_OPTC, pergunta.getOpcaoC());
        values.put(KEY_OPTD, pergunta.getOpcaoD());
        values.put(KEY_REPOSTA, pergunta.getResposta());
        res = dbase.insert(TABLE_NAME,null,values);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public List<Perguntas> getAllQuestion() {

        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.jhona.admquiz/databases/Quiz.db", null, 0);

        List<Perguntas> pergLista = new ArrayList<Perguntas>();

        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        dbase = this.getReadableDatabase();

        Cursor cursor = dbase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
               Perguntas perguntas = new Perguntas();
                perguntas.setID(cursor.getInt(0));
                perguntas.setPergunta(cursor.getString(1));
                perguntas.setResposta(cursor.getString(2));
                perguntas.setOpcaoA(cursor.getString(3));
                perguntas.setOpcaoB(cursor.getString(4));
                perguntas.setOpcaoC(cursor.getString(5));
                perguntas.setOpcaoD(cursor.getString(6));
                pergLista.add(perguntas);
            }while (cursor.moveToNext());
        }
        return pergLista;
    }

    public int rowCount(){

        int row=0;
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        row =cursor.getCount();
        return row;
    }


    public void setPontos(String nome, int pontos) {

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("pontos", pontos);

        res = dbase.insert("pontuacao",null,values);
    }

    public List<Perguntas> getPontos() {

        String selectQuery = "SELECT * FROM pontuacao  ORDER BY pontos DESC";
        dbase = this.getReadableDatabase();
        List<Perguntas> info = new ArrayList<>();
        Cursor cursor = dbase.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{

                Perguntas perguntas = new Perguntas();

                perguntas.setPontos((cursor.getInt(0)));
                perguntas.setNome(cursor.getString(1));

                info.add(perguntas);
            }while (cursor.moveToNext());
        }

        return  info;
    }
}
