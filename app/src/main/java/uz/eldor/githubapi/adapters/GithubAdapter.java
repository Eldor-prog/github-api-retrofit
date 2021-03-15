package uz.eldor.githubapi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import uz.eldor.githubapi.R;
import uz.eldor.githubapi.models.GitHubUser;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.VH> {

    private List<GitHubUser> gitHubUserList;
    private OnItemClickListener listener;

    public GithubAdapter(List<GitHubUser> gitHubUserList, OnItemClickListener listener) {
        this.gitHubUserList = gitHubUserList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_user, parent, false);
        VH vh =  new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        GitHubUser gitHubUser = gitHubUserList.get(position);
        Glide.with(holder.itemView).load(gitHubUser.getAvatarUrl()).into(holder.circleImageView);
        holder.textView.setText(gitHubUser.getLogin());

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(gitHubUser);
        });

        holder.button.setOnClickListener(v -> {
            listener.onUrlButtonClick(gitHubUser);
        });
    }

    @Override
    public int getItemCount() {
        return gitHubUserList.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView textView;
        Button button;

        public VH(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.login);
            button = itemView.findViewById(R.id.github_button);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(GitHubUser gitHubUser);

        void onUrlButtonClick(GitHubUser gitHubUser);
    }
}
