package com.fpt.hava.web.api.theory;

import com.fpt.hava.hava_manager.theory.domain.TheoryEntity;
import com.fpt.hava.hava_manager.theory.service.TheoryService;
import com.fpt.hava.web.api.hava_manager.theory.ApiUtil;
import com.fpt.hava.web.api.hava_manager.theory.TheoryApi;
import com.fpt.hava.web.api.hava_manager.theory.dto.TheoryDTO;
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
import org.apache.commons.lang3.StringEscapeUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TheoryController implements TheoryApi {

  private final TheoryService theoryService;
  private final ModelMapper modelMapper;

  public ResponseEntity<TheoryDTO> getTheory(@ApiParam(value = "id of theory",required=true) @PathVariable("id") String id) {
    TheoryEntity theoryEntity = theoryService.findAllById(Integer.valueOf(id));
    TheoryDTO theoryDTO = new TheoryDTO();

    theoryEntity.setContents(theoryEntity.getContents().replace("/uploads/images/","https://hava.edu.vn/uploads/images/"));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-top:none;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-top:solid;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-top:solid", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-bottom:none;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-bottom:solid;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-bottom:solid", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border:none;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border:solid;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border:solid", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-left:none;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-left:solid;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-left:solid", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-right:none;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-right:solid;", ""));
    theoryEntity.setContents(theoryEntity.getContents().replaceAll("border-right:solid", ""));

    String escapedHTML = StringEscapeUtils.escapeHtml4(theoryEntity.getContents());

    theoryEntity.setContents(escapedHTML);

    modelMapper.map(theoryEntity, theoryDTO);
    return ResponseEntity.ok(theoryDTO);

  }
}
