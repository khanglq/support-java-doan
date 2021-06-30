package com.fpt.hava.web.api.feels;

import com.fpt.hava.hava_manager.feels.domain.FeelsEntity;
import com.fpt.hava.hava_manager.feels.service.FeelService;
import com.fpt.hava.web.api.hava_manager.feels.FeelsApi;
import com.fpt.hava.web.api.hava_manager.feels.dto.FeelDTO;
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
public class FeelController implements FeelsApi {

  private final FeelService feelService;
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<List<FeelDTO>> getFeels() {
    List<FeelDTO> feelDTOs = new ArrayList<>();
    List<FeelsEntity> feels = feelService.getTop5Feel();

    for(FeelsEntity item : feels){
      FeelDTO feelDTO = new FeelDTO();

      modelMapper.map(item, feelDTO);
      feelDTOs.add(feelDTO);
    }


    return ResponseEntity.ok(feelDTOs);

  }
}
