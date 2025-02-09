package com.sayit.shadhi.Repositories;


import com.sayit.shadhi.Models.Astrologer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AstrologerRepository extends JpaRepository<Astrologer , Long> {

     @Query("SELECT a FROM Astrologer WHERE a.price BETWEEN :startFrom AND :endAt")
     public List<Astrologer> getAllAstrologerBetweenTheRange(
             @Param("startFrom") double startfrom ,@Param("endAt") double endAt
     );
}
