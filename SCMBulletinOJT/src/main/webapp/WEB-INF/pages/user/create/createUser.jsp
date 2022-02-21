<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="forms-mr">
          <div class="col-sm-6 col-md-6 form-detail">
            <c:url var="addAction" value="/createUserConfirm"></c:url>
            <form:form action="createUserConfirm"
              modelAttribute="rollBackUserForm" method="POST" id="form"
              role="form" enctype="multipart/form-data">
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>User Creation</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
                  <div class="form-group">
                    <label for="name">Name</label>
                    <form:input path="name"
                      value="${rollBackUserForm.name }"
                      class="form-control" placeholder="Enter Name" />
                    <div class="input-group">
                      <form:errors path="name"
                        class="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="email">Email</label>
                    <form:input path="email"
                      value="${rollBackUserForm.email }"
                      class="form-control" placeholder="Enter Email"
                      autocomplete="false" />
                    <div class="input-group">
                      <form:errors path="email"
                        class="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <form:password path="password"
                      value="${rollBackUserForm.password }"
                      class="form-control" placeholder="Enter Password" />
                    <div class="input-group">
                      <form:errors path="password"
                        class="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label>Confirm Password</label> <input
                      type="password" name="confirmPassword"
                      class="form-control"
                      value="${rollBackUserForm.password }"
                      placeholder="Enter Confirm Password" />
                  </div>
                  <div class="form-group">
                    <label for="type">Authority</label>
                    <form:select path="type" id="authority"
                      class="form-control"
                      value="${rollBackUserForm.type }">
                      <option value="0">Admin</option>
                      <option value="1">User</option>
                    </form:select>
                    <div class="input-group">
                      <form:errors path="type"
                        cssClass="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="phone">Phone</label>
                    <form:input type="text" path="phone"
                      pattern="[0]{1}[9]{1}[0-9]{7,9}"
                      value="${rollBackUserForm.phone }"
                      class="form-control" placeholder="Enter Phone" />
                    <div class="input-group">
                      <form:errors path="phone"
                        cssClass="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="dob">Date Of Birth</label>
                    <div class="input-group date datepicker"
                      data-provide="datepicker">
                      <form:input value="${rollBackUserForm.dob }"
                        path="dob" type="text" class="form-control"
                        placeholder="Enter Date Of Birth" />
                      <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                      </div>
                    </div>
                    <div class="input-group">
                      <form:errors path="dob"
                        cssClass="has-error text-danger" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="address">Address</label>
                    <form:textarea path="address"
                      value="${rollBackUserForm.address }"
                      class="form-control" placeholder="Enter Address"></form:textarea>
                  </div>
                  <div class="form-group">
                    <label for="profile">Profile</label>
                    <div class="input-group">
                      <input type="file" name="fileUpload"
                        id="fileUpload" accept="image/*"
                        value="${imageData }"
                        onchange="showImage.call(this)" /> <input
                        name="imageData" type="hidden" id="imageData"
                        value="" /> <a class="float-right"> <img
                        src="${rollBackUserForm.profile }" id="image"
                        class="profile-user-img img-fluid img-circle" /></a>
                      <form:input path="profile" type="hidden"
                        value="${imageData }" />
                    </div>
                  </div>
                  <button type="submit" class="btn btn-info"
                    name="confirmUser">Confirm</button>
                  <a class="btn btn-secondary"
                    href="${pageContext.request.contextPath}/userList">Back</a>
                  <a class="btn btn-secondary"
                    href="${pageContext.request.contextPath}/createUser">ClearAll</a>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
<script src="<c:url value="/resources/js/imagePreview.js"/>"></script>