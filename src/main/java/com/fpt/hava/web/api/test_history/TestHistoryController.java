package com.fpt.hava.web.api.test_history;

import com.fpt.hava.hava_manager.f_q_as.service.FQASService;
import com.fpt.hava.hava_manager.test_history.service.TestHistoryService;
import com.fpt.hava.web.api.hava_manager.test_history.TestHistoryApi;
import com.fpt.hava.web.api.hava_manager.test_history.dto.TestHistoryCreateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TestHistoryController implements TestHistoryApi {

  private final TestHistoryService testHistoryService;

  @Override
  public ResponseEntity<Void> createTestHistory(@ApiParam(value = "" ,required=true )  @Valid @RequestBody TestHistoryCreateRequest testHistoryCreateRequest) {
    testHistoryService.CreateTestHistory(testHistoryCreateRequest);
    return ResponseEntity.ok().build();
  }
}
