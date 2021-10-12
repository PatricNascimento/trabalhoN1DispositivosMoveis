package br.com.patric.appcadastroveiculousuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
public class VeiculoDAD {

    public static void inserir(Context context, Veiculo veiculos){
        ContentValues valores = new ContentValues();
        valores.put("nome", veiculos.getNomeVeiculo());
        valores.put("km", veiculos.getKmVeiculo());
        valores.put("marca", veiculos.getMarcaVeiculo());
        valores.put("ano", veiculos.getAnoVeiculo());
        valores.put("arcondicionado", veiculos.getArVeiculo());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();


        db.insert("veiculos", null, valores);
    }
    public static void editar(Context context, Veiculo veiculos){
        ContentValues valores = new ContentValues();
        valores.put("nome", veiculos.getNomeVeiculo());
        valores.put("km", veiculos.getKmVeiculo());
        valores.put("marca", veiculos.getMarcaVeiculo());
        valores.put("ano", veiculos.getAnoVeiculo());
        valores.put("arcondicionado", veiculos.getArVeiculo());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();


        db.update("veiculos", valores,"id = " + veiculos.getId(), null);
    }
    public static void excluir(Context context, int idVeiculo){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("veiculos","id ="+ idVeiculo, null);
    }
    public static List<Veiculo> getVeiculos(Context context) {
        List<Veiculo> Lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM produto ORDER BY nome", null);

        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Veiculo pro = new Veiculo();
                pro.setId( cursor.getInt( 0 ));
                pro.setNomeVeiculo( cursor.getString( 1));
                pro.setKmVeiculo( cursor.getString( 2));
                pro.setMarcaVeiculo( cursor.getString( 3));
                pro.setAnoVeiculo( cursor.getString( 4));
                pro.setArVeiculo( cursor.getString( 5));
                Lista.add ( pro );

            }while (cursor.moveToNext());
        }
        return Lista;
    }
    public static Veiculo getVeiculosById(Context context, int idVeiculo) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produto WHERE id = " + idVeiculo, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            Veiculo pro = new Veiculo();
            pro.setId( cursor.getInt( 0 ));
            pro.setNomeVeiculo( cursor.getString( 1));
            pro.setKmVeiculo( cursor.getString( 2));
            pro.setMarcaVeiculo( cursor.getString( 3));
            pro.setAnoVeiculo( cursor.getString( 4));
            pro.setArVeiculo( cursor.getString( 5));

            return pro;
        }
        else{
            return null;
        }
    }


}
