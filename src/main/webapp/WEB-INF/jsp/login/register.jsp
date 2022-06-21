<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<form action="/login/registerSubmit" method ="get">

    <div>
        <input type="hidden" name="id" value="${form.id}">
    </div>

    <div>
        <input type="email" id="emailId" name="email" value="${form.email}">
        <label>Email Address:</label>
        <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
            <div style="color: red">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>

    <div>
        <input type="text" id="firstNameId" name="firstName" value="${form.firstName}">
        <label>First Name:</label>
        <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
            <div style="color: red">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>

    <div>
        <input type="text" id="lastNameId" name="lastName" value="${form.lastName}">
        <label>Last Name:</label>
        <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
            <div style="color: red">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>

    <div>
        <input type="text" id="townStateId" name="townState" value="${form.townState}">
        <label>Town, State:</label>
        <c:forEach items='${bindingResult.getFieldErrors("townState")}' var="error">
            <div style="color: red">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>

    <div>
        <input type="password" id="passwordID" name="password" value="${form.password}">
        <label>Password</label>
    </div>

    <div>
        <input type="password" id="confirmPasswordID" name="confirmPassword" value="${form.confirmPassword}">
        <label> Confirm Password</label>
        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
            <div style="color: red">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>

    <h5>Optional</h5>

    <div>
        <label>About You?</label>
        <input type="text" id="profileDescriptionId" name="profileDescription" value="${form.profileDescription}">
    </div>


    <div>
        <label>Favorite Meetup spots?</label>
        <input type="text" id="favoriteMeetUpsId" name="favoriteMeetUps" value="${form.favoriteMeetUps}">
    </div>

    <div>
        <label>Insert Profile Image Address?</label>
        <input type="text" id="profileImgId" name="profileImg" value="${form.profileImg}">
    </div>

    <button type="submit">Create Account</button>
    <br>
    <br>
    <input type="checkbox" name="checkboxTermsOfUse">
    <a href="#">Terms Of Use</a>
    <input type="checkbox" name ="checkboxPrivacy">
    <a href="#">Privacy Policy</a>

    <c:forEach items='${bindingResult.getFieldErrors("checkboxPrivacy")}' var="error">
        <div style="color: red">${error.getDefaultMessage()}</div>
    </c:forEach>
</form><%--form end for log in reg submit--%>

Sign Up For Newsletter? <select name="newsletter" >
    <option> Yes </option>
    <option> No </option>
</select>

<jsp:include page="../include/footer.jsp"/>