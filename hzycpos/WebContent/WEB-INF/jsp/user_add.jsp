<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
fffff
<s:property value="#request.name"/>
<%-- <s:property value="user.password"/> --%>
 <s:iterator value="userList" var="user">
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.password"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <s:property value="user.username"/>
        <!--<s:property />-->
    </s:iterator>
    
    <s:form>  
       <s:textfield name="a">
       sdadas
       </s:textfield>  
      
    </s:form>
</body>
</html>