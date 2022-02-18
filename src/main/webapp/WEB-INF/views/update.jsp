<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Accident</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Редактировать инцедент
            </div>
            <div class="card-body">
                <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                    <div class="form-group">
                        <label>Название</label>
                        <input type="text" name="name" class="form-control" value="${accident.name}">
                    </div>
                    <div class="form-group">
                        <label>Описание</label>
                        <input type="text" name="text" class="form-control" value="${accident.text}">
                    </div>
                    <div class="form-group">
                        <label>Адрес</label>
                        <input type="text" name="address" class="form-control" value="${accident.address}">
                    </div>
                    <div class="form-group">
                        <label>Тип</label>
                        <select class="form-control" name="type.id">
                            <c:forEach var="type" items="${types}">
                                <option value="${type.id}"
                                    <c:if test="${type.id == accident.type.id}">
                                        ${"selected"}
                                    </c:if>
                                >${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Статьи</label>
                        <select class="form-control" name="rIds" multiple>
                            <c:forEach var="rule" items="${rules}">
                                <option value="${rule.id}"
                                        <c:if test="${accident.rules.contains(rule)}">
                                            ${"selected"}
                                        </c:if>
                                >${rule.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
