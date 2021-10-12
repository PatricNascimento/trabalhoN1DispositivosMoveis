package br.com.patric.appcadastroveiculousuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import br.com.patric.appcadastroveiculousuario.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    private ListView lvVeiculos;
    private List<Veiculo> ListaDeVeiculos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity( intent );
            }
        });

        lvVeiculos = findViewById(R.id.lvVeiculos);

        lvVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                int idVeiculo = ListaDeVeiculos.get(i).getId();
                intent.putExtra("idVeiculo", idVeiculo);
                startActivity( intent );
            }
        });

        lvVeiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                excluir (i);

                return true;
            }
        });

        carregarVeiculos();
    }

    private void excluir(int posicao){
        Veiculo veiSelecionado = ListaDeVeiculos.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon( android.R.drawable.ic_delete );
        alerta.setMessage("Confirma a exclusão do veículo" + veiSelecionado.getNomeVeiculo() + "?");

        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                VeiculoDAD.excluir(MainActivity.this, veiSelecionado.getId());
                carregarVeiculos();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarVeiculos();
    }

    private void carregarVeiculos(){

        ListaDeVeiculos = VeiculoDAD.getVeiculos(this);

        if(ListaDeVeiculos.size() == 0){
            Veiculo fake = new Veiculo("Sem Veiculo","","","","");
            ListaDeVeiculos.add(fake);
            lvVeiculos.setEnabled(false);
        }
        else{
            lvVeiculos.setEnabled(true);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListaDeVeiculos);
        lvVeiculos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}