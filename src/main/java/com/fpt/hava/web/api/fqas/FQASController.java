package com.fpt.hava.web.api.fqas;

import com.fpt.hava.hava_manager.f_q_as.domain.FQAsEntity;
import com.fpt.hava.hava_manager.f_q_as.service.FQASService;
import com.fpt.hava.hava_manager.user.repository.UserRepository;
import com.fpt.hava.web.api.hava_manager.fqas.FqasApi;
import com.fpt.hava.web.api.hava_manager.fqas.dto.FqasDTO;
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
public class FQASController implements FqasApi {

  private final FQASService fqasService;
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  public ResponseEntity<List<FqasDTO>> getFQAS(){
    List<FqasDTO> fqasDTOs = new ArrayList<>();
    List<FQAsEntity> fqAsEntities = fqasService.getAllFQAS();

    for(FQAsEntity item : fqAsEntities){
      FqasDTO fqasDTO = new FqasDTO();

      modelMapper.map(item, fqasDTO);
      fqasDTOs.add(fqasDTO);
    }

    return ResponseEntity.ok(fqasDTOs);
  }

//  public static String md5(String input) throws NoSuchAlgorithmException {
//    String result = input;
//    if(input != null) {
//      MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
//      md.update(input.getBytes());
//      BigInteger hash = new BigInteger(1, md.digest());
//      result = hash.toString(16);
//      while(result.length() < 32) { //40 for SHA-1
//        result = "0" + result;
//      }
//    }
//    return result;
//  }
}
