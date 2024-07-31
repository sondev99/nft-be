package com.code.ecommerce.controller;

import com.code.ecommerce.constance.ResponseStatus;
import com.code.ecommerce.dto.response.PagingData;
import com.code.ecommerce.dto.response.ResponseMessage;
import com.code.ecommerce.dto.response.UserDto;
import com.code.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/current")
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  public ResponseEntity<ResponseMessage> getCurrentUser(
      @RequestHeader("Authorization") String token) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            ResponseMessage.<UserDto>builder().code(200).message("Get current user successful !!!")
                .data(userService.getCurrentUser(token)).build());
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  public ResponseEntity<ResponseMessage> getById(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseMessage.<UserDto>builder().code(200).message("Get user successful !!!")
            .data(userService.getUserById(id)).build());

  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  public ResponseEntity<ResponseMessage> updateUserInfo(@RequestBody Map<String, Object> fields,
      @PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseMessage.<UserDto>builder().code(200)
            .message("Update user information successful !!!")
            .data(userService.updateUser(fields, id)).build());


  }

  @PutMapping("block/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<ResponseMessage> blockUser(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseMessage.<String>builder().code(200)
            .message("Block user information successful !!!").data(userService.blockUser(id))
            .build());


  }

  @PutMapping("/avatar/{id}")
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  public ResponseEntity<ResponseMessage> updateUserAvatar(@RequestParam MultipartFile file,
      @PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(
            ResponseMessage.<String>builder().code(200).message("Update user avatar successful !!!")
                .data(userService.updateAvatar(file, id)).build());

  }

  @GetMapping()
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<ResponseMessage> getUserWithCondition(
      @RequestParam(name = "searchText") String searchText,
      @RequestParam(name = "offset") Integer offset,
      @RequestParam(name = "pageSize") Integer pageSize,
      @RequestParam(name = "sortStr") String sortStr) {
    return ResponseEntity
        .status(HttpStatus.OK)
            .
        body(ResponseMessage.<PagingData>builder().code(200)
            .message("Update user information successful !!!")
            .data(userService.getUsers(searchText, offset, pageSize, sortStr)).build());

  }

  @GetMapping("/all")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<ResponseMessage> getAllUsers() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseMessage.<List<UserDto>>builder().code(200)
            .message("Update user information successful !!!").data(userService.getAllUser())
            .build());

  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<ResponseMessage> deleteUser(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.OK)

        .body(ResponseMessage.<String>builder().code(200).message("Delete user successful !!!")
            .data(userService.deleteUser(id)).build());

  }
}
