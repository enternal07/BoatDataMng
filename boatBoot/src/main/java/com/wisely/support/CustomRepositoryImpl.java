package com.wisely.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.wisely.specs.CustomerSpecs;

import static com.wisely.specs.CustomerSpecs.*;

public class CustomRepositoryImpl <T, ID extends Serializable> 
					extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID> {
	
	private final EntityManager entityManager;
	
	public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {
		return findAll(CustomerSpecs.byAuto(entityManager, example),pageable);
	}

	public List<T> findAllByCondtion(T example,int rateS,int rateM) {
		// TODO Auto-generated method stub
		return findAll(CustomerSpecs.byAuto(entityManager, example));
	}


}
