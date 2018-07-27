package com.wisely.support;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.wisely.domain.Item;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable>extends JpaRepository<T, ID> ,JpaSpecificationExecutor<T>{
	
	Page<T> findByAuto(T item,Pageable pageable);
	
	List<T> findAllByCondtion(T example,int rateS,int rateM) ;

}
