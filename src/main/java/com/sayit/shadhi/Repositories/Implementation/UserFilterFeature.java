package com.sayit.shadhi.Repositories.Implementation;

import com.sayit.shadhi.Exceptions.AgeNotSupportedException;
import com.sayit.shadhi.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserFilterFeature implements FilterUser{

    @PersistenceContext
    private  final EntityManager entityManager;

    @Override
    public List<User> getUserBasedOnFilter(int fromAge, int toAge, String caste, String subCaste, List<Long> salaryRange, String religion)
    throws AgeNotSupportedException
    {
        StringBuilder filterString = new StringBuilder(
                "SELECT * FROM User WHERE"
        );
        if(fromAge > 21 && toAge > fromAge){
            filterString.append(" age BETWEEN :fromAge AND :toAge");
        }else {
            throw new AgeNotSupportedException("Age not supported , change the age range and try again");
        }
        if (caste != null){
            filterString.append(" AND caste = :caste");
        }
        if (subCaste != null){
            filterString.append(" AND subCaste = :subCaste");
        }
        if(salaryRange.indexOf(0) != 0 && salaryRange.indexOf(1) > salaryRange.indexOf(0)){
            filterString.append(" AND salaryRange BETWEEN :startRange AND :endRange");
        }
        TypedQuery<User> filteredUser= entityManager.createQuery(filterString.toString() , User.class);
        filteredUser.setParameter("fromAge", fromAge);
        filteredUser.setParameter("toAge", toAge);

        if (caste != null) {
            filteredUser.setParameter("caste", caste);
        }
        if (subCaste != null) {
            filteredUser.setParameter("subCaste", subCaste);
        }
        if (salaryRange != null && salaryRange.size() == 2 && salaryRange.get(1) > salaryRange.get(0)) {
            filteredUser.setParameter("startRange", salaryRange.get(0));
            filteredUser.setParameter("endRange", salaryRange.get(1));
        }
        if (religion != null) {
            filteredUser.setParameter("religion", religion);
        }

        return filteredUser.getResultList();
    }
}
