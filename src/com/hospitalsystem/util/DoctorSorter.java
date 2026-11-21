package com.hospitalsystem.util;

import com.hospitalsystem.model.Doctor;
import java.util.List;

public class DoctorSorter {
    public static void sortByFeeAscending(List<Doctor> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getFee() < list.get(minIndex).getFee()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Doctor temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }
}
