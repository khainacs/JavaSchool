package com.example.demo.controller;

import com.example.demo.consts.UrlPath;
import com.example.demo.dto.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.impl.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class RoleController {
    @Autowired
    RoleRepository repository;

    @Autowired
    RoleServiceImpl roleService;
    @GetMapping(value = UrlPath.ROLE_BEGIN_PAGE)
    public String roleNav(Model model) {
        model.addAttribute("activeNav", "role");
        return "role/roleNav";
    }

    //GET-ALL
    @GetMapping(UrlPath.ROLE_VIEW_ALL)
    public String roleViewAll(Model model){
        try{
            List<RoleDTO> roleDTOList = roleService.getAll();
            model.addAttribute("roleDtoList", roleDTOList);
            model.addAttribute("activeNav", "role");
            model.addAttribute("activeTab", "roleView");
            return "role/roleViewAll";
        }catch (Exception e){
            e.getMessage();
            return "error-Page";
        }

    }
    //CREATE
    @GetMapping(UrlPath.ROLE_CREATE)
    public String roleCreatePage(Model model){
        model.addAttribute("roleDTO", new RoleDTO());
        model.addAttribute("activeNav", "role");
        model.addAttribute("activeTab", "roleCreate");
        return "role/roleCreate";
    }
    @PostMapping(UrlPath.ROLE_CREATE_SUBMIT)
    public String roleCreatePageSubmit(@Valid @ModelAttribute("roleDTO") RoleDTO roleDTO, BindingResult bindingResult, Model model){
        try {
            RoleDTO result = roleService.createRole(roleDTO);
            if (result == null) {
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }

        } catch (Exception ex) {
            ex.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + ex.getMessage());
            return "error-page";
        }
        return "redirect:" + UrlPath.ROLE_VIEW_ALL;
    }

    //FIND BY EMAIL

    //DELETE BY UUID
    @PostMapping(UrlPath.ROLE_DELETE)
    public String roleDelete(@RequestParam("id") String id, Model model){
        try{
            if (!roleService.getByID(id)){
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }
            model.addAttribute("successMessage", "SUCCESSFULLY");
            List<RoleDTO> roleDTOList = roleService.getAll();
            model.addAttribute("roleDtoList", roleDTOList);
            model.addAttribute("activeNav", "role");
            model.addAttribute("activeTab", "roleView");
            return "role/roleViewAll";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }

    //UPDATE
    @PostMapping(UrlPath.ROLE_UPDATE)
    public String roleUpdate(@RequestParam("id") String id, Model model){
        try{
            RoleDTO roleDTO = roleService.getByID(id);
            model.addAttribute("roleDTO", roleDTO);
            model.addAttribute("activeNav", "role");
            model.addAttribute("activeTab", "roleUpdate");
            return "role/roleUpdate";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }

    @PostMapping(UrlPath.ROLE_UPDATE_SUBMIT)
    public String roleUpdateSubmit(@Valid @ModelAttribute("roleDTO") RoleDTO roleDTO, Model model){
        try{
            if (!roleService.updateInfo(roleDTO)){
                model.addAttribute("errorMessage", "ERROR!!");
                return "error-page";
            }
            List<RoleDTO> roleDTOList = roleService.getAll();
            // Data
            if (roleDTOList != null) {
                for (RoleDTO roleDTO2 : roleDTOList) {
                    Set<Role> roles = roleDTO.getRoles();
                    if (roles != null && !roles.isEmpty()) {
                        Role role = roles.iterator().next();
                        roleDTO.setRole(role.getRoleName().name());
                    }
                }
            }
            model.addAttribute("roleDtoList", roleDTOList);
            model.addAttribute("activeNav", "role");
            model.addAttribute("activeTab", "roleView");
            return "role/roleViewAll";
        }catch (Exception e){
            e.getMessage();
            model.addAttribute("errorMessage", "ERROR!!" + e.getMessage());
            return "error-page";
        }
    }
}
