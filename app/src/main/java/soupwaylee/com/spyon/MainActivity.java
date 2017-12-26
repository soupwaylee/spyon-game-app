package soupwaylee.com.spyon;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGame = (Button) findViewById(R.id.start_game);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View mView = getLayoutInflater().inflate(R.layout.dialog_numberofplayers, null);

                builder.setView(mView);
                final AlertDialog playerNumberDialog = builder.create();
                playerNumberDialog.show();
                playerNumberDialog.setCancelable(false);
                playerNumberDialog.setCanceledOnTouchOutside(false);

                final TextView selectedNumberTextView = mView.findViewById(R.id.numberOfPlayersTextView);

                NumberPicker playerNumberPicker = mView.findViewById(R.id.numberOfPlayersPicker);
                playerNumberPicker.setMinValue(3);
                playerNumberPicker.setMaxValue(20);
                playerNumberPicker.setWrapSelectorWheel(false);
                playerNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        numberOfPlayers = newVal;
                        selectedNumberTextView.setText(String.format("Players: %d", numberOfPlayers));
                    }
                });

                // todo add small margin between the buttons..
                Button mConfirm = mView.findViewById(R.id.numberOfPlayersConfBtn);
                mConfirm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,
                            String.format("Confirmed %d Players", numberOfPlayers),
                            Toast.LENGTH_SHORT).show();
                    }
                });

                Button mCancel = mView.findViewById(R.id.numberOfPlayersCancelBtn);
                mCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        playerNumberDialog.dismiss();
                    }
                });


            }
        });
    }
}
