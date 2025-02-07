package com.sayit.shadhi.Repositories.Implementation;

import com.sayit.shadhi.Models.User;

import java.util.List;

@FunctionalInterface
public interface FilterUser {

    public List<User> getUserBasedOnFilter(
              int fromAge
            , int toAge
            , String caste
            , String subCaste
            , List<Long> salaryRange
            , String Religion
            );
}
