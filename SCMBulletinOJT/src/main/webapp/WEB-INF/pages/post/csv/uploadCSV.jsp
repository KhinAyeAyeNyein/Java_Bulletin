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
            <c:url var="addAction" value="/uploadCSV"></c:url>
            <form:form action="${addAction}" method="POST" id="form"
              enctype="multipart/form-data">
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-mr">
                    <b>CSV File Upload</b>
                  </h4>
                  <c:forEach items="${uploadErrorMsg}" var="err"
                    varStatus="loop">
                    <c:if test="${err != null }">
                      <div class="alert alert-danger">
                        <strong>${err }</strong>
                      </div>
                    </c:if>
                  </c:forEach>
                  <div class="form-group">
                    <div class="input-group">
                      <input type="file" name="fileUpload"
                        id="fileUpload" accept=".csv">
                    </div>
                  </div>
                  <button type="submit" class="btn btn-info">Upload</button>
                  <a href="${pageContext.request.contextPath}/postList"
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
<script type="text/javascript">
	$('#fileUpload').on('change', function() {
		const size = (this.files[0].size).toFixed(2);
		if (size > 2000000) {
			alert("File size is too Large!");
		}
	});
</script>