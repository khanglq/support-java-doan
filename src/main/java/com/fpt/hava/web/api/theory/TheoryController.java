package com.fpt.hava.web.api.theory;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.service.TheoryService;
import com.fpt.hava.web.api.hava_manager.theory.TheoryApi;
import com.fpt.hava.web.api.hava_manager.theory.dto.TheoryDTO;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringEscapeUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TheoryController implements TheoryApi {

  private final TheoryService theoryService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<TheoryDTO>> getTheory(@ApiParam(value = "id of category",required=true) @PathVariable("id") String id) {

    List<TheoryDTO> theoryDTOS = new ArrayList<>();
    List<TheoryEntity> theoryEntity = theoryService.getAllByIdCat(Integer.valueOf(id));
    for (TheoryEntity item : theoryEntity){
      TheoryDTO theoryDTO = new TheoryDTO();

      item.setContents(item.getContents().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
      String escapedHTML = StringEscapeUtils.escapeHtml4(item.getContents());

      item.setContents(escapedHTML);
      
      modelMapper.map(item, theoryDTO);
      theoryDTOS.add(theoryDTO);
    }

    return ResponseEntity.ok(theoryDTOS);

  }
}
