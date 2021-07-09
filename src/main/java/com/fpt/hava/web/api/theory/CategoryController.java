package com.fpt.hava.web.api.theory;

import com.fpt.hava.hava_manager.theory.domain.CategoryEntity;
import com.fpt.hava.hava_manager.theory.service.CategoryService;
import com.fpt.hava.web.api.hava_manager.theory.CategoryApi;
import com.fpt.hava.web.api.hava_manager.theory.dto.CategoryDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

  public ResponseEntity<List<CategoryDTO>> getCategory(@ApiParam(value = "id of parent",required=true) @PathVariable("id") String id) {
    List<CategoryDTO> categoryDTOS = new ArrayList<>();
    List<CategoryEntity> categoryEntities = categoryService.getCatByParentId(id);

    for (CategoryEntity item : categoryEntities){
      CategoryDTO categoryDTO = new CategoryDTO();

      modelMapper.map(item,categoryDTO);
      categoryDTOS.add(categoryDTO);
    }

    return ResponseEntity.ok(categoryDTOS);

  }
}
