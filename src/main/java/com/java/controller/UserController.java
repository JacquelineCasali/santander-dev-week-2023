package com.java.controller;

import com.java.domain.model.User;
import com.java.dto.UserDto;
import com.java.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }


   @GetMapping
  public ResponseEntity <List<UserDto>> findAll(){
   var users=userService.findAll();
   var usuario = users.stream().map(UserDto::new).collect(Collectors.toList());
   return ResponseEntity.ok(usuario);

   }



    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(new UserDto(user));
    }
// cria conta
  @PostMapping

  public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
      var userCreated = userService.create(userDto.toUsuario());
      //localizaçao
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(userCreated.getId())
              .toUri();
      return ResponseEntity.created(location).body(new UserDto(userCreated));
  }

  //editar
    @PutMapping

    public ResponseEntity <UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        var user = userService.update(id,userDto.toUsuario());
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
