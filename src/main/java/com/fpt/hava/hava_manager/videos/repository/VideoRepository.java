package com.fpt.hava.hava_manager.videos.repository;

import com.fpt.hava.hava_manager.videos.domain.VideosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideosEntity, Integer> {

}
