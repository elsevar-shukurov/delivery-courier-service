package com.example.mscourier.specification;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.criteria.CourierCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CourierSpecification implements Specification<Courier> {

    private final CourierCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
        }
        if (criteria.getMinCreatedAt() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), criteria.getMinCreatedAt()));
        }
        if (criteria.getMaxCreatedAt() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), criteria.getMaxCreatedAt()));
        }

        boolean hasProfileCriteria = StringUtils.hasText(criteria.getName()) ||
                StringUtils.hasText(criteria.getSurname()) ||
                StringUtils.hasText(criteria.getPhone()) ||
                StringUtils.hasText(criteria.getVehicleType()) ||
                StringUtils.hasText(criteria.getLicensePlate());

        if (hasProfileCriteria) {
            var profileJoin = root.join("profile");
            if (StringUtils.hasText(criteria.getName())) {
                predicates.add(cb.like(cb.lower(profileJoin.get("name")),
                        "%" + criteria.getName().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(criteria.getSurname())) {
                predicates.add(cb.like(cb.lower(profileJoin.get("surname")),
                        "%" + criteria.getSurname().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(criteria.getPhone())) {
                predicates.add(cb.equal(profileJoin.get("phone"), criteria.getPhone()));
            }
            if (StringUtils.hasText(criteria.getVehicleType())) {
                predicates.add(cb.like(cb.lower(profileJoin.get("vehicleType")),
                        "%" + criteria.getVehicleType().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(criteria.getLicensePlate())) {
                predicates.add(cb.equal(profileJoin.get("licensePlate"), criteria.getLicensePlate()));
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}