package gdbsteam.guiabussaco;


import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class DescricaoFragment extends Fragment {

    // Create a DetailsFragment that contains the hero data for the correct index
    public static DescricaoFragment newInstance(int index) {
        DescricaoFragment f = new DescricaoFragment();

        // Bundles are used to pass data using a key "index" and a value
        Bundle args = new Bundle();
        args.putInt("index", index);

        // Assign key value to the fragment
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {

        // Returns the index assigned
        return getArguments().getInt("index", 0);
    }

    // LayoutInflator puts the Fragment on the screen
    // ViewGroup is the view you want to attach the Fragment to
    // Bundle stores key value pairs so that data can be saved
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create a ScrollView to put your hero data in
        ScrollView scroller = new ScrollView(getActivity());

        // TextView goes in the ScrollView
        TextView text = new TextView(getActivity());

        // A TypedValue can hold multiple dimension values which can be assigned dynamically
        // applyDimensions receives the unit type to use which is COMPLEX_UNIT_DIP, which
        // is Device Independent Pixels
        // The padding amount being 4
        // The final part is information on the devices size and density
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        // Set the padding to the TextView
        text.setPadding(padding, padding, padding, padding);



        scroller.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));
        text.setBackgroundColor(0x80FFFFFF);
        text.setTextSize(20);

        // Add the TextView to the ScrollView
        scroller.addView(text);


        Resources desc = getResources();
        String[] descPontos = desc.getStringArray(R.array.desc_pontos);

        // Set the currently selected heros data to the textView
        text.setText(descPontos[getShownIndex()]);
        return scroller;
    }
}