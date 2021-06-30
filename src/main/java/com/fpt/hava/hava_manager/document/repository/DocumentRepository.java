package com.fpt.hava.hava_manager.document.repository;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentsEntity, Integer> {
  List<DocumentsEntity> findByDocumentCategoryId(Integer id);
}
