<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>


<<section class="h-100 gradient-custom-2">
<div class="container py-5 h-300">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-xl-10">
            <div class="card" style="border-radius: 1rem;">

                <div class="col-lg-7 d-flex align-items-center" style="display: block; margin: 0 auto;">
                    <div class="card-body p-4 p-lg-5 text-black">


                        <div class="d-flex align-items-center mb-3 pb-1">
                            <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                            <span class="h1 fw-bold mb-0"
                                  style="color: rgba(39,58,171,0.9); display: block; margin: 0 auto;">
                                <c:if test="${empty form.id}">
                                    Create Meetup Post
                                </c:if>

                                 <c:if test="${not empty form.id}">
                                     Update Meetup Post
                                 </c:if>  </span>
                        </div>

                        <form action="/meetuppost/createpost" method="get" style="display: block; margin: auto" ;>

                            <br>

                            <div class="form-outline mb-4">

                                <input type="hidden" name="id" id="id" value="${form.id}">
                                <label class="form-label" for="form2Example17">Meetup Post </label>
                                <input type="jsonString" id="form2Example17" class="form-control form-control-lg"
                                       name="meetupMessage" id="meetupMessageId" value="${form.meetupMessage}"
                                       style="display: inline-block;" ;/>
                                <c:forEach items='${bindingResult.getFieldErrors("meetupMessage")}' var="error">
                                    <div style="color: red">${error.getDefaultMessage()}</div>
                                </c:forEach>
                            </div>
                            <br>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form2Example17">Location</label>
                                <input type="text" class="form-control form-control-lg" name="location"
                                       id="locationId" value="${form.location}" style="display: inline-block;" ;/>
                                <c:forEach items='${bindingResult.getFieldErrors("location")}' var="error">
                                    <div style="color: red">${error.getDefaultMessage()}</div>
                                </c:forEach>

                            </div>
                            <br>


                            <div class="form-outline mb-4">
                                <label class="form-label" for="form2Example17">Date</label>
                                <input type="date" class="form-control form-control-lg" name="meetupDate"
                                       id="meetupDateId" style="display: inline-block;" ;/>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="form2Example17">Time</label>
                                <input type="time" class="form-control form-control-lg" name="meetupTime"
                                       id="meetupTimeId" style="display: inline-block;" ;/>
                            </div>

                            <br>


                            <div class="pt-1 mb-4">
                                <button class="btn btn-dark btn-lg btn-block" type="submit"
                                        style="background-color: #000000">Submit
                                </button>
                            </div>


                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>


<jsp:include page="../include/footer.jsp"/>