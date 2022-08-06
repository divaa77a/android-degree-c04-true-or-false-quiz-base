package com.barmej.guesstheanswer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswersActivity extends AppCompatActivity {

    private TextView mTextAnswersDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        mTextAnswersDetails = findViewById(R.id.TEXT_VIEW_ANSWERS_DETAILS);
        String answers = getIntent().getStringExtra("question_answers");
        if (answers != null){
            mTextAnswersDetails.setText(answers);
        }
    }
    public void onClickReturn (View view){
        finish();
    }

}