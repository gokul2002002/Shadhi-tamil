package com.sayit.shadhi.Repositories;

import com.sayit.shadhi.Models.User;
import com.sayit.shadhi.Repositories.Implementation.FilterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUserName(@Param("userName") String userName);


    @Modifying
    @Query("UPDATE User u SET u.authority = :role WHERE u.userName = :username")
    public void updateRoleOfTheUser(@Param("username") String userName , @Param("role") String role);

    @Query("SELECT u FROM User u " +
            "WHERE u.age BETWEEN :fromAge AND :toAge " +
            "AND (:community IS NULL OR u.community = :community) " +
            "AND (:subCaste IS NULL OR u.subCaste = :subCaste) " +
            "AND (:startRange IS NULL OR :endRange IS NULL OR u.salaryPerAnnum BETWEEN :startRange AND :endRange) " +
            "AND (:religion IS NULL OR u.religion = :religion)")
    List<User> getUserBasedOnFilter(
            @Param("fromAge") int fromAge,
            @Param("toAge") int toAge,
            @Param("caste") String community,
            @Param("subCaste") String subCaste,
            @Param("startRange") Long startRange,
            @Param("endRange") Long endRange,
            @Param("religion") String religion
    );
}
