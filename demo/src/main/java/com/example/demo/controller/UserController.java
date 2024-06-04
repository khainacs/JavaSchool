package com.example.demo.controller;

import com.example.demo.consts.UrlPath;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserServiceImpl userService;
    @GetMapping(value = UrlPath.USER_BEGIN_PAGE)
    public String userNav(Model model) {
        model.addAttribute("activeNav", "user");
        return "user/userNav";
    }

   //GET-ALL
    @GetMapping(UrlPath.USER_VIEW_ALL)
    public String userViewAll(Model model){
        try{
            List<UserDTO> userDTOList = userService.findAllisNotDelete();
            // Data
            if (userDTOList != null) {
                for (UserDTO userDTO : userDTOList) {
                    Set<Role> roles = userDTO.getRoles();
                    if (roles != null && !roles.isEmpty()) {
                        Role role = roles.iterator().next();
                        userDTO.setRole(role.getRoleName().name());
                    }
                }
            }
            model.addAttribute("userDtoList", userDTOList);
            model.addAttribute("activeNav", "user");
            model.addAttribute("activeTab", "userView");
            return "user/userViewAll";
        }catch (Exception e){
            e.getMessage();
            return "error-Page";
        }

    }
    //CREATE
    @GetMapping(UrlPath.USER_CREATE)
    public String userCreatePage(Model model){
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("activeNav", "user");
        model.addAttribute("activeTab", "userCreate");
        return "user/userCreate";
    }
    @PostMapping(UrlPath.USER_CREATE_SUBMIT)
    public String userCreatePageSubmit(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model){
        try {
            UserDTO result = userService.createUser(userDTO);
            if (result == null) {
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }

        } catch (Exception ex) {
            ex.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + ex.getMessage());
            return "error-page";
        }
        return "redirect:" + UrlPath.USER_VIEW_ALL;
    }

    //FIND BY EMAIL

    //DELETE BY UUID
    @PostMapping(UrlPath.USER_DELETE)
    public String userDelete(@RequestParam("uuid") String uuid, Model model){
        try{
            if (!userService.deleteByUuid(uuid)){
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }
            model.addAttribute("successMessage", "SUCCESSFULLY");
            List<UserDTO> userDTOList = userService.findAllisNotDelete();
            // Data
            if (userDTOList != null) {
                for (UserDTO userDTO : userDTOList) {
                    Set<Role> roles = userDTO.getRoles();
                    if (roles != null && !roles.isEmpty()) {
                        Role role = roles.iterator().next();
                        userDTO.setRole(role.getRoleName().name());
                    }
                }
            }
            model.addAttribute("userDtoList", userDTOList);
            model.addAttribute("activeNav", "user");
            model.addAttribute("activeTab", "userView");
            return "user/userViewAll";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }

    //UPDATE
    @PostMapping(UrlPath.USER_UPDATE)
    public String userUpdate(@RequestParam("id") String id, Model model){
        try{
            UserDTO userDTO = userService.getByID(id);
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("activeNav", "user");
            model.addAttribute("activeTab", "userUpdate");
            return "user/userUpdate";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }

    @PostMapping(UrlPath.USER_UPDATE_SUBMIT)
    public String userUpdateSubmit(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model){
        try{
            if (!userService.updateInfo(userDTO)){
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }
            List<UserDTO> userDTOList = userService.findAllisNotDelete();
            // Data
            if (userDTOList != null) {
                for (UserDTO userDTO2 : userDTOList) {
                    Set<Role> roles = userDTO.getRoles();
                    if (roles != null && !roles.isEmpty()) {
                        Role role = roles.iterator().next();
                        userDTO.setRole(role.getRoleName().name());
                    }
                }
            }
            model.addAttribute("userDtoList", userDTOList);
            model.addAttribute("activeNav", "user");
            model.addAttribute("activeTab", "userView");
            return "user/userViewAll";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }
}
