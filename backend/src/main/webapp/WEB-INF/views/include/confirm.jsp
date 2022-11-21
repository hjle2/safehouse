<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li class="nav-item" id="login-list"><a class="nav-link" href="${root }/user/update"><strong>${userInfo.name}</strong> (${userInfo.id})님 안녕하세요.</a></li>
<li class="nav-item" id="login-list"><a class="nav-link"
    href="${root}/user/logout">로그아웃</a></li>