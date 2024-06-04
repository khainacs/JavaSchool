package com.example.demo.consts;

public interface UrlPath {
    String URL_BASE = "/khainacs";

    String HOME_PAGE = URL_BASE + "/";

    //USER
    String USER_BEGIN_PAGE = URL_BASE + "/user";

    String USER_VIEW_ALL = URL_BASE + "/user/view-all-user";
    String USER_DELETE = URL_BASE + "/user/delete";
    String USER_CREATE = URL_BASE + "/user/create";
    String USER_CREATE_SUBMIT = URL_BASE + "/user/create/submit";
    String USER_UPDATE = URL_BASE + "user/update";
    String USER_UPDATE_SUBMIT = URL_BASE + "user/update/submit";


    String ROLE_BEGIN_PAGE = URL_BASE + "/role";
    String ROLE_VIEW_ALL = URL_BASE + "/role/view-all-role";
    String ROLE_DELETE = URL_BASE + "/role/delete";
    String ROLE_CREATE = URL_BASE + "/role/create";
    String ROLE_CREATE_SUBMIT = URL_BASE + "/role/create/submit";
    String ROLE_UPDATE = URL_BASE + "role/update";
    String ROLE_UPDATE_SUBMIT = URL_BASE + "role/update/submit";
}
