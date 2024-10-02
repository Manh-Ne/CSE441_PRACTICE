package com.example.pj_prac02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StaffViewModel {
    private final MutableLiveData<List<Staff>> staffList = new MutableLiveData<>();
    private final List<Staff> staffData = new ArrayList<>();

    public LiveData<List<Staff>> getStaffList(){
        return staffList;
    }
    public void addStaff(int id, String name, String birthDay, String salary){
        Staff newStaff = new Staff(id, name, birthDay, salary);
        staffData.add(newStaff);
        staffList.setValue(staffData);
    }
}
