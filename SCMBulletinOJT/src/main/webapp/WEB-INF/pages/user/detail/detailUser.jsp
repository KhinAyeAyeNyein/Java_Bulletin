<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="forms-mr">
          <div class="col-sm-6 col-md-6 form-detail">
            <div class="card card-primary card-outline">
              <div class="card-body box-profile">
                <div class="text-center">
                  <c:if test="${empty detailUser.profile }">
                    <img class="profile-user-img img-fluid img-circle"
                      src="<c:url value='/resources/img/noimage.png'/>"
                      alt="User profile picture">
                  </c:if>
                  <c:if test="${not empty detailUser.profile }">
                    <img class="profile-user-img img-fluid img-circle"
                      src="${detailUser.profile}" />
                  </c:if>
                </div>
                <h3 class="profile-username text-center">${detailUser.name }</h3>
                <ul class="list-group list-group-unbordered mb-3">
                  <li class="list-group-item"><b>Email</b> <a
                    class="float-right">${detailUser.email }</a></li>
                  <li class="list-group-item"><b>Authority</b> <c:if
                      test="${detailUser.type == 0 }">
                      <a class="float-right">ADMIN</a>
                    </c:if> <c:if test="${detailUser.type == 1 }">
                      <a class="float-right">USER</a>
                    </c:if></li>
                  <li class="list-group-item"><b>Phone</b> <a
                    class="float-right">${detailUser.phone }</a></li>
                  <li class="list-group-item"><b>Date Of Birth</b>
                    <a class="float-right">${detailUser.dob }</a></li>
                  <li class="list-group-item"><b>Address</b> <a
                    class="float-right">${detailUser.address }</a></li>
                </ul>
                <a
                  href="${pageContext.request.contextPath}/updateUser?id=${detailUser.id }"
                  class="btn btn-info btn-block"><b>Edit Profile</b></a>
                <a href="${pageContext.request.contextPath}/userList"
                  class="btn btn-block btn-secondary">Back</a>
                <div class="changePwd-txt">
                  <a
                    href="${pageContext.request.contextPath}/changePassword?id=${detailUser.id }">Change
                    Password</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>