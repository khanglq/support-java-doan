package com.fpt.hava.web.api.document;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.document.service.DocumentService;
import com.fpt.hava.web.api.hava_manager.document.ApiUtil;
import com.fpt.hava.web.api.hava_manager.document.DocumentsApi;
import com.fpt.hava.web.api.hava_manager.document.dto.DocumentDTO;
import com.fpt.hava.web.api.hava_manager.document_categories.dto.DocumentCategoryDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DocumentController implements DocumentsApi {

  private final DocumentService documentService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<DocumentDTO>> getDocument(@ApiParam(value = "id of document category",required=true) @PathVariable("id") Integer id) {
    List<DocumentsEntity> documentsEntity = documentService.findByDocumentCategoryId(id);
    List<DocumentDTO> documentDTO = new ArrayList<>();

    for(DocumentsEntity item : documentsEntity){
      DocumentDTO doc = new DocumentDTO();
      modelMapper.map(item, doc);

      documentDTO.add(doc);
    }

    return ResponseEntity.ok(documentDTO);

  }

  public ResponseEntity<List<DocumentDTO>> getDocument() {
    List<DocumentsEntity> documentsEntity = documentService.findAllDocuments();
    List<DocumentDTO> documentDTO = new ArrayList<>();

    for(DocumentsEntity item : documentsEntity){
      if (item.getDocumentCategoryId() == 10) continue;
      DocumentDTO doc = new DocumentDTO();
      modelMapper.map(item, doc);

      documentDTO.add(doc);
    }

    return ResponseEntity.ok(documentDTO);

  }

}
