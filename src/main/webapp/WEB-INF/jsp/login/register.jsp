<jsp:include page="../include/header.jsp"/>

<form action="/login/registerSubmit" method ="get">

    <div>
        <input type="hidden" name="id" value="${form.id}">
    </div>

    <div>
        <input type="email" id="emailId" name="email" value="${form.email}">
        <label>Email Address:</label>
    </div>

    <div>
        <input type="text" id="firstNameId" name="firstName" value="${form.firstName}">
        <label>First Name:</label>
    </div>

    <div>
        <input type="text" id="lastNameId" name="lastName" value="${form.lastName}">
        <label>Last Name:</label>
    </div>

    <div>
        <input type="text" id="townStateId" name="townState" value="${form.townState}">
        <label>Town, State:</label>
    </div>

    <div>
        <input type="password" id="passwordID" name="password" value="${form.password}">
        <label>Password</label>
    </div>

    <div>
        <input type="password" id="confirmPasswordID" name="confirmPassword" value="${form.confirmPassword}">
        <label> Confirm Password</label>
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
</form><%--form end for log in reg submit--%>


<jsp:include page="../include/footer.jsp"/>