package aaron.com.pokedexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;

public class PokemonListActivity extends AppCompatActivity implements ConnectionErrorDialogFragment.TryAgainListener {

    private static final int BUFFER_SIZE = 100;
    private static final int START_OFFSET = 0;
    private RecyclerView pokemonRecyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private Controller controller;
    private ProgressBar listLoadingBar;
    private boolean awaitingResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        controller = new Controller(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listLoadingBar = (ProgressBar)findViewById(R.id.list_progress_bar);
        pokemonRecyclerView = (RecyclerView)findViewById(R.id.pokemon_list_recycler_view);
        pokemonRecyclerView.setLayoutManager(layoutManager);

        pokemonRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!awaitingResponse && dy > 0){//Scrolled down
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                        controller.getPokemon(BUFFER_SIZE, totalItemCount);
                        listLoadingBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        pokemonListAdapter = new PokemonListAdapter(controller);
        pokemonRecyclerView.setAdapter(pokemonListAdapter);

        controller.getPokemon(BUFFER_SIZE, START_OFFSET);

    }

    public void updatePokemonList(AllPokemonResponse allPokemon){
        pokemonListAdapter.setMyPokemon(allPokemon.getPokemonInfo());
        pokemonListAdapter.notifyDataSetChanged();
        listLoadingBar.setVisibility(View.GONE);
    }

    public void showPokemonInfo(PokemonInfo pokemonInfo){
        PokemonInfoDialogFragment infoDialog = new PokemonInfoDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("pokemonInfo", pokemonInfo);
        infoDialog.setArguments(args);
        infoDialog.show(getSupportFragmentManager(), "PokemonInfo");
    }

    public void showConnectionError(String errorInfo, Call<AllPokemonResponse> call){
        ConnectionErrorDialogFragment errorDialog = new ConnectionErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString("errorInfo", errorInfo);
        args.putSerializable("failedCall", new CallSerializableWrapper(call));
        errorDialog.setArguments(args);
        errorDialog.show(getSupportFragmentManager(), "ErrorInfo");
    }

    public void setAwaitingResponse(Boolean awaitingResponse){
        this.awaitingResponse = awaitingResponse;
    }

    @Override
    public void onTryAgain(Call<AllPokemonResponse> call) {
        controller.resendCall(call);
    }
}
