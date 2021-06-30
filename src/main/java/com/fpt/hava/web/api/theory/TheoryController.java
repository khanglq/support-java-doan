package com.fpt.hava.web.api.theory;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.service.TheoryService;
import com.fpt.hava.web.api.hava_manager.theory.ApiUtil;
import com.fpt.hava.web.api.hava_manager.theory.TheorysApi;
import com.fpt.hava.web.api.hava_manager.theory.dto.TheoryDTO;
import io.swagger.annotations.ApiParam;
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
public class TheoryController implements TheorysApi {
  private final ModelMapper modelMapper;
  private final TheoryService theoryService;

  public ResponseEntity<TheoryDTO> getTheory(@ApiParam(value = "id of theory",required=true) @PathVariable("id") Integer id) {
    TheoryDTO theoryDTO = new TheoryDTO();
    TheoryEntity theoryEntity = theoryService.getAllById(id);

    modelMapper.map(theoryEntity, theoryDTO);

    return ResponseEntity.ok(theoryDTO);

  }
}
