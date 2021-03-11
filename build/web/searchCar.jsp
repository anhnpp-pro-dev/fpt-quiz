<%-- 
    Document   : car
    Created on : Mar 7, 2021, 5:13:38 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang ="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Car</title>
        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <!--Navbar-->
        <nav class="navbar  navbar-expand mb-5 navbar-light">
            <div class="container-fluid">
                <a class="navbar-brand mb-0 h1" href="HomePageController">
                    <img src="https://fpt.com.vn/Content/home/images/icon/logo-ft.png" alt="" height="70px">
                    <span class="fs-2 fst-italic">Car Rental</span>
                </a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="HomePageController">Home</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link active" aria-current="page" href="SearchCarPageController">Car</a>
                        </li>
                    </ul>      
                    <form  class="d-flex btn-group" role="group" method="POST">
                        <c:if test="${sessionScope.USER eq null}">
                            <!-- Button trigger modal login-->
                            <button id="buttonLogin" type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#modalLogin">Login</button>
                        </c:if>
                        <c:if test="${sessionScope.USER ne null}">
                            <button class="btn btn-outline-secondary" type="submit" formaction="LogoutController">Logout</button>
                        </c:if>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row  justify-content-center">
                <!--Search-->
                <div class="col-12 mb-4">
                    <div class="row">
                        <form action="SearchCarController" method="POST" class="row g-3 justify-content-md-center">
                            <div class="col-3 mt-0 align-items-start row">
                                <!--Car name-->
                                <div class="input-group mb-2">
                                    <div class="input-group-text">
                                        <div class="form-check form-switch">
                                            <input class="form-check-input" type="radio" name="search" value="name"
                                                   <c:if test="${sessionScope.search eq 'name'}">
                                                       checked
                                                   </c:if>>
                                        </div>
                                    </div>
                                    <input id="name" type="text" class="form-control" name="name" placeholder="Car name"
                                           value="${sessionScope.name}">
                                </div>
                                <c:if test="${not empty requestScope.ValidateSearchCar.errorName}">
                                    <p style="color: red">${requestScope.ValidateSearchCar.errorName}</p>
                                </c:if>
                                <!--Category-->
                                <div class="input-group mb-1">
                                    <div class="input-group-text">
                                        <div class="form-check form-switch">
                                            <input class="form-check-input" type="radio" name="search" value="cate"
                                                   <c:if test="${sessionScope.search eq 'cate'}">
                                                       checked
                                                   </c:if>>
                                        </div>
                                    </div>
                                    <select id="cate" class="form-select" name="cate">
                                        <c:forEach var="Cate" items="${requestScope.ListCate}">
                                            <option <c:if test="${sessionScope.cate eq Cate.cateId}"> selected </c:if> value="${Cate.cateId}">${Cate.cateName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <c:if test="${not empty requestScope.ValidateSearchCar.errorSearch}">
                                    <p style="color: red">${requestScope.ValidateSearchCar.errorSearch}</p>
                                </c:if>
                            </div>
                            <!--Rental Date-->
                            <div class="col-2 align-items-start row me-1">
                                <input id="rental" type="date" class="form-control" name="rentalDate" placeholder="Rental Date"
                                       value="${sessionScope.rentalDate}">
                                <c:if test="${not empty requestScope.ValidateSearchCar.errorRentalDate}">
                                    <p style="color: red">${requestScope.ValidateSearchCar.errorRentalDate}</p>
                                </c:if>
                            </div>

                            <!--Return Date-->
                            <div class="col-2 align-items-start row me-1">
                                <input id="return" type="date" class="form-control" name="returnDate" placeholder="Return Date"
                                       value="${sessionScope.returnDate}">
                                <c:if test="${not empty requestScope.ValidateSearchCar.errorReturnDate}">
                                    <p style="color: red">${requestScope.ValidateSearchCar.errorReturnDate}</p>
                                </c:if>
                            </div>

                            <!--Amount-->
                            <div class="col-2 align-items-start row">
                                <input id="amount" type="number" class="form-control" name="amount" placeholder="Amount"
                                       value="${sessionScope.amount}" min="1" max="1000">
                                <c:if test="${not empty requestScope.ValidateSearchCar.errorAmount}">
                                    <p style="color: red">${requestScope.ValidateSearchCar.errorAmount}</p>
                                </c:if>
                            </div>

                            <!--Button search-->
                            <div class="col-3 align-items-start row">
                                <c:if test="${not empty requestScope.ValidateSearchQuestion.errorSearch}">
                                    <p style="color: red">${requestScope.ValidateSearchQuestion.errorSearch}</p>
                                </c:if>
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-outline-primary" name="pageSearch" value="1">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!--List Question-->
                <div class="col-11 justify-content-center">
                    <c:if test="${empty requestScope.ListCarByCate}">
                        <div class="alert alert-danger" role="alert">
                            Not found any car
                        </div>
                    </c:if>
                    <c:forEach var="ListCarByCate" items="${requestScope.ListCarByCate}">
                        <div class="alert alert-primary d-flex justify-content-center" role="alert">
                            ${ListCarByCate.key.cateName}
                        </div>
                        <div class="row" >
                            <c:forEach var="listCar" items="${ListCarByCate.value}" varStatus="count">
                                <div class="card col-4">
                                    <img src="${listCar.carImage}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="card-title">${listCar.carYear} ${listCar.carName}</h3>
                                        <p class="card-text mb-0 ms-3"><strong>Color: </strong> ${listCar.carColor}</p>
                                        <p class="card-text mb-0 ms-3"><strong>Year: </strong> ${listCar.carYear}</p>
                                        <p class="card-text mb-0 ms-3"><strong>Price: </strong> ${listCar.carPrice} $</p>
                                        <p class="card-text mb-2 ms-3"><strong>Quantity: </strong> ${listCar.carQuantity}</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                    <!--Paging-->
                    <c:if test="${requestScope.NumPageSearch > 1}">
                        <form action="ChangePageController" method="POST" class="d-flex justify-content-center">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <button class="page-link" value="${requestScope.PageSearch - 1}"
                                                type="submit" name="pageSearch" 
                                                <c:if test="${requestScope.PageSearch <= 1 }">style="visibility: hidden"</c:if>>Previous</button>
                                        </li>
                                    <c:forEach begin="1" end="${requestScope.NumPageSearch}" step="1" varStatus="count">
                                        <li class="page-item<c:if test="${requestScope.PageSearch == count.count}"> active</c:if>">
                                            <button class="page-link" value="${count.count}" type="submit" name="pageSearch">${count.count}</button>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <button class="page-link" value="${requestScope.PageSearch + 1}" type="submit" name="pageSearch"
                                                <c:if test="${requestScope.PageSearch >= requestScope.NumPageSearch}">style="visibility: hidden"</c:if>>Next</button>
                                        </li>
                                    </ul>
                                </nav>
                            </form>
                    </c:if>
                </div>
            </div>
        </div>


        <!-- Modal login-->
        <div class="modal fade" id="modalLogin" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Login</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="LoginController" method="POST" class="col-md-auto">
                            <div class="form-floating mb-3">
                                <input id="emailL" type="email" class="form-control form-control-sm" placeholder="a"
                                       name="emailL"  value="${sessionScope.email}">
                                <label for="emailL">Email address</label>
                            </div>
                            <c:if test="${sessionScope.action eq 'openLogin'}">
                                <p style="color: red">${requestScope.ValidateRegistration.errorEmail}</p>
                            </c:if>


                            <div class="form-floating mb-3">
                                <input id="passwordL" type="password" class="form-control form-control-sm" placeholder="a"
                                       name="passwordL">
                                <label for="passwordL">Password</label>
                            </div>
                            <c:if test="${sessionScope.action eq 'openLogin'}">
                                <p style="color: red">${requestScope.ValidateRegistration.errorPassword}</p>
                            </c:if>

                            <div class="g-recaptcha mb-3" data-sitekey="6LdGWHUaAAAAAJM4txGEIFnP6tQUTOJ08CkEs7-M"></div>

                            <input type="hidden" name="currentPage" value="SearchCarPageController"/>

                            <div class="row justify-content-md-center mt-1 mb-1">
                                <div class="btn-group col-12" role="group">
                                    <button type="submit" class="btn btn-outline-success">Login</button>
                                    <button type="reset" class="btn btn-outline-secondary">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group">
                            <button id="buttonRegister" type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#modalRegister" data-bs-dismiss="modal">Sign up</button>
                            <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal register-->
        <div class="modal fade" id="modalRegister" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Sign up</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="RegisterController" method="POST" class="col-md-auto">
                            <div class="form-floating mb-3">
                                <input id="fullname" type="text" class="form-control form-control-sm"  placeholder="a"
                                       name="fullname" value="${param.fullname}">
                                <label for="fullname">Full Name</label>
                            </div>
                            <p style="color: red">${requestScope.ValidateRegistration.errorFullname}</p>

                            <div class="form-floating mb-3">
                                <input id="email" type="email" class="form-control form-control-sm" placeholder="a"
                                       name="email"  value="${param.email}">
                                <label for="email">Email address</label>
                            </div>
                            <c:if test="${sessionScope.action eq 'openRegister'}">
                                <p style="color: red">${requestScope.ValidateRegistration.errorEmail}</p>
                            </c:if>

                            <div class="form-floating mb-3">
                                <input id="phone" type="text" class="form-control form-control-sm" placeholder="a"
                                       name="phone" value="${param.phone}">
                                <label for="phone">Phone</label>
                            </div>
                            <p style="color: red">${requestScope.ValidateRegistration.errorPhone}</p>

                            <div class="form-floating mb-3">
                                <input id="address" type="text" class="form-control form-control-sm" placeholder="a"
                                       name="address" value="${param.address}">
                                <label for="address">Address</label>
                            </div>
                            <p style="color: red">${requestScope.ValidateRegistration.errorAddress}</p>

                            <div class="form-floating mb-3">
                                <input id="password" type="password" class="form-control form-control-sm" placeholder="a"
                                       name="password">
                                <label for="password">Password</label>
                            </div>
                            <c:if test="${sessionScope.action eq 'openRegister'}">
                                <p style="color: red">${requestScope.ValidateRegistration.errorPassword}</p>
                            </c:if>

                            <div class="form-floating mb-3">
                                <input id="confirm" type="password" class="form-control form-control-sm" placeholder="a"
                                       name="confirm">
                                <label for="confirm">Confirm</label>
                            </div>
                            <p style="color: red">${requestScope.ValidateRegistration.errorConfirm}</p>

                            <input type="hidden" name="currentPage" value="SearchCarPageController"/>

                            <div class="row justify-content-md-center mt-1 mb-1">
                                <div class="btn-group col-12" role="group">
                                    <button type="submit" class="btn btn-outline-success">Sign Up</button>
                                    <button type="reset" class="btn btn-outline-secondary">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal verification-->
    <button id="buttonVerifi" type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#modalVerifi" data-bs-dismiss="modal" style="visibility: hidden"></button>

    <div class="modal fade" id="modalVerifi" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Verification</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="VerificationController" method="POST" class="col-md-auto">
                        Enter the verification code sent to the email address ${sessionScope.email} to activate your account
                        <div class="form-floating mb-3 mt-3">
                            <input id="verifiCode" type="text" class="form-control form-control-sm" placeholder="a"
                                   name="verifiCode">
                            <label for="verifiCode">Verification Code</label>
                        </div>
                        <p style="color: red">${requestScope.errorVerificationCode}</p>

                        <input type="hidden" name="currentPage" value="SearchCarPageController"/>

                        <div class="row justify-content-md-center mt-1 mb-1">
                            <div class="btn-group col-12" role="group">
                                <button type="submit" class="btn btn-outline-success" name="verifiAction" value="submit">Submit</button>
                                <button type="submit" class="btn btn-outline-secondary" name="verifiAction" value="resend">Resend</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="btn-group">
                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--Modal welcome-->
    <button id="buttonWelcome" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#welcomeModal" style="display: none"></button>
    <div class="modal fade" id="welcomeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Welcome</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="text-center fs-3">Welcome ${sessionScope.USER.fullname}</div>
                </div>
                <div class="modal-footer">
                    <form method="POST">
                        <input type="hidden" name="id">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</body>
<c:if test="${sessionScope.action eq 'openRegister'}">
    <script>
        document.getElementById("buttonRegister").click();
    </script>
</c:if>
<c:if test="${sessionScope.action eq 'openLogin'}">
    <script>
        document.getElementById("buttonLogin").click();
    </script>
</c:if>
<c:if test="${sessionScope.action eq 'openVerifi'}">
    <script>
        document.getElementById("buttonVerifi").click();
    </script>
</c:if>
<c:if test="${requestScope.action eq 'openWelcome'}">
    <script>
        document.getElementById("buttonWelcome").click();
    </script>
</c:if>
</html>
