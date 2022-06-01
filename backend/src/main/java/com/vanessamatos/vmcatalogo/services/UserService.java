package com.vanessamatos.vmcatalogo.services;

import com.vanessamatos.vmcatalogo.dto.CategoryDTO;
import com.vanessamatos.vmcatalogo.dto.RoleDTO;
import com.vanessamatos.vmcatalogo.dto.UserDTO;
import com.vanessamatos.vmcatalogo.dto.UserInsertDTO;
import com.vanessamatos.vmcatalogo.entities.Category;
import com.vanessamatos.vmcatalogo.entities.Role;
import com.vanessamatos.vmcatalogo.entities.User;
import com.vanessamatos.vmcatalogo.repositories.CategoryRepository;
import com.vanessamatos.vmcatalogo.repositories.RoleRepository;
import com.vanessamatos.vmcatalogo.repositories.UserRepository;
import com.vanessamatos.vmcatalogo.repositories.UserRepository;
import com.vanessamatos.vmcatalogo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> list = userRepository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }


//    public List<UserDTO> findAll(){
//        List<User> list = UserRepository.findAll();
//        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
//    }
    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> userObj = userRepository.findById(id);
        User user = userObj.orElseThrow(()-> new ResourceNotFoundException("Entidade não encontrada"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO userInsertDTO){
        User newUser = new User();
        copyDtoToNewUser(userInsertDTO, newUser);
        newUser.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));
        newUser = userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO){
        try{
            User newUser = userRepository.getById(id);
            copyDtoToNewUser(userDTO, newUser);
            newUser = userRepository.save(newUser);
            return new UserDTO(newUser);
        } catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("Id não existe " + id);
        }
    }

    public void delete(Long id){
        try{
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Id não encontrado " + id);
        }
        catch (DataIntegrityViolationException e){
            throw  new DataIntegrityViolationException("Violação de integridade");
        }
    }

    private void copyDtoToNewUser(UserDTO userDTO, User newUser){
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());

        newUser.getRoles().clear();
        for(RoleDTO roleDTO : userDTO.getRoles()){
            Role role = roleRepository.getById(roleDTO.getId());
            newUser.getRoles().add(role);
        }
    }
}