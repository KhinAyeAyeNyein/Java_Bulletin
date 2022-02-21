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
            <c:url var="addAction" value="/editUser"></c:url>
            <form:form action="editUser" modelAttribute="updateUserForm"
              method="POST" id="form">
              <input type="hidden" name="id"
                value="${updateUserForm.id }">
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>Edited User Confirm</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
                  <ul class="list-group list-group-unbordered mb-3">
                    <li class="list-group-item"><b>Name</b> <a
                      class="float-right">${updateUserForm.name } <input
                        type="hidden" name="name"
                        value="${updateUserForm.name }"
                        class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Email</b> <a
                      class="float-right">${updateUserForm.email } <input
                        type="hidden" name="email"
                        value="${updateUserForm.email }"
                        class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Type</b> <c:if
                        test="${updateUserForm.type == 0 }">
                        <a class="float-right">ADMIN <form:input
                            path="type" type="hidden" name="type"
                            value="${updateUserForm.type }"
                            class="form-control" /></a>
                      </c:if> <c:if test="${updateUserForm.type == 1 }">
                        <a class="float-right">USER <form:input
                            path="type" type="hidden" name="type"
                            value="${updateUserForm.type }"
                            class="form-control" /></a>
                      </c:if></li>
                    <li class="list-group-item"><b>Phone</b> <a
                      class="float-right">${updateUserForm.phone } <input
                        type="hidden" name="phone"
                        value="${updateUserForm.phone }"
                        class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Date Of
                        Birth</b> <a class="float-right">${updateUserForm.dob }
                        <input type="hidden" name="dob"
                        value="${updateUserForm.dob }"
                        class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Address</b> <a
                      class="float-right">${updateUserForm.address }
                        <input type="hidden" name="address"
                        value="${updateUserForm.address }"
                        class="form-control" />
                    </a></li>
                    <li class="list-group-item"><b>Profile</b> <a
                      class="float-right"><img
                        src="${updateUserForm.profile }" id="image"
                        class="profile-user-img img-fluid img-circle" /><input
                        type="hidden" name="profile"
                        value="${updateUserForm.profile }"
                        class="form-control" /></a></li>
                  </ul>
                  <input type="hidden" name="password"
                    value="${updateUserForm.password }"
                    class="form-control" />
                  <button type="submit" class="btn btn-info"
                    name="update">Update</button>
                  <button type="submit" class="btn btn-secondary"
                    name="cancel">Cancel</button>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>