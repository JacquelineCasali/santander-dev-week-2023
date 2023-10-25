package com.java.service.impl;

import com.java.domain.model.User;
import com.java.domain.repository.UserRepository;
import com.java.infra.exception.BusinessException;
import com.java.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class UserServiceImpl implements UserService {
    private static final Long UNCHANGEABLE_USER_ID = 1L ;
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


  @Transactional(readOnly = true)
  public List<User> findAll(){
        return this.userRepository.findAll();
  }




    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("Usuario Já existe");
        }
        if(userRepository.existsByCardNumber(userToCreate.getCard().getNumber())){
            throw new IllegalArgumentException("Numero de Conta Já existe");
        }
        return userRepository.save(userToCreate);
    }
    @Override
    public User update(Long id, User userToUpdate) {
//validação
        this.validateChangeableId(id,"updated");
User dbUser=this.findById(id);
if(!dbUser.getId().equals(userToUpdate.getId())){
    throw new BusinessException("O ID de atualização devem ser iguais");
}

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());
        return userRepository.save(dbUser);
    }
    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }
    private void validateChangeableId(Long id, String operation) {
        if(UNCHANGEABLE_USER_ID.equals(id)){
            throw new BusinessException("O ID de atualização devem ser iguais". formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}
