<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>


<section class="h-100 gradient-custom-2">
    <div class="container py-5 h-300">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-7">
                <div class="card" style="border-radius: 1rem;">

                    <div class="col-lg-10 d-flex align-items-center" style="display: block; margin: 0 auto;">
                        <div class="card-body p-4 p-lg-5 text-black">

                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0"
                                      style="color: rgba(39,58,171,0.9); display: block; margin:0 auto;">Woohoo! Have a SPLASH! </span>
                            </div>


                            <div class="form-outline mb-4">

                            </div>

                            <div>
                                <button class="btn btn-dark btn-lg btn-block" type="submit"
                                        style="background-color: #000000"
                                        onclick="location.href='http://localhost:8000/meetuppost/search'">
                                    Explore More Meetups
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>