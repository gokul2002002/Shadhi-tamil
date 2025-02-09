package com.sayit.shadhi.Repositories;

import com.sayit.shadhi.Models.ChartRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<ChartRequest , Long> {

    @Query(value = "SELECT ChartRequest crec WHERE crec.brideID = :brideID AND crec.groomID = :groomId" , nativeQuery = true)
    public List<?> getDocuments(@Param("brideID") long brideId , @Param("groomId") long groomId);

    @Query(value = "SELECT ChartRequest crec WHERE crec.astrologer.astrologerId = :astrologerId" , nativeQuery = true)
    public List<ChartRequest> getChartsOfTheAstrologer(@Param("astrologerId") long astrologerId);
}
