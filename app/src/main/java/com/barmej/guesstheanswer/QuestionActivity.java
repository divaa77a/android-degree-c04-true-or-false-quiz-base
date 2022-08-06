package com.barmej.guesstheanswer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.intellij.lang.annotations.Language;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private TextView mTextQouestion;

    private  String[] QUESTIONS;

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true
    };

    private String[] ANSWERS_DETAILS;

    private  String mCurronQustion, mCurronAnswrsDetails;
    private boolean mCurronAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("app pref" ,MODE_PRIVATE);
        String applang = sharedPreferences.getString("app lang","");
        if (!applang.equals(""))
            LocaleHelper.setLocale(this,applang);

        setContentView(R.layout.activity_question);
        mTextQouestion = findViewById(R.id.text_question);
        QUESTIONS = getResources().getStringArray(R.array.questions);
        ANSWERS_DETAILS = getResources().getStringArray(R.array.answers_details);
        ShowNuwQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meun,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuChangeLang) {
            showLanguageDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLanguageDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.Language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }
                        saveLanguage(language);
                        LocaleHelper.setLocale(QuestionActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).create();
        alertDialog.show();
    }

    private void saveLanguage(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app pref" ,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app lang",lang);
        editor.apply();
    }

    private void ShowNuwQuestion(){
        Random random = new Random();
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurronQustion = QUESTIONS[randomQuestionIndex];
        mCurronAnswers = ANSWERS[randomQuestionIndex];
        mCurronAnswrsDetails = ANSWERS_DETAILS[randomQuestionIndex];
        mTextQouestion.setText(mCurronQustion);
    }

    public void onChangeQuestion(View view){
        ShowNuwQuestion();

    }

    public void onTrueClicked(View view){
        if (mCurronAnswers == true){
            Toast.makeText(this,"الاجابه صحيحه" ,Toast.LENGTH_LONG).show();
            ShowNuwQuestion();
        }else{
           Toast.makeText(this,"الاجابه خطأ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuestionActivity.this,AnswersActivity.class);
            intent.putExtra("question_answers",mCurronAnswrsDetails);
            startActivity(intent);
        }
    }

    public void onFalseClicked(View view){
        if (mCurronAnswers == false){
           Toast.makeText(this,"الاجابه صحيحه",Toast.LENGTH_LONG).show();
            ShowNuwQuestion();
        }else {
           Toast.makeText(this,"الاجابه خطأ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuestionActivity.this,AnswersActivity.class);
            intent.putExtra("question_answers",mCurronAnswrsDetails);
            startActivity(intent);
        }
    }

    public void onQuestionShareClicked(View view){
        Intent intent = new Intent(QuestionActivity.this,ShareActivity.class);
        intent.putExtra("Question Text Extra",mCurronQustion);
        startActivity(intent);
    }



}
