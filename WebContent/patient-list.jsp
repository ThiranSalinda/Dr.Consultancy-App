<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>DrConsultancy Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body style="background-color:#F8F8FF">

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
                    <div>
                        <a href="http://localhost:8084/DrConsultancyApp/list" class="navbar-brand"> DrConsultancy App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">My Patients</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Your Patients</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Patient</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="patient" items="${listPatient}">

                                <tr>
                                    <td>
                                        <c:out value="${patient.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.description}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${patient.id}' />" onclick="return confirm('Are you sure you want to UPDATE patient?')">Edit Patient</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${patient.id}' />"onclick="return confirm('Are you sure you want to DELETE patient?')">Delete Patient</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>