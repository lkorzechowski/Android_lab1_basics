package com.orzechowski.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean[] condition = {false, false, false};
    public boolean verifyCompletion(){
        return condition[0] && condition[1] && condition[2];
    }

    public void buttonShowHide(){
        Button button = findViewById(R.id.button1);
        if(verifyCompletion()) button.setVisibility(View.VISIBLE);
        else button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText input1 = findViewById(R.id.input1);
        EditText input2 = findViewById(R.id.input2);
        EditText input3 = findViewById(R.id.input3);
        if(input1.getText().toString().length() < 1) input1.setError(null);
        if(input2.getText().toString().length() < 1) input2.setError(null);
        if(input3.getText().toString().length() < 1) input3.setError(null);
    }

    @Override
    protected void onResume() {
        Bundle pakunek = getIntent().getExtras();
        super.onResume();
        if(pakunek!=null) {
            Button button = findViewById(R.id.button_exit);
            TextView text = findViewById(R.id.text4);
            button.setVisibility(View.VISIBLE);
            int suma = 0;
            for (int i = 0; i < pakunek.getInt("liczba"); i++) {
                suma = suma + pakunek.getInt("ocena"+i);
            }
            float srednia = (float) suma / pakunek.getInt("liczba");
            if (srednia > 3) {
                text.setText("Gratulacje, twoja średnia to: " + srednia);
                button.setText("Super!");
            } else {
                text.setText("Niestety, twoja średnia to jedynie: " + srednia);
                button.setText("Tym razem mi nie poszło.");
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (srednia > 3){
                        Toast komunikat = Toast.makeText(getApplicationContext(),
                                "Gratulacje! Otrzymujesz Zaliczenie!", Toast.LENGTH_SHORT);
                        komunikat.show();
                    } else {
                        Toast komunikat = Toast.makeText(getApplicationContext(),
                                "Wysyłam podanie o zaliczenie warunkowe.", Toast.LENGTH_SHORT);
                        komunikat.show();
                    }
                    finishAffinity();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button1);
        button.setVisibility(View.INVISIBLE);
        EditText input1 = findViewById(R.id.input1);
        EditText input2 = findViewById(R.id.input2);
        EditText input3 = findViewById(R.id.input3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int liczba = Integer.parseInt(input3.getText().toString());
                Intent toRecycle = new Intent(MainActivity.this, Recycler.class);
                toRecycle.putExtra("imie", input1.getText().toString());
                toRecycle.putExtra("nazwisko", input2.getText().toString());
                toRecycle.putExtra("liczba", liczba);
                button.setVisibility(View.INVISIBLE);
                startActivity(toRecycle);
            }
        });

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                condition[0] = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String evaluate = input1.getText().toString();
                if(evaluate.matches(".*\\d.*")) input1.setError("Wpisz imię bez cyfr");
                else if(evaluate.length()==0) input1.setError("Nazwisko nie może być puste");
                else condition[0] = true;

                buttonShowHide();
            }
        });

        input1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!condition[0] && input1.getText().toString().length()>0){
                    CharSequence tekst = "Nie podano poprawnego imienia";
                    Toast komunikat = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
                    komunikat.show();
                }
            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                condition[1] = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String evaluate = input2.getText().toString();
                if(evaluate.matches(".*\\d.*")) input2.setError("Wpisz nazwisko bez cyfr");
                else if(evaluate.length()==0) input2.setError("Nazwisko nie może być puste");
                else condition[1] = true;

                buttonShowHide();
            }
        });

        input2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!condition[1] && input2.getText().toString().length()>0){
                    CharSequence tekst = "Nie podano poprawnego nazwiska";
                    Toast komunikat = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
                    komunikat.show();
                }
            }
        });

        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                condition[2] = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String evaluate = input3.getText().toString();
                boolean letters = false;
                for(int i = 0; i < evaluate.length(); i++)
                    if(evaluate.charAt(i) < 48 || evaluate.charAt(i) > 57) letters = true;
                if(letters) input3.setError("Wpisz liczbę bez liter");
                else if(evaluate.length()==0) input3.setError("Pole nie może być puste");
                else if(Integer.parseInt(evaluate)<5 || Integer.parseInt(evaluate)>15) input3.setError("Podana ilość musi leżeć w zakresie 5-15");
                else condition[2] = true;

                buttonShowHide();
            }
        });

        input3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!condition[2] && input3.getText().toString().length()>0){
                    CharSequence tekst = "Nie podano poprawnej liczby";
                    Toast komunikat = Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT);
                    komunikat.show();
                }
            }
        });
    }
}