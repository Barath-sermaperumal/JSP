<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
    <%
        if(session.getAttribute("id") == null){
             response.sendRedirect(request.getContextPath());
        }
    %>

    <form action="logout.jsp" method="link">
        <input type="submit" value="Logout"/>
    </form>

<form action="home" method="post">
<p>Enter todo: <input type="text" name="todo"/></p>
<input type="submit" value="Add">
</form>
        <c:if test="${todos.size() eq 0}">
            <p>No Items to display</p>
        </c:if>
         <c:if test="${todos.size() gt 0}">
                <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Todo</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="todo" items="${todos}">
                                <tr>
                                    <td>
                                        <c:out value="${todo.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${todo.todo}" />
                                    </td>
                                    <td><a href="home?id=<c:out value='${todo.id}'/>">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
            </c:if>
</body>
</html>






