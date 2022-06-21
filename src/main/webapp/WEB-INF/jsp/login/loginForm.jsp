<jsp:include page="../include/header.jsp"/>

    <form name="loginForm" action="/login/loginSubmit" method="post">
        <h1>Water Play</h1>

    <div>
        <label>Email Address</label>
        <input type="text" id="emailId" name="email">
    </div>

    <div>
        <label>Password</label>
        <input type="password" id="passwordId" name="password">
    </div>

        <button type="submit" onclick="ValidateEmail(document.loginForm.username)">
            Login
        </button>

        <p><input type="radio"><label>Save Password?</label></p>
        <p>Don't have an account?<a href="/login/register">Register Here</a></p>
    </form>

<jsp:include page="../include/footer.jsp"/>