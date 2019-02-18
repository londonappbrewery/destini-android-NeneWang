package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    Button buttonTop, buttonBotton;
    TextView storyTextView;


    final int FIRST_END_STAGE = 4;

    enum buttonClicked
    {
        TOP_BUTTON, BOTTON_BUTTON;
    }


    int[] answerOnesArray = {
            R.string.T1_Ans2,
            R.string.T1_Ans1,
            R.string.T2_Ans1,
            R.string.T3_Ans1
    };

    int[] answerTwosArray = {
            R.string.T1_Ans2,
            R.string.T1_Ans2,
            R.string.T2_Ans2,
            R.string.T3_Ans2
    };

    int[] storiesArray = {
            R.string.T1_Story,
            R.string.T1_Story,
            R.string.T2_Story,
            R.string.T3_Story,
            R.string.T4_End,
            R.string.T5_End,
            R.string.T6_End,
    };

    int[] topChoicesResultArrangedByStage = {
            1,3,3,6
    };

    int[] bottonChoicesResultArrangedByStage = {
            1,2,4,5
    };


    int i_currentStage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        buttonTop = (Button)findViewById(R.id.buttonTop);
        buttonBotton = (Button)findViewById(R.id.buttonBottom);

        storyTextView = (TextView)findViewById(R.id.storyTextView);


        View.OnClickListener myListener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // String toastMessage = "";
                switch (view.getId())
                {
                    case R.id.buttonTop :
                        processChoice(buttonClicked.TOP_BUTTON);
                        break;
                    case R.id.buttonBottom :
                        processChoice(buttonClicked.BOTTON_BUTTON);
                        break;
                }
                UpdateViews();
            }
        };


        UpdateViews();
        buttonTop.setOnClickListener(myListener);
        buttonBotton.setOnClickListener(myListener);
        if(savedInstanceState != null) {
            i_currentStage = savedInstanceState.getInt("i_lastStage");
            UpdateViews();

        }


        }

    //Intended to be activated each time that a choice is selected and also in the start,it changes the Btns and Tv texts
    void UpdateViews(){

        storyTextView.setText(storiesArray[i_currentStage]);

        if(i_currentStage >= FIRST_END_STAGE){
            buttonTop.setVisibility(View.GONE);
            buttonBotton.setVisibility(View.GONE);
            return;
        }
        buttonTop.setText(answerOnesArray[i_currentStage]);
        buttonBotton.setText(answerTwosArray[i_currentStage]);

    }

    //Intended to be called each time user select an option
    //INPUT : global Current stage & If it was selected botton or Top
    void processChoice (buttonClicked buttonClicked){

        //buttonClicked buttonClicked = MainActivity.buttonClicked.values()[buttonClickedInteger];

        switch (buttonClicked){
            case TOP_BUTTON:
                i_currentStage =  topChoicesResultArrangedByStage[i_currentStage];
                break;

            case BOTTON_BUTTON:
                i_currentStage =  bottonChoicesResultArrangedByStage[i_currentStage];
                break;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("i_lastStage", i_currentStage);
    }


}
