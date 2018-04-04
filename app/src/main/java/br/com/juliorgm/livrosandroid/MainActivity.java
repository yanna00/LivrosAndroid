package br.com.juliorgm.livrosandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.juliorgm.livrosandroid.model.Livro;

import static com.orm.SugarRecord.delete;

public class MainActivity extends AppCompatActivity {

    ListView listViewLivros;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewLivros = findViewById(R.id.listViewLivros);
        carregaLivro();

    }
    @Override
    protected void onResume() {
        super.onResume();
        listViewLivros = findViewById(R.id.listViewLivros);
        listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livro livro = (Livro) listViewLivros.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, FormLivrosActivity.class);
                intent.putExtra("LIVRO",livro);
                startActivity(intent);

            }
        });

        registerForContextMenu(listViewLivros);

    }
    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

               livro = new Livro();
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
                int position = menuInfo.position;

                livro = (Livro) listViewLivros.getItemAtPosition(position);
               delete(livro);
               carregaLivro();
                return false;
            }
        });

    }


    public void carregaLivro () {
        List<Livro> livroLista = Livro.listAll(Livro.class);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, livroLista);
        listViewLivros.setAdapter(adapter);
    }

    public void novoLivro (View view) {
       Intent intent = new Intent(MainActivity.this,FormLivrosActivity.class);
        startActivity(intent);
    }
}
