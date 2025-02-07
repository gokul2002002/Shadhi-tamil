package com.sayit.shadhi.Repositories;

import com.sayit.shadhi.Models.AccesRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<AccesRequest , Long> {

}
