<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://website.com/style.css">
    <link href="<c:url value="../../../pub/css/404.css" />" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Splash Blast</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/user/profile">Home</a>
                    </li>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/user/search">Admin</a>
                        </li>
                    </sec:authorize>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Meetups
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/meetuppost/search">Find Meetups</a></li>
                            <li><a class="dropdown-item" href="/meetuppost/create">Create Meetup Post</a></li>
                            <li><a class="dropdown-item" href="/meetuppost/userposts"> MyMeetup Posts</a></li>
                        </ul>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Account
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/wateractivity/add">Add Water Craft</a></li>
                            <li><a class="dropdown-item" href="/user/mywateractivities"> View Water Crafts</a></li>
                        </ul>


                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/login/logout">Logout</a>
                    </li>


                    <li class="nav-item">

                        <a class="nav-link active" aria-current="page" href="#">
                            <sec:authentication property="principal.username"/>
                        </a>

                    </li>
                </sec:authorize>


                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/login/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/login/register">Sign Up</a>
                    </li>

                </sec:authorize>

            </ul>
        </div>
    </div>

</nav>


<hr>

<link href='https://fonts.googleapis.com/css?family=Anton|Passion+One|PT+Sans+Caption' rel='stylesheet' type='text/css'>
<body>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 pages</title>
</head>

<body>


<div id="container">

    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
         viewBox="0 0 200 82.7" style="enable-background:new 0 0 200 82.7;" xml:space="preserve">

<g id="Calque_1">
    <text id="XMLID_3_" transform="matrix(1.2187 0 0 1 13 75.6393)" class="st0 st1">4</text>
    <text id="XMLID_4_" transform="matrix(1.2187 0 0 1 133.0003 73.6393)" class="st0 st1">4</text>
</g>
        <g id="Calque_2">
            <g>
                <path id="XMLID_11_" d="M81.8,29.2c4.1-5.7,10.7-9.4,18.3-9.4c6.3,0,12.1,2.7,16.1,6.9c0.6-0.4,1.1-0.7,1.7-1.1
		c-4.4-4.8-10.8-7.9-17.8-7.9c-8.3,0-15.6,4.2-20,10.6C80.7,28.5,81.3,28.8,81.8,29.2z"/>
                <path id="XMLID_2_" d="M118.1,53.7c-4,5.7-10.7,9.5-18.2,9.5c-6.3,0-12.1-2.6-16.2-6.8c-0.6,0.4-1.1,0.7-1.7,1.1
		c4.4,4.8,10.8,7.8,17.9,7.8c8.3,0,15.6-4.3,19.9-10.7C119.2,54.5,118.6,54.1,118.1,53.7z"/>
                <animateTransform attributeName="transform" type="rotate" from="360 100 41.3" to="0 100 41.3" dur="10s" repeatCount="indefinite" />
            </g>
            <g id="XMLID_6_">
                <g  id="XMLID_18_">



                    <circle class="circle"  cx="100" cy="41" r="1"></circle>
                </g>
            </g><defs>
            <filter id="blurFilter4" x="-20" y="-20" width="200" height="200">
                <feGaussianBlur in="SourceGraphic" stdDeviation="2" />
            </filter>
        </defs>
            <path    id="XMLID_5_" class="st2" d="M103.8,16.7c0.1,0.3,0.1,0.6,0.1,0.9c11.6,1.9,20.4,11.9,20.4,24.1c0,13.5-10.9,24.4-24.4,24.4
  S75.6,55.1,75.6,41.7c0-3.2,0.6-6.3,1.7-9.1c-0.3-0.2-0.5-0.3-0.7-0.5c-1.2,3-1.9,6.2-1.9,9.6c0,14,11.3,25.3,25.3,25.3
  s25.3-11.3,25.3-25.3C125.3,29,115.9,18.5,103.8,16.7z"/>


        </g>
</svg>

    <div class="message">
        Page not found
    </div>
</div>
</body>
</html>
