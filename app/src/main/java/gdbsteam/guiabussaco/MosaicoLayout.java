package gdbsteam.guiabussaco;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// Shows the title fragment which is a ListView
// When a ListView item is selected we will put the DetailsFragment in the
// Framelayout if we are in horizontal mode, or we will create a DetailsActivity // and switch to it if in portrait mode
public class MosaicoLayout extends ListFragment {

    // True or False depending on if we are in horizontal or duel pane mode
    boolean mDuelPane;

    // Currently selected item in the ListView
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // An ArrayAdapter connects the array to our ListView
        // getActivity() returns a Context so we have the resources needed
        // We pass a default list item text view to put the data in and the
        // array

        Resources ptos = getResources();
        String[] pontosBussaco = ptos.getStringArray(R.array.pontos);

        ArrayAdapter<String> connectArrayToListView = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                pontosBussaco);

        // Connect the ListView to our data
        setListAdapter(connectArrayToListView);

        // Check if the FrameLayout with the id details exists
        View detailsFrame = getActivity().findViewById(R.id.descricao);

        // Set mDuelPane based on whether you are in the horizontal layout
        // Check if the detailsFrame exists and if it is visible
        mDuelPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // If the screen is rotated onSaveInstanceState() below will store the // hero most recently selected. Get the value attached to curChoice and // store it in mCurCheckPosition
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDuelPane) {
            // CHOICE_MODE_SINGLE allows one item in the ListView to be selected at a time
            // CHOICE_MODE_MULTIPLE allows multiple
            // CHOICE_MODE_NONE is the default and the item won't be highlighted in this case'
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            // Send the item selected to showDetails so the right hero info is shown
            showDetails(mCurCheckPosition);
        }
    }

    // Called every time the screen orientation changes or Android kills an Activity
    // to conserve resources
    // We save the last item selected in the list here and attach it to the key curChoice
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    // When a list item is clicked we want to change the hero info
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    // Shows the hero data
    void showDetails(int index) {

        // The most recently selected hero in the ListView is sent
        mCurCheckPosition = index;

        // Check if we are in horizontal mode and if yes show the ListView and
        // the hero data
        if (mDuelPane) {

            // Make the currently selected item highlighted
            getListView().setItemChecked(index, true);

            // Create an object that represents the current FrameLayout that we will
            // put the hero data in
            DescricaoFragment details = (DescricaoFragment)
                    getFragmentManager().findFragmentById(R.id.descricao);

            // When a DetailsFragment is created by calling newInstance the index for the data
            // it is supposed to show is passed to it. If that index hasn't been assigned we must
            // assign it in the if block
            if (details == null || details.getShownIndex() != index) {

                // Make the details fragment and give it the currently selected hero index
                details = DescricaoFragment.newInstance(index);

                // Start Fragment transactions
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                // Replace any other Fragment with our new Details Fragment with the right data
                ft.replace(R.id.descricao, details);

                // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Launch a new Activity to show our DetailsFragment
            Intent intent = new Intent();

            // Define the class Activity to call
            intent.setClass(getActivity(), DescricaoActivity.class);

            // Pass along the currently selected index assigned to the keyword index
            intent.putExtra("index", index);

            // Call for the Activity to open
            startActivity(intent);
        }
    }
}