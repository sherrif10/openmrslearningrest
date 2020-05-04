<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:require
  allPrivileges="Get laborator,Manage Laboratory"
  otherwise="/login.htm" redirect="/module/openmrslearningrest/webservices/rest/laboratory.htm" />
  
  <spring:hasBindErrors name="laboratory">
  <div class="error">
    <spring:message code="fix.error" />
  </div>
  <br />
</spring:hasBindErrors>

<c:if test="${laboratory.deleted}">
    <div class="deletedMessage">
        <div>
            <openmrs:message code="general.deletedBy"/>
            <c:out value="${laboratory.deletedBy.personName}" />
            <span class="datetime">"${laboratory.dateDeleted}"</span>
            -
            <c:out value="${laboratory.deletedReason}"/>
        </div>
    </div>
</c:if>
<div>
  <c:choose>
    <c:when test="${empty laboratory.lab_Id}">
      <span class="boxHeader"> <b><spring:message code="laboratory.laboratory.form.boxheader.add" /></b>
      </span>
    </c:when>
    <c:otherwise>
      <span class="boxHeader"> <b><spring:message code="laboratory.laboratory.form.boxheader.edit" /></b>
      </span>
    </c:otherwise>
  </c:choose>
  
  <form:form method="post" modelAttribute="laboratory" cssClass="box">
    <%-- following properties are bound to the form as hidden since they should be or since we show them only in a readonly manner. --%>
    <%-- if you delete for example the dateCreated it will change on every update --%>
    <form:hidden path="uuid" />
    <form:hidden path="creator" />
    <form:hidden path="dateCreated" />
    <form:hidden path="retired" />
    <form:hidden path="retireReason" />
    <form:hidden path="retiredBy" />
    <form:hidden path="dateRetired" />
    <table>
      <tr>
        <td><spring:message code="laboratory.laboratory.aeTitle" /><span class="required">*</span></td>
        <td><form:input path="aeTitle" />
            <form:errors path="aeTitle" cssClass="error" />
        </td>
      </tr>
      <tr>
        <td><spring:message code="general.name" /><span class="required">*</span></td>
        <td><form:input path="name" />
            <form:errors path="name" cssClass="error" />
        </td>
      </tr>
      <tr>
        <td><spring:message code="general.equipments" /></td>
        <td><form:input path="equipments" />
            <form:errors path="equipments" cssClass="error" />
        </td>
      </tr>
      <c:if test="${not empty laboratory.creator}">
          <tr>
            <td><spring:message code="general.createdBy" /></td>
            <td>${laboratory.creator.personName} - <span class="datetime"> ${laboratory.dateCreated} </span></td>
          </tr>
      </c:if>
      <c:if test="${not empty laboratory.changedBy}">
          <tr>
            <td><spring:message code="general.changedBy" /></td>
            <td>${laboratory.changedBy.personName} - <span class="datetime"> ${laboratory.dateChanged} </span></td>
          </tr>
      </c:if>
      <c:if test="${not empty laboratory.modalityId}">
          <tr>
            <td><font color="#D0D0D0"><sub><openmrs:message code="general.uuid" /></sub></font></td>
            <td><font color="#D0D0D0"><sub>${laboratory.uuid}</sub></font></td>
          </tr>
      </c:if>
      <tr>
        <td/>
        <td><input type="submit" name="saveLaboratory" value="<spring:message code="general.save"/>"></td>
      </tr>
    </table>
  </form:form>
  
    <c:if test="${not laboratory.deleted && not empty laboratory.lab_Id}">
      </br>
      <form:form method="post" modelAttribute="laboratory" cssClass="box">
        <form:hidden path="id" />
        <form:hidden path="uuid" />
        <form:hidden path="aeTitle" />
        <form:hidden path="name" />
        <form:hidden path="equipments" />
        <input type="hidden" name="retired" value="true" />
          <table>
            <tr>
              <td><spring:message code="general.reason" /><span class="required">*</span></td>
              <td>
                    <form:input path="retireReason" />
                    <form:errors path="retireReason" cssClass="error" />
              </td>
            </tr>
            <tr>
              <td/>
              <td>
                <input type="submit" value='<openmrs:message code="general.retire"/>' name="deleteLaboratory"/>
              </td>
            </tr>
          </table>
      </form:form>
  </c:if>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>