package com.ybj.appmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ybj.appmvp.App;
import com.ybj.appmvp.R;
import com.ybj.appmvp.model.Repo;
import com.ybj.appmvp.util.FavoReposHelper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 杨阳洋 on 2017/12/30.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private Context mContext;

    private List<Repo> mRepoList;

    private LayoutInflater mLayoutInflater;

    public RepoListAdapter(Context context, List<Repo> repoList) {
        mContext = context;
        mRepoList = repoList;
        mLayoutInflater.from(mContext);
    }

    public void setRepoList(List<Repo> repoList) {
        mRepoList = repoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.repo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = mRepoList.get(position);

        holder.setRepoClick(repo);
        holder.title.setText(repo.getOwner() + " / " + repo.getName());
        if(TextUtils.isEmpty(repo.getDes())) {
            holder.description.setVisibility(View.GONE);
        }else{
            holder.description.setText(repo.getDes());
            holder.description.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(repo.getMeta())) {
            holder.meta.setVisibility(View.GONE);
        } else {
            holder.meta.setText(repo.getMeta());
            holder.meta.setVisibility(View.VISIBLE);
        }

        if (FavoReposHelper.getInstance().contains(repo)) {
            holder.starImage.setImageResource(R.drawable.ic_star_checked);
        } else {
            holder.starImage.setImageResource(R.drawable.ic_star_unchecked);
        }

        for (int i = 0; i < holder.avatars.size(); i++) {
            if (i < repo.getContributors().size()) {
                Picasso.with(App.getContext()).load(repo.getContributors().get(i).getAvatar()).into(holder.avatars.get(i));
                holder.avatars.get(i).setVisibility(View.VISIBLE);
            } else {
                holder.avatars.get(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Repo repo ;
        private final TextView title;
        private final TextView description;
        private final TextView meta;
        private final ImageView starImage;
        private List<CircleImageView> avatars;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            meta = (TextView) itemView.findViewById(R.id.meta);
            starImage = (ImageView) itemView.findViewById(R.id.star);
            avatars = new ArrayList<>();
            avatars.add((CircleImageView) itemView.findViewById(R.id.avatar1));
            avatars.add((CircleImageView) itemView.findViewById(R.id.avatar2));
            avatars.add((CircleImageView) itemView.findViewById(R.id.avatar3));
            avatars.add((CircleImageView) itemView.findViewById(R.id.avatar4));
            avatars.add((CircleImageView) itemView.findViewById(R.id.avatar5));
        }

        public void setRepoClick(Repo repo){
            this.repo = repo ;
            for(int i = 0 ; i < repo.getContributors().size() ; i ++){
                avatars.get(i).setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            String name ;

            switch (v.getId()) {
                case R.id.repo_card:
                    //条目的点击
                    name = repo.getOwner();
                    openUserRepoActivity(name);
                    break;
                case R.id.star:
                    //收藏
                    if (FavoReposHelper.getInstance().contains(repo)) {
                        FavoReposHelper.getInstance().removeFavo(repo);
                        starImage.setImageResource(R.drawable.ic_star_unchecked);
                    } else {
                        FavoReposHelper.getInstance().addFavo(repo);
                        starImage.setImageResource(R.drawable.ic_star_checked);
                    }
                    break;
                case R.id.avatar1:
                    name = repo.getContributors().get(0).getName();
                    openUserRepoActivity(name);
                    break ;
                case R.id.avatar2:
                    name = repo.getContributors().get(1).getName();
                    openUserRepoActivity(name);
                    break ;
                case R.id.avatar3:
                    name = repo.getContributors().get(2).getName();
                    openUserRepoActivity(name);
                    break ;
                case R.id.avatar4:
                    name = repo.getContributors().get(3).getName();
                    openUserRepoActivity(name);
                    break ;
                case R.id.avatar5:
                    name = repo.getContributors().get(4).getName();
                    openUserRepoActivity(name);
                    break ;
            }
        }

        private void openUserRepoActivity(String name){
//            Intent intent = new Intent(mContext, UserRepoActivity.class) ;
//            intent.putExtra("username",name);
//            mContext.startActivity(intent);
        }

    }
}
