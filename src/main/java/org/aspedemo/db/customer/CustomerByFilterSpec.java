package org.aspedemo.db.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomerByFilterSpec implements Specification<Customer> {
	
	private Map<String, Object> filter = null;
	
	CustomerByFilterSpec(Map<String, Object> filter) {
		this.filter = filter;
	}
	
	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		
		for (Entry<String, Object> criteria : filter.entrySet()) {
			String name  = criteria.getKey();
			Object value = criteria.getValue();
			
			if (value != null && !value.equals("")) {
				
				// like %...%
				if ("firstName".equals(name) || "lastName".equals(name) || "street".equals(name) || "city".equals(name)) {
					predicates.add(builder.like(root.get(name), "%" + value + "%"));
				}
				
				// like ...%
				else if ("zipcode".equals(name)) {
					predicates.add(builder.like(root.get(name), value + "%"));
				}
				
				// exact match
				else if ("id".equals(name) || "email".equals(name) || "phone".equals(name)) {
					predicates.add(builder.equal(root.get(name), value));
				}
			}
		}
		
		return builder.and(predicates.toArray(new Predicate[0]));
	}
	
}
