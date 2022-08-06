package com.barmej.guesstheanswer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShareActivity extends AppCompatActivity {

    private String mQuestionText;
    public EditText mEdiTextQuestionTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mEdiTextQuestionTitle = findViewById(R.id.Edit_Text_Share_Title);
        mQuestionText = getIntent().getStringExtra("Question Text Extra");
        SharedPreferences sharedPreferences = getSharedPreferences("app pref",MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("Share Title","");
        mEdiTextQuestionTitle.setText(questionTitle);


    }

    public void onQuestionShareClicked(View view){
        String questionTitle =mEdiTextQuestionTitle.getText().toString();
        Intent ShareIntent = new Intent();
        ShareIntent.setAction(Intent.ACTION_SEND);
        ShareIntent.putExtra(Intent.EXTRA_TEXT,questionTitle + "/n" + mEdiTextQuestionTitle);
        ShareIntent.setType("Text/plain");
        startActivity(ShareIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questionTitle);
        editor.apply();
    }
}