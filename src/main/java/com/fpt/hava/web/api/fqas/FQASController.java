package com.fpt.hava.web.api.fqas;

import com.fpt.hava.hava_manager.f_q_as.domain.FQAsEntity;
import com.fpt.hava.hava_manager.f_q_as.service.FQASService;
import com.fpt.hava.hava_manager.videos.domain.VideosEntity;
import com.fpt.hava.hava_manager.videos.service.VideoService;
import com.fpt.hava.web.api.hava_manager.fqas.ApiUtil;
import com.fpt.hava.web.api.hava_manager.fqas.FqasApi;
import com.fpt.hava.web.api.hava_manager.fqas.dto.FqasDTO;
import com.fpt.hava.web.api.hava_manager.videos.dto.VideoDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FQASController implements FqasApi {

  private final FQASService fqasService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<FqasDTO>> getFQAS() {
    List<FqasDTO> fqasDTOs = new ArrayList<>();
    List<FQAsEntity> fqAsEntities = fqasService.getAllFQAS();

    for(FQAsEntity item : fqAsEntities){
      FqasDTO fqasDTO = new FqasDTO();

      modelMapper.map(item, fqasDTO);
      fqasDTOs.add(fqasDTO);
    }


    return ResponseEntity.ok(fqasDTOs);

  }
}
