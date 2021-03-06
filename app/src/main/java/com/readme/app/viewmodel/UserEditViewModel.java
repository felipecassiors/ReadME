package com.readme.app.viewmodel;

import android.app.Application;

import com.readme.app.model.database.AppDatabase;
import com.readme.app.model.database.dao.UserDao;
import com.readme.app.model.entity.User;
import com.readme.app.view.activity.SessionManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class UserEditViewModel extends AndroidViewModel {

    private SessionManager sessionManager;

    private UserDao dao;
    private User newItem;
    private User oldItem;

    private boolean editing;

    private boolean initialized = false;

    public UserEditViewModel(@NonNull Application application) {
        super(application);
        dao = AppDatabase.getInstance(application).getUserDao();
        sessionManager = SessionManager.getInstance(application);
    }

    public void initialize(final int id) {
        if(!initialized) {
            editing = id != -1;

            newItem = new User();

            if(editing) {
                oldItem = dao.getById(id);
                newItem.setId(oldItem.getId());
            }

            initialized = true;
        }
    }

    public void save() {
        dao.save(newItem);
    }

    public void delete() {
        dao.delete(oldItem);
    }

    public boolean isEditing() {
        return editing;
    }

    public User getNewItem() {
        return newItem;
    }

    public User getOldItem() {
        return oldItem;
    }
}
