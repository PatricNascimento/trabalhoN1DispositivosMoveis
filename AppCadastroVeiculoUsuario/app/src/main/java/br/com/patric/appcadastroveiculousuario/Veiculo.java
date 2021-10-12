package br.com.patric.appcadastroveiculousuario;

import androidx.annotation.NonNull;

public class Veiculo {
    public int id;
    public String nomeVeiculo, kmVeiculo, marcaVeiculo, anoVeiculo, arVeiculo;

    public Veiculo() {

    }

    public Veiculo(String nomeVeiculo, String kmVeiculo, String marcaVeiculo, String anoVeiculo, String arVeiculo ) {
        this.nomeVeiculo = nomeVeiculo;
        this.kmVeiculo = kmVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.anoVeiculo = arVeiculo;
    }

    public Veiculo(int id, String nomeVeiculo, String kmVeiculo, String marcaVeiculo, String anoVeiculo, String arVeiculo) {
        this.id = id;
        this.nomeVeiculo = nomeVeiculo;
        this.kmVeiculo = kmVeiculo;
        this.marcaVeiculo = marcaVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.arVeiculo = arVeiculo;
    }

    @Override
    public String toString() {
        return  nomeVeiculo + " | " + kmVeiculo + " | " + marcaVeiculo + " | " +  anoVeiculo + " | " + arVeiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public String getKmVeiculo() {
        return kmVeiculo;
    }

    public void setKmVeiculo(String kmVeiculo) {
        this.kmVeiculo = kmVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getArVeiculo() {return arVeiculo;}

    public void setArVeiculo(String arVeiculo) { this.arVeiculo = arVeiculo; }
}
