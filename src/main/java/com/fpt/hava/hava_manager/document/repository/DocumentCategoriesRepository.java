package com.fpt.hava.hava_manager.document.repository;

import com.fpt.hava.hava_manager.document.DocumentCategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentCategoriesRepository extends JpaRepository<DocumentCategoriesEntity, Integer> {

}
