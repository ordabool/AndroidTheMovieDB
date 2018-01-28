package ordabool.themoviedb.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ordabool.themoviedb.Adapters.FeaturedMediaListAdapter;
import ordabool.themoviedb.Adapters.SearchedMediaAdapter;
import ordabool.themoviedb.AsyncFunctions.GetSearchResults;
import ordabool.themoviedb.Handlers.AppManager;
import ordabool.themoviedb.Model.Media;
import ordabool.themoviedb.Model.Movie;
import ordabool.themoviedb.Model.TVShow;
import ordabool.themoviedb.R;

public class SearchActivity extends BaseActivity {

    EditText searchEditText;
    Button searchButton;
    public static ListView resultsListView;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_search, frameLayout);
        context = getApplicationContext();
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        resultsListView = findViewById(R.id.searchResultsTableView);
        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Media media = AppManager.shared.getSearchResult()[i];
                if (media instanceof Movie){
                    Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                    MovieActivity.movie = (Movie) media;
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), TVShowActivity.class);
                    TVShowActivity.tvShow = (TVShow) media;
                    startActivity(intent);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchEditText.getText().toString().matches("")){
                    Toast.makeText(SearchActivity.this, "No query has been entered..", Toast.LENGTH_SHORT).show();
                } else {
                    GetSearchResults getSearchResults = new GetSearchResults(getApplicationContext());
                    getSearchResults.execute(searchEditText.getText().toString());
                }

            }
        });

    }
}
