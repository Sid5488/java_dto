package br.com.solcode.treino_dto.DTOs;

import java.util.ArrayList;
import java.util.List;

import br.com.solcode.treino_dto.models.User;

public class UserDTO {
    
    private Long id;
    private String name;
    private String email;


    public UserDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public List<UserDTO> usersToDTOs(List<User> users) {
        List<UserDTO> usersDTOs = new ArrayList<>();
        UserDTO dto = new UserDTO();

        for(User user: users) {
            usersDTOs.add(dto.userToDTO(user));
        }

        return usersDTOs;
    }

}