package com.sayit.shadhi.Repositories;

import com.sayit.shadhi.Models.ChartRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChartRepository extends JpaRepository<ChartRequest , Long> {

    @Query(value = "SELECT ChartRequest crec WHERE crec.brideID = :brideID AND crec.groomID = :groomId" , nativeQuery = true)
    public List<?> getDocuments(@Param("brideID") long brideId , @Param("groomId") long groomId);
}
