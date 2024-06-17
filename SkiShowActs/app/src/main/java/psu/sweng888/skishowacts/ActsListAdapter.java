package psu.sweng888.skishowacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ActsListAdapter extends ArrayAdapter<Act> {

    // Constructor
    public ActsListAdapter(Context context, ArrayList<Act> acts) {
        super(context, 0, acts);
    }

    // Get the view for the list item
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_act, parent, false);
        }
        // Get the act at the current position
        Act act = getItem(position);

        // Find the TextView for the title and subtitle
        TextView textViewTitle = convertView.findViewById(R.id.list_item_title);
        TextView textViewSubtitle = convertView.findViewById(R.id.list_item_subtitle);

        // Set the text for the title and subtitle
        textViewTitle.setText(act.getTitle());
        textViewSubtitle.setText(act.getSubtitle());

        // Find the Show Details Button
        /*Button showDetailsButton = convertView.findViewById(R.id.list_item_btn);

        // Set the click listener for the Show Details Button
        showDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the details of the act
                // TODO: Implement the functionality to show the details of the act
                String nonesense = "No Nonsense Response";
                nonesense = nonesense.substring(3, 11);
            }
        });*/

        return convertView;
    }
}
