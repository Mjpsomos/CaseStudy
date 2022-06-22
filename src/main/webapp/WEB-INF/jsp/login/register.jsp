<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<c:if test="${empty form.id}">
</c:if>

<c:if test="${not empty form.id}">
</c:if>

<section class="h-100 gradient-custom-2">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="https://images.pexels.com/photos/1806765/pexels-photo-1806765.jpeg?auto=compress&cs=tinysrgb&w=600"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                            <img src="https://images.pexels.com/photos/1504215/pexels-photo-1504215.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form action="/login/registerSubmit" method="get">

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>

                                        <c:if test="${empty form.id}">
                                            <span  class="h1 fw-bold mb-0" style="color: rgba(39,58,171,0.9);" >Create An Account</span>  </c:if>
                                        <c:if test="${not empty form.id}">
                                            <span  class="h1 fw-bold mb-0" style="color: rgba(45,68,218,0.9);" >Update Account</span> </c:if>
                                    </div>



                                    <div class="form-outline mb-4">

                                        <input type="hidden" name="id" value="${form.id}" >


                                        <div>
                                            <input type="text" id="form2Example27" class="form-control form-control-lg" name="email" value="${form.email}" >
                                            <label class="form-label" for="form2Example27">Email Address</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>

                                        </div>

                                        <div class="form-outline mb-4">


                                            <input type="text" id="form2Example27" class="form-control form-control-lg" name="firstName" id="firstNameId" value="${form.firstName}" />
                                            <label class="form-label" for="form2Example27">First Name</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>
                                        </div>

                                        <div class="form-outline mb-4">


                                            <input type="text" id="form2Example27" class="form-control form-control-lg" name="lastName" id="lastNameId" value="${form.lastName}"/>
                                            <label class="form-label" for="form2Example27">Last Name</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>
                                        </div>

                                        <div class="form-outline mb-4">


                                            <input type="text" id="form2Example27" class="form-control form-control-lg" name="townState" id="townStateId" value="${form.townState}"/>
                                            <label class="form-label" for="form2Example27">Town, State</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("townState")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>
                                        </div>

                                        <div class="form-outline mb-4">


                                            <input type="password" id="form2Example27" class="form-control form-control-lg" name="password" id="passwordId"/>
                                            <label class="form-label" for="form2Example27">Password</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>
                                        </div>

                                        <div class="form-outline mb-4">


                                            <input type="password" id="form2Example27" class="form-control form-control-lg" name="confirmPassword" id="confirmPasswordId"/>
                                            <label class="form-label" for="form2Example27">Confirm Password</label>
                                            <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
                                                <div style="color: red">${error.getDefaultMessage()}</div>
                                            </c:forEach>
                                        </div>


                                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Optional</h5>


                                        <div class="form-outline mb-4">


                                            <label class="form-label" for="form2Example27"> About You:</label>
                                            <input type="text" id="form2Example27" class="form-control form-control-lg" name="profileDescription" id="profileDescriptionId" value="${form.profileDescription}" />
                                        </div>

                                        <div class="form-outline mb-4">


                                            <label class="form-label" for="form2Example27">Favorite Meetup Location(s)</label>
                                            <input type="text" id="form2Example27" class="form-control form-control-lg"  name="favoriteMeetUps" id="favoriteMeetUpsId" value="${form.favoriteMeetUps}"/>

                                        </div>

                                        <div class="form-outline mb-4">

                                            <label class="form-label" for="form2Example27">Insert Profile Image Address</label>
                                            <input type="text" id="form2Example27" class="form-control form-control-lg"  name="profileImg" id="profileImgId" value="${form.profileImg}"/>

                                        </div>

                                        <div class="pt-1 mb-4">


                                            <c:if test="${empty form.id}">
                                                <button class="btn btn-dark btn-lg btn-block" type="submit">Create Account</button>
                                            </c:if>
                                            <c:if test="${not empty form.id}">

                                                <button class="btn btn-dark btn-lg btn-block" onclick="location.href='/user/profile'" style="background-color: #f38181">Update Account</button>
                                            </c:if>
                                        </div>


                                        <input type="checkbox" name="checkboxTermsOfUse" style="display: inline-block;">

                                        <a href="#!" class="small text-muted">Terms of use.</a>



                                        <input type="checkbox" name="checkboxPrivacy" style="display: inline-block;">
                                        <a href="#!" class="small text-muted">Privacy policy</a>

                                        <br>
                                        <br>

                                        <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
                                        <div style="color: red">${error.getDefaultMessage()}</div>
                                        </c:forEach>
                                        <c:forEach items='${bindingResult.getFieldErrors("checkboxPrivacy")}' var="error">
                                        <div style="color: red">${error.getDefaultMessage()}</div>
                                        </c:forEach>
                                </form>

                                Sign Up For Newsletter? <select name="newsletter" >
                                <option> Yes </option>
                                <option> No </option>
                            </select>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp"/>