package com.example.android.todolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

// DONE (5) Make this class extend ViewModel
public class AddTaskViewModel extends ViewModel {

    // Done (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    LiveData<TaskEntry> mData;

    // DONE (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddTaskViewModel(AppDatabase db, int taskId){
        mData = db.taskDao().loadTaskById(taskId);
    }

    // DONE (7) Create a getter for the task variable
    public LiveData<TaskEntry> getTask(){
        return mData;
    }
}
