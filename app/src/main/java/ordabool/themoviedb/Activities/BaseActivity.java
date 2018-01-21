package ordabool.themoviedb.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import ordabool.themoviedb.R;

public class BaseActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        frameLayout = findViewById(R.id.content_frame);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId()) {
                    case R.id.featuredMediaItem:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        //finish() - close one activity
                        finishAffinity();
                        break;
                    case R.id.popularMoviesItem:
                        i = new Intent(getApplicationContext(), PopularMovies.class);
                        startActivity(i);
                        finishAffinity();
                        break;
                    case R.id.popularTVShowsItem:
                        i = new Intent(getApplicationContext(), PopularTVShows.class);
                        startActivity(i);
                        finishAffinity();
                        break;
                }
                return false;
            }
        });

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
