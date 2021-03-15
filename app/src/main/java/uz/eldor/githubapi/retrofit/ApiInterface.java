package uz.eldor.githubapi.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uz.eldor.githubapi.models.GitHubUser;
import uz.eldor.githubapi.models.User;

public interface ApiInterface {

    @GET("users")
    Call<List<GitHubUser>> getGithubUsers();

    @GET("users/{login}")
    Call<User> getUser(@Path("login") String login);
}
