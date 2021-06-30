package com.fpt.hava.web.api.videos;

import com.fpt.hava.hava_manager.videos.domain.VideosEntity;
import com.fpt.hava.hava_manager.videos.service.VideoService;
import com.fpt.hava.web.api.hava_manager.videos.VideosApi;
import com.fpt.hava.web.api.hava_manager.videos.dto.VideoDTO;
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
public class VideoController implements VideosApi {

  private final VideoService videoService;
  private final ModelMapper modelMapper;

  public ResponseEntity<List<VideoDTO>> getVideos() {
    List<VideoDTO> videoDTO = new ArrayList<>();
    List<VideosEntity> videosEntitie = videoService.getAllVideos();

    for(VideosEntity item : videosEntitie){
      VideoDTO video = new VideoDTO();

      modelMapper.map(item, video);
      videoDTO.add(video);
    }


    return ResponseEntity.ok(videoDTO);

  }
}
