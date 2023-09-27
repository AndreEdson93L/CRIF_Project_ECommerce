package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.exception.EmailAlreadyInUseException;
import com.ecommercecrif.E_Commerce_application.exception.NicknameAlreadyInUseException;
import com.ecommercecrif.E_Commerce_application.exception.UserNotFoundException;
import com.ecommercecrif.E_Commerce_application.mapper.UserMapper;
import com.ecommercecrif.E_Commerce_application.model.EnumRole;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.*;
import com.ecommercecrif.E_Commerce_application.repository.UserRepository;
import jakarta.ws.rs.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtService jwtService;

     /* @Autowired
    private WebClient.Builder webClientBuilder;*/




    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Transactional
    @Override
    public UserResponseDTO addUser(RegisterUserDTO registerUserDTO) throws ConnectException {

        if(existByEmail(registerUserDTO.getEmail())){
            throw new EmailAlreadyInUseException(registerUserDTO.getEmail());
        }
        if(existByNickname(registerUserDTO.getNickname())){
            throw new NicknameAlreadyInUseException(registerUserDTO.getNickname());
        }
        var password = registerUserDTO.getPassword();
        registerUserDTO.setPassword(passwordEncoder.encode(password));

        UserEntity userEntity = userMapper.dtoToUserEntity(registerUserDTO);

        //This is a workaround to remove the role = null problem in insertion into DB
        userEntity.setRole(EnumRole.valueOf("USER"));
        AuthUserDTO authUserDTO = new AuthUserDTO(registerUserDTO.getEmail(), registerUserDTO.getPassword());
        /*
    Momentarily not Working, try again when we have more time
        webClientBuilder.build()
                .post()
                .uri("http://localhost/8090/api/v1/authentication/register")
                .body(Mono.just(authUserDTO), AuthUserDTO.class)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();
*/

        if((restTemplate.postForEntity("http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/register", authUserDTO, AuthUserDTO.class ).getStatusCode().is2xxSuccessful())){
            UserEntity savedUser = repository.save(userEntity);

            return userMapper.userEntityToDto(savedUser);
        }

        throw new ConnectException("Could not complete registration, try again");

    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException(email)
        );
    }

    @Override
    public UserEntity getUserByEmailInJwt(String token) {
        String email = jwtService.extractUsername(token);
        return repository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(email));
    }


    @Override
    public Collection<UserEntity> findAll() {
        return repository.findAllByOrderByEmailAsc();
    }

    public boolean existByEmail(String email) {
        boolean isInDB = repository.existsByEmail(email);
        return isInDB;
    }


    public boolean existByNickname(String nickname) {
        boolean isInDb = repository.existsByNickname(nickname);
        return isInDb;
    }


    @Override
    public UserResponseDTO updateUserByEmailInJwt(String token, UpdateUserDTO updateUserDTO) {
        String email = jwtService.extractUsername(token);
          if(jwtService.isTokenValidForResource(token, findByEmail(email))){
            if(!email.equals(updateUserDTO.getEmail())){
                AuthUpdDTO authUpdEmail = new AuthUpdDTO(email);
                String url = "http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/update-user/"+email;

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", token);

                HttpEntity<UpdateUserDTO> entity = new HttpEntity<UpdateUserDTO>(updateUserDTO, headers);
                if((restTemplate.exchange(url, HttpMethod.PUT, entity, UserResponseDTO.class)).getStatusCode().is2xxSuccessful()) {
                    return updateUser(email, updateUserDTO);
                }
            }
            return updateUser(email, updateUserDTO);
        }
        throw new ForbiddenException("This operation is not authorized");
    }




    @Transactional
    @Override
    public UserResponseDTO updateUser(String email, UpdateUserDTO updateUserDTO) {

        //verifying if user exists
        UserEntity userToUpdate = findByEmail(email);

    if(!updateUserDTO.getEmail().isEmpty()){
        //setting email
        userToUpdate.setEmail(updateUserDTO.getEmail());
    }

    if(!updateUserDTO.getNickname().isEmpty()){
        userToUpdate.setNickname(updateUserDTO.getNickname());
    }

    if(updateUserDTO.getNickname().isEmpty() && updateUserDTO.getEmail().isEmpty())


        //saving user in the repo
        repository.save(userToUpdate);

        //returning a dto (email, role)
        return userMapper.userEntityToDto(userToUpdate);
    }


    public Boolean deleteByEmailInJwt(String token){
        String email = jwtService.extractUsername(token);

        if(jwtService.isTokenValidForResource(token, findByEmail(email))){
            String url = "http://AUTHENTICATION-MANAGEMENT/api/v1/authentication/delete-user/"+email;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            if((restTemplate.exchange(url, HttpMethod.DELETE, entity, Boolean.class)).getStatusCode().is2xxSuccessful()){
                return deleteByEmail(email);
            }
        }
        throw new ForbiddenException("This operation is not authorized");
    }


    @Transactional
    @Override
    public boolean deleteByEmail(String email) {

        long deletedRecords = repository.deleteByEmail(email);
        return deletedRecords == 1;
    }





    public HttpEntity<String> createHttpEntityWithJwtHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }

    // Utility function to create HttpEntity with JWT token && DTO
    public HttpEntity<UpdateUserDTO> createHttpEntityWithJWTHeaderAndDTO (String token, UpdateUserDTO updateUserDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<UpdateUserDTO> entity = new HttpEntity<>(updateUserDTO, headers);
        return entity;
    }
}
