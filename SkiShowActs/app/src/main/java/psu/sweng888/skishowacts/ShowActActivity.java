package psu.sweng888.skishowacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowActActivity extends AppCompatActivity {

    // Display the specific Act passed in as an Intent extra
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_act);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.show_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the selected Act object from the Intent extras
        Act selectedAct = (Act) getIntent().getSerializableExtra("selected_act");

        // Find the TextViews in the layout
        TextView mTextViewTitle = findViewById(R.id.textview_ActTitle);
        TextView mTextViewSubtitle = findViewById(R.id.textview_ActSubtitle);
        TextView mTextViewDescription = findViewById(R.id.textview_ActDescription);

        // Set the TextViews with the selected Act data
        mTextViewTitle.setText(selectedAct.getTitle());
        mTextViewSubtitle.setText(selectedAct.getSubtitle());
        mTextViewDescription.setText(selectedAct.getDescription());

        // Enable the return button
        Button mButtonReturn = findViewById(R.id.button_back);
        mButtonReturn.setOnClickListener(
            v -> {
                Intent intent = new Intent(ShowActActivity.this, MainActivity.class);
                intent.putExtra("display_success_snackbar", true);
                startActivity(intent);
            }
        );

        // Display the snackbar
        // TODO: Implement the snackbar
    }
}