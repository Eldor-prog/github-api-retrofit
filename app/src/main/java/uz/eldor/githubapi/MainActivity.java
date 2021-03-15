package uz.eldor.githubapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uz.eldor.githubapi.adapters.GithubAdapter;
import uz.eldor.githubapi.models.GitHubUser;
import uz.eldor.githubapi.retrofit.ApiClient;
import uz.eldor.githubapi.retrofit.ApiInterface;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GithubAdapter githubAdapter;
    private List<GitHubUser> gitHubUserList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress);

        ApiClient apiClient = new ApiClient();
        Retrofit retrofit = apiClient.getRetrofit();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getGithubUsers().enqueue(new Callback<List<GitHubUser>>() {
            @Override
            public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    gitHubUserList = response.body();
                    githubAdapter = new GithubAdapter(gitHubUserList, new GithubAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(GitHubUser gitHubUser) {
                            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                            intent.putExtra("login", gitHubUser.getLogin());
                            startActivity(intent);
                        }

                        @Override
                        public void onUrlButtonClick(GitHubUser gitHubUser) {
                            Intent intent = new Intent(MainActivity.this, GithubActivity.class);
                            intent.putExtra("url", gitHubUser.getHtmlUrl());
                            startActivity(intent);

                        }
                    });
                    DividerItemDecoration dividerItemDecoration =
                            new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(githubAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<GitHubUser>> call, Throwable t) {

            }
        });


    }

    private void loadData() {
        gitHubUserList = new ArrayList<>();
        GitHubUser gitHubUser = new GitHubUser();
        gitHubUser.setLogin("Eldor");
        gitHubUser.setAvatarUrl("https://avatars0.githubusercontent.com/u/1?v=4");
        gitHubUser.setHtmlUrl("https://github.com/mojombo");

        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
        gitHubUserList.add(gitHubUser);
    }
}