package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.service.UploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WbwBlogApplication.class)
public class UploadControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private UploadService mockUploadService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void uploadImageShouldReturnOkResult() throws Exception {
    MockMultipartFile file = BlogTestHelper.IMG_FILE;
    String returnUrl = BlogTestHelper.UPLOAD_RETURN_URL;
    ResponseEntity response = new ResponseEntity<>(returnUrl, HttpStatus.OK);
    when(mockUploadService.uploadImg(any())).thenReturn(response);
    mockMvc.perform(MockMvcRequestBuilders.multipart(BlogTestHelper.UPLOAD_IMAGE_API_PATH)
                    .file("img", file.getBytes()))
        .andExpect(status().isOk());
  }

  @Test
  public void uploadNullImageShouldReturnIllegalArgumentException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.multipart(BlogTestHelper.UPLOAD_IMAGE_API_PATH))
        .andExpect(status().is4xxClientError());
  }
}
