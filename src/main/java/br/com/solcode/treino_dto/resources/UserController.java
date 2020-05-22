package br.com.solcode.treino_dto.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.solcode.treino_dto.DTOs.UserDTO;
import br.com.solcode.treino_dto.models.User;
import br.com.solcode.treino_dto.repositories.UserRepository;
import br.com.solcode.treino_dto.response.Response;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    private UserDTO userDTO = new UserDTO();

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDTO> getAll() {
        List<User> users = new ArrayList<>();
        List<UserDTO> dto = new ArrayList<>();

        users = userRepository.findAll();
        dto = userDTO.usersToDTOs(users);

        return dto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> getUser = userRepository.findById(id);
        UserDTO dto = new UserDTO();

        if(getUser.isPresent())
            return ResponseEntity.ok(dto.userToDTO(getUser.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/singin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Response<UserDTO>> singin(@Valid @RequestBody User user, BindingResult result) {
        Response<UserDTO> response = new Response<UserDTO>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        userRepository.save(user);
        response.setData(userDTO.userToDTO(user));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public UserDTO update(@Valid @RequestBody User user) {
        UserDTO dto = new UserDTO();
        
        userRepository.save(user);
        dto.userToDTO(user);

        return dto;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}