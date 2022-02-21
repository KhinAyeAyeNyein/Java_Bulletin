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
            <c:url var="addAction" value="/updatePostConfirm"></c:url>
            <form:form class="form-create" action="${addAction}"
              modelAttribute="editPostForm" method="POST" id="form">
              <input type="hidden" name="id" value="${editPostForm.id }" />
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>Post Edition</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
                  <div class="form-group">
                    <label for="title">Title</label> <input
                      class="form-control" type="text" name="title"
                      value="${editPostForm.title }" />
                    <form:errors path="title" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label for="description">Description</label> <input
                      class="form-control" name="description"
                      value="${editPostForm.description }">
                    <form:errors path="description" class="text-danger" />
                  </div>
                  <div class="input-post-sec02 clearFix">
                    <label class="switch clearFix"> <c:if
                        test="${editPostForm.status == '1' }">
                        <input id="status-checked" class="status-check"
                          type="checkbox" name="status"
                          value="${editPostForm.status }" checked>
                      </c:if> <c:if test="${editPostForm.status == '0' }">
                        <input id="status-checked" class="status-check"
                          type="checkbox" name="status"
                          value="${editPostForm.status }">
                      </c:if> <span class="slider round"></span> <span
                      class="slider round"></span>
                    </label> <label for="status">Status</label>
                  </div>
                  <button type="submit" class="btn btn-info">Update</button>
                  <a
                    href="${pageContext.request.contextPath}/updatePost?id=${editPostForm.id }"
                    class="btn btn-success">Reset</a> <a
                    href="${pageContext.request.contextPath}/postList"
                    class="btn btn-secondary">Back</a>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>