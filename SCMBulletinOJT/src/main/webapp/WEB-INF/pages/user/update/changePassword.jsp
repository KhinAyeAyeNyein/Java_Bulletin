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
            <c:url var="addAction" value="/changePassword"></c:url>
            <form:form action="${addAction}" modelAtrribute="userID"
              method="POST" id="form">
              <input type="hidden" value="${userId}" name="userId" />
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>Change User Password</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
                  <div class="form-group">
                    <label>Old Password</label> <input
                      class="form-control" type="password"
                      name="oldPassword"
                      placeholder="Enter Old Password">
                    <form:errors path="password" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label>New Password</label> <input
                      class="form-control" type="password"
                      name="newPassword"
                      placeholder="Enter New Password" />
                    <form:errors path="password" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label>Confirm Password</label> <input
                      class="form-control" type="password"
                      name="confirmPassword"
                      placeholder="Enter Confirm Password" />
                    <form:errors path="password" class="text-danger" />
                  </div>
                  <button type="submit" class="btn btn-info">Change</button>
                  <a class="btn btn-secondary"
                    href="${pageContext.request.contextPath}/detailUser?id=${userId }">Cancel</a>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>