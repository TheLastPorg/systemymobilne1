package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex;
    private int questionsAnswered;
    private int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chceckAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chceckAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }
    private Question[] questions = new Question[] {
            new Question(R.string.q1, true),
            new Question(R.string.q2, false),
            new Question(R.string.q3, true),
            new Question(R.string.q4, true),
            new Question(R.string.q5, false)
    };
    private void chceckAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
            correctAnswers++;
        }
        else {
            resultMessageId = R.string.incorrect_answer;
        }
        questionsAnswered++;
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion() {
        if(questionsAnswered == 5) {
            Toast.makeText(this, "Poprawnych odpowiedzi: " + correctAnswers, Toast.LENGTH_SHORT).show();
            questionsAnswered = 0;
            correctAnswers = 0;
        }
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }
}
