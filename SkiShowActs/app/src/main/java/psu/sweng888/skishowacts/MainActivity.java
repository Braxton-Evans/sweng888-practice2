package psu.sweng888.skishowacts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Main UI element is the ListView
    ListView mListViewActs;

    // Display the main list of acts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // If returning via Intent from ShowActActivity, display the "operation completed successfully" snackbar
        Intent referringIntent = getIntent();
        if (referringIntent.getBooleanExtra("display_success_snackbar", false)) {
            Snackbar.make(findViewById(R.id.main), "Operation completed successfully", Snackbar.LENGTH_SHORT).show();
        }

        // Set up the ListView by loading the data and setting the adapter
        ArrayList<Act> acts = loadActsData();
        ActsListAdapter adapter = new ActsListAdapter(MainActivity.this, acts);

        // Set the ListView's adapter
        mListViewActs = findViewById(R.id.listview_acts);
        mListViewActs.setAdapter(adapter);

        // Define the click listener for the ListView
        mListViewActs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked Act
                Act selectedAct = (Act) parent.getItemAtPosition(position);

                // Display a snackbar to cancel the selection, if wrong:
                Snackbar confirmSnackbar = Snackbar.make(findViewById(R.id.main),"Loading Act: "
                    + selectedAct.getTitle() + "...", Snackbar.LENGTH_SHORT);
                confirmSnackbar.setAction("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { /* Cancel the selection (Do nothing) */ }
                });
                confirmSnackbar.show();

                // Add a callback to start the ShowActActivity after the snackbar is dismissed
                confirmSnackbar.addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        // Snackbar was dismissed (timed out or swiped)
                        if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                            new Handler(Looper.getMainLooper()).postDelayed(
                                () -> runIntentToShowActActivity(selectedAct), 10);
                        }
                    }
                });
            }
        });
    }

    // Defines the list of Acts - could be loaded from a data source instead of hard-coded
    private ArrayList<Act> loadActsData() {
        ArrayList<Act> acts = new ArrayList<>();

        acts.add(new Act("Barefoot Line", "Up to 50 mph!",
                "These adrenaline junkies say \"Who needs 'em?\" to skis and ski on their " +
                          "bare feet, alone! But without that surface to hold them up, they need to " +
                          "go much faster than traditional skiers. Roughly twice as fast, around 40 " +
                          "to 50 miles per hour! Watch close, as some skiers in the line might even " +
                          "take it to the next level by using only a single foot! Incredible! "));

        acts.add(new Act("Wakeboard", "Let's get some AIR!",
                "Hold on to your hats, folks, because these wakeboarders have a series of " +
                          "mind-blowing tricks in store for you, including the Scarecrow, the  Tantrum, " +
                          "and the Elephant! If you're lucky, they might even throw in the elusive " +
                          "Moby Dick 540! Keep your eyes peeled for massive air, gravity-defying spins, " +
                          "and maybe even a splash or two!"));

        acts.add(new Act("ATB", "Around-The-Boat",
                "In ATB, one or more slalom skiers cut out wide as their boat turns the " +
                          "other way. As the skier reaches 90 degrees, the boat quickly stops and " +
                          "turns back towards the skier, allowing the skier to ski past the boat, " +
                          "eventually skiing all the way around the boat! This is exceptionally " +
                          "challenging with multiple skiers, as they must be careful to avoid each " +
                          "other's ropes."));

        return acts;
    }

    // Starts the ShowActActivity
    private void runIntentToShowActActivity(Act selectedAct) {
        Intent intent = new Intent(MainActivity.this, ShowActActivity.class);
        intent.putExtra("selected_act", selectedAct);
        startActivity(intent);
    }
}