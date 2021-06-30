package com.fpt.hava.hava_manager.videos.service.impl;

import com.fpt.hava.hava_manager.document.domain.DocumentsEntity;
import com.fpt.hava.hava_manager.videos.domain.VideosEntity;
import com.fpt.hava.hava_manager.videos.repository.VideoRepository;
import com.fpt.hava.hava_manager.videos.service.VideoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoImpl implements VideoService {

  private final VideoRepository videoRepository;

  @Override
  public List<VideosEntity> getAllVideos() {
    List<VideosEntity> videos = videoRepository.findAll();
    return videos;
  }
}
