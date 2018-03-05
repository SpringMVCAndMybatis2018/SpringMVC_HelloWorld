<%--
  Created by IntelliJ IDEA.
  User: Anlu
  Date: 2018/2/26
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC表单处理</title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorStyle {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/FormHandling/addStudentValidator"
           commandName="student">
    <form:errors path="*" cssClass="errorStyle" element="div" />
    <table>
        <tr>
            <td><form:label path="name">姓名：</form:label></td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="age">年龄：</form:label></td>
            <td><form:input path="age" /></td>
            <td><form:errors path="age" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="id">编号：</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
