<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
	  <meta charset="ISO-8859-1">
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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${patient != null}">
                            <form action="update" method="post">
                            
                        </c:if>
                        <c:if test="${patient == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${patient != null}">
                                    Edit Patient with ID : ${patient.id}
                                </c:if>
                                <c:if test="${patient == null}">
                                    Add New Patient
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${patient != null}">
                            <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Patient Name</label> <input type="text" value="<c:out value='${patient.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Patient Email</label> <input type="text" value="<c:out value='${patient.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Patient Description</label> <input type="text" value="<c:out value='${patient.description}' />" class="form-control" name="description">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

</body>
</html>


