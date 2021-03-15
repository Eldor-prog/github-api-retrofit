package uz.eldor.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uz.eldor.githubapi.models.User;
import uz.eldor.githubapi.retrofit.ApiClient;
import uz.eldor.githubapi.retrofit.ApiInterface;

public class InfoActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tv1, tv2;
    private User body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        progressBar = findViewById(R.id.progres_bar);
        tv1 = findViewById(R.id.text_view1);
        tv2 = findViewById(R.id.text_view2);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");

        ApiClient apiClient = new ApiClient();
        Retrofit retrofit = apiClient.getRetrofit();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getUser(login).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    body = response.body();
                    tv1.setText(body.getName());
                    tv2.setText(body.getBlog());
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        tv2.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, BlogActivity.class);
            intent1.putExtra("blog", body.getBlog());
            startActivity(intent1);
        });

    }
}