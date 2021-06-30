package com.fpt.hava.web.api.document;

import com.fpt.hava.hava_manager.document.DocumentCategoriesEntity;
import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.document.service.DocumentCategoryService;
import com.fpt.hava.hava_manager.document.service.DocumentService;
import com.fpt.hava.web.api.hava_manager.document.dto.DocumentDTO;
import com.fpt.hava.web.api.hava_manager.document_categories.DocumentCategoriesApi;
import com.fpt.hava.web.api.hava_manager.document_categories.dto.DocumentCategoryDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DocumentCategoryController implements DocumentCategoriesApi {

  private final DocumentCategoryService documentCategoryService;
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<List<DocumentCategoryDTO>> getDocumentCategories() {
    List<DocumentCategoryDTO> documentDTO = new ArrayList<>();
    List<DocumentCategoriesEntity> documentsEntity = documentCategoryService.getAllDocumentCategories();

    for(DocumentCategoriesEntity item : documentsEntity){
      if(item.getId() == 10) continue;

      DocumentCategoryDTO doc = new DocumentCategoryDTO();

      modelMapper.map(item, doc);
      documentDTO.add(doc);
    }


    return ResponseEntity.ok(documentDTO);

  }

}
