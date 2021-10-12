package br.com.patric.appcadastroveiculousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText edNomeVeiculo;
    private EditText edKm;
    private Spinner spMarcaVeiculo;
    private Spinner spAnoVeiculo;
    private Spinner spArVeiculo;
    private Button btnSalvar;
    private String acao;
    private Veiculo veiculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        edNomeVeiculo = findViewById(R.id.edNomeVeiculo);
        edKm = findViewById(R.id.edKm);
        spMarcaVeiculo = findViewById(R.id.spMarcaVeiculo);
        spAnoVeiculo = findViewById(R.id.spAnoVeiculo);
        spArVeiculo = findViewById(R.id.spArVeiculo);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { salvar(); }
        });

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            carregarFormulario();
        }

    }

    private void carregarFormulario(){
        int idVeiculo = getIntent().getIntExtra("idVeiculo", 0);
        veiculo = VeiculoDAD.getVeiculosById(this, idVeiculo);
        edNomeVeiculo.setText(veiculo.getNomeVeiculo());

        String[] marcas = getResources().getStringArray(R.array.marcaVeiculo);

        for( int i = 0; i < marcas.length; i++){
            if( veiculo.getMarcaVeiculo().equals( marcas[i] )){
                spMarcaVeiculo.setSelection( i );
                break;
            }
        }
    }
    private void salvar(){
        String nome = edNomeVeiculo.getText().toString();

        if( nome.isEmpty() || spMarcaVeiculo.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Você deve preencher todos os campos", Toast.LENGTH_LONG).show();
        }
        else{

            if ( acao.equals("inserir")) {
                veiculo = new Veiculo();
            }
            veiculo.setNomeVeiculo( nome );
            veiculo.setMarcaVeiculo( spMarcaVeiculo.getSelectedItem().toString() );

            if ( acao.equals("inserir")) {
                VeiculoDAD.inserir(this, veiculo);
                edNomeVeiculo.setText("");
                spMarcaVeiculo.setSelection(0, true);
            }
            else{
                VeiculoDAD.editar(this, veiculo);
                finish();
            }
        }
    }

}