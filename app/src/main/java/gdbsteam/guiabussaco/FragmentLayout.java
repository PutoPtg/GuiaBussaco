package gdbsteam.guiabussaco;

/**
 * Created by Manuel on 28/12/2014.
 */
import android.app.Activity;
import android.os.Bundle;

public class FragmentLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for fragment_layout.xml
        setContentView(R.layout.guia_lista);
    }

}