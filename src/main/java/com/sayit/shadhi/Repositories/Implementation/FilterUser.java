package com.sayit.shadhi.Repositories.Implementation;

import com.sayit.shadhi.Models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterUser {
     List<User> getUserBasedOnFilter(
            int fromAge, int toAge, String caste, String subCaste, List<Long> salaryRange, String religion
            );
}
