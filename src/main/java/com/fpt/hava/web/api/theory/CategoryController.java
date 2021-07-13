package com.fpt.hava.web.api.theory;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.domain.TheoryDTO;
import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.hava_manager.theory.service.TheoryService;
import com.fpt.hava.web.api.hava_manager.theory.CategoryApi;
import com.fpt.hava.web.api.hava_manager.theory.dto.CategoryDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController implements CategoryApi {
  private final ModelMapper modelMapper;
  private final CategoryService categoryService;
  private final TheoryService theoryService;

  public ResponseEntity<List<CategoryDTO>> getCategory(@ApiParam(value = "id is parent_id in [categories] table and category_id in [theories] table too",required=true) @PathVariable("id") String id) {
    List<TheoryEntity> theoryEntities = theoryService.getAllByIdCat(Integer.valueOf(id));
    List<CategoryDTO> categoryDTOS = new ArrayList<>();

    List<CategoryEntity> categoryEntities = categoryService.findAllByParentId(id);
    for (CategoryEntity item : categoryEntities){
      CategoryDTO categoryDTO = new CategoryDTO();
      modelMapper.map(item, categoryDTO);
      categoryDTO.setExamId(0);
      categoryDTO.setContents("");
      categoryDTOS.add(categoryDTO);
    }

    for (TheoryEntity item : theoryEntities){
      CategoryDTO categoryDTO = new CategoryDTO();
      item.setContents(item.getContents().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      item.setContents(item.getContents().replaceAll("border-top:none;", ""));
      item.setContents(item.getContents().replaceAll("border-top:solid;", ""));
      item.setContents(item.getContents().replaceAll("border-top:solid", ""));
      item.setContents(item.getContents().replaceAll("border-bottom:none;", ""));
      item.setContents(item.getContents().replaceAll("border-bottom:solid;", ""));
      item.setContents(item.getContents().replaceAll("border-bottom:solid", ""));
      item.setContents(item.getContents().replaceAll("border:none;", ""));
      item.setContents(item.getContents().replaceAll("border:solid;", ""));
      item.setContents(item.getContents().replaceAll("border:solid", ""));
      item.setContents(item.getContents().replaceAll("border-left:none;", ""));
      item.setContents(item.getContents().replaceAll("border-left:solid;", ""));
      item.setContents(item.getContents().replaceAll("border-left:solid", ""));
      item.setContents(item.getContents().replaceAll("border-right:none;", ""));
      item.setContents(item.getContents().replaceAll("border-right:solid;", ""));
      item.setContents(item.getContents().replaceAll("border-right:solid", ""));

      String escapedHTML = StringEscapeUtils.escapeHtml4(item.getContents());
      item.setContents(escapedHTML);

      modelMapper.map(item, categoryDTO);
      categoryDTOS.add(categoryDTO);
    }
    return ResponseEntity.ok(categoryDTOS);

  }
}
