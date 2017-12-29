package com.ybj.appmvp.util;

import android.app.Application;
import android.content.Context;

import com.ybj.appmvp.model.Repo;

/**
 * Created by 杨阳洋 on 2017/12/29.
 */

public class FavoReposHelper {

    protected static FavoReposHelper instance;

    public static synchronized FavoReposHelper getInstance(){
        return instance;
    }

    public static void init(Application application){
        instance = new FavoReposHelper(application);
    }

    String favoReposJson = "";

    Context context;

    private FavoReposHelper(Context context) {
        this.context = context;
        favoReposJson = PreferenceManager.getString(context, "favo_repos", "");
    }

    public boolean contains(Repo repo){
        if(repo != null) {
            return favoReposJson.contains(repo.getHref());
        }
        return false;
    }

    public void addFavo(Repo repo) {
        favoReposJson = favoReposJson + "," + repo.getHref();
        saveToPref();
    }

    public void removeFavo(Repo repo) {
        favoReposJson = favoReposJson.replace(repo.getHref(),"");

        saveToPref();
    }

    private void saveToPref() {
        PreferenceManager.putString(context, "favo_repos", favoReposJson);
    }

}
